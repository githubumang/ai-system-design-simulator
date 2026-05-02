# AI System Design Simulator - Phase 1 API Spec

Base URL: `/api/v1`

## 1) User signup

- **Method:** `POST`
- **Path:** `/auth/signup`

### Request body

```json
{
  "name": "Umang",
  "email": "umang@example.com",
  "password": "StrongPassword123!"
}
```

### Response `201 Created`

```json
{
  "user": {
    "id": "usr_123",
    "name": "Umang",
    "email": "umang@example.com"
  },
  "token": "jwt-token"
}
```

## 2) User login

- **Method:** `POST`
- **Path:** `/auth/login`

### Request body

```json
{
  "email": "umang@example.com",
  "password": "StrongPassword123!"
}
```

### Response `200 OK`

```json
{
  "user": {
    "id": "usr_123",
    "name": "Umang",
    "email": "umang@example.com"
  },
  "token": "jwt-token"
}
```

## 3) Start interview

- **Method:** `POST`
- **Path:** `/interviews`
- **Auth:** Bearer token required

### Request body

```json
{
  "topic": "Design YouTube",
  "difficulty": "medium"
}
```

### Response `201 Created`

```json
{
  "interviewId": "int_456",
  "status": "started",
  "topic": "Design YouTube",
  "difficulty": "medium",
  "startedAt": "2026-05-01T10:00:00Z"
}
```

## 4) Generate question (AI)

- **Method:** `POST`
- **Path:** `/interviews/{interviewId}/questions/generate`
- **Auth:** Bearer token required

### Request body

```json
{
  "context": "User is ready for first question",
  "questionNumber": 1
}
```

### Response `200 OK`

```json
{
  "questionId": "q_789",
  "questionNumber": 1,
  "question": "Design a high-level architecture for YouTube.",
  "generatedBy": "ai"
}
```

## 5) Submit answer

- **Method:** `POST`
- **Path:** `/interviews/{interviewId}/answers`
- **Auth:** Bearer token required

### Request body

```json
{
  "questionId": "q_789",
  "answer": "I would start with clients, API gateway, metadata service, and storage service."
}
```

### Response `201 Created`

```json
{
  "answerId": "ans_101",
  "questionId": "q_789",
  "status": "submitted",
  "submittedAt": "2026-05-01T10:05:00Z"
}
```

## 6) Get feedback

- **Method:** `GET`
- **Path:** `/interviews/{interviewId}/feedback/{answerId}`
- **Auth:** Bearer token required

### Response `200 OK`

```json
{
  "answerId": "ans_101",
  "score": 7.8,
  "strengths": [
    "Clear high-level decomposition",
    "Good scaling intuition"
  ],
  "improvements": [
    "Add data partitioning strategy",
    "Discuss cache invalidation trade-offs"
  ],
  "overallFeedback": "Strong start. Add deeper storage and consistency considerations."
}
```

## Common errors

- `400 Bad Request` - invalid input
- `401 Unauthorized` - missing or invalid token
- `404 Not Found` - interview/question/answer not found
- `500 Internal Server Error` - unexpected server error

