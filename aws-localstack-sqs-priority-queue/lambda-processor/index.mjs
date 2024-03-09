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
		} else if (queue === priorityQueue) {
			console.log('Processing priority queue --')

			console.log(event.Records[i].body)

		} else {
			console.log('unknown')
		}

	}

	return messages;
};