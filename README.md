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

### ✅ Phase 2: Backend Development

* Question management APIs
* Answer submission APIs
* DTO-based API architecture
* Input validation using Jakarta Validation
* Global exception handling
* Structured API error responses
* Relational mapping between Users, Questions, and Answers
* Swagger/OpenAPI integration for API documentation
* OpenAI integration using Spring AI
* Structured AI-powered evaluation workflow
* Evaluation caching and lifecycle management
* Seed data loader for local development
* API versioning using `/api/v1`

### ✅ Phase 3: Frontend Development (In Progress)

* React + TypeScript frontend initialized using Vite
* Tailwind CSS integration
* Client-side routing using React Router
* Reusable frontend architecture and folder structure
* Shared layout components (Navbar, Footer, MainLayout)
* Foundation for API integration using Axios and React Query

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
* **Separate submission and evaluation workflows**

---

## 🧱 Architecture

```text
Client → Controller → Service → Evaluation Engine → Prompt Builder → Repository → Database
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

One Answer → One Evaluation

Unique Constraint:
One User can submit only one answer per question
```

---

## 📌 API Endpoints

### 🔹 Questions

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `api/v1/questions` | Fetch all questions |
| GET | `api/v1/questions/{id}` | Fetch question by ID |

---

### 🔹 Theory Answers

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `api/v1/answers/theory` | Submit or update answer for THEORY questions |

#### Theory Answer Request

