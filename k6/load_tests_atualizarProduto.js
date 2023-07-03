import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    vus: 30, // foram testados os valores 1, 5, 10, 15, 20, 25 e 30
    duration: '60s',
};

const params = {
    headers: {
        "Content-Type": "application/json"
    }
  };

const payload = {
    "id": 4,
    "nome": "Tomate Cereja Solo Vivo Organicos",
    "preco": "7.99",
    "validade": "2021-12-19",
    "fotoUrl": "https://i.imgur.com/K3DDCDn.jpg",
    "quantidade": 87,
    "fornecedor": {
        "nomeFantasia": "Hortifruti Maracanã",
        "email": "jpportocampos@gmail.com",
        "cnpj": "73063766000199",
        "telefoneMovel": "21982375059"
    },
    "deleted": false
};


export default function () {
    http.put(
        `http://localhost:8082/organo/produto/4/atualizar`,
        JSON.stringify(payload),
        params
      );
  sleep(1);
}