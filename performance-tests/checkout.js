import http from 'k6/http';
import { sleep } from 'k6';

const BASE = __ENV.BASE_URL || 'http://localhost:8080/api';

export const options = {
  vus: 20,
  duration: '60s',
};

export default function () {
  const userId = 1;
  http.post(`${BASE}/orders/checkout/${userId}`);
  sleep(2);
}
