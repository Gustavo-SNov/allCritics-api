# AllCritics API

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.x-brightgreen)
![Java](https://img.shields.io/badge/Java-21-blue)
![Database](https://img.shields.io/badge/Database-MySQL-orange)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

Bem-vindo à API do **AllCritics**, o back-end que alimenta a plataforma unificada para registro, avaliação e discussão de diversas formas de entretenimento, como filmes, séries, livros e jogos.

## Índice

- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Pré-requisitos](#pré-requisitos)
- [Como Configurar e Executar](#como-configurar-e-executar)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Endpoints da API](#endpoints-da-api)
- [Como Contribuir](#como-contribuir)

## Tecnologias Utilizadas

- **[Spring Boot 3.4.x](https://spring.io/projects/spring-boot):** Framework principal para a construção da API.
- **[Java 21 (LTS)](https://www.oracle.com/java/):** Linguagem de programação base.
- **[Spring Security](https://spring.io/projects/spring-security):** Para autenticação e autorização via JWT.
- **[Spring Data JPA](https://spring.io/projects/spring-data-jpa):** Para persistência de dados.
- **[MySQL](https://www.mysql.com/):** Banco de dados relacional.
- **[Maven](https://maven.apache.org/):** Ferramenta de gerenciamento de dependências e build.

## Pré-requisitos

Antes de começar, você precisará ter as seguintes ferramentas instaladas em sua máquina:
- JDK 21 ou superior
- Maven 3.8 ou superior
- MySQL Server 8.0 ou superior
- Uma IDE de sua preferência (recomendamos IntelliJ IDEA)

## Como Configurar e Executar

Siga os passos abaixo para ter o projeto rodando localmente.

**1. Clone o repositório:**
```bash
git clone [https://github.com/seu-usuario/allcritics-api.git](https://github.com/seu-usuario/allcritics-api.git)
cd allcritics-api
```

**2. Configure o Banco de Dados:**
- Crie um banco de dados no seu MySQL.
  ```sql
  CREATE DATABASE allcritics_db;
  ```
- Crie um usuário ou utilize um existente e dê a ele as permissões necessárias para este banco.

**3. Configure as Variáveis de Ambiente:**
- Na raiz do projeto, você encontrará um arquivo chamado `application.properties.example`. Crie uma cópia dele e renomeie para `application.properties`.
- Abra o arquivo `application.properties` e preencha com suas configurações locais:

```properties
# Exemplo de application.properties
# SERVER
server.port=8080

# DATABASE
spring.datasource.url=jdbc:mysql://localhost:3306/allcritics_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=seu_usuario_mysql
spring.datasource.password=sua_senha_mysql
spring.jpa.hibernate.ddl-auto=update

# JWT SECRET (MUITO IMPORTANTE: USE UM VALOR LONGO E ALEATÓRIO)
jwt.secret=S3cr3t0L0ng03Al34t0r1oParaAss1n4rS3usT0k3nsJWT
```
**Atenção:** Nunca cometa o arquivo `application.properties` com dados sensíveis para o repositório. Ele já está incluído no `.gitignore`.

**4. Execute a Aplicação:**
- Você pode executar a aplicação diretamente pela sua IDE (procurando pela classe `AllCriticsApiApplication` e rodando o método `main`).
- Ou, pelo terminal, usando o Maven:
```bash
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

## Estrutura do Projeto

O projeto segue uma arquitetura em camadas para separar as responsabilidades:

- `config`: Configurações de segurança e beans do Spring.
- `controller`: Recebe as requisições HTTP e retorna as respostas.
- `service`: Contém a lógica de negócios da aplicação.
- `repository`: Define a comunicação com o banco de dados.
- `model`: Representa as tabelas do banco de dados (Entidades JPA).
- `dto`: Objetos que definem a estrutura de dados para a comunicação via API.
- `security`: Lógica específica para a geração e validação de tokens JWT.

## Endpoints da API

Abaixo estão os endpoints principais disponíveis no MVP.

| Método   | Endpoint                  | Descrição                                         | Requer Autenticação |
| :------- | :------------------------ | :------------------------------------------------ | :------------------ |
| `POST`   | `/api/auth/register`      | Registra um novo usuário.                         | Não                 |
| `POST`   | `/api/auth/login`         | Autentica um usuário e retorna um token JWT.      | Não                 |
| `GET`    | `/api/users/{username}`   | Busca os detalhes de um perfil de usuário.        | Sim                 |
| `POST`   | `/api/reviews`            | Cria uma nova crítica para uma mídia.             | Sim                 |
| `GET`    | `/api/reviews/{mediaId}`  | Lista todas as críticas de uma determinada mídia. | Não                 |

Futuramente, a documentação completa da API estará disponível via Swagger/OpenAPI em `/swagger-ui.html`.

## Como Contribuir

Contribuições são bem-vindas! Por favor, siga os seguintes passos:

1.  Faça um **Fork** deste repositório.
2.  Crie uma nova **Branch** (`git checkout -b feature/sua-feature`).
3.  Faça suas alterações e **Commit** (`git commit -m 'feat: Adiciona sua feature'`).
4.  Faça o **Push** para a sua branch (`git push origin feature/sua-feature`).
5.  Abra um **Pull Request**.

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.