
import { SQSClient, GetQueueAttributesCommand } from '@aws-sdk/client-sqs';
import {setTimeout} from "timers/promises";

const sqsClient = new SQSClient();

const mainQueueUrl = 'http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-messages-main'; 
const priorityQueueUrl = 'http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-messages-priority'; 

const mainQueue = "my-messages-main";

const priorityQueue = "my-messages-priority";

export const handler = async (event) => {

	const failedMessageIds = [];  

	for(var i=0; i<event.Records.length; i++){

		const queue = extractQueueName(event.Records[i]);

		if (queue === priorityQueue) {
			await process(event.Records[i], 'Priority');

		} else if(queue === mainQueue) {
			const approximateNumberOfMessages = await getTotalMessageFromPriorityQueue(priorityQueueUrl);
			
			if (approximateNumberOfMessages > 0) {
				console.log(`##### ERROR : Main Queue: message can't be processed now because priority queue has messages: ${approximateNumberOfMessages}`);
				failedMessageIds.push(event.Records[i].messageId);

			} else {
				await process(event.Records[i], 'Main');
			}

		} else {
			console.log('# Unknown Queue');
		}

	}

	if(failedMessageIds > 0) {
		return {    
			batchItemFailures: failedMessageIds.map(id => {      
				return {        
				itemIdentifier: id      
				}    
			})
			};
	}

	return ;


};

const process = async (record, origin) => {
	await setTimeout(1000);
	console.log(`${record.body} = ${origin} Queue completed`);
};

const getTotalMessageFromPriorityQueue = async (queueUrl) => {
	const getAttributesParams = {
		QueueUrl: queueUrl,
		AttributeNames: ['ApproximateNumberOfMessages', 'ApproximateNumberOfMessagesNotVisible'],
	};

	const getAttributesCommand = new GetQueueAttributesCommand(getAttributesParams);

	const getAttributesResult = await sqsClient.send(getAttributesCommand);
		
	return getAttributesResult.Attributes.ApproximateNumberOfMessages;
};

const extractQueueName = (record) => record.eventSourceARN.split(':').pop() 