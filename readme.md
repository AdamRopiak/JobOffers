# Job Offers Application

A backend service built with **Java 17 / Spring Boot 3.x** designed for managing job postings and user authentication via **JWT tokens**. The system leverages **MongoDB** for data persistence and **Redis** as an in-memory caching layer to optimize query performance.

---

## 🛠️ Tech Stack

* **Java 17** & **Spring Boot 3.1
* **Spring Security** (JWT Authentication)
* **Spring Data MongoDB** (Persistence)
* **Spring Data Redis / Lettuce** (Caching)
* **Docker & Docker Compose v2** (Containerization)
* **Maven** (Dependency Management)
* **JUnit 5 / MockMvc / AssertJ** (Testing)

---

## 🚀 Key Features & Architecture

1. **Authentication & Authorization:**
   * `/register` – User registration with password encryption using `BCryptPasswordEncoder`.
   * `/token` – User login and JWT generation.
   * Endpoint protection configured via `SecurityFilterChain`.

2. **Caching & Performance:**
   * Query caching implemented with **Redis** (using the Lettuce client).
   * Conditional cache enablement via Spring properties (`spring.cache.type=redis`).

3. **Profile-Based Configuration:**
   * `local` – For local development with IDE connecting to Dockerized services on `localhost`.
   * `docker` – For running the entire stack within a unified Docker network.
   * `integration` – Configured for automated integration tests.

---

## 📋 Prerequisites

Ensure you have the following installed before running the project:

* **JDK 17** or higher
* **Maven 3.8+**
* **Docker** & **Docker Compose (v2)**

---

## ⚙️ Environment Variables (`.env`)

The application requires specific environment variables for sensitive configuration. Create a `.env` file in the root directory based on this template:

```env
# JWT Security
JWT_SECRET=your_super_secret_jwt_key_that_is_at_least_32_bytes_long!

# MongoDB Configuration
MONGO_ROOT_USER=root
MONGO_ROOT_PASSWORD=example
MONGO_DB_NAME=job-offers

# Active Profile
SPRING_PROFILES_ACTIVE=local

