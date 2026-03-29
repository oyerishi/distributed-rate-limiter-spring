# Distributed Rate Limiter 🛡️ (High Performance Edition)

A production-grade backend service built with **Spring Boot 3.x** and **Redis** to control API traffic flow and prevent server overload using **Atomic Lua Scripting**.

## 📖 Overview
This project implements a **Distributed Token Bucket Algorithm**. While Version 1.0 used PostgreSQL, this version (v2.0) utilizes **Redis In-Memory Storage** to achieve sub-millisecond latency. By offloading the rate-limiting logic to **Server-Side Lua scripts**, we ensure 100% atomicity and prevent race conditions in high-concurrency environments.

## 🛠️ Tech Stack
* **Language:** Java 25 (Latest)
* **Framework:** Spring Boot 4.x
* **In-Memory Store:** Redis (Performance & Synchronization)
* **Scripting:** Lua (Atomic Business Logic)
* **Build Tool:** Maven
* **Containerization:** Docker (Redis Stack)

## ✨ Key Features
- **In-Memory Performance:** Redis-backed state tracking for <2ms latency per request.
- **Lua Scripting (Atomicity):** Prevents the "Check-then-Act" race condition by executing the entire token bucket logic inside Redis.
- **Dynamic Configuration:** Adjust `capacity` and `refill-rate` in `application.properties` without recompiling.
- **Spring Interceptor Integration:** Guards endpoints globally or via custom annotations.
- **Clean Architecture:** Uses Constructor Injection and Lombok for maintainable, testable code.

## 🚀 How to Run
1. **Spin up Redis via Docker:**
   `docker run -d --name redis-limiter -p 6379:6379 redis`
2. **Configure Environment:**
   Update `src/main/resources/application.properties` with your Redis host and desired limits.
3. **Build & Run:**
   `mvn spring-boot:run`
4. **Test the Endpoint:**
   `GET http://localhost:8080/api/v1/test`