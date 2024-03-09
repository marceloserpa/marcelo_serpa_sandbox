
import { SQSClient, GetQueueAttributesCommand } from '@aws-sdk/client-sqs';


const sqsClient = new SQSClient();


const queueUrl = 'http://localhost:4566/000000000000/my-messages-priority'; 



export const handler = async (event) => {



	console.log("++++++++++++++++++==")

	const mainQueue = "my-messages-main";
	const priorityQueue = "my-messages-priority";

	const messages = event.Records.map(record => record.body);

	for(var i=0; i<event.Records.length;i++){

		console.log("Loading messages")
		var queue = event.Records[i].eventSourceARN.split(':').pop() 

		if(queue === mainQueue) {
			console.log('main --')
			
			const getAttributesParams = {
				QueueUrl: queueUrl,
				AttributeNames: ['ApproximateNumberOfMessages'],
			  };
		  
			  const getAttributesCommand = new GetQueueAttributesCommand(getAttributesParams);
		  
			  const getAttributesResult = await sqsClient.send(getAttributesCommand);
		  
			  
			  const approximateNumberOfMessages = getAttributesResult.Attributes.ApproximateNumberOfMessages;
			  if (approximateNumberOfMessages > 0) {
				console.log(`priority queue has messages: ${approximateNumberOfMessages}`);
			  } else {
				console.log('priority queue is empty.');
			  }

		} else if (queue === priorityQueue) {
			console.log('Processing priority queue --')

			console.log(event.Records[i].body)

		} else {
			console.log('unknown')
		}

	}

	return messages;
};