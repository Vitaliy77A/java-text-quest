<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>JavaRush Quest</title>
    <style>
        body { font-family: sans-serif; text-align: center; margin-top: 50px; background-color: #f4f4f9; }
        .container { background: white; padding: 30px; border-radius: 10px; display: inline-block; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        h1 { color: #333; }
        .btn {
            display: block;
            width: 100%;
            margin: 10px 0;
            padding: 15px;
            font-size: 18px;
            cursor: pointer;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
        }
        .btn:hover { background-color: #45a049; }
        .restart { background-color: #f44336; }
        .restart:hover { background-color: #d32f2f; }
    </style>
</head>
<body>

<div class="container">
    <h1><c:out value="${question.text}"/></h1>

    <c:if test="${question.gameState == null}">
        <form action="quest" method="post">
            <c:forEach items="${question.answers}" var="answer">
                <button type="submit" name="nextQuestionId" value="${answer.nextQuestionId}" class="btn">
                    ${answer.text}
                </button>
            </c:forEach>
        </form>
    </c:if>

    <c:if test="${question.gameState != null}">
        <h2 style="color: ${question.gameState == 'WIN' ? 'green' : 'red'}">
            <c:if test="${question.gameState == 'WIN'}">ПЕРЕМОГА!</c:if>
            <c:if test="${question.gameState == 'LOSS'}">ПОРАЗКА!</c:if>
        </h2>

        <form action="quest" method="post">
             <button type="submit" class="btn restart">ПОЧАТИ СПОЧАТКУ</button>
        </form>
    </c:if>
</div>

</body>
</html>