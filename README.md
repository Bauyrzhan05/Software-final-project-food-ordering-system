SoftFinalProject – Online Food Ordering System (Backend)

SoftFinalProject is the backend part of the online food ordering system. The project is based on Spring Boot and works through the REST API.

Functional:
User management 
Food
Categories
Additional products(Extra)
Orders
Roles and permissions
Security (Spring Security)

🛠️ Technologies Used:
Java 17
Spring Boot
Spring Data JPA
Spring Security
Flyway (Database Migration)
PostgreSQL
MapStruct (DTO ↔ Entity mapping)
JUnit 5 / Mockito (Testing)
Gradle

📂Project Architecture:
controller  →  service  →  repository  →  database
Layers Description
config: SecurityConfig - Spring Security Configuration
controller:
REST API endpoints
UserController
FoodController
CategoryController
ExtraController
OrderController
dto: DTO models carried by the API
Database entity classes: User, Food, Category, Extra, Order, Permission
mapper: DTO to Entity conversion (MapStruct)
repository: JPA Repository interfaces
Database access layer
service: Business logic Logic between Controller and Repository
resources: application.properties - Flyway migrations
test: Service layer tests (@SpringBootTest)

🗄️ Database & Migrations (Flyway)
src/main/resources/db.migration/
 ├── V1__creates_tables.sql
 └── V2__insert_initial_data.sql








