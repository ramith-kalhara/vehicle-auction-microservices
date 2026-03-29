#  Vehicle Auction System – Microservices Architecture

##  Overview
This project is a **Microservices-based backend system** developed for a Vehicle Auction Platform.  
The system allows users to register, list vehicles, participate in auctions, place bids, and complete payments.

This project demonstrates key concepts of **Microservices Architecture**, including service separation, API Gateway routing, and independent deployment.

---

##  Objectives
- Implement a scalable backend using Microservices Architecture
- Demonstrate separation of concerns across services
- Use REST APIs for communication
- Integrate services using an API Gateway
- Provide Swagger documentation for each service

##  Microservices

### 1️. User Service
- Handles user registration and management
- CRUD operations for users

### 2️. Vehicle Service
- Manage vehicle listings
- Add, update, delete vehicles

### 3️. Auction Service
- Manage auction lifecycle (start/end)

### 4️. Bidding Service
- Handle bidding process
- Store and retrieve bids

### 5️. Payment Service
- Manage payments after auction

### 6️. Notification Service
- Send notifications (email/SMS simulation)

---

##  API Gateway
- Central entry point for all requests
- Routes requests to appropriate microservices
- Runs on **Port 8080**


---

##  Project Structure

vehicle-auction-microservices/
│
├── user-service/
├── vehicle-service/
├── auction-service/
├── bidding-service/
├── payment-service/
├── notification-service/
├── api-gateway/
└── README.md


---

##  Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Spring Cloud Gateway
- Swagger (OpenAPI)
- Maven / Gradle

---

##  Database Design

Each microservice uses its own database:

| Service            | Database Name     |
|------------------|------------------|
| User Service      | user_db          |
| Vehicle Service   | vehicle_db       |
| Auction Service   | auction_db       |
| Bidding Service   | bidding_db       |
| Payment Service   | payment_db       |
| Notification Service | notification_db |

---

##  How to Run the Project

### Step 1: Clone the Repository


---

##  Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Spring Cloud Gateway
- Swagger (OpenAPI)
- Maven / Gradle

---

##  Database Design

Each microservice uses its own database:

| Service            | Database Name     |
|------------------|------------------|
| User Service      | user_db          |
| Vehicle Service   | vehicle_db       |
| Auction Service   | auction_db       |
| Bidding Service   | bidding_db       |
| Payment Service   | payment_db       |
| Notification Service | notification_db |

---

##  How to Run the Project

### Step 1: Clone the Repository

---

### Step 2: Run Services
Start each service separately:
Repeat for all services.

---

### Step 3: Run API Gateway

---

### Step 4: Access APIs

| Service | URL |
|--------|-----|
| Gateway | http://localhost:8080 |
| User Service | http://localhost:8081 |
| Vehicle Service | http://localhost:8082 |

---

##  API Documentation

Swagger UI is available for each service:


---

##  Team Members

| Member Name | Microservice |
|------------|-------------|
| Member 1 | User Service |
| Member 2 | Vehicle Service |
| Member 3 | Auction Service |
| Member 4 | Bidding Service |
| Member 5 | Payment Service |
| Member 6 | Notification Service |

---

##  Key Features

- Microservices Architecture
- API Gateway Routing
- RESTful APIs
- Independent Service Deployment
- Swagger API Documentation

---

##  Conclusion

This project successfully demonstrates how a real-world application can be designed using Microservices Architecture, enabling scalability, maintainability, and modular development.

---

##  Future Improvements

- Add Authentication (JWT / OAuth)
- Implement Service Discovery (Eureka)
- Add Docker & Kubernetes deployment
- Integrate Message Queues (Kafka / RabbitMQ)

---

