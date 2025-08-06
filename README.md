# 🍽️ Cravory - Food Delivery Platform

Cravory is a full-stack **Spring Boot** application designed to streamline the online food ordering and delivery experience. It provides APIs for managing restaurants, menu categories, items, users, and authentication. It can be used as the backend for a food delivery mobile or web app.

---

## 🚀 Features

- 🔐 JWT-based Authentication & Authorization
- 🍽️ Restaurant, Category, and Menu Item Management
- ❤️ Favorite Restaurants for Users
- 🛒 Cart Functionality (Add, Remove, View)
- 📦 Order Placement and Order History
- 🧑‍🍳 Role-based Access: Admin, Restaurant Owner, User
- 🌐 RESTful APIs with JSON Responses
- 📁 Modular Project Structure

---

## 🏗️ Tech Stack

| Layer          | Technology                        |
|----------------|------------------------------------|
| Backend        | Spring Boot, Java 17              |
| Security       | Spring Security, JWT              |
| Build Tool     | Maven                             |
| Database       | (Assumed) MySQL / PostgreSQL       |
| API Handling   | Spring MVC (REST)                 |

---





---
---

## ⚙️ Getting Started

Follow these steps to set up and run the project locally:

### 📥 Clone the Repository

```bash
git clone https://github.com/Harshgupta88156/Cravory.git
cd Cravory

Configure the Database

spring.datasource.url=jdbc:mysql://localhost:3306/cravory
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update


Run the App
./mvnw spring-boot:run

The application will be accessible at:
📍 http://localhost:8080
