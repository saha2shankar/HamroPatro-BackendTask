# 📘 HamroPatro Backend API – School Data Management

A comprehensive Spring Boot REST API for managing Students, Classes, Teachers, Subjects, and Dashboard statistics.

Version: **1.0.0**  
API Spec: **OpenAPI 3.1**  
Swagger Docs: [`/school-api-doc/school-api`](http://localhost:8080/)  
Author: **Harishankar Sah**

---

## 🌐 Server

- **Local Dev Server:** `http://localhost:8080`

---

## 📖 API Documentation

Interactive API documentation is available via Swagger UI:

📎 [Swagger UI Documentation](http://localhost:8080/school-api-doc/school-api)

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
