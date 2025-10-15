## TaskTracker

Spring Boot REST API to manage task lists and tasks.

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
- For PostgreSQL, set Spring datasource properties in `application.properties`.

### Build & Test
```bash
./mvnw -q -DskipTests=false test
```

### Docker (optional)
If you use `docker-compose.yaml`, start services with:
```bash
docker compose up -d
```

### License
MIT


