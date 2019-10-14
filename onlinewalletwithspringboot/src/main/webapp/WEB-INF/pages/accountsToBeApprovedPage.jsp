<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"

        crossorigin="anonymous">

    <link rel="stylesheet" href="styles/style.css">

    <title>Wallet Login Form</title>
    <link rel="stylesheet" href="<c:url value="/pages/styles/style.css"/>" >
     <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="<c:url value="/pages/styles/bootstrap.min.css"/>" >

  <!-- Custom fonts for this template -->
  <link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link rel="stylesheet" href="<c:url value="/pages/styles/one-page-wonder.min.css"/>">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 

<style type="text/css">
.body{font-family:Lato}h1,h2,h3,h4,h5,h6{font-family:Catamaran;font-weight:800!important}.btn-xl{text-transform:uppercase;padding:1.5rem 3rem;font-size:.9rem;font-weight:700;letter-spacing:.1rem}.bg-black{background-color:#000!important}.rounded-pill{border-radius:5rem}.navbar-custom{padding-top:1rem;padding-bottom:1rem;background-color:rgba(0,0,0,.7)}.navbar-custom .navbar-brand{text-transform:uppercase;font-size:1rem;letter-spacing:.1rem;font-weight:700}.navbar-custom .navbar-nav .nav-item .nav-link{text-transform:uppercase;font-size:.8rem;font-weight:700;letter-spacing:.1rem}header.masthead{position:relative;overflow:hidden;padding-top:calc(7rem + 72px);padding-bottom:7rem;background:linear-gradient(0deg,#ff6a00 0,#ee0979 100%);background-repeat:no-repeat;background-position:center center;background-attachment:scroll;background-size:cover}header.masthead .masthead-content{z-index:1;position:relative}header.masthead .masthead-content .masthead-heading{font-size:4rem}header.masthead .masthead-content .masthead-subheading{font-size:2rem}header.masthead .bg-circle{z-index:0;position:absolute;border-radius:100%;background:linear-gradient(0deg,#ee0979 0,#ff6a00 100%)}header.masthead .bg-circle-1{height:90rem;width:90rem;bottom:-55rem;left:-55rem}header.masthead .bg-circle-2{height:50rem;width:50rem;top:-25rem;right:-25rem}header.masthead .bg-circle-3{height:20rem;width:20rem;bottom:-10rem;right:5%}header.masthead .bg-circle-4{height:30rem;width:30rem;top:-5rem;right:35%}@media (min-width:992px){header.masthead{padding-top:calc(10rem + 55px);padding-bottom:10rem}header.masthead .masthead-content .masthead-heading{font-size:6rem}header.masthead .masthead-content .masthead-subheading{font-size:4rem}}.bg-primary{background-color:#ee0979!important}.btn-primary{background-color:#ee0979;border-color:#ee0979}.btn-primary:active,.btn-primary:focus,.btn-primary:hover{background-color:#bd0760!important;border-color:#bd0760!important}.btn-primary:focus{box-shadow:0 0 0 .2rem rgba(238,9,121,.5)}.btn-secondary{background-color:#ff6a00;border-color:#ff6a00}.btn-secondary:active,.btn-secondary:focus,.btn-secondary:hover{background-color:#c50!important;border-color:#c50!important}.btn-secondary:focus{box-shadow:0 0 0 .2rem rgba(255,106,0,.5)}
 

</style>
<style>

#accounts {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

#accounts td,  th {
  border: 1px solid #ddd;
  padding: 8px;
  column-fill: white;
  background-color: white;
}


#accounts th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #f08024;
  color: white;
}
#accounts td, th {
  position: relative;
}
#accounts td:hover::after,
 th:hover::after {
  content: "";
  position: absolute;
  background-color: #f56d0c;
  left: 0;
  top: -5000px;
  height: 10000px;
  width: 100%;
  z-index: -1;
}

</style>



    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"

        crossorigin="anonymous">

    <link rel="stylesheet" href="styles/style.css">

    <title>Approving Accounts</title>
    <link rel="stylesheet" href="<c:url value="/pages/styles/style.css"/>" >
     <!-- Bootstrap core CSS -->
  <link rel="stylesheet" href="<c:url value="/pages/styles/bootstrap.min.css"/>" >

  <!-- Custom fonts for this template -->
  <link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link rel="stylesheet" href="<c:url value="/pages/styles/one-page-wonder.min.css"/>">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 

</head>




<body>
<% Cookie cookies[] = request.getCookies() ;
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
<!-- 
	Author:Utkarsh
	Description: This page gives the list of accounts which is waiting for approval.
	CreatedDate:5/10/2019
	LastModified:9/10/2019
	 -->
<header class="masthead text-center text-white">
 <div class="masthead-content">
    <div>
 <h1  style="font-size: 30px;font-family: sans-serif;
 padding-left: 0px;color:white">Welcome Admin <a href="signOut">
<div align="right"> <button type="button" class="btn btn-primary btn-med" >
SignOut</button></div></a> </h1>
</div> 
<form action ="getApproveAccountNo" method= "post">
<table id= "accounts">
<tr>
<th>Account Id</th>
<th>Account Balance</th>
<th>Account Status</th>
<th>Approval</th>
</tr>



<a:forEach var="pro" items="${accounts}">
<tr style="color: black">
<td>${pro.accountNo}</td>
<td>${pro.balance}</td>
<td>${pro.accountStatus}</td>

<td><input name="accountNo" type="hidden" value="${pro.accountNo}"/>
<button type="submit" class="btn btn-warning btn-med">Approve</button></td>
</tr>
</a:forEach>

</table>
</form>
</div>
</header>
<header class="masthead text-center text-white">
 <div class="masthead-content">
 </div>
 </header>
</body>
</html>