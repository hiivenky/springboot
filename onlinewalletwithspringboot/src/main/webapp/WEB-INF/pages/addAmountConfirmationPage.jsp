<%@page import="com.cg.onlinewalletwithspringboot.dto.WalletUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/webjars/css/bootstrap.min.css"/>" > 
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body class="bg-dark">
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
<div class="d-flex justify-content-center align-items-center login-container box box-warning bg-success">
<div class="alert alert-primary" role="alert">
 <h3>Review Your order here</h3>
 <h2>Amount : "${amount}" </h2>
</div>
<div>
<button id="rzp-button1" class="btn btn-primary">Pay</button><script src="https://checkout.razorpay.com/v1/checkout.js"></script><script>var options = {   
		 "key": "rzp_test_caWad8vPPWMbqH", // Enter the Key ID generated from the Dashboard    
		"amount": "${amount}", // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise or INR 500.   
		 "currency": "INR",    "name": "Our Wallet",   
		 "description": "Add Your amount here",   
		 "image": "",   
		"order_id": "${order}",//This is a sample Order ID. Create an Order using Orders API. (https://razorpay.com/docs/payment-gateway/orders/integration/#step-1-create-an-order). Refer the Checkout form table given below    
		"handler": function (response){        alert(response.razorpay_payment_id);    },   
		 "prefill": {        "name": "${user.userName}",        "email": "gaurav.kumar@example.com",        "contact": "${user.phoneNo}"    },   
		 "notes": {        "address": "note value"    },    "theme": {        "color": "#F37254"    }};
		var rzp1 = new Razorpay(options);document.getElementById('rzp-button1').onclick = function(e){ rzp1.open();    e.preventDefault();}
		</script>
</div>
</div>
<a href="redirectAfterTransaction">Return</a>
</body>
</html>