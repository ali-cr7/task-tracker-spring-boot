## TaskTracker

TaskTracker is a clean Spring Boot REST API for organizing work into task lists and tasks. It exposes CRUD endpoints, uses layered architecture (controller → service → repository), JPA entities with DTO mappers, and ships with H2 for quick local runs or PostgreSQL via Docker for a production-like setup.

### Tech
- **Java**: 21
- **Spring Boot**: Web, Data JPA
- **DB**: H2 (runtime), PostgreSQL (optional)

### Getting Started
1) Build
```bash
./mvnw clean package
```

2) Run
```bash
./mvnw spring-boot:run
```

The API starts on `http://localhost:8080`.

### API

- Task Lists
  - `GET /task-lists` — list task lists
  - `POST /task-lists/create-task` — create task list
  - `GET /task-lists/{task_list_id}` — get task list
  - `PUT /task-lists/{task_list_id}` — update task list
  - `DELETE /task-lists/{task_list_id}` — delete task list

- Tasks (scoped under a task list)
  - `GET /task-lists/{task_list_id}/tasks` — list tasks
  - `POST /task-lists/{task_list_id}/tasks` — create task
  - `GET /task-lists/{task_list_id}/tasks/{task_id}` — get task by id
  - `PUT /task-lists/{task_list_id}/tasks/{task_id}` — update task
  - `DELETE /task-lists/{task_list_id}/tasks/{task_id}` — delete task

### DTOs
`TaskDto`
```json
{
  "id": "uuid",
  "title": "string",
  "description": "string|null",
  "dueDate": "2025-10-15T12:00:00",
  "priority": "LOW|MEDIUM|HIGH",
  "status": "OPEN|IN_PROGRESS|CLOSED"
}
```

### Database
- Default in-memory H2 for local dev.
- PostgreSQL ready; see Docker section to run a local Postgres easily and point Spring to it.

### Build & Test
```bash
./mvnw -q -DskipTests=false test
```

### Docker + PostgreSQL (optional)
This project includes a `docker-compose.yaml` to spin up PostgreSQL.

1) Start Postgres
```bash
docker compose up -d
```

2) Configure Spring for Postgres in `src/main/resources/application.properties` (example):
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tasktracker
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

3) Run the app
```bash
./mvnw spring-boot:run
```

Notes:
- Update credentials/DB name to match your `docker-compose.yaml` if different.
- Switch back to H2 by removing the Postgres properties (the default H2 dev profile will be used).

### License
MIT


