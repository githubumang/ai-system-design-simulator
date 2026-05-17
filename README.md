# 🧠 AI System Design Simulator

An AI-powered backend system that helps users practice **system design interview questions** and receive structured feedback.

---

## 🚀 Project Overview

This project simulates a **real system design interview platform** where users can:

* Submit answers to system design questions
* Receive feedback (AI-powered in later phases)
* Learn structured thinking (functional, non-functional, trade-offs)

---

## 🏗️ Current Status

### ✅ Phase 1: Design & Planning

* API specifications
* System architecture design (monolith-first)
* Architecture Decision Records (ADR)
* Documentation-first approach

### ✅ Phase 2: Backend Development (Current)

* Question management APIs
* Answer submission APIs
* DTO-based API architecture
* Input validation using Jakarta Validation
* Global exception handling
* Structured API error responses
* Relational mapping between Users, Questions, and Answers
* Swagger/OpenAPI integration for API documentation
* One answer per user per question
* Update existing answer flow
* H2 database integration
* Clean layered architecture

---

## 📂 Documentation

All system design artifacts are maintained in the `docs/` folder:

```
docs/
├── api-specs/
│   └── api-spec-v1.md
├── architecture/
│   ├── monolith-architecture-v1.md
│   └── monolith-architecture-v1.drawio
└── decisions/
    ├── ADR-001-monolith-first.md
    └── ADR-002-openai-integration.md
```

### 📌 Highlights

* **API-first design**
* **Monolith-first strategy (scalable to microservices)**
* **Clear architectural decisions via ADRs**

---

## 🧱 Architecture

```text
Client → Controller → Service → Repository → Database
```

### Layer Responsibilities

- Controller → Handles HTTP requests/responses
- Service → Business logic
- Repository → Database operations
- Database → Persistent storage

---

## 🧱 Domain Relationships

```text
One User → Many Answers
One Question → Many Answers
Each Answer belongs to one User and one Question

Unique Constraint:
One User can submit only one answer per question
```

---

## 📌 API Endpoints

### 🔹 Questions

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/questions` | Fetch all questions |
| GET | `/questions/{id}` | Fetch question by ID |

---

### 🔹 Answers

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/answers` | Submit answer for question |

#### Request

```json
{
  "questionId": 1,
  "answer": "Design a URL shortener..."
}
```

#### Response

```json
{
  "id": 1,
  "questionId": 1,
  "questionTitle": "Design URL Shortener",
  "answer": "...",
  "feedback": "Good attempt. AI feedback will be added later.",
  "createdAt": "2026-05-09T18:30:00"
}
```

---

## 🧪 Validation

Required fields:

- `userId`
- `questionId`
- `answer`

Example validation response:

```json
{
  "userId": "User ID is required",
  "questionId": "Question ID is required",
  "answer": "Answer cannot be empty"
}
```

---

## 📘 API Documentation

Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

The APIs are documented using OpenAPI/Swagger annotations.

---

## ⚠️ Error Handling

Example error response:

```json
{
  "timestamp": "2026-05-16T18:30:00",
  "status": 404,
  "message": "Question not found"
}
```

---

## 🧠 Business Rules

- One user can submit only one answer per question
- Submitting again updates the existing answer
- Invalid users/questions return proper HTTP 404 responses

---

## ⚠️ Current Limitations

- Authentication is not implemented yet
- AI feedback is currently mocked
- H2 is used for local development only

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot 3.x
* Spring Data JPA
* H2 Database
* Maven

---

## ⚙️ Running the Project

```bash 
git clone https://github.com/githubumang/ai-system-design-simulator.git
cd backend/system-design-ai
./mvnw clean install
./mvnw spring-boot:run
```

---

## 🧪 H2 Console

```id="h21"
http://localhost:8080/h2-console
```

* JDBC URL: `jdbc:h2:mem:testdb`
* Username: `sa`

---

## 📈 Future Roadmap

* 🔜 User management & authentication
* 🔜 Structured answer sections (FR, NFR, APIs, DB design, scaling)
* 🔜 AI-powered answer evaluation
* 🔜 Structured system design feedback
* 🔜 Progress tracking dashboard
* 🔜 Microservices migration
* 🔜 Caching & performance optimization

---

## 🧠 Key Highlights

* Documentation-driven development
* Clean architecture layering
* DTO-based API design
* Validation & error handling
* Scalable system design approach

---

## 👨‍💻 Author

**Umang Agrawal**

---

## 📄 License

For learning and demonstration purposes.
