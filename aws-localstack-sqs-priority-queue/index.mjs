export const handler = async (event) => {
	/*
	const messages = event.Records.map(record => record.body);
	
	messages.forEach(message => {
		console.log(message);
	});
	return messages;
	*/

	console.log("++++++++++++++++++==")

	const mainQueue = "my-messages-main";
	const priorityQueue = "my-messages-priority";

	const messages = event.Records.map(record => record.body);

	for(var i=0; i<event.Records.length;i++){

		console.log("processing")
		var queue = event.Records[i].eventSourceARN.split(':').pop() 
		if(queue === mainQueue) {
			console.log('main --')
		} else if (queue === priorityQueue) {
			console.log('priority --')
		} else {
			console.log('unknown')
		}

	}

	return messages;
};