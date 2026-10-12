<<<<<<< HEAD
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
=======
# AcxiomCRM --- Customer Relationship Management System

AcxiomCRM is a web-based CRM application for organizing customer
information and tracking the sales process from lead capture to
opportunity management. It is intended to demonstrate the core CRM
workflow described in the AcxiomCRM assignment: maintaining customer
records, tracking leads, managing sales opportunities, and viewing
sales-pipeline information.

> **Implementation note:** This project currently uses **Java, Spring
> Boot, Spring MVC, Spring Data JPA, Thymeleaf, and H2**. The assignment
> document specifies **ASP.NET Core, Entity Framework Core, ASP.NET Core
> Identity, and Chart.js**. This implementation therefore differs from
> the specified technology stack. Review the implemented features and
> acceptance criteria before submission; this README does not claim that
> unimplemented requirements are complete.

## 1. Project Goals

-   Keep customer information organized in one place.
-   Track prospective customers as leads and maintain their status.
-   Manage sales opportunities, including amount, stage, probability,
    and expected close date.
-   Calculate the weighted value of an opportunity using
    `amount × probability / 100`.
-   Provide a simple web interface for common CRM operations.
-   Expose customer data through a REST endpoint where available in the
    current implementation.

## 2. Technology Stack

  Area                    Technology
  ----------------------- --------------------------------------------------------
  Language                Java
  Application framework   Spring Boot
  Web layer               Spring MVC
  Server-rendered UI      Thymeleaf
  Persistence             Spring Data JPA
  Database                H2 (current development configuration)
  Build tool              Maven
  Styling                 HTML, CSS, Bootstrap (where included in the templates)

The exact database and UI libraries depend on the configuration and
dependencies in the project source.

## 3. Main CRM Workflow

### Customers

1.  Open the Customers section.
2.  Create a customer with the available contact and company details.
3.  View the customer list and open a record to review its details.
4.  Edit or remove a record when required.
5.  Use any available search/filter controls to find a customer.

### Leads

1.  Open the Leads section and create a prospective-customer record.
2.  Enter the available contact details, company, source, expected
    value, and assigned user.
3.  Update the lead as the sales conversation progresses.
4.  Use the configured lead status to distinguish new, contacted,
    qualified, unqualified, converted, or lost leads, where those
    statuses are supported by the current UI.

### Opportunities

1.  Open the Opportunities section.
2.  Select the associated customer and enter the opportunity details.
3.  Set the amount, probability, stage/status, and expected close date.
4.  Save and review the opportunity in the list.
5.  Use the weighted opportunity value to understand the
    probability-adjusted pipeline contribution.

**Weighted value formula**

`Weighted Value = Opportunity Amount × Probability / 100`

For example, an opportunity worth `₹100,000` with a probability of `60%`
has a weighted value of `₹60,000`.

### Dashboard

Use the dashboard to review the summary information exposed by the
current implementation. The assignment describes a fuller dashboard with
role-specific KPIs, lead-status and opportunity-pipeline charts, and
monthly sales reporting. Only describe those as available in a live
demonstration if they are actually implemented and working in this
checkout.

## 4. Running the Application

### Prerequisites

-   Java Development Kit (JDK) compatible with the project's `pom.xml`
-   Maven (or the Maven Wrapper, if included)
-   Git (optional)

### Start locally

1.  Clone or download the project.

2.  Open a terminal in the directory containing `pom.xml`.

3.  Run:

    ``` bash
    mvn spring-boot:run
    ```

    If the project includes Maven Wrapper, use `./mvnw spring-boot:run`
    on macOS/Linux or `mvnw.cmd spring-boot:run` on Windows.

4.  Wait for Spring Boot to finish starting.

5.  Open `http://localhost:8080` in a browser.

If startup fails, check the Java version, Maven output, configured
server port, and database settings. Do not assume demo credentials exist
unless they are explicitly configured in the source.

## 5. Configuration and Database

The current development setup uses H2 according to the project
configuration. Check `src/main/resources/application.properties` (or
`application.yml`) for the active database URL, server port, schema
settings, and other environment-specific configuration.

Do not put production database passwords or other secrets in source
control. Use environment variables or an external secret/configuration
provider for deployment.

## 6. REST API

The project has a customer API route intended for returning customer
data as JSON:

``` http
GET /api/customers
```

Test it locally at `http://localhost:8080/api/customers` after starting
the application. Authentication/authorization behavior depends on the
current security configuration. The assignment expects secured APIs for
customers, leads, opportunities, follow-ups, authentication, and
reporting; do not assume all of those endpoints exist unless they are
present in the code.

