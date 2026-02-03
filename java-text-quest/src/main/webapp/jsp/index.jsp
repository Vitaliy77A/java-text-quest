<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JavaRush Quest - Start</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css">
</head>
<body>

<div class="card">
    <div class="header">
        <h1>ТРОХИ ІСТОРІЇ</h1>
    </div>
    <div class="content">
        <p>
            Вітаємо у текстовому квесті! <br>
            Ви прокидаєтеся на борту невідомого корабля.
            Пам'ять стерта, системи дають збій.
        </p>
        <p>Введіть своє ім'я:</p>

        <c:if test="${param.error != null}">
                    <div class="error-message">
                        Помилка: Ім'я не може бути пустим!
                    </div>
                </c:if>

        <form action="${pageContext.request.contextPath}/start" method="post">
            <input type="text" name="nickname" placeholder="Ваш нікнейм" required>
            <button type="submit" class="btn btn-start">ПОЧАТИ ГРУ</button>
        </form>
    </div>
</div>

</body>
</html>