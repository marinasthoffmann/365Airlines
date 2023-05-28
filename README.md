# 365Airlines
Projeto referente ao módulo 3 do programa de formação DEVinHouse. Contexto: A empresa 365Linhas Aéreas entrou em operação com uma aeronave para atender a um grupo seleto de clientes que fazem o trecho Florianópolis - São Paulo diariamente. Um sistema para gerenciamento de passageiros do voo está sendo desenvolvido. Este repositório contempla o back-end da aplicação. Os recursos tecnológicos utilizados para o desenvolvimento deste projeto são os seguintes:
- Utilização do Framework Spring Boot;
- Utilização do padrão REST;
- Utilização de banco de dados SQL embarcado (H2);
- Cobertura de testes unitários da camada Service e Controller utilizando JUnit e Mockito;

# Rotas & Funcionalidades
### Consulta de todos os passageiros registrados no voo:
HTTP GET → /api/passageiros

![image](https://github.com/marinasthoffmann/365Airlines/assets/77811782/4479586c-39cb-431a-b2e7-4b0d791756cb)

### Consulta de passageiro por CPF:
HTTP GET → /api/passageiros/{cpf}

![image](https://github.com/marinasthoffmann/365Airlines/assets/77811782/df901a81-4eb0-4a96-b65d-1a22d0a85f8e)

### Consulta de assentos da aeronave:
HTTP GET → /api/assentos

![image](https://github.com/marinasthoffmann/365Airlines/assets/77811782/ab315f74-ff6a-46c7-aa72-6904b95ddfea)


### Realização de confirmação (checkin) do passageiro no voo:
HTTP POST → /api/passageiros/confirmacao

Exemplo de Requisição:

![image](https://github.com/marinasthoffmann/365Airlines/assets/77811782/f0711e86-390d-4054-9e44-dec65912b283)

Exemplo de Resposta:

![image](https://github.com/marinasthoffmann/365Airlines/assets/77811782/44aeecaf-a1d4-4571-b21a-c3b99a21d3f9)