## 7. Business Rules to Demonstrate

When these rules are implemented in the running version, demonstrate
them with both valid and invalid inputs:

-   Required customer and lead fields cannot be empty.
-   Email and phone values must follow the configured formats.
-   Customer email/phone uniqueness and duplicate-customer checks should
    be enforced.
-   Lead status must be one of the configured values.
-   Active opportunity amount must be greater than zero.
-   Opportunity probability must be between `0` and `100`.
-   An active opportunity's expected close date must not be in the past.
-   A new/planned follow-up date must not be earlier than today.

The assignment specifically expects **client-side and server-side
validation**. Browser validation alone is not sufficient because
requests can be crafted outside the browser.

## 8. Assignment Requirements and Verification Checklist

The assignment defines AcxiomCRM as more than a basic CRUD application.
Use this checklist to verify the actual implementation before presenting
it:

-   [ ] Login, logout, secure password handling, and account lockout
-   [ ] Role-based access for Admin, Manager, and Sales Executive
-   [ ] Customer management, search, duplicate checks, and customer
    history
-   [ ] Lead management, assignment, status tracking, and lead
    conversion
-   [ ] Opportunity stages, amount/probability rules, and weighted
    pipeline
-   [ ] Follow-up scheduling, completion, pending/overdue views, and
    date validation
-   [ ] Activity management for calls, meetings, emails, and tasks
-   [ ] Role-based dashboard KPIs and Chart.js charts
-   [ ] User and role administration
-   [ ] Audit logging for authentication and important
    create/update/delete operations
-   [ ] Authorized REST APIs for customers, leads, opportunities, and
    other required resources
-   [ ] Server-side validation, anti-forgery protection, and
    ownership/role checks
-   [ ] Tests covering valid requests, invalid requests, unauthorized
    access, and business rules

A checked item should only be marked complete after it has been verified
in the source and demonstrated in the running application. The
assignment's final acceptance scenarios should be used as the final test
plan.

## 9. Suggested Demonstration Sequence

For a concise walkthrough:

1.  Start the application and open the dashboard.
2.  Create a customer with valid details, then demonstrate the customer
    list and details.
3.  Create a lead and change its status as it moves through
    qualification.
4.  Create an opportunity associated with the customer and explain
    amount, probability, stage, expected close date, and weighted value.
5.  Show the customer API response at `/api/customers`.
6.  Demonstrate invalid input handling and verify that invalid data is
    rejected by the server.
7.  If implemented, sign in using each role and show the different
    access scopes.
8.  If implemented, show follow-ups, audit records, and dashboard
    charts.

Do not demonstrate a feature as complete if it is not working in the
submitted version.

## 10. Project Structure

The application is organized around the Spring Boot project structure.
Typical areas include:

``` text
src/
  main/
    java/
      com/acxiom/crm/
        customer/       # Customer model, repository, controller
        lead/           # Lead model, repository, controller
        opportunity/    # Opportunity model, repository, controller
        ...             # Application and other configured packages
    resources/
      templates/        # Thymeleaf views
      static/           # CSS, JavaScript, and static assets
      application.properties
pom.xml
README.md
```

The exact package and file names may differ slightly; the repository
contents are the source of truth.

## 11. Security and Production Notes

-   Use server-side authorization for protected pages and API endpoints.
-   Never store plain-text passwords or expose password hashes.
-   Validate user input on the server, not only in the browser.
-   Protect state-changing form submissions against CSRF where
    applicable.
-   Keep secrets outside source control and use HTTPS in production.
-   Restrict audit records and administrative functions to authorized
    roles.

These are requirements to verify against the implementation, not a claim
that every control is already present.

## 12. Known Scope Limitation

The assignment's prescribed stack is ASP.NET Core with Entity Framework
Core, ASP.NET Core Identity, and Chart.js. This repository uses a
Java/Spring Boot approach. It also must be checked against the
assignment's required workflows---especially role-based authorization,
follow-ups, activities, audit logging, reporting, and secured
APIs---before it can be considered fully compliant.

------------------------------------------------------------------------

**Project:** AcxiomCRM\
**Purpose:** Demonstrate customer relationship and sales-pipeline
workflows\
**Status:** Development project; verify all required features against
the acceptance checklist before submission.
>>>>>>> 9dd2206f7395e774da43934b07149a27f3ff0f23
