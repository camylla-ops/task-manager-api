# Task Manager API (Gerenciador de Tarefas) ✅


Esta é uma API **RESTful completa** para gerenciamento de tarefas, desenvolvida em Java com Spring Boot.

Desenvolvi este projeto para aprimorar minhas habilidades em **Back-end Java**, focando na implementação de regras de negócio complexas e na **qualidade do código em arquitetura em camadas**.

O objetivo é permitir que usuários criem, compartilhem e gerenciem suas tarefas com validações avançadas, como o **Conflito de Agenda**.

##  Tecnologias Chave (Hard Skills)

Esta aplicação foi construída com tecnologias padrão da indústria:

* **Java 21:** Linguagem principal do desenvolvimento.
* **Spring Boot (WebMVC):** Framework principal da API.
* **Spring Data JPA / Hibernate:** Para persistência de dados e mapeamento Objeto-Relacional.
* **H2 Database:** Banco de dados em memória, ideal para desenvolvimento e testes.
* **Lombok:** Para reduzir o *boilerplate* de código.
* **Docker:** Para containerização.
* **Render:** Plataforma de CI/CD para deploy.

###  Integração com Serviços Externos

* **API Externa (DummyJSON):** Utilizei o **WebClient** (parte do WebFlux) para consumir a API pública DummyJSON.
* **Conceito:** O projeto usa WebMVC como motor principal da API, mas inclui o WebClient para consumo externo.

### Este projeto implementa módulos avançados que são cruciais em projetos reais de Back-end:

### Visibilidade e Compartilhamento 

* **Regra:** Uma tarefa pode ter um Dono e múltiplos Participantes.
* **Solução:** Implementação do relacionamento **`@ManyToMany`** no JPA, utilizando uma `@Query` JPQL avançada para listar tarefas onde o usuário é Dono **OU** é um Participante **OU** a tarefa é pública.

### Conflitos de Agenda 

* **Regra de Negócio:** O sistema impede que um usuário crie ou atualize uma tarefa para o status `DOING` (Em Andamento) se já houver outra tarefa que se sobreponha na data/hora.
* **Conceito:** Implementado com validação customizada no Service Layer, protegendo a lógica de negócio.

### Qualidade de Código e Erros 

* **Tratamento Global de Erros:** Uso do padrão `@ControllerAdvice` para interceptar todas as exceções (400, 403, 404) e transformá-las em uma resposta JSON limpa e padronizada.

## Testes e Deploy

* **Testes com Apidog:** Eu utilizei o **Apidog** para testar manualmente todas as rotas e validar as regras de negócio complexas da API.
* **Deploy no Render:** O projeto está containerizado com Docker e com Continuous Deployment configurado no Render.

### Rotas Principais (CRUD Completo)

| Método | Rota | Descrição |
| :--- | :--- | :--- |
| `POST` | `/users` | Cria uma nova conta. |
| `POST` | `/tasks?userId={id}` | Cria uma tarefa. **(Validação de Conflito ativada)** |
| `GET` | `/tasks?userId={id}` | Lista todas as tarefas acessíveis. |
| `GET` | `/tasks/{taskId}?userId={id}` | Busca uma tarefa específica (Com validação de visibilidade). |
| `PUT` | `/tasks/{taskId}?userId={id}` | Atualiza todos os campos da tarefa (Com validação de Conflito). |
| `PATCH` | `/tasks/{taskId}` | Atualiza apenas o status da tarefa. |
| `DELETE` | `/tasks/{taskId}?userId={id}` | Exclui a tarefa. |

##  Como Rodar Localmente (Via Docker)

1.  Clone o projeto e navegue até a pasta:
    ```bash
    git clone [https://github.com/Camylla-Ops/task-manager-api.git](https://github.com/Camylla-Ops/task-manager-api.git)
    cd task-manager-api
    ```
2.  Construa a imagem Docker:
    ```bash
    docker build -t task-manager-api:latest .
    ```
3.  Execute o contêiner Docker:
    ```bash
    docker run -p 8080:8080 task-manager-api:latest
    ```
    A API estará acessível em `http://localhost:8080`.

---
Feito com 💜 por **Camylla Oliveira**.