```json
{
  "userId": 1,
  "questionId": 1,
  "answer": "Spring Boot simplifies Java backend development..."
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

### 🔹 Design Answers

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `api/v1/answers/design` | Submit or update answer for DESIGN questions |

#### Design Answer Request

```json
{
  "userId": 1,
  "questionId": 2,
  "functionalRequirements": "Users should create short URLs",
  "nonFunctionalRequirements": "High availability and scalability",
  "apiDesign": "POST /shorten",
  "databaseDesign": "Use SQL for metadata storage",
  "scalingStrategy": "Use caching and DB sharding",
  "tradeOffs": "SQL consistency vs NoSQL scalability"
}
```

#### Response

```json
{
  "id": 1,
  "questionId": 1,
  "questionTitle": "Design URL Shortener",
  "functionalRequirements": "Users should create short URLs",
  "nonFunctionalRequirements": "High availability and scalability",
  "apiDesign": "POST /shorten",
  "databaseDesign": "Use SQL for metadata storage",
  "scalingStrategy": "Use caching and DB sharding",
  "tradeOffs": "SQL consistency vs NoSQL scalability",
  "feedback": "Good attempt. AI feedback will be added later.",
  "createdAt": "2026-05-09T18:30:00"
}
```

### 🔹 Evaluations

| Method | Endpoint                               | Description                                  |
| ------ | -------------------------------------- | -------------------------------------------- |
| POST   | `/api/v1/evaluations/{answerId}`       | Generate evaluation for submitted answer     |
| POST   | `/api/v1/evaluations/{answerId}/retry` | Force re-evaluate an answer and bypass cache |

#### Evaluation Response

```json
{
  "answerId": 1,
  "questionType": "DESIGN",
  "status": "COMPLETED",
  "overallScore": 8,
  "functionalRequirementsScore": 8,
  "nonFunctionalRequirementsScore": 7,
  "apiDesignScore": 8,
  "databaseDesignScore": 7,
  "scalingScore": 9,
  "tradeOffsScore": 8,
  "overallFeedback": "Good overall system design with clear trade-off analysis.",
  "evaluatedAt": "2026-05-18T20:30:00"
}
```

---

## 🧪 Validation

### Theory Answers

Required fields:
- `userId`
- `questionId`
- `answer`

### Design Answers

Required fields:
- `userId`
- `questionId`
- `functionalRequirements`
- `nonFunctionalRequirements`

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

* One user can submit only one answer per question
* Submitting an answer again updates the existing answer
* Invalid users/questions return proper HTTP 404 responses
* Theory and Design questions use separate answer workflows
* Design answers support structured system design sections
* Evaluations are generated independently from answer submission
* Evaluations are cached and reused if the submitted answer has not changed
* Updating an answer automatically invalidates stale evaluations
* Clients can explicitly retry evaluations using the retry endpoint
* Evaluation lifecycle is tracked using `PROCESSING`, `COMPLETED`, and `FAILED` states

---

## ⚠️ Current Limitations

- Authentication is not implemented yet
- AI evaluation quality depends on prompt engineering
- AI responses may vary slightly between evaluations
- Evaluation workflow is currently synchronous
- H2 is used for local development only

---

## 🧠 Supported Question Types

### THEORY Questions
Used for conceptual/backend knowledge questions.

Example:
- What is Spring Boot?
- Explain Dependency Injection

### DESIGN Questions
Used for structured system design interview practice.

Supported sections:
- Functional Requirements
- Non-Functional Requirements
- API Design
- Database Design
- Scaling Strategy
- Trade-offs

---

## 🛠️ Tech Stack

### Backend

* Java 17
* Spring Boot 3.x
* Spring Data JPA
* Hibernate
* H2 Database
* Maven
* Swagger/OpenAPI
* Spring AI
* OpenAI API

### Frontend

* React 19
* TypeScript
* Vite
* Tailwind CSS
* React Router DOM
* Axios
* TanStack React Query
* ESLint
* Prettier

---

## 🖥️ Frontend Architecture

```text
frontend/
├── src/
│   ├── components/
│   ├── pages/
│   ├── layouts/
│   ├── routes/
│   ├── services/
│   ├── hooks/
│   ├── context/
│   ├── types/
│   ├── utils/
│   ├── assets/
│   ├── App.tsx
│   └── main.tsx
```

### Frontend Responsibilities

* Present system design questions
* Allow users to submit answers
* Display AI-generated feedback
* Track user submissions and evaluation history
* Communicate with backend APIs

```
```

---

## ⚙️ Running the Project

### Prerequisites

Ensure the following are installed on your machine:

* Java 17+
* Maven 3.9+
* Node.js 20+
* npm 10+
* Git

---

### 1. Clone the Repository

```bash
git clone https://github.com/githubumang/ai-system-design-simulator.git
cd ai-system-design-simulator
```

---

### 2. Run the Backend Application

Navigate to the backend directory:

```bash
cd backend/system-design-ai
```

Install dependencies and start the Spring Boot application:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

The backend application will be available at:

```text
http://localhost:8080
```

Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

H2 Console:

```text
http://localhost:8080/h2-console
```

---

### 3. Run the Frontend Application

Open a new terminal and navigate to the frontend directory:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the Vite development server:

```bash
npm run dev
```

The frontend application will be available at:

```text
http://localhost:5173
```

---

### 4. Run Backend Tests

```bash
cd backend/system-design-ai
./mvnw test
```

---

### 5. Run Frontend Checks

```bash
cd frontend
npm run lint
```

---

## 🧪 H2 Console

```text
http://localhost:8080/h2-console
```

* JDBC URL: `jdbc:h2:mem:testdb`
* Username: `sa`

---

## 📈 Future Roadmap

* 🔜 User management & authentication
* 🔜 Asynchronous AI evaluation workflow
* 🔜 AI evaluation retry resilience policies
* 🔜 Prompt versioning and evaluation invalidation strategy
* 🔜 Progress tracking dashboard
* 🔜 Frontend application (React)
* 🔜 Microservices migration
* 🔜 Redis caching & performance optimization
* 🔜 Production deployment

---

## 🧠 Key Highlights

* Documentation-driven development
* Clean architecture layering
* DTO-based API design
* Validation & error handling
* Scalable system design approach
* AI-powered structured evaluation workflow
* Prompt-engineering-based evaluation architecture
* Lifecycle-aware evaluation caching
* Lifecycle-aware AI evaluation caching
* Retryable AI evaluation workflow
* Evaluation state management for frontend readiness

---

## 👨‍💻 Author

**Umang Agrawal**

---

## 📄 License

For learning and demonstration purposes.
