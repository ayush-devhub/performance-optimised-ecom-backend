import http from 'k6/http';
import { sleep } from 'k6';

const BASE = __ENV.BASE_URL || 'http://localhost:8080/api';

export const options = {
  vus: 50,
  duration: '60s',
};

export default function () {
  const userId = 1;
  const productId = Math.floor(Math.random() * 5) + 1; // adjust range for your DB
  const url = `${BASE}/carts/${userId}/add?productId=${productId}&quantity=1`;
  http.post(url);
  sleep(0.5);
}
