# Koina

Backend em Java/Spring Boot para gestão de usuários e atendimentos.

## Visão geral

O projeto expõe uma API REST para:

- cadastrar, listar, buscar e excluir usuários;
- gerenciar usuários anônimos e cadastrados;
- registrar atendimentos e vinculá-los a voluntários;
- documentar a API com Swagger/OpenAPI.

## Tecnologias

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Spring Security
- PostgreSQL e H2
- OpenAPI/Swagger
- Maven

## Estrutura do projeto

```text
src/
├── main/
│   ├── java/com/example/koina/
│   │   ├── atendimento/
│   │   ├── config/
│   │   ├── exception/
│   │   ├── usuario/
│   │   └── KoinaApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/example/koina/KoinaApplicationTests.java
```

## Requisitos

- JDK 21+
- Maven 3.9+
- Opcionalmente: PostgreSQL para ambiente de produção/local

## Execução

Na raiz do projeto, execute:

```bash
./mvnw spring-boot:run
```

No Windows PowerShell:

```powershell
mvnw.cmd spring-boot:run
```

A aplicação iniciará em:

- `http://localhost:8080`

A documentação OpenAPI/Swagger estará disponível em:

- `http://localhost:8080/swagger-ui/index.html`

## Endpoints principais

### Usuários

- `POST /api/v1/usuarios/anonimos`
- `POST /api/v1/usuarios/cadastrados`
- `GET /api/v1/usuarios`
- `GET /api/v1/usuarios/{idUsuario}`
- `PUT /api/v1/usuarios/{idUsuario}`
- `DELETE /api/v1/usuarios/{idUsuario}`

### Atendimentos

- `POST /api/v1/atendimentos`
- `GET /api/v1/atendimentos`
- `GET /api/v1/atendimentos/{idAtendimento}`
- `PUT /api/v1/atendimentos/{idAtendimento}`
- `DELETE /api/v1/atendimentos/{idAtendimento}`

## Configuração

A aplicação usa o arquivo `application.properties` para configurar o banco de dados e outros parâmetros do Spring.

Por padrão, o projeto inclui suporte ao H2 para facilitar execução local e desenvolvimento, e também possui o driver do PostgreSQL configurado para uso em ambientes mais completos.

## Observações

- A API contém autenticação/segurança via Spring Security.
- Os endpoints de documentação e health checks podem variar conforme configuração do ambiente.
- Este repositório foi estruturado como um backend MVC com separação por domínio (`usuario` e `atendimento`).

## Licença

Este projeto não possui licença definida no momento.
