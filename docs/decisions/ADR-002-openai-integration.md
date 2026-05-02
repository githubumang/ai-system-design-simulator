# ADR-002: OpenAI Integration

- Status: Accepted
- Date: 2026-05-02

## Context

Phase 1 requires AI support for:

- Generating system design interview questions
- Producing feedback on submitted answers

We need a reliable LLM integration that supports:

- Deterministic-ish behavior via structured prompting
- Timeouts/retries for transient failures
- Cost controls and basic observability
- A path to swap providers later if needed

## Decision

Integrate with **OpenAI** via a **single backend module** (or wrapper) that:

- Centralizes all model calls (no direct OpenAI calls from controllers/routes)
- Uses prompt templates for:
  - `question_generation`
  - `answer_feedback`
- Enforces timeouts, retries with backoff, and response validation
- Logs token usage + latency (at least at request level)

If OpenAI fails:
- Retry with exponential backoff
- If still failing, return fallback response (e.g., generic question or “feedback unavailable”)

## Interaction Flow

Controller → AI Service → OpenAI Wrapper → OpenAI API → Response → Validation → Return

## Consequences

### Positive

- Clear single integration point for LLM calls
- Easier to adjust prompts, models, and parameters centrally
- Easier to add guardrails (PII redaction, schema validation) later

### Negative

- Vendor dependency on OpenAI
- Need to manage secrets (API keys) securely
- LLM non-determinism can affect scoring/feedback consistency

## Notes / Follow-ups

- Store the OpenAI API key in environment variables (never in git).
- Add a lightweight response schema for feedback (score/strengths/improvements/summary) to keep output consistent.
- If costs/latency become issues, add:
  - caching for repeated prompts
  - background job processing for feedback generation
  - provider fallback (Anthropic/Google/etc.)

