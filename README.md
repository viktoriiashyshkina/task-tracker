# 🗂️ Task Tracker API

**Task Tracker** is a web application for managing projects and their task states. It's built with Spring Boot and PostgreSQL, and includes auto-generated API documentation via Swagger UI.

## 🚀 Getting Started

1. Make sure PostgreSQL is running and the database `tracker` is created.
2. Configure your database credentials in `application.properties`.
3. Run the application using Maven:

```bash
mvn spring-boot:run
```
Access Swagger UI:
🔗 http://localhost:8080/swagger-ui.html

⚙️ Technologies Used
Java 21

Spring Boot 3.4.4

Spring Web, Spring Data JPA, JDBC

PostgreSQL

Lombok

Swagger / OpenAPI (springdoc-openapi)

📡 REST API Overview
📁 Project Controller
GET /api/projects
Returns a list of all projects. Supports filtering by name prefix.

Query parameter:

prefix_name (optional): filters projects by starting characters of the name

Example response:

json
Kopieren
Bearbeiten
[
  {
    "id": 1,
    "name": "Demo Project",
    "create_at": "2025-05-06T08:42:35.800Z",
    "updated_at": "2025-05-06T08:42:35.800Z"
  }
]
PUT /api/projects
Updates an existing project using JSON payload.

DELETE /api/projects/{project_id}
Deletes a project by ID.

📌 Task State Controller
GET /api/projects/{project_id}/task-states
Retrieves all task states for a given project.

POST /api/projects/{project_id}/task-states
Creates a new task state inside a specific project.

DELETE /api/task-states/{task_state_id}
Deletes a task state by its ID.

PATCH /api/task-states/{task_state_id}
Partially updates a task state.

PATCH /api/task-states/{task_state_id}/position/change
Changes the position (ordering) of a task state.

📘 Swagger UI
API documentation is available at:
🔗 http://localhost:8080/swagger-ui.html

You can:

Browse available endpoints

Test API requests directly from the browser

🛠 **Sample `application.properties`**:

```properties
spring.application.name=task-tracker  
spring.datasource.url=jdbc:postgresql://localhost:5432/tracker  
spring.datasource.username=postgres  
spring.datasource.password=vika  
spring.jpa.hibernate.ddl-auto=create-drop  

springdoc.api-docs.enabled=true  
springdoc.swagger-ui.enabled=true  
````


👤 Author
Viktoriia Shyshkina

