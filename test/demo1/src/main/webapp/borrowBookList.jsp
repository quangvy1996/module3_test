<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Borrow Book Statistics</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center">Borrow Book Statistics</h2>

    <form action="borrowBookStats" method="GET" class="mb-4">
        <div class="row">
            <div class="col-md-4 mb-3">
                <label for="bookName" class="form-label">Book Name</label>
                <input type="text" class="form-control" id="bookName" name="bookName">
            </div>
            <div class="col-md-4 mb-3">
                <label for="studentName" class="form-label">Student Name</label>
                <input type="text" class="form-control" id="studentName" name="studentName">
            </div>
            <div class="col-md-4 mb-3">
                <button type="submit" class="btn btn-primary mt-4">Search</button>
            </div>
        </div>
    </form>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>Borrow ID</th>
            <th>Book Name</th>
            <th>Author</th>
            <th>Student Name</th>
            <th>Class</th>
            <th>Borrow Date</th>
            <th>Return Date</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="stats" items="${borrowBookStats}">
            <tr>
                <td><c:out value="${stats.borrowId}"/></td>
                <td><c:out value="${stats.bookName}"/></td>
                <td><c:out value="${stats.author}"/></td>
                <td><c:out value="${stats.studentFullName}"/></td>
                <td><c:out value="${stats.studentClass}"/></td>
                <td><c:out value="${stats.borrowDate}"/></td>
                <td><c:out value="${stats.returnDate}"/></td>
                <td>
                    <form action="returnBook" method="POST">
                        <input type="hidden" name="borrowId" value="${stats.borrowId}">
                        <button type="submit" class="btn btn-success">Return</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty borrowBookStats}">
            <tr>
                <td colspan="8" class="text-center">No records found.</td>
            </tr>
        </c:if>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
