<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>JavaRush Quest</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css">
</head>
<body>

<div class="card">
    <div class="header">
        <h1>JavaRush Quest</h1>
        <div class="user-info">
            Гравець: <b><c:out value="${sessionScope.nickname}"/></b> |
            Зіграно ігор: <b><c:out value="${sessionScope.gameCount}"/></b>
        </div>
    </div>

    <div class="content">
        <div class="question-text">
            <c:out value="${question.text}"/>
        </div>

        <c:if test="${question.gameState == null}">
            <form action="${pageContext.request.contextPath}/game" method="post">
                <c:forEach items="${question.answers}" var="answer">
                    <button type="submit" name="nextQuestionId" value="${answer.nextQuestionId}" class="btn btn-answer">
                        ${answer.text}
                    </button>
                </c:forEach>
            </form>
        </c:if>

        <c:if test="${question.gameState != null}">
            <h2 style="color: ${question.gameState == 'WIN' ? 'green' : 'red'}">
                <c:if test="${question.gameState == 'WIN'}">МІСІЯ ВИКОНАНА!</c:if>
                <c:if test="${question.gameState == 'LOSS'}">МІСІЯ ПРОВАЛЕНА...</c:if>
            </h2>

            <form action="${pageContext.request.contextPath}/start" method="post">
                 <button type="submit" class="btn btn-restart">ПОЧАТИ ЗАНОВО</button>
            </form>
        </c:if>
    </div>
</div>

</body>
</html>