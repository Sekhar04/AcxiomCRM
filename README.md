# AcxiomCRM — Java Spring Boot CRM

## Run
Requirements: Java 21 and Maven 3.9+.

```bash
mvn clean spring-boot:run
```
Open http://localhost:8080. H2 file database is stored under `./data` and survives restarts.

## Demo users
- Admin: `admin` / `Admin@123`
- Manager: `manager` / `Manager@123`
- Sales: `sales` / `Sales@123`

## Included modules
- Spring Security form login/logout and BCrypt-encoded demo passwords; Admin-only audit log route.
- Customer CRUD/search and server-side validation.
- Lead CRUD/search/status and validation.
- Opportunity CRUD, customer relationship, stages, amount/probability/date validation, weighted pipeline.
- Follow-up CRUD, planned/completed/missed/cancelled status, overdue indicator and planned-date validation.
- Persistent audit events for opportunity/follow-up changes, Admin audit page.
- Secured REST endpoints: `/api/customers`, `/api/leads`, `/api/opportunities`, `/api/followups`, `/api/reports/summary`, `/api/reports/pipeline`.
- Dashboard navigation and Chart.js sample chart.

## API notes
All `/api/**` routes require login. Use browser session authentication or an authenticated HTTP client. JSON endpoints accept normal JSON request bodies and use Jakarta Bean Validation. H2 console is at `/h2-console` for local development only.

## Important implementation notes
This is a time-constrained assignment MVP, not a production-hardened CRM. Demo users are in-memory; lockout, persistent user/role administration, complete record ownership enforcement, authentication-event auditing, full DTO isolation, pagination/export, and comprehensive automated integration tests remain production/acceptance hardening tasks. Verify all flows in your environment before submission.
