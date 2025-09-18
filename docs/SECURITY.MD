# Security Design

## Authentication

- Spring Security HTTP Basic authentication.
- Users stored in H2 database.
- Passwords hashed with **BCrypt**.

## Authorization

- Roles: `ROLE_ADMIN`, `ROLE_USER`.
- All `/api/**` endpoints require authentication.
- `/h2-console/**` is open for convenience.

## Password Management

- `UserEntity.password` stores **BCrypt hashed passwords**.
- PasswordEncoder bean provided via `SecurityConfig`.
- Example: `passwordEncoder.encode("password")`.

## Best Practices

- Never store plaintext passwords.
- Use `ResponseEntity` to clearly communicate HTTP status codes.
- Future improvement: JWT tokens or OAuth2 for production deployments.
