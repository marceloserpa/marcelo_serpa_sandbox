import http from 'k6/http';
import { check } from 'k6';

export const options = {
    scenarios: {
        constant_rps: {
            executor: 'constant-arrival-rate',
            rate: 1,
            timeUnit: '1s',
            duration: '5s',
            preAllocatedVUs: 20,
            maxVUs: 100,
        },
    },
    thresholds: {
        http_req_failed: ['rate<0.01'],
        http_req_duration: ['p(95)<1000'],
    },
};

export default function () {
    const username = `user-${__VU}-${__ITER}-${Date.now()}`;

    const payload = JSON.stringify({
        username: username,
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const response = http.post(
        'http://localhost:9090/users',
        payload,
        params
    );

    check(response, {
        'status is 2xx': (r) => r.status >= 200 && r.status < 300,
    });
}