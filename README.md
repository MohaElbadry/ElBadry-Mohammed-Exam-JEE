# Banking Credit Management System

This project is a Spring Boot and Angular JEE application for managing banking credits.

## Project Overview

The application allows for the management of banking credits with the following features:
- A client can have multiple credits
- There are three types of credits: Personal Credit, Real Estate Credit, and Professional Credit
- A credit can have multiple repayments
- Security is implemented using Spring Security with JWT

## Architecture

The application follows a layered architecture:
- DAO layer based on Spring Data, JPA, Hibernate, and JDBC
- Service layer with interfaces and implementations
- Web layer based on Spring MVC using REST Controllers

## Entity Model

- **Client**: Defined by id, name, and email
- **Credit**: Abstract class defined by id, request date, status, acceptance date, amount, duration, and interest rate
  - **Personal Credit**: Extends Credit with a reason field
  - **Real Estate Credit**: Extends Credit with a property type field
  - **Professional Credit**: Extends Credit with reason and company name fields
- **Remboursement**: Defined by id, date, amount, and type

## Technologies Used

- Spring Boot 3.x
- Spring Data JPA
- Spring Security with JWT
- H2 Database (for development)
- MySQL (for production)
- Lombok
- MapStruct for DTO mapping
- Swagger/OpenAPI for API documentation

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application using Maven:
   ```
   mvn spring-boot:run
   ```
4. Access the application at http://localhost:8080
5. Access the Swagger UI at http://localhost:8080/swagger-ui.html

### Testing

Run the tests using Maven:
```
mvn test
```

## API Endpoints

The application provides the following API endpoints:

### Authentication
- POST /api/auth/login - Login with username and password
- POST /api/auth/register - Register a new user

### Clients
- GET /api/clients - Get all clients
- GET /api/clients/{id} - Get client by ID
- GET /api/clients/email/{email} - Get client by email
- POST /api/clients - Create a new client
- PUT /api/clients/{id} - Update an existing client
- DELETE /api/clients/{id} - Delete a client

### Credits
- GET /api/credits - Get all credits
- GET /api/credits/{id} - Get credit by ID
- GET /api/credits/client/{clientId} - Get credits by client ID
- GET /api/credits/statut/{statut} - Get credits by status
- POST /api/credits/personnel - Create a new personal credit
- POST /api/credits/immobilier - Create a new real estate credit
- POST /api/credits/professionnel - Create a new professional credit
- PATCH /api/credits/{id}/statut - Update credit status
- DELETE /api/credits/{id} - Delete a credit

### Remboursements
- GET /api/remboursements - Get all remboursements
- GET /api/remboursements/{id} - Get remboursement by ID
- GET /api/remboursements/credit/{creditId} - Get remboursements by credit ID
- GET /api/remboursements/type/{type} - Get remboursements by type
- GET /api/remboursements/date-range - Get remboursements by date range
- POST /api/remboursements - Create a new remboursement
- PUT /api/remboursements/{id} - Update an existing remboursement
- DELETE /api/remboursements/{id} - Delete a remboursement

## Security

The application uses Spring Security with JWT for authentication and authorization. The following roles are defined:
- ROLE_CLIENT - Regular client users
- ROLE_EMPLOYE - Bank employees
- ROLE_ADMIN - Administrators with full access

## License

This project is licensed under the MIT License.
