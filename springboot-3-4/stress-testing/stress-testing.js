import http from 'k6/http';
import { check, sleep } from 'k6';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";
import { textSummary } from "https://jslib.k6.io/k6-summary/0.0.1/index.js";

export let options = {
  stages: [
    { duration: '30s', target: 20 }, // ramp up traffic
    { duration: '30s', target: 40}, // stay at 40 users for 30 seconds
    { duration: '30s', target: 0 }, // ramp-down to 0 
  ],
  thresholds: {
    http_req_duration: ['p(99)<500'], // 99% of requests must complete within 500ms
    http_req_failed: ['rate<0.01'] // request failure rate must be below 1%
  },
};

export default function () {

  const get_request = {
    method: 'GET',
    url: 'http://localhost:8080/person/1',
  };

  const data = JSON.stringify({name: "Marcelo",lastname: "Serpa"})

  const post_request = {
    method: 'POST',

    url: 'http://localhost:8080/person',
    body: data,
    params: {
      headers: { 'Content-Type': 'application/json' },
    },
  };

  const get_all_request = {
    method: 'GET',
    url: 'http://localhost:8080/person',
  };


  const responses = http.batch([post_request, get_request, get_all_request]);
  check(responses[0], {
    'GET status is 200': (r) => r.status === 200,
  });

  check(responses[1], {
    'POST status is 200': (r) => r.status === 200,
  });

  check(responses[2], {
    'GET status is 200': (r) => r.status === 200,
  });

}

export function handleSummary(data) {
  return {
    "summary.json": JSON.stringify(data),
    "result.html": htmlReport(data),
    stdout: textSummary(data, { indent: " ", enableColors: true })
  };
}