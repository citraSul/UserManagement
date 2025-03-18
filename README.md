# 🛠️ User Management Service

## 📌 Overview
User Management Service is a **Spring Boot-based REST API** that provides functionalities for managing users, including:
- **User Creation, Update, and Deletion**
- **Assigning Supervisors to Users**
- **Fetching Users by Supervisor**
- **Spring Security with OAuth2 (JWT-based authentication)**
- **PostgreSQL Database Integration**
- **Unit and Integration Testing with JUnit & Mockito**

---
## 🔗 API Endpoints

### **User Management APIs**
| HTTP Method | Endpoint | Description | Required Scope |
|------------|---------|------------|---------------|
| **POST**   | `/api/users` | Create a new user | `write` |
| **PUT**    | `/api/users/{userId}` | Update user details | `write` |
| **DELETE** | `/api/users/{userId}` | Delete a user | `write` |
| **GET**    | `/api/users/supervisor/{supervisorId}` | Get users by supervisor | `read` |
| **PATCH**  | `/api/users/{userId}/supervisor/{newSupervisorId}` | Update user’s supervisor | `write` |

### **Authentication & Token APIs**
| HTTP Method | Endpoint | Description |
|------------|---------|------------|
| **POST**   | `/oauth2/token` | Get OAuth2 access token |
| **GET**    | `/oauth2/jwks` | Retrieve JSON Web Key Set (JWKS) |

---

## 🚀 **Technologies Used**
- **Java 17**
- **Spring Boot 3.4+**
- **Spring Security (OAuth2, JWT)**
- **Spring Data JPA**
- **Hibernate ORM**
- **PostgreSQL Database**
- **Lombok**
- **HikariCP Connection Pool**
- **JUnit 5 & Mockito (Testing)**

---

## 🔧 **Project Setup**
### **1️⃣ Prerequisites**
Ensure you have the following installed:
- **Java 17**
- **Maven 3+**
- **PostgreSQL Database**
- **Postman (for API testing)**

