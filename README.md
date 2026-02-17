# TaskList

![Java](https://img.shields.io/badge/Java-21-007396?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.2-6DB33F?logo=springboot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-Database-47A248?logo=mongodb&logoColor=white)
![JWT](https://img.shields.io/badge/Auth-JWT-000000?logo=jsonwebtokens&logoColor=white)
![OAuth2](https://img.shields.io/badge/OAuth2-Google-4285F4?logo=google&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-21.1-DD0031?logo=angular&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-5.9-3178C6?logo=typescript&logoColor=white)
![Chart.js](https://img.shields.io/badge/Chart.js-4.5-FF6384?logo=chartdotjs&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-7952B3?logo=bootstrap&logoColor=white)
![Maven](https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven&logoColor=white)

Aplicacao full stack para gerenciamento de tarefas com autenticacao JWT/OAuth2, API REST em Spring Boot e frontend em Angular.

## Sumario

- [Visao geral](#visao-geral)
- [Arquitetura](#arquitetura)
- [Funcionalidades](#funcionalidades)
- [Estrutura do repositorio](#estrutura-do-repositorio)
- [API (Backend)](#api-backend)
- [Frontend](#frontend)
- [Como rodar localmente (full stack)](#como-rodar-localmente-full-stack)
- [Observacoes](#observacoes)

## Visao geral

O projeto e dividido em dois aplicativos:

- `taskListApi`: backend REST com Spring Boot + MongoDB.
- `tasklist`: frontend SPA com Angular.

Fluxo principal:

1. Usuario se registra ou faz login.
2. API retorna JWT.
3. Frontend salva token e envia `Authorization: Bearer <token>` nas requisicoes.
4. Usuario cria, lista e atualiza status das tarefas.
5. Dashboard mostra grafico com contagem de tarefas por status.

## Arquitetura

- Backend:
  - Spring Boot Web + Security
  - MongoDB para persistencia
  - JWT para autenticacao stateless
  - OAuth2 Google para login social
- Frontend:
  - Angular (standalone components)
  - HttpClient + interceptor para JWT
  - Chart.js para visualizacao do resumo de tarefas
  - Bootstrap para UI

## Funcionalidades

- Cadastro de usuario
- Login com email/senha
- Login com Google (OAuth2)
- Criacao de tarefas
- Listagem por status (`PENDING`, `COMPLETED`, `CANCELLED`)
- Atualizacao de status (cancelar/concluir)
- Dashboard com grafico dinamico de tarefas por status

## Estrutura do repositorio

```text
TaskList/
|- taskListApi/   # API Spring Boot
|- tasklist/      # Frontend Angular
`- README.md
```

## API (Backend)

Diretorio: `taskListApi`

### Stack

- Java 21
- Spring Boot 4.0.2
- Spring Security
- Spring Data MongoDB
- JWT (`io.jsonwebtoken`)
- OAuth2 Client (Google)
- Maven Wrapper (`mvnw`, `mvnw.cmd`)

### Pre-requisitos

- JDK 21
- MongoDB local em `localhost:27017`

### Configuracao

Arquivo ativo por padrao:

- `taskListApi/src/main/resources/application.properties`

Perfil em uso:

- `spring.profiles.active=dev`

Arquivo de desenvolvimento:

- `taskListApi/src/main/resources/application-dev.properties`

Recomendado substituir segredos por variaveis de ambiente (especialmente JWT secret e credenciais OAuth2).

### Executar

No Windows (PowerShell), dentro de `taskListApi`:

```bash
.\mvnw.cmd spring-boot:run
```

API base URL:

- `http://localhost:8080/api/v1`

### Seguranca

Publico (sem token):

- `POST /api/v1/auth/login`
- `POST /api/v1/auth/register`
- `GET /api/v1/oauth2/**`

Protegido (requer JWT Bearer):

- Endpoints de tarefas (`/api/v1/tasks/**`)

CORS permitido para:

- `http://localhost:4200`

### Endpoints principais

#### Auth

- `POST /api/v1/auth/register`
- `POST /api/v1/auth/login`
- `GET /api/v1/auth/logout`

Exemplo `register`:

```json
{
  "email": "user@example.com",
  "password": "123456",
  "username": "usuario"
}
```

Exemplo `login`:

```json
{
  "email": "user@example.com",
  "password": "123456"
}
```

Resposta esperada no login:

```json
{
  "token": "<jwt>"
}
```

#### Tasks

- `GET /api/v1/tasks/listAllTasksByUserIdAndStatus?status=PENDING&userId=<userId>`
- `POST /api/v1/tasks/save`
- `PATCH /api/v1/tasks/complete/{taskId}`
- `PATCH /api/v1/tasks/cancel/{taskId}`
- `GET /api/v1/tasks/countTasks?userId=<userId>`

Exemplo `save`:

```json
{
  "taskTitle": "Estudar Spring Security",
  "taskDescription": "Revisar JWT filter",
  "userId": "<userId>"
}
```

Resposta esperada de `countTasks`:

```json
{
  "PENDING": 5,
  "COMPLETED": 8,
  "CANCELLED": 3
}
```

## Frontend

Diretorio: `tasklist`

### Stack

- Angular 21
- TypeScript 5.9
- RxJS
- Bootstrap 5
- Chart.js 4
- jwt-decode

### Pre-requisitos

- Node.js 20+
- npm 10+

### Instalar dependencias

Dentro de `tasklist`:

```bash
npm install
```

### Executar

```bash
npm start
```

Frontend URL:

- `http://localhost:4200`

### Rotas principais

- `/login`
- `/register`
- `/oauth2/redirect`
- `/dashboard`
- `/tasks/:status`

### Integracao com API

URLs configuradas no frontend:

- `http://localhost:8080/api/v1/auth`
- `http://localhost:8080/api/v1/tasks`

O interceptor adiciona automaticamente o header `Authorization: Bearer <token>` quando ha token salvo.

## Como rodar localmente (full stack)

1. Suba o MongoDB local (`localhost:27017`).
2. Inicie o backend em `taskListApi`:
   - `./mvnw spring-boot:run` (Linux/Mac)
   - `.\mvnw.cmd spring-boot:run` (Windows)
3. Inicie o frontend em `tasklist`:
   - `npm install`
   - `npm start`
4. Acesse `http://localhost:4200`.

## Observacoes

- O projeto usa OAuth2 com Google; para uso em outro ambiente, configure client ID/secret proprios.
- Ha segredos no arquivo de configuracao de desenvolvimento; ideal mover para variaveis de ambiente.
- Licenca nao definida no repositorio ate o momento.
