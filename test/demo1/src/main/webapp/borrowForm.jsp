<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Borrow Book</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Mượn sách</h2>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <form action="books?action=borrowBook" method="POST">
        <div class="mb-3">
            <label for="borrowId" class="form-label">Tên sách (MS-XXXX)</label>
            <input type="text" class="form-control" id="borrowId" name="borrowId" placeholder="MS-XXXX" required>
        </div>

        <div class="mb-3">
            <label for="bookId" class="form-label">Sách</label>
            <select class="form-select" id="bookId" name="bookId" readonly>
                <c:forEach var="book" items="${books}">
                    <option value="${book.bookId}">${book.bookName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="studentId" class="form-label">Tên học sinh</label>
            <select class="form-select" id="studentId" name="studentId">
                <c:forEach var="student" items="${students}">
                    <option value="${student.studentId}">${student.fullName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="borrowDate" class="form-label">Ngày mượn sách</label>
            <input type="text" class="form-control" id="borrowDate" name="borrowDate" value="<%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()) %>" readonly>
        </div>

        <div class="mb-3">
            <label for="returnDate" class="form-label">Ngày trả sách (dd/MM/yyyy)</label>
            <input type="text" class="form-control" id="returnDate" name="returnDate" placeholder="dd/MM/yyyy" required>
        </div>

        <button type="submit" class="btn btn-primary">Mượn sách</button>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
