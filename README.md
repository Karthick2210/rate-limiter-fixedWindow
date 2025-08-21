Rate Limiter Assignment

--Overview
This project implements a Fixed Window Rate Limiter using Spring Boot and Redis.
The rate limiter restricts the number of requests a user/client/IP can make within a specific time window.
It exposes a REST API that allows checking whether a request is allowed or blocked based on defined rules.

--Features

Fixed Window algorithm with Redis.

--Rules:

Limit by User ID, Client ID, or IP.

Custom API path, request limit, and time window.

--Returns detailed response:

allowed → whether request is permitted.

remaining → how many requests are left in the window.

resetMs → time in ms until counter resets.

reason → explanation string.

--Technologies Used:

Java 17

Spring Boot

Spring Data Redis

Redis (for storing counters)


--URL:

GET http://localhost:8080/ratelimit/check


--Example Request:

GET http://localhost:8080/ratelimit/check?api=/api/hello&method=GET&userId=123&clientId=abc&ip=127.0.0.1


--Example Response (Allowed):

{
  "allowed": true,
  "remaining": 4,
  "resetMs": 59997,
  "reason": "Allowed"
}


--Example Response (Blocked):

{
  "allowed": false,
  "remaining": 0,
  "resetMs": 23000,
  "reason": "Rate limit exceeded"
}

--How to Run the project:

Start Redis locally:

redis-server

Run the Spring Boot application:

mvn spring-boot:run

Open Postman and test with the above URL.

 Future Improvements

Support additional algorithms: Sliding Window, Token Bucket.

Add Resilience4j CircuitBreaker for fault tolerance.

Expose metrics via Spring Boot Actuator.
