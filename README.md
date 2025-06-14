# 📡 AllCritics API

A **AllCritics API** é a base do backend da plataforma AllCritics — um espaço onde usuários podem registrar, avaliar e discutir suas experiências com diferentes tipos de mídia: filmes, séries, livros, músicas, jogos e muito mais. Essa API fornece os endpoints e a estrutura necessários para autenticação, gerenciamento de usuários, cadastro de obras e interações críticas entre os membros da comunidade.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **MySQL**
- **JWT (JSON Web Token)**
- **Lombok**
- **Maven**

## 🔧 Funcionalidades Principais

- ✅ Cadastro e autenticação de usuários
- ✅ Registro e gerenciamento de mídias (filmes, séries, livros, etc.)
- ✅ Sistema de avaliação e comentários
- ✅ Criação de listas personalizadas
- ✅ Integração com banco de dados relacional (MySQL)
- ✅ Segurança baseada em autenticação JWT

## 🏗️ Estrutura do Projeto
```bash
src/
├── main/
│ ├── java/com/allcritics/
│ │ ├── config/ # Configurações de segurança e beans
│ │ ├── controller/ # Controladores REST
│ │ ├── dto/ # Objetos de transferência de dados
│ │ ├── model/ # Entidades do banco de dados
│ │ ├── repository/ # Interfaces de acesso aos dados
│ │ ├── service/ # Lógica de negócio
│ │ └── AllCriticsApplication.java
│ └── resources/
│ ├── application.properties
│ └── ...
```


   
