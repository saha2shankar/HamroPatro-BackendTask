# 📘 HamroPatro Backend API – School Data Management

A comprehensive RESTful backend system for managing **students**, **teachers**, **classes**, **subjects**, and school-level dashboards — built using **Java Spring Boot**, **MySQL**, and documented with **Swagger/OpenAPI 3.1**.

Version: **1.0.0**  
API Spec: **OpenAPI 3.1**  
Swagger Docs: [`/school-api-doc/school-api`](http://localhost:8080/)  
Author: **Harishankar Sah**

---

## 📌 Project Highlights

- Full CRUD operations for Students, Teachers, Classes, and Subjects
- DTO-based architecture for secure and clean data exchange
- Partial updates using PATCH
- Role-based access (using dummy header: `X-Role`)
- Aggregate statistics (total students, teachers, class sizes, etc.)
- Swagger UI integrated for API testing and documentation

## 🌐 Server

- **Local Dev Server:** `http://localhost:8080`

---

## 📖 API Documentation

Interactive API documentation is available via Swagger UI:

📎 [Swagger UI Documentation](http://localhost:8080/)

---

## 📚 API Overview

### 📘 Classes - `ClassSectionController`

| Method | Endpoint            | Description                   |
|--------|---------------------|-------------------------------|
| `GET`  | `/api/classes`      | Get all classes               |
| `POST` | `/api/createclass`  | Create a new class section    |

---

### 📊 Dashboard - `DashboardController`

| Method | Endpoint         | Description                     |
|--------|------------------|---------------------------------|
| `GET`  | `/api/dashboard` | Overall school dashboard stats  |

---

### 📈 Stats - `StatsController`

| Method | Endpoint     | Description                                                               |
|--------|--------------|---------------------------------------------------------------------------|
| `GET`  | `/api/stats` | Aggregate statistics (total teachers, total students, avg class size, etc) |

---

### 👨‍🎓 Students - `StudentController`

| Method  | Endpoint                  | Description                                           |
|---------|---------------------------|-------------------------------------------------------|
| `GET`   | `/api/students`           | Get list of all students                             |
| `GET`   | `/api/students/{id}`      | Get student by ID                                    |
| `GET`   | `/api/filter`             | Filter students by className, section, or teacherId  |
| `POST`  | `/api/students`           | Save student (allowed only for teachers)             |
| `PUT`   | `/api/students`           | Full update of student (all fields)                  |
| `PATCH` | `/api/students/{id}`      | Partial update (e.g., contact details)               |
| `DELETE`| `/api/students/{id}`      | Delete student by ID                                 |

---

### 📘 Subjects - `SubjectController`

| Method | Endpoint             | Description        |
|--------|----------------------|--------------------|
| `GET`  | `/api/subjectlist`   | Get all subjects   |
| `POST` | `/api/addsubject`    | Add new subject    |

---

### 👨‍🏫 Teachers - `TeacherController`

| Method | Endpoint             | Description               |
|--------|----------------------|---------------------------|
| `GET`  | `/api/teachers`      | List all teachers (DTO)   |
| `GET`  | `/api/teacher/{id}`  | Get teacher by ID         |
| `POST` | `/api/teachers`      | Create a new teacher      |

---

### 🛠️ Setup Instructions

1. **Clone the repo**
   ```bash
   git clone https://github.com/saha2shankar/HamroPatro-BackendTask.git
   cd HamroPatro-BackendTask

2. **Configure MySQL databas**

Create a database named school_db and update src/main/resources/application.properties:

spring.application.name=HamroPatro-BackendTask


# Data configuration
 ```bash
spring.datasource.url=jdbc:mysql://localhost:3306/hamroparto-collagesystem?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=

# JPA Hibernate configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Swagger Configuration
springdoc.swagger-ui.operations-sorter=method
springdoc.swagger-ui.tags-sorter=alpha
springdoc.swagger-ui.path=/
springdoc.api-docs.path=/sclool-api-doc
```

3. **Configure MySQL databas**
Run the project
```bash
./mvnw spring-boot:run
```

5. **Open Swagger UI**
```bash
Visit: http://localhost:8080/
```



🧪 Testing the API
🏗 Initial Setup Required
Before adding students, first create:
➕ Add Class
POST /api/createclass
```bash
{ "className": "10A" }
```
➕ Add Subject
POST /api/addsubject
```bash
{ "name": "Mathematics" }
```
➕ Add Teacher
POST /api/teachers
```bash
{
  "name": "Mr. Harishanakr sah",
  "subjectId": 1,
  "classSectionId": 1
}
```
➕ Add Student (Protected by Role Header)
POST /api/students
Headers Required:
X-Role: teacher
Request Body:
```bash
{
  "name": "Sanjay Yadav",
  "classNumber": "10",
  "section": "A",
  "rollNumber": 20,
  "contactDetails": "9800000000",
  "classSectionId": 1
}
```
🔐 Role-Based Access
Update and Create operations on students are restricted to users with role teacher.
Use the header in Swagger or Postman:
X-Role: teacher

📊 API Coverage
Module	Endpoints
```bash
Classes	GET /api/classes, POST /api/createclass
Students	GET /api/students, GET /api/students/{id}, POST /api/students, PUT /api/students, PATCH /api/students/{id}, DELETE /api/students/{id}, GET /api/filter
Teachers	GET /api/teachers, GET /api/teacher/{id}, POST /api/teachers
Subjects	GET /api/subjectlist, POST /api/addsubject
Dashboard	GET /api/dashboard, GET /api/stats
```
📂 Project Structure
```bash
src/
├── controller/
├── service/
├── dto/
├── model/
├── repository/
├── exception/
└── config/
```

## 🔐 Security

All endpoints that modify data (POST, PUT, PATCH, DELETE) are protected by a dummy header-based role validation.

> ✅ Add `X-Role: teacher` in request headers for operations like:
> - Saving students
> - Updating students
> - Creating classes

Example using `curl`:
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -H "X-Role: teacher" \
  -d '{ "name": "Harishankar sah", "classNumber": "10", "section": "A", "rollNumber": 20, "contactDetails": "9800000000", "classSectionId": 1 }'

 
 
 ⚙️ Technologies Used
	- Java 17
	- Spring Boot 3
	- Spring Data JPA
	- MySQL
	- Lombok
	- OpenAPI 3.1 / Swagger
	- Maven

 🧑 Author
	Harishankar Sah

 📄 License
 This project is part of a backend development task for HamroPatro Internship and is meant for academic and demo purposes.
 
 "# HamroPatro-BackendTask" 
