# Automação Industrial - Maquinário

API totalmente feita para **estudos e consultas**. Caso note excesso de linhas de código, é porque houve uma empolgação na hora de codificar e fui adicionando o que lembrava, juntamente com pesquisas e consultas.

## Tecnologias Utilizadas

- **Spring Boot 3.2.4**: Framework para desenvolvimento da API.
- **Java 21**: Linguagem utilizada para o desenvolvimento da aplicação.
- **Springdoc OpenAPI 2.5.0**: Para documentação da API via Swagger.
- **JUnit 5 e MockMvc**: Para realização de testes unitários e de integração.
- **Docker**: Para containerização da aplicação.
- **Abstract Factory Pattern**: Padrão de design utilizado para a criação de objetos de forma desacoplada.

## Tipos de Testes

- **Testes Unitários**: Testes realizados para verificar a lógica individual de componentes da aplicação.
- **Testes de Integração**: Testes que validam a integração entre diferentes componentes e serviços da aplicação, como a execução de endpoints.
- **Testes de Controller**: Testes feitos com MockMvc para simular requisições HTTP e validar respostas dos endpoints da API.
- **Testes de Validação de Erros**: Testes para garantir que erros sejam tratados corretamente e que as respostas sejam padronizadas.
