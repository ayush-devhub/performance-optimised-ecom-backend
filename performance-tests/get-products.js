import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 100,              // 100 concurrent virtual users
  duration: '20s',       // run for 20 seconds
};

export default function () {
  const res = http.get('http://localhost:8080/api/products');
  check(res, {
    'status is 200': (r) => r.status === 200,
  });
  sleep(0.5); // wait 500ms between requests
}