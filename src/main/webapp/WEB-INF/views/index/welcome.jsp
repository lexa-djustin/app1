<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>

</head>
<body>
<div>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form th:action="@{/logout}" method="post">
            <input type="submit" value="Log out" />
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name}</h2>
    </c:if>
</div>
</body>
</html>
