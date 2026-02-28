<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'uk'}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="start.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/my.css">
</head>
<body>

<div class="lang-switch">
    <a href="${pageContext.request.contextPath}/lang?lang=uk" class="${sessionScope.language == 'uk' || empty sessionScope.language ? 'active' : ''}">UA</a> |
    <a href="${pageContext.request.contextPath}/lang?lang=en" class="${sessionScope.language == 'en' ? 'active' : ''}">EN</a>
</div>

<div class="card">
    <div class="header">
        <h1><fmt:message key="start.title"/></h1>
    </div>

    <div class="content">
        <div class="story-box">
            <fmt:message key="start.welcome"/>
        </div>

        <p class="input-label"><fmt:message key="start.input.label"/></p>

        <c:if test="${param.error != null}">
            <div class="error-message">
                <fmt:message key="start.error"/>
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/start" method="post">
            <input type="text" name="nickname" placeholder="<fmt:message key='start.input.placeholder'/>" required>
            <button type="submit" class="btn btn-start"><fmt:message key="start.btn"/></button>
        </form>
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
                const card = document.querySelector(".card");
                card.classList.add("fade-out");
                setTimeout(() => {
                    form.submit();
                }, 200);
            });
        });
    });
</script>