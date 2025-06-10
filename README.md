# EventSync-API 

Backend do projeto **EventSync**, desenvolvido em Spring Boot para a disciplina de **Sistemas Distribuídos**.

Esta API é responsável por toda a lógica de negócio e gerenciamento de dados da aplicação, fornecendo endpoints RESTful para serem consumidos por um cliente frontend (desenvolvido em React).

---

## Funcionalidades

* **Visualização Pública:** Qualquer pessoa pode visualizar a lista de eventos públicos.
* **Autenticação de Usuários:** Sistema de cadastro e login com autenticação baseada em **JWT (JSON Web Token)**.
* **Gerenciamento de Eventos:**
    * Usuários autenticados podem **criar** novos eventos.
    * Usuários podem **editar e excluir** apenas os eventos que eles mesmos criaram.
* **Participação em Eventos:** Usuários autenticados podem **marcar presença** em qualquer evento.
* **Upload de Imagens:** Funcionalidade para fazer upload de imagens de capa para os eventos, utilizando **AWS S3**.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3**
* **Spring Security** (com JWT)
* **Spring Data JPA** (Hibernate)
* **PostgreSQL** (Banco de Dados Relacional)
* **Maven** (Gerenciador de Dependências)
* **Lombok**
* **Flyway** (Ferramenta de Migração de Banco de Dados)
* **AWS S3** (Armazenamento de Objetos)

---

## Como Rodar o Projeto

Siga os passos abaixo para configurar e executar a aplicação localmente.

### Pré-requisitos

Antes de começar, garanta que você tenha instalado em sua máquina:

* [**Java Development Kit (JDK) 17 ou superior**](https://www.oracle.com/java/technologies/downloads/#java17)
* [**Apache Maven**](https://maven.apache.org/download.cgi)
* [**PostgreSQL**](https://www.postgresql.org/download/)
* Uma IDE de sua preferência (ex: IntelliJ IDEA, VS Code com Java Extension Pack).

### ⚙️ Configuração

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/barbaraguarino/EventSync-API.git
    cd EventSync-API
    ```

2.  **Configure o Banco de Dados:**
    * Abra o PostgreSQL.
    * Crie um novo banco de dados para o projeto.
        ```sql
        CREATE DATABASE nomebancodedados;
        ```

3.  **Configure as Variáveis de Ambiente:**
    * Na pasta `src/main/resources`, abra o arquivo `application.properties` e preencha com as suas credenciais e configurações.

    ```properties
    # =======================================
    # CONFIGURAÇÃO DO BANCO DE DADOS (POSTGRESQL)
    # =======================================
    spring.datasource.url=jdbc:postgresql://localhost:5432/nomebancodedados
    spring.datasource.username=seu_usuario_postgres
    spring.datasource.password=sua_senha_postgres

    # =======================================
    # CONFIGURAÇÃO DA AWS (PARA O S3)
    # =======================================
    aws.region=us-east-1 # ou a sua região de preferência
    aws.s3.bucket-name=nome-do-seu-bucket-s3
    aws.access-key-id=sua-access-key-id-da-aws
    aws.secret-access-key=sua-secret-access-key-da-aws

    ```

4.  **Execute a Aplicação:**
    * Abra um terminal na raiz do projeto.
    * Use o Maven para instalar as dependências e iniciar o servidor.

    ```bash
    # Instala as dependências e empacota o projeto
    mvn clean install

    # Inicia a aplicação Spring Boot
    mvn spring-boot:run
    ```
    A API estará rodando em `http://localhost:8080`.

---

## Endpoints da API

Aqui está uma lista dos principais endpoints disponíveis.

| Verbo HTTP | URI                         | Descrição                                           | Proteção  | Status           |
|:-----------|:----------------------------|:----------------------------------------------------|:----------|:-----------------|
| `POST`     | `/auth/register`            | Registra um novo usuário.                           | Público   | Não Implementado |
| `POST`     | `/auth/login`               | Autentica um usuário e retorna um token JWT.        | Público   | Não Implementado |
| `GET`      | `/events`                   | Lista todos os eventos públicos.                    | Público   | Não Implementado |
| `GET`      | `/events/{id}`              | Busca um evento específico pelo seu ID.             | Público   | Não Implementado |
| `POST`     | `/events`                   | Cria um novo evento.                                | Protegido | Não Implementado |
| `PUT`      | `/events/{id}`              | Atualiza um evento existente.                       | Protegido | Não Implementado |
| `DELETE`   | `/events/{id}`              | Deleta um evento.                                   | Protegido | Não Implementado |
| `POST`     | `/events/{eventId}/checkin` | Marca presença em um evento.                        | Protegido | Não Implementado |
| `GET`      | `/me/events`                | Lista os eventos em que o usuário marcou presença.  | Protegido | Não Implementado |

---

## 🏛️ Arquitetura do Projeto

O projeto segue uma arquitetura em camadas, separando as responsabilidades para manter o código organizado, testável e escalável.

* `domain`: Contém as entidades de negócio (`User`, `Event`) e as interfaces dos repositórios. É o coração da aplicação.
* `application`: Contém a lógica de negócio (casos de uso). Orquestra o fluxo de dados entre a apresentação e o domínio, utilizando DTOs e Mappers.
* `presentation`: Responsável pela exposição da API (Controladores REST). Lida com as requisições HTTP e respostas, delegando o trabalho para a camada de `application`.
* `infrastructure`: Implementações de tecnologias externas, como a integração com serviços da AWS (S3) e a implementação da persistência de dados.
* `config`: Configurações globais da aplicação, como segurança (Spring Security) e beans de integração.
* `shared`: Componentes reutilizáveis, como manipuladores de exceções globais (`GlobalExceptionHandler`), que servem a toda a aplicação.