
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
- Data da medição: 18/06/2023
- Descrição das configurações: máquina local Windows 11
- Testes de carga (SLA): latência, vazão e concorrência (limite de requisições simultâneas)


- Potenciais gargalos do sistema

### Atualizar produto de um fornecedor
- Tipo de operações: atualização
- Arquivos envolvidos:
    - [ProdutoController.java](../src/main/java/bsi/pcs/organo/controller/ProdutoController.java)
    - [ProdutoService.java](../src/main/java/bsi/pcs/organo/service/ProdutoService.java)
    - [ProdutoRepository.java](../src/main/java/bsi/pcs/organo/repository/ProdutoRepository.java)
    - [Produto.java](../src/main/java/bsi/pcs/organo/model/Produto.java)

- Arquivos com o código fonte de medição do SLA:
    - [load_tests_atualizarProduto.js](load_tests_atualizarProduto.js)
    - [results_load_test_atualizarProduto.csv](results_load_test_atualizarProduto.csv)
- Data da medição: 18/06/2023
- Descrição das configurações: máquina local Windows 11
- Testes de carga (SLA): latência, vazão e concorrência (limite de requisições simultâneas)
- Potenciais gargalos do sistema