# Arsenal FC: History of a Gooner (Java Web Quest)

A web-based interactive trivia game dedicated to the history of **Arsenal FC**.
Built with **Java 23**, this application implements the classic **MVC (Model-View-Controller)** architectural pattern, runs on a Tomcat Servlet container, and is fully containerized using **Docker** for cloud deployment.

**Live Demo:** [Play the game on Render](https://java-text-quest.onrender.com)

## Features

* **Arsenal Trivia:** 13 deeply researched questions about the history of Arsenal, from Dial Square to the Invincibles.
* **Bilingual Localization (i18n):** The UI seamlessly switches between English and Ukrainian (`.properties` files).
* **Modern UI/UX:** Responsive design with smooth CSS animations, custom fonts, and an immersive Emirates Stadium background.
* **Session Management:** Tracks player's nickname, progress, and determines win/loss states.
* **Data-Driven Logic:** Questions and answers are dynamically parsed from JSON.
* **Dockerized:** Fully configured multi-stage `Dockerfile` for easy deployment and scalability.
* **CI/CD Pipeline:** Automated deployment to Render linked directly to the GitHub repository.

## Tech Stack

* **Core:** Java JDK **23**
* **Build Tool:** Maven
* **Web Layer:** Jakarta Servlet API 5.0, JSP, JSTL
* **Architecture:** MVC (Model-View-Controller)
* **Data Processing:** Jackson (JSON parsing)
* **Utilities:** Project Lombok (reduces boilerplate)
* **Logging:** Log4j2
* **DevOps & Cloud:** Docker, Render (PaaS)

## Architecture

The application strictly separates concerns into defined layers:

1.  **Controller Layer (`com.javarush.controller`):**
    * `InitServlet`: Handles user registration, locale switching, and session initialization.
    * `QuestServlet`: Manages game flow, processes answers, and handles navigation.
2.  **Service Layer (`com.javarush.service`):**
    * `GameService`: Contains game rules, validation logic, and statistics.
    * `QuestService`: Acts as a bridge between the controller and the JSON data layer.
3.  **Model Layer (`com.javarush.model`):**
    * `Question`, `Answer`: POJOs representing the trivia data.
    * `QuestionRepository`: Manages data storage and parsing.
4.  **View Layer (`webapp/jsp`):**
    * Dynamic JSP pages utilizing JSTL and Expression Language (EL).

## Testing

The project maintains code quality with Unit Tests:

* **Frameworks:** JUnit 5 (Jupiter) & Mockito **5.14.2**.
* **Coverage:**
    * **Services:** Isolated business logic testing.
    * **Controllers:** Servlets tested using `Mockito` to mock `HttpServletRequest`, `HttpServletResponse`, and `HttpSession`.

## How to Run Locally

### Option 1: Using Docker (Recommended)
1. Ensure Docker is installed and running.
2. Build the image:
   ```bash
   docker build -t arsenal-quest .