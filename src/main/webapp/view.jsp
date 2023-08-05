<%@page import="com.js.dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
     <h1>PRODUCT TABLE</h1>
     <table border="10px">
      <tr>
         <th>ID</th>
         <th>NAME</th>
         <th>BRAND</th>
         <th>PRICE</th>
         <th>QUANTITY</th>
         <th>DELETE</th>
         <th>UPDATE</th>
      </tr>
     
     <% ArrayList<Product> al = (ArrayList)request.getAttribute("data"); %>
     
     <% for(Product p:al) { %>
         <tr>
             <td><%= p.getId() %></td>
             <td><%= p.getName() %></td>
             <td><%= p.getBrand() %></td>
             <td><%= p.getPrice() %></td>
             <td><%= p.getQuantity() %></td>
             <td><a href="delete?id=<%= p.getId()%>">delete</a></td>
              <td><a href="edit?id=<%= p.getId()%>">edit</a></td>
              
             
         </tr>
     
     <% } %>
     </table>
</body>
</html>