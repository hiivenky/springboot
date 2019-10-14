<%@page import="com.cg.onlinewalletwithspringboot.dto.WalletUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
#transaction {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#transaction td, #customers th {
  border: 1px solid #ddd;
  padding: 8px;
}

#transaction tr:nth-child(even){background-color: #f2f2f2;}

#transaction tr:hover {background-color: #ddd;}

#transaction th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #f08024;
  color: white;
}
body{
background-size: cover;

}

</style>
</head>
<body background="<c:url value= "/resources/images/trans.jpg"/>">
<!-- 
	Author:Utkarsh
	Description: This page gives the details of each transaction done by the user from one date to another in the form of table. 
	CreatedDate:8/10/2019
	LastModified:9/10/2019
	 -->
<% 
WalletUser user=(WalletUser)request.getAttribute("user");
session.setAttribute("user",user);
System.out.println(user.getPhoneNo());
Cookie cookies[] = request.getCookies() ;
boolean flag=false;
   for(Cookie c : cookies){
	   if(c.getName().equals("status")){
		   System.out.println(c.getValue());
		  if(c.getValue().equals("loggedin")){
			  flag=true;
			  break;
		  }
	   }
   }
   if(!flag){
	   System.out.println("hii");
	   response.sendRedirect("login");
   }


%>
<table cellspacing="10" id ="transaction">
<tr>
<th>transaction Id</th>
<th>transaction Date</th>
<th>transaction amount</th>
<th>transaction Description</th>
<th>transaction balance</th>
</tr>
<a:forEach var="pro" items="${transactions}">
<tr>
<td>${pro.transactionId}</td>
<td>${pro.dateOfTransaction}</td>
<td>${pro.amount}</td>
<td>${pro.description}</td>
<td>${pro.balance}</td>
</tr>
</a:forEach>
</table>
<a href="getTransactionsExcel">Get Excel Sheet</a>
</body>
</html>