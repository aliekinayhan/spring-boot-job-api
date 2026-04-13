# Spring Boot Job API

A RESTful Job Posting API built with Spring Boot, Spring Data JPA, and MySQL.

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- MapStruct
- Lombok
- Hibernate Validator
- UUID Creator (UUIDv7)

## Features

- Full CRUD operations for job listings
- Slice-based pagination (infinite scroll friendly)
- Global exception handling with consistent error responses
- Input validation with detailed error messages
- UUIDv7 primary key

## Endpoints

| Method | URL               | Description                      | Status |
| ------ | ----------------- | -------------------------------- | ------ |
| GET    | /api/v1/jobs      | Get all job listings (paginated) | 200    |
| GET    | /api/v1/jobs/{id} | Get job by ID                    | 200    |
| POST   | /api/v1/jobs      | Create a new job listing         | 201    |
| PUT    | /api/v1/jobs/{id} | Update a job listing             | 200    |
| DELETE | /api/v1/jobs/{id} | Delete a job listing             | 204    |

## Pagination

```
GET /api/v1/jobs?page=0&size=15
```

Response:

```json
{
  "content": [...],
  "hasNext": true
}
```

## Error Handling

All errors return a consistent response format:

```json
{
  "timestamp": "2026-04-13T12:00:00",
  "status": 400,
  "error": "VALIDATION_ERROR",
  "message": "title: cannot be blank, salary: must be positive",
  "path": "/api/v1/jobs"
}
```
