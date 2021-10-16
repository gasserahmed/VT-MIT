<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JSP Page</title>
</head>
<body>
<h1>Database content</h1>
<sql:query var="result" dataSource="jdbc/GasserBookstore">
  SELECT * FROM category, book
  WHERE category.category_id = book.category_id
</sql:query>
<table border="1">
  <!-- column headers -->
  <tr>
    <c:forEach var="columnName" items="${result.columnNames}">
      <th><c:out value="${columnName}"/></th>
    </c:forEach>
  </tr>
  <!-- column data -->
  <c:forEach var="row" items="${result.rowsByIndex}">
    <tr>
      <c:forEach var="column" items="${row}">
        <td><c:out value="${column}"/></td>
      </c:forEach>
    </tr>
  </c:forEach>
</table>
</body>
</html>