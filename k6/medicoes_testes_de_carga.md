
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
    - [results_load_test_listarProdutos_medicao_2.csv](results_load_test_listarProdutos_medicao_2.csv)
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

- Melhorias/otimizações:

Como a aplicação foi construída utilizando a versão 2.4.0 do Spring Boot, o HikariCP é o pool de conexões default, então a aplicação já estava utilizando um pool desde a primeira medição. Além disso, como a relação entre fornecedor e produto é OneToMany, não tivemos que corrigir o problema de complexidade n+1, que ocorreria no caso de uma relação ManyToMany. Esse serviço utiliza o Redis como cache, então, a cada teste, o banco é consultado apenas na primeira requisição. 

A melhoria que implementamos foi buscar os produtos de um fornecedor utilizando a coluna fornecedor_id da coluna Produto, que é uma chave estrangeira e indexada por definição no MySQL. Na primeira medição,
a busca pelos produtos era realizada utilizando a coluna cnpj da tabela Fornecedor. Assim, era necessário performar um inner join entre as tabelas Fornecedor e Produto. Ao buscar utilizando a coluna fornecedor_id, a execução de um select simples é suficiente.

Os arquivos modificados foram:
- [FornecedorController.java](../src/main/java/bsi/pcs/organo/controller/FornecedorController.java), em que o método `listarProdutos` foi alterado para receber o id do Fornecedor como path variable e chamar o método `listarProdutos` da classe `FornecedorService` passando o id do Fornecedor como argumento. 
- [FornecedorService.java](../src/main/java/bsi/pcs/organo/service/FornecedorService.java), em que o método `listarProdutos` foi sobrecarregado para aceitar o id do Fornecedor como argumento.
- [ProdutoRepository.java](../src/main/java/bsi/pcs/organo/repository/ProdutoRepository.java), em que o método `findByFornecedorId` foi adicionado para realizar a busca dos produtos utilizando a coluna fornecedor_id.

#### MEDIÇÃO 3
- Data da medição: 09/07/2023
- Testes de carga (SLA): latência, vazão e concorrência (limite de requisições simultâneas)

![dados listarProdutos](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/dados_medicao_3_listarProdutos.png)

![vazao por concorrencia listarProdutos](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/vazao_por_concorrencia_listarProdutos_medicao_3.png)

![latencia por concorrencia listarProdutos](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/latencia_por_concorrencia_listarProdutos_medicao_3.png)

- Melhorias/otimizações: Nenhuma melhoria foi realizada.

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
    - [results_load_test_atualizarProduto_medicao_2.csv](results_load_test_atualizarProduto_medicao_2.csv)
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

- Melhorias/otimizações:

Como a aplicação foi construída utilizando a versão 2.4.0 do Spring Boot, o HikariCP é o pool de conexões default, então a aplicação já estava utilizando um pool desde a primeira medição.

A melhoria que implementamos foi adicionar cache (utilizando o Redis) no método que verifica se o produto existe na base antes de atualizá-lo. Assim, a cada teste, o banco busca o produto no banco apenas na primeira requisição. Em vez de consultar o banco duas vezes, uma para leitura e outra para escrita, o banco é consultado apenas na atualização.

Os arquivos modificados foram:
- [ProdutoController.java](../src/main/java/bsi/pcs/organo/controller/ProdutoController.java), em que o método `update` foi alterado para chamar o método `retornarById` da classe `ProdutoService` passando o id do Produto enviado no corpo da requisição como argumento. Anteriormente era utilizado o método `retornar` (também da classe `ProdutoService`) que não utilizava cache.

#### MEDIÇÃO 3
- Data da medição: 09/07/2023
- Testes de carga (SLA): latência, vazão e concorrência (limite de requisições simultâneas)

![dados atualizarProduto](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/dados_medicao_3_atualizarProduto.png)

![vazao por concorrencia atualizarProduto](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/vazao_por_concorrencia_atualizarProduto_medicao_3.png)

![latencia por concorrencia atualizarProduto](https://github.com/pcs-sgbd-organo/organo-api/blob/master/k6/latencia_por_concorrencia_atualizarProduto_medicao_3.png)

- Melhorias/otimizações:

A melhoria que implementamos foi adicionar um if para verificar se o produto enviado no corpo da requisição é igual ao produto encontrado no banco. Se eles forem iguais, então nenhum dado foi modificado e portanto o produto não precisa ser atualizado. Assim, chamadas desnecessárias ao banco são evitadas e a latência diminui.

Os arquivos modificados foram:
- [ProdutoController.java](../src/main/java/bsi/pcs/organo/controller/ProdutoController.java), em que o método `update` foi alterado para verificar se o produto enviado no corpo da requisição é igual ao produto encontrado no banco. Caso o produto seja igual, então o banco não atualiza o registro.
- [Produto.java](../src/main/java/bsi/pcs/organo/model/Produto.java), em que o método equals() foi overrided.
- [Fornecedor.java](../src/main/java/bsi/pcs/organo/model/Fornecedor.java), em que o método equals() foi overrided.
