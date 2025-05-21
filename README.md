🎓 LC Task – Student Management API

A Spring Boot RESTful API for managing students, courses, and groups, built with a clean architecture approach and layered domain logic. This project is part of a learning assignment for LeadConsult.
📂 Project Structure

    Domain Layer: Contains core business models (Student, Course, Group) and repository interfaces.

    Application Layer: Handles DTOs, services, and mapping logic.

    Infrastructure Layer: Manages REST controllers and API endpoints.

    Ports: Defines interfaces for repository access (StudentRepositoryPort, GroupRepositoryPort, CourseRepositoryPort).

    Mapper: Transforms between domain models and DTOs.

🚀 Features

    CRUD Operations: Create, read, update, and delete students.

    Search Functionality: Find students by course, group, or a combination of course and age.

    Validation: Ensures data integrity using Jakarta Bean Validation.

    Exception Handling: Custom exceptions for resource not found scenarios.

    Swagger Integration: Interactive API documentation via Swagger UI.

    Unit Testing: Comprehensive tests for both controller and service layers using JUnit and Mockito.

🧪 Testing
Controller Tests

    Valid Input: Ensures endpoints return expected results for valid requests.

    Invalid Input: Tests validation constraints and error responses.

    Exception Handling: Verifies proper handling of exceptions like ResourceNotFoundException.

Service Tests

    Business Logic: Validates core functionalities like updating student details.

    Edge Cases: Tests scenarios like non-existent course IDs during updates.

📦 Getting Started
Prerequisites

    Java 17 or higher

    Maven

Installation

    Clone the repository:

git clone https://github.com/SChu20/lc_task.git
cd lc_task

Build the project:

mvn clean install

Run the application:

    mvn spring-boot:run

📘 API Documentation

Once the application is running, access the Swagger UI at:

http://localhost:8080/swagger-ui/index.html

This interface provides detailed information about all available endpoints, request/response models, and allows for interactive testing.
🧾 Sample Endpoints

    GET /api/v1/students: Retrieve all students.

    GET /api/v1/students/{id}: Retrieve a student by ID.

    POST /api/v1/students: Create a new student.

    PUT /api/v1/students/{id}: Update an existing student.

    DELETE /api/v1/students/{id}: Delete a student.

    GET /api/v1/students/search/c/?courseId=1: Find students by course ID.

    GET /api/v1/students/search/g/?groupId=1: Find students by group ID.

    GET /api/v1/students/search/?courseId=1&age=20: Find students by course ID and age.

🧰 Technologies Used

    Spring Boot: Application framework.

    Spring Web: Building RESTful APIs.

    Spring Validation: Input validation.

    Lombok: Reducing boilerplate code.

    Jakarta Bean Validation: Ensuring data integrity.

    JUnit & Mockito: Testing frameworks.

    Springdoc OpenAPI: Swagger integration.

🧑‍💻 Author

    GitHub: SChu20

📄 License

This project is licensed under the MIT License.
