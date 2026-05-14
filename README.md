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

* Answer submission API (`POST /answers`)
* DTO-based API design
* Input validation
* Global exception handling
* H2 database integration
* Clean layered architecture

---

## 📂 Documentation

All system design artifacts are maintained in the `docs/` folder:

```id="doc1"
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

```id="arch1"
Controller → Service → Repository → Database
```

---

## 📌 API Endpoints

### 🔹 Submit Answer

**POST** `/answers`

#### Request:

```json id="req1"
{
  "questionId": "q1",
  "answer": "Design a URL shortener..."
}
```

#### Response:

```json id="res1"
{
  "id": 1,
  "questionId": "q1",
  "answer": "...",
  "feedback": "Good attempt. AI feedback will be added later.",
  "createdAt": "2026-05-09T18:30:00"
}
```

---

## 🧪 Validation

* `questionId` → required
* `answer` → required

Example:

```json id="err1"
{
  "questionId": "Question ID cannot be empty",
  "answer": "Answer cannot be empty"
}
```

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot 3.x
* Spring Data JPA
* H2 Database
* Maven

---

## ⚙️ Running the Project

```bash id="run1"
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

* 🔜 AI feedback integration (OpenAI)
* 🔜 Question management API
* 🔜 Structured answer evaluation (FR/NFR, trade-offs)
* 🔜 User authentication
* 🔜 Microservices migration
* 🔜 Caching & scaling

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
