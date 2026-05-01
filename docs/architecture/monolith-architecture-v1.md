# AI System Design Simulator - Monolith Architecture (V1)

This is the initial-phase architecture with a single frontend and a single backend monolith.

```mermaid
flowchart TB
    U[User] --> FE[Frontend App<br/>Web UI]

    FE --> BE[Monolithic Backend<br/>Auth + Interview + AI Flow + Feedback]

    BE --> DB[(PostgreSQL / MySQL<br/>Users, Sessions, Answers, Feedback)]
    BE --> LLM[LLM API<br/>for Question Generation + Feedback]

    subgraph Core Flow in Monolith
      A[1. Login / Signup]
      B[2. Start Interview]
      C[3. Generate Question (AI)]
      D[4. Submit Answer]
      E[5. Get Feedback]
      A --> B --> C --> D --> E
    end

    BE -. executes .-> A
```

