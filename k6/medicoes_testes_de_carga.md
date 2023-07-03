
## MEDIÇÕES DO SLA

### Listar produtos de um fornecedor
- Tipo de operações: leitura
- Arquivos envolvidos:
    - [FornecedorController.java](../src/main/java/bsi/pcs/organo/controller/FornecedorController.java)
    - [FornecedorService.java](../src/main/java/bsi/pcs/organo/service/FornecedorService.java)
    - [FornecedorRepository.java](../src/main/java/bsi/pcs/organo/repository/FornecedorRepository.java)
    - [Produto.java](../src/main/java/bsi/pcs/organo/model/Produto.java)

- Arquivos com o código fonte de medição do SLA:
    - [load_tests_listarProdutos.js](load_tests_listarProdutos.js)
    - [results_load_test_listarProdutos.csv](results_load_test_listarProdutos.csv)
- Descrição das configurações: máquina local Windows 11

#### MEDIÇÃO 1
- Data da medição: 18/06/2023
- Testes de carga (SLA): latência, vazão e concorrência (limite de requisições simultâneas)

![dados listarProdutos](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/dados_medicao_listarProdutos.png)

![vazao por concorrencia listarProdutos](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/vazao_por_concorrencia_listarProdutos.png)

![latencia por concorrencia listarProdutos](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/latencia_por_concorrencia_listarProdutos.png)

- Potenciais gargalos do sistema:

No caso do serviço `listarProdutos`, não conseguimos identificar um potencial gargalo no sistema pois não foi possível estabelecer um padrão de crescimento da latência em comparação ao aumento de requisições concorrentes. Esse comportamento pode ser decorrente do uso do Redis como memória em cache, fazendo com que a aplicação não precise ir ao banco para recuperar os dados a cada requisição.

#### MEDIÇÃO 2
- Data da medição: 01/07/2023
- Testes de carga (SLA): latência, vazão e concorrência (limite de requisições simultâneas)

![dados listarProdutos](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/dados_medicao_2_listarProdutos.png)

![vazao por concorrencia listarProdutos](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/vazao_por_concorrencia_listarProdutos_medicao_2.png)

![latencia por concorrencia listarProdutos](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/latencia_por_concorrencia_listarProdutos_medicao_2.png)

- Potenciais gargalos do sistema:

### Atualizar produto de um fornecedor
- Tipo de operações: leitura e atualização
- Arquivos envolvidos:
    - [ProdutoController.java](../src/main/java/bsi/pcs/organo/controller/ProdutoController.java)
    - [ProdutoService.java](../src/main/java/bsi/pcs/organo/service/ProdutoService.java)
    - [ProdutoRepository.java](../src/main/java/bsi/pcs/organo/repository/ProdutoRepository.java)
    - [Produto.java](../src/main/java/bsi/pcs/organo/model/Produto.java)

- Arquivos com o código fonte de medição do SLA:
    - [load_tests_atualizarProduto.js](load_tests_atualizarProduto.js)
    - [results_load_test_atualizarProduto.csv](results_load_test_atualizarProduto.csv)
- Descrição das configurações: máquina local Windows 11

#### MEDIÇÃO 1
- Data da medição: 18/06/2023
- Testes de carga (SLA): latência, vazão e concorrência (limite de requisições simultâneas)

![dados atualizarProduto](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/dados_medicao_atualizarProduto.png)

![vazao por concorrencia atualizarProduto](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/vazao_por_concorrencia_atualizarProduto.png)

![latencia por concorrencia atualizarProduto](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/latencia_por_concorrencia_atualizarProduto.png)

- Potenciais gargalos do sistema:

No caso do serviço `atualizarProdutos`, não conseguimos identificar um potencial gargalo no sistema pois não foi possível estabelecer um padrão de crescimento da latência em comparação ao aumento de requisições concorrentes. Esse comportamento pode ser decorrente da pequena quantidade de registros contidos no banco, fazendo com que os processos de leitura e atualização sejam realizados rapidamente.

#### MEDIÇÃO 2
- Data da medição: 01/07/2023
- Testes de carga (SLA): latência, vazão e concorrência (limite de requisições simultâneas)

![dados atualizarProduto](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/dados_medicao_2_atualizarProduto.png)

![vazao por concorrencia atualizarProduto](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/vazao_por_concorrencia_atualizarProduto_medicao_2.png)

![latencia por concorrencia atualizarProduto](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/latencia_por_concorrencia_atualizarProduto_medicao_2.png)

- Potenciais gargalos do sistema: