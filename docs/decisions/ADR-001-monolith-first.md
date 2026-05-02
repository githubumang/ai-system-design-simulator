# ADR-001: Monolith First

- Status: Accepted
- Date: 2026-05-02

## Context

We are building **AI System Design Simulator** (Phase 1). The initial feature set is:

- User login/signup
- Start interview
- Generate question (AI)
- Submit answer
- Get feedback

We want to ship an end-to-end experience quickly, iterate on prompts/UX, and avoid premature complexity.

## Decision

We will implement a **modular monolith architecture**, where all features are part of a single deployable unit but organized into well-defined modules.

The monolith will own:

- Authentication and user management
- Interview/session lifecycle
- AI question generation and feedback generation
- Persistence for users/interviews/questions/answers/feedback

## Consequences

### Positive

- Faster delivery and iteration on product + prompts
- Simpler local development and deployment
- Easier to debug end-to-end flows early on

### Negative

- Codebase can become harder to modularize over time
- Scaling and reliability boundaries are coupled
- Slower parallel development as the team grows

## Notes / Follow-ups

- Keep clear module boundaries inside the monolith (e.g., `auth`, `interviews`, `ai`, `feedback`).
- When usage grows, migrate to services by extracting the AI integration and interview workflow first.


## Future Plan

The system will evolve into microservices by extracting:
- AI Service (LLM interaction)
- Interview Service (session orchestration)
- Auth Service (user management)

Each module is designed with clear boundaries to support this transition.