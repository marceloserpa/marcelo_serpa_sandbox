import http from 'k6/http';
import { uuidv4 } from 'https://jslib.k6.io/k6-utils/1.1.0/index.js';


export default function () {
  const randomUUID = uuidv4();
  const url = "http://localhost:8080/producer/message";

  const payload = JSON.stringify({
    text: `Hello World ${randomUUID}`,
    delay: 3,
  });

  const params = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  http.post(url, payload, params);
}