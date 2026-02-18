E-Commerce Backend API

RESTful API for E-Commerce system built with Spring Boot.
Includes JWT Authentication, Role-based Authorization, Cart & Order Workflow.

## 🚀 Tech Stack

- Java 17
- Spring Boot 3
- Spring Security (JWT)
- Spring Data JPA
- MySQL
- Docker
- Maven

- ## 📦 Architecture

Layered Architecture:

Controller → Service → Repository → Database

Packages:
- controller
- service
- repository
- entity
- dto
- security

- ## 🗄 Database Design

Main Tables:
- users
- products
- categories
- carts
- cart_items
- orders
- order_items

Relationships:
- 1 User → 1 Cart
- 1 User → Many Orders
- 1 Category → Many Products
- 1 Order → Many OrderItems

- ## ✅ Features

### Authentication
- Register
- Login (JWT)
- Role-based access (USER / ADMIN)

### Product
- Create / Update / Delete (Admin)
- Get all products
- Filter by category

### Cart
- Add to cart
- Update quantity
- Remove item

### Order
- Checkout
- Order history
- Change order status (Admin)

- ## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/auth/register | Register user |
| POST | /api/auth/login | Login |
| GET | /api/products | Get products |
| POST | /api/cart | Add to cart |
| POST | /api/orders/checkout | Checkout |

## ⚙️ How to Run

### 1. Clone project

git clone https://github.com/yourusername/ecommerce-api.git

### 2. Configure database

Update application.yml:

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=yourpassword

### 3. Run application

mvn spring-boot:run

## 🐳 Run with Docker

docker build -t ecommerce-api .

docker run -p 8080:8080 ecommerce-api

## 🔒 Security

- Password encrypted using BCrypt
- JWT-based authentication
- Role-based authorization
- Stateless session

- ## 🎯 Purpose

This project was built as a portfolio project to demonstrate backend development skills including:
- Clean Architecture
- Secure Authentication
- Order Workflow Management
- Docker Deployment
