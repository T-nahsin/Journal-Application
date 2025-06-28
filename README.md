# 📝 Journal Application

A secure and modular Spring Boot-based backend application that allows users to create, read, update, and delete journal entries with JWT-based authentication and role-based access control.

## 🚀 Features

- ✅ User Registration & Login
- 🔐 JWT Authentication (Access Token)
- 👤 Role-Based Authorization (`USER`, `ADMIN`)
- 📓 Create, Read, Update, Delete (CRUD) for Journals
- 🧪 Unit Testing with JUnit & Mockito
- 🌐 MongoDB Atlas Integration
- ⚙️ Clean and modular project structure

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.4.6**
- **Spring Security 6**
- **MongoDB Atlas**
- **JWT (JJWT 0.11.5)**
- **JUnit & Mockito**

---

## 📁 Project Structure

src/
├── config/ # Spring Security configuration
├── controller/ # REST Controllers
├── dto/ # DTOs for requests and responses
├── exception/ # Custom exception handling
├── filter/ # JWT Filter
├── model/ # MongoDB entity models
├── repository/ # MongoDB repositories
├── service/ # Business logic and user details service
├── utils/ # JWT utilities
└── JournalApplication.java # Main application entry point

yaml
Copy
Edit

---

## 🔐 Authentication Flow (JWT)

1. User logs in using `/public/login` and receives a JWT.
2. JWT is sent in `Authorization` header as `Bearer <token>`.
3. Filter verifies the token, and Spring Security sets up the context.
4. Based on role (`USER`, `ADMIN`), endpoints are accessed.

---

## 📦 API Endpoints

### 🔓 Public Routes
- `POST /public/signup` — Register a new user
- `POST /public/login` — Authenticate and get JWT

### 🔐 Authenticated Routes
- `GET /journal` — Get all journals (for user)
- `POST /journal` — Add a new journal
- `PUT /journal/id/{id}` — Update a journal
- `DELETE /journal/id/{id}` — Delete a journal

### 🛡️ Admin Routes
- `GET /admin/all-users` — List all registered users
- `DELETE /admin/delete-user/{id}` — Delete a user by ID
- `POST  /admin` — create new admin

---

## 🧪 Running the App Locally

### 📌 Prerequisites
- Java 21
- Maven
- MongoDB Atlas (or local instance)

### ⚙️ Configuration

Update the following in `application.properties` or `application.yml`:
```yaml
spring:
  data:
    mongodb:
      uri: mongodb+srv://<username>:<password>@cluster0.mongodb.net/journaldb
▶️ Run with Maven
bash
Copy
Edit
./mvnw spring-boot:run
🧪 Testing
Run unit tests using:

bash
Copy
Edit
./mvnw test
📸 Screenshots
Auth with JWT	Access Denied (No Token)

🙋‍♂️ Author
Nishant Singh