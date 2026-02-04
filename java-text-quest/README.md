# Java Text Quest

A web-based interactive text adventure game built with **Java 23**.
The application implements the classic **MVC (Model-View-Controller)** architectural pattern and runs on a Servlet container (Tomcat).

## Features

* **Interactive Story:** The plot evolves based on the user's choices.
* **Session Management:** Keeps track of the user's progress, name, and game count.
* **Data-Driven:** Questions and answers are loaded dynamically from JSON files (or repository).
* **Validation:** Input validation for nicknames.
* **Logging:** Comprehensive logging of game events and errors using Log4j2.
* **Win/Loss Logic:** Handles different game endings based on decisions.

## Tech Stack

The project utilizes modern Java ecosystem tools:

* **Core:** Java JDK **23**
* **Build Tool:** Maven
* **Web Layer:** Jakarta Servlet API 5.0, JSP, JSTL
* **Architecture:** MVC (Model-View-Controller)
* **Data Processing:** Jackson (JSON parsing)
* **Utilities:** Project Lombok (to reduce boilerplate code)
* **Logging:** Log4j2

## Architecture

The application is structured into distinct layers to separate concerns:

1.  **Controller Layer (`com.javarush.controller`):**
    * `InitServlet`: Handles user registration and session initialization.
    * `QuestServlet`: Manages the game flow, processes user answers, and handles navigation.
2.  **Service Layer (`com.javarush.service`):**
    * `GameService`: Contains game rules, validation logic, and statistics calculation.
    * `QuestService`: Acts as a bridge between the controller and the data layer.
3.  **Model Layer (`com.javarush.model`):**
    * `Question`, `Answer`: POJOs representing the game data.
    * `QuestionRepository`: Manages data storage and retrieval.
4.  **View Layer (`webapp/jsp`):**
    * JSP pages with JSTL for rendering dynamic content to the user.

## Testing

The project maintains high code quality with Unit Tests:

* **Frameworks:** JUnit 5 (Jupiter) & Mockito **5.14.2** (fully compatible with Java 23).
* **Coverage:**
    * **Services:** Business logic is tested in isolation.
    * **Controllers:** Servlets are tested using `Mockito` to mock `HttpServletRequest`, `HttpServletResponse`, and `HttpSession`.
    * **Repository:** Data integrity checks.

## How to Run

1.  **Prerequisites:**
    * Java 23
    * Maven
    * Tomcat 10+ (supporting Jakarta EE 9/10)

2.  **Build the project:**
    ```bash
    mvn clean package
    ```

3.  **Deploy:**
    * Deploy the resulting `.war` file to your Tomcat server.
    * Or use the Smart Tomcat plugin in IntelliJ IDEA.

4.  **Access:**
    Open your browser and navigate to: `http://localhost:8080/java-text-quest`

---
*Author: [Vitaly Kharchenko]*