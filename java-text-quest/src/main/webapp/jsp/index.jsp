<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Arsenal Quest - Start</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css">
</head>
<body>

<div class="card">
    <div class="header">
        <h1>ШЛЯХ КАНОНІРА</h1>
    </div>

    <div class="content">
        <div class="story-box">
            Ласкаво просимо на Емірейтс! <br>
            Твоя кар'єра в лондонському "Арсеналі" починається прямо зараз.
        </div>

        <p class="input-label">Введіть ім'я гравця:</p>

        <c:if test="${param.error != null}">
            <div class="error-message">
                Помилка: Ім'я не може бути пустим!
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/start" method="post">
            <input type="text" name="nickname" placeholder="Ваш нікнейм" required>
            <button type="submit" class="btn btn-start">ВИЙТИ НА ПОЛЕ</button>
        </form>
    </div>
</div>

</body>
</html>