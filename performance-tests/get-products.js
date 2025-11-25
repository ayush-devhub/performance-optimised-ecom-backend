import http from 'k6/http';
import { check, sleep } from 'k6';

const BASE = __ENV.BASE_URL || 'http://localhost:8080/api';

export const options = {
  vus: 100,         // default; you can override from CLI
  duration: '60s',
};

export default function () {
  const res = http.get(`${BASE}/products`);
  check(res, { 'status 200': (r) => r.status === 200 });
  sleep(0.5);
}
