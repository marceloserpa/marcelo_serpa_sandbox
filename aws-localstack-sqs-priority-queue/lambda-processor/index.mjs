
import { SQSClient, GetQueueAttributesCommand } from '@aws-sdk/client-sqs';


const sqsClient = new SQSClient();

const queueUrl = 'http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/my-messages-priority'; 

const mainQueue = "my-messages-main";

const priorityQueue = "my-messages-priority";

export const handler = async (event) => {

	console.log("Lambda Triggered")

	const failedMessageIds = [];  

	for(var i=0; i<event.Records.length; i++){

		const queue = extractQueueName(event.Records[i]);

		if (queue === priorityQueue) {
			await process(event.Records[i], 'Priority');

		} else if(queue === mainQueue) {
			const approximateNumberOfMessages = await getTotalMessageFromPriorityQueue();
			
			if (approximateNumberOfMessages > 0) {
				console.log(`# Main Queue: priority queue has messages: ${approximateNumberOfMessages} return message to Main Queue`);
				failedMessageIds.push(event.Records[i].messageId);

			} else {
				await process(event.Records[i], 'Main');
			}

		} else {
			console.log('# Unknown Queue');
		}

	}

	return {    
		batchItemFailures: failedMessageIds.map(id => {      
		  return {        
			itemIdentifier: id      
		  }    
		})
	  };
};

const process = async (record, origin) => {
	console.log(`# ${origin} Queue:  ${record.body}`);
};

const getTotalMessageFromPriorityQueue = async () => {
	const getAttributesParams = {
		QueueUrl: queueUrl,
		AttributeNames: ['ApproximateNumberOfMessages'],
	};

	const getAttributesCommand = new GetQueueAttributesCommand(getAttributesParams);

	const getAttributesResult = await sqsClient.send(getAttributesCommand);
		
	return getAttributesResult.Attributes.ApproximateNumberOfMessages;
};

const extractQueueName = (record) => record.eventSourceARN.split(':').pop() 