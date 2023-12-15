<%@ page import="java.util.ArrayList" %>
<%@ page import="pak.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Display Books</title>
<body>
<div align="center">
<h1>Список книг хранящихся в файле XML</h1>
   <% ArrayList<pak.Book> books = (ArrayList) request.getAttribute("book");%>
   <table border="1" width="400" height="220">
   <% for (pak.Book book : books) {%>
   <tr>
   <td><b>Author:</b><%= book.getAuthor() %></td>
   <td><b>Title:</b><%= book.getTitle() %></td>
   <td><b>Genre:</b><%= book.getGenre() %></td>
   <td><b>Price:</b><%= book.getPrice() %></td>
   </tr>
   <% } %>
   </table>
   </div>
</body>
</html>