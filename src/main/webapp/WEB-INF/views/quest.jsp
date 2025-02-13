<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quest Game</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/quest.css" rel="stylesheet">
</head>
<body class="bg-light d-flex flex-column min-vh-100"
      style="background-image: url('${pageContext.request.contextPath}/images/${question.imageLink}'); background-size: cover; background-position: center;">

<div class="container flex-grow-1 d-flex justify-content-center align-items-center">
    <div class="p-5 shadow-lg rounded w-100 p-translucent" style="max-width: 800px;">
    <h1 class="question-text display-1"><strong>${question.text}</strong></h1>
    <c:if test="${reasonIfKnightDies != null}">
        <h2 class="reason-text">${reasonIfKnightDies}</h2>
    </c:if>

    <form action="${pageContext.request.contextPath}/quest" method="post">
        <ul class="list-group">
            <c:forEach var="answer" items="${question.answers}">
                <li class="list-group-item d-flex align-items-center">
                    <input type="radio" id="answer${answer.id}" name="selectedAnswer" value="${answer.id}" class="me-2"
                           required>
                    <label for="answer${answer.id}">${answer.text}</label>
                    <input type="hidden" name="questionId" value="${question.id}">
                    <input type="hidden" name="nextQuestionId" value="${answer.nextQuestionId}">
                    <input type="hidden" name="reasonIfKnightDies" value="${answer.reasonIfKnightDies}">
                </li>
            </c:forEach>
        </ul>

        <button class="btn w-100 mt-4 ${question.id == -2 ? 'btn-danger' : question.id == -1 ? 'btn-success' : 'btn-dark'}"
                type="submit">
            <c:choose>
                <c:when test="${question.id == -2}">End quest</c:when>
                <c:when test="${question.id == -1}">You won!</c:when>
                <c:otherwise>Next step</c:otherwise>
            </c:choose>
        </button>

        <div class="text-center mt-3">
            <c:choose>
                <c:when test="${question.id == 1 || question.id == 2}">
                    <img src="${pageContext.request.contextPath}/images/knight.jpg" class="question-image" alt="Knight"
                         style="width: 50px; height: 50px;">
                </c:when>
                <c:when test="${question.id == 3}">
                    <img src="${pageContext.request.contextPath}/images/knight.jpg" class="question-image" alt="Knight"
                         style="width: 50px; height: 50px;">
                    <img src="${pageContext.request.contextPath}/images/magic-sword.jpg" class="question-image"
                         alt="Magic Sword" style="width: 50px; height: 50px;">
                </c:when>
                <c:when test="${question.id == -1}">
                    <img src="${pageContext.request.contextPath}/images/shield.jpg" class="question-image"
                         alt="Magic Sword" style="width: 50px; height: 50px;">
                    <img src="${pageContext.request.contextPath}/images/knight.jpg" class="question-image" alt="Knight"
                         style="width: 50px; height: 50px;">
                    <img src="${pageContext.request.contextPath}/images/magic-sword.jpg" class="question-image"
                         alt="Magic Sword" style="width: 50px; height: 50px;">
                    <img src="${pageContext.request.contextPath}/images/princess.jpg" class="question-image"
                         alt="Princess" style="width: 50px; height: 50px;">
                </c:when>
                <c:when test="${question.id == -2}">
                    <img src="${pageContext.request.contextPath}/images/knight-remains.jpg" class="question-image"
                         alt="Knight Remains" style="width: 50px; height: 50px;">
                </c:when>
            </c:choose>
        </div>
    </form>
</div>
</div>

<footer class="text-center text-white bg-dark py-3 mt-auto">
    <div>
        <h5>Player: ${username}</h5>
        <p>&copy; 2025 Text Quest by Yahor Harmilin | Session ID: ${sessionId} | Created at: ${sessionCreateTime}</p>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>