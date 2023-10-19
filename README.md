# GeoByte Inc - Delivery Management System

GeoByte Inc is a comprehensive software solution that enables users to manage delivery routes, set up locations, and calculate optimal routes for delivering packages from one location to another. This system is accessible through web browsers, making it convenient for users to plan and optimize their delivery operations.

## Technologies Used

- Backend: Spring Framework
- Frontend: Angular 16.2.5
- Database: H2 (embedded database)

## Setup Instructions

Follow these steps to set up the GeoByte Inc software on your local environment:

### Prerequisites

Before you begin, ensure you have the following prerequisites installed:

- Java Development Kit (JDK)
- Node.js and npm
- Angular CLI
- Git

### Backend Setup

1. Clone the GeoByte Inc repository from GitHub: git clone https://github.com/Okorojeremiah/geobyte-inc
2. Open the backend project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).

3. Build and run the backend application. The Spring Boot application should start at http://localhost:8080.

### Frontend Setup

1. Install the required frontend dependencies: npm install
2. Start the Angular development server: ng serve
3. The frontend application should be accessible at http://localhost:4200.

### Database
The GeoByte Inc system uses the H2 embedded database by default. You can access the H2 console at http://localhost:8080/h2-console. Use the following database settings:

JDBC URL: jdbc:h2:mem:geobyte_inc
User Name: sa
Password: [Leave it blank]


### Usage
Launch the GeoByte Inc application by visiting the frontend URL (http://localhost:4200).

Set up locations by using the Location Management features.

Select an origin and destination location for your delivery route.

Calculate the best route and cost for delivering packages from the origin to the destination.

Manage and optimize your delivery operations seamlessly.

### Feedback and Contributions
We welcome your feedback and contributions to the GeoByte Inc project. If you encounter any issues or have suggestions for improvement, please submit them to our GitHub repository.

Thank you for using GeoByte Inc to streamline your delivery management.


