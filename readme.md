# Job Portal Backend

A backend REST API for a Job Portal application built with Spring Boot.

## Tech Stack
- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- PostgreSQL
- Swagger (SpringDoc OpenAPI)
- Lombok

## Roles
- **Recruiter** - Post jobs, view applications for their jobs
- **Candidate** - View all jobs, apply for jobs, view own applications

## Security
- JWT based stateless authentication
- Role based access control (RECRUITER / CANDIDATE)
- BCrypt password encryption

## How to Run
1. Clone the repo
2. Set up PostgreSQL and update `application.properties`
3. Run with `mvn spring-boot:run`
4. Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## API Endpoints
| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| POST | /auth/register | Public | Register user |
| POST | /auth/login | Public | Login and get JWT |
| POST | /jobs | Recruiter | Post a job |
| GET | /jobs | All | View all jobs |
| GET | /jobs/my | Recruiter | View my posted jobs |
| POST | /application/apply/{jobId} | Candidate | Apply for job |
| GET | /application | Candidate | View my applications |
| GET | /application/{jobId} | Recruiter | View job applicants |