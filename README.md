# Distributed Rate Limiter 🛡️

A high-performance backend service built with **Spring Boot** and **PostgreSQL** to control API traffic flow and prevent server overload.

## 📖 Overview
This project implements a **Distributed Token Bucket Algorithm**. Unlike a local rate limiter, this version synchronizes request limits across multiple application instances using a shared database, making it ideal for microservices and scaled environments.

## 🛠️ Tech Stack
* **Language:** Java 17+
* **Framework:** Spring Boot 3.x
* **Database:** PostgreSQL (Persistence & Synchronization)
* **API Testing:** Postman / cURL
* **Build Tool:** Maven

## ✨ Key Features
- **Distributed State:** Centralized rate-limit tracking in PostgreSQL.
- **Token Bucket Algorithm:** Allows for bursts of traffic while maintaining a strict average rate.
- **Atomic Updates:** (Work in Progress) High-concurrency SQL logic to prevent race conditions.
- **Spring Interceptor:** Seamlessly plugs into existing REST controllers.

## 🚀 How to Run
1. Clone the repository:
   `git clone https://github.com/YOUR_USERNAME/distributed-rate-limiter.git`
2. Create a PostgreSQL database named `ratelimiter_db`.
3. Update `src/main/resources/application.properties` with your DB credentials.
4. Run the app: `mvn spring-boot:run`