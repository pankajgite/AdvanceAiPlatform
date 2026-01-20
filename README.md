# Advance AI Platform - Lovable Clone

This project is an advanced AI platform built with Spring Boot, designed to clone the functionality of "Lovable". It includes features for project management, user authentication, subscription handling via Stripe, and more.

## Features Implemented So Far

### 1. Project Management
- **Create Project**: Users can create new projects.
- **Get User Projects**: Retrieve a list of projects owned by the authenticated user.
- **Get Project by ID**: Fetch detailed information about a specific project.
- **Update Project**: Modify project details (e.g., name).
- **Soft Delete Project**: Mark projects as deleted without removing them from the database.
- **Project Members**: Manage project members (invite, update role, remove).

### 2. User Authentication & Security
- **JWT Authentication**: Secure endpoints using JSON Web Tokens.
- **Sign Up & Login**: User registration and authentication endpoints.
- **Profile Management**: Retrieve authenticated user's profile.
- **Role-Based Access Control**:
    - Project roles: `OWNER`, `EDITOR`, `VIEWER`.
    - Permissions checks using `@PreAuthorize` and custom security expressions (e.g., `@security.canUpdateProject(#id)`).

### 3. Subscription & Billing (Stripe Integration)
- **Stripe Checkout**: Create checkout sessions for subscription plans.
- **Plan Management**: Database entities for subscription plans (`Plan`).
- **Webhook Handling**: (Planned/In-progress) To handle Stripe events.

### 4. Error Handling
- **Global Exception Handler**: Centralized handling for exceptions like `ResourceNotFoundException`, `BadRequestException`, and `AccessDeniedException`.
- **Custom Error Responses**: Standardized JSON error format (`ApiError`).

### 5. Database & Entities
- **PostgreSQL**: Database backend.
- **Entities**:
    - `User`: Stores user details.
    - `Project`: Stores project metadata.
    - `ProjectMember`: Manages user-project relationships and roles.
    - `Plan`: Stores subscription plan details.
    - `Subscription`: (Planned) To track user subscriptions.

## Tech Stack
- **Java 21**
- **Spring Boot 3.x**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **MapStruct** (for DTO mapping)
- **Stripe Java SDK**

## API Endpoints

### Auth
- `POST /api/auth/signup`: Register a new user.
- `POST /api/auth/login`: Authenticate and get JWT.
- `GET /api/auth/me`: Get current user profile.

### Projects
- `GET /api/projects`: List user's projects.
- `POST /api/projects`: Create a new project.
- `GET /api/projects/{id}`: Get project details.
- `PATCH /api/projects/{id}`: Update a project.
- `DELETE /api/projects/{id}`: Soft delete a project.

### Project Members
- `GET /api/projects/{projectId}/members`: List members of a project.
- `POST /api/projects/{projectId}/members/invite`: Invite a user to a project.

### Billing
- `POST /api/billing/checkout`: Initiate a Stripe checkout session.

## Setup & Configuration
1.  **Database**: Ensure PostgreSQL is running and configured in `application.yaml`.
2.  **Stripe Keys**: Add your Stripe Secret Key and Webhook Secret to `application.yaml` or environment variables.
3.  **Run**: Execute `LovableCloneApplication.java` to start the server.

## Recent Fixes & Improvements
- Fixed `ProjectServiceImpl` to correctly fetch user projects.
- Added `DataSeeder` to create a default user for testing.
- Corrected logic in `ProjectMemberServiceImpl` for inviting users.
- Fixed `StripePaymentProcessor` builder pattern usage.
- Improved Global Exception Handling for 403 Forbidden errors.
