import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    vus: 30, // foram testados os valores 1, 5, 10, 15, 20, 25 e 30
    duration: '60s',
};

export default function () {
  http.get('http://localhost:8082/organo/fornecedor/1/listarProdutos');
  sleep(1);
}
