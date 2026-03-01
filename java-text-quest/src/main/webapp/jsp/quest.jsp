<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'uk'}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="game.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css">
</head>
<body>

<div class="lang-switch">
    <a href="${pageContext.request.contextPath}/lang?lang=uk" class="${sessionScope.language == 'uk' || empty sessionScope.language ? 'active' : ''}">UA</a> |
    <a href="${pageContext.request.contextPath}/lang?lang=en" class="${sessionScope.language == 'en' ? 'active' : ''}">EN</a>
</div>

<div class="card">
    <div class="header">
        <h1><fmt:message key="game.title"/></h1>
        <div class="user-info">
            <fmt:message key="game.player"/>: <b><c:out value="${sessionScope.nickname}"/></b> |
            <fmt:message key="game.matches"/>: <b><c:out value="${sessionScope.gameCount}"/></b>
        </div>
    </div>

    <div class="content">
        <div class="question-text">
            <c:out value="${sessionScope.language == 'en' ? question.textEn : question.textUk}"/>
        </div>

        <c:if test="${question.gameState == null}">
            <form action="${pageContext.request.contextPath}/game" method="post">
                <c:forEach items="${question.answers}" var="answer">
                    <button type="submit" name="nextQuestionId" value="${answer.nextQuestionId}" class="btn btn-answer">
                        <c:out value="${sessionScope.language == 'en' ? answer.textEn : answer.textUk}"/>
                    </button>
                </c:forEach>
            </form>
        </c:if>

        <c:if test="${question.gameState != null}">
            <h2 class="game-status ${question.gameState == 'WIN' ? 'status-win' : 'status-loss'}">
                <c:if test="${question.gameState == 'WIN'}"><fmt:message key="game.win"/></c:if>
                <c:if test="${question.gameState == 'LOSS'}"><fmt:message key="game.loss"/></c:if>
            </h2>
            <form action="${pageContext.request.contextPath}/start" method="post">
                <button type="submit" class="btn btn-restart"><fmt:message key="game.restart"/></button>
            </form>
        </c:if>
    </div>
</div>

</body>
</html>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const forms = document.querySelectorAll("form");
        forms.forEach(form => {
            form.addEventListener("submit", function(e) {
                e.preventDefault();
                const submitter = e.submitter;
                if (submitter && submitter.name) {
                    const hiddenInput = document.createElement("input");
                    hiddenInput.type = "hidden";
                    hiddenInput.name = submitter.name;
                    hiddenInput.value = submitter.value;
                    form.appendChild(hiddenInput);
                }
                const card = document.querySelector(".card");
                card.classList.add("fade-out-slow");
                setTimeout(() => {
                    form.submit();
                }, 500);
            });
        });
    });
</script>