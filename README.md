# SentinelGate – Secure AI API Gateway

The goal of SentinelGate is to act as a single, controlled entry point for all AI requests inside an organization similar in spirit to an API gateway or service mesh but purpose-built for AI workloads.
This repository currently focuses on architecture, interfaces and system design. Implementation is being built incrementally.



## Problem Statement

Teams adopting LLMs face several recurring issues:

<ul>
<li> No governance layer - Prompts and responses bypass existing security, privacy and audit controls.</li>

 
<li> Data leakage risk -
Sensitive data can be sent to external providers without detection. </li>

<li> Unbounded cost -
Token usage scales silently and unpredictably.</li>


<li> No evaluation discipline -
Prompt changes regress quality with no signal until users complain.</li><br>
</ul>

SentinelGate is designed to address these issues by introducing a dedicated AI control plane.


## Primary goals
<ul>
<li>Centralize LLM access behind a single gateway.</li>
<li>Enforce security and usage policies consistently.</li>
<li>Make AI usage observable, auditable and reproducible</li>
<li>Enable structured evaluation of prompts and policies </li>

</ul>


## High Level Arch

## High Level Architecture
```text
Client Service
   |
   v
SentinelGate (API Gateway)
   |
   +--> Policy Engine
   |
   +--> Rate Limiter / Budget Enforcer
   |
   +--> Provider Router
            |
            +--> LLM Provider (OpenAI / Anthropic / Gemini / Local)
            |
            +--> Audit & Telemetry Queue


