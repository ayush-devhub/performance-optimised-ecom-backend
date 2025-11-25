import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  vus: 50,
  duration: '60s',
};

export default function () {
  const userId = 1;
  const productId = Math.floor(Math.random() * 5) + 1; // random product ID 1–5

  http.post(`http://localhost:8080/api/carts/${userId}/add?productId=${productId}&quantity=1`);
  sleep(0.5);
}