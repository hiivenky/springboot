<%@page import="com.cg.onlinewalletwithspringboot.dto.WalletUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="registrationForm"
	uri="http://www.springframework.org/tags/form"%>
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

<style type="text/css">
input[type=number]::-webkit-inner-spin-button, 
input[type=number]::-webkit-outer-spin-button { 
  -webkit-appearance: none; 
  margin: 0; 
}
	
    .form-control{
		height: 40px;
		box-shadow: none;
		color: #969fa4;
	}
	.form-control:focus{
		border-color: #5cb85c;
	}
    .form-control, .btn{        
        border-radius: 3px;
    }
	.signup-form{
		width: 400px;
		margin: 0 auto;
		padding: 30px 0;
	}
	.signup-form h2{
		color: #636363;
        margin: 0 0 15px;
		position: relative;
		text-align: center;
    }
	.signup-form h2:before, .signup-form h2:after{
		content: "";
		height: 2px;
		width: 30%;
		background: #d4d4d4;
		position: absolute;
		top: 50%;
		z-index: 2;
	}	
	.signup-form h2:before{
		left: 0;
	}
	.signup-form h2:after{
		right: 0;
	}
    .signup-form .hint-text{
		color: #999;
		margin-bottom: 30px;
		text-align: center;
	}
    .signup-form form{
		color: #999;
		border-radius: 3px;
    	margin-bottom: 15px;
        background: #f5f1d7;
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        padding: 30px;
    }
	.signup-form .form-group{
		margin-bottom: 20px;
	}
	.signup-form input[type="checkbox"]{
		margin-top: 3px;
	}
	.signup-form .btn{        
        font-size: 16px;
        font-weight: bold;		
		min-width: 140px;
        outline: none !important;
    }
	.signup-form .row div:first-child{
		padding-right: 10px;
	}
	.signup-form .row div:last-child{
		padding-left: 10px;
	}    	
    .signup-form a{
		color: #fff;
		text-decoration: underline;
	}
    .signup-form a:hover{
		text-decoration: none;
	}
	.signup-form form a{
		color: #5cb85c;
		text-decoration: none;
	}	
	.signup-form form a:hover{
		text-decoration: underline;
	}  
</style>

</head>
<body>

<% 
WalletUser user=(WalletUser)session.getAttribute("user");
WalletUser user1=(WalletUser)request.getAttribute("user"); 
if(user1==null){
	user1=user;
}
session.setAttribute("user",user);
System.out.println(user.getPhoneNo());
Cookie cookies[] = request.getCookies() ;

%>
<!-- 
	Author:Utkarsh
	Description: This page gives the whole list of the functionalities like adding amount to the wallet, transfering amount
	from one account to a different account and also getting the list of transactions s/he has done over a particular time period
	 
	CreatedDate:5/10/2019
	LastModified:9/10/2019
	 -->
	 <header class="masthead text-center text-white">

    
<div>

 <h1 style="font-size: 30px;font-family: fantasy;
 padding-left: 0px;color:white">Welcome <span style="font-family: sans-serif;">${user.userName}</span></h1>
 <div align="right">
<a href="logout"><button type="button" class="btn btn-primary btn-med" style="background-color: green;">SignOut</button></a>
</div> 
 <div class="masthead-content">
</div>
<form class="bg-dark" style="margin-top:25px;" >
  <div class="form-row bg-info">
    <div class="col-md-4">
      <input type="text" class="form-control" placeholder="First name" disabled="disabled" 
      value="    account Number :   ${user.account.accountNo}">
    </div>
    <div class="col-md-4">
      <input type="text" class="form-control" placeholder="Last name" disabled="disabled"
      value="    account balance :  ${user.account.balance}">
    </div>
    <div class="col-md-4">
      <input type="text" class="form-control" placeholder="Last name" disabled="disabled"
      value="    phone Number :   ${user.phoneNo}">
    </div>
  </div>
</form>

<nav class="navbar navbar-light bg-light">
  <form class="form-inline" action="getAmount" method="post">
    <input name="amount" title="enter a valid amount" class="form-control mr-sm-2" type="number" placeholder="amount" aria-label="Search">
    <button class="btn btn-outline-success " type="submit">Add Amount</button>
  </form>
</nav>





<div class="row">

<div class="col-sm-5">

<div class="signup-form">

    <form action="transferAmount" method="post">
		
           <div class="checkbox form-group d-flex align-items-left ">

			<input type="radio" name="accountType" value="same" checked> Same wallet <br>

			</div>

			<div class="checkbox form-group d-flex align-items-right">

            <input type="radio" name="accountType" value="diff"> Different Wallet<br>

			</div>
        <div class="form-group">
        	<input type="text" class="form-control" name="phoneNo" placeholder="Phone Number" required="required"/>
        </div>
		<div class="form-group">
            <input type="number" class="form-control" name="amount" placeholder="Amount" required="required"/>
            </div>
<div class="form-group">
</div>
<div style="margin-top: 60px">
            <button type="submit" value="Transfer" class="btn btn-success btn-md btn-block">Transfer</button>
     </div>
     </form>
        </div>
        
</div>
<div class="col-sm-1 middle-border"></div>
<div class="col-sm-1"></div>
                        	
<div class="col-sm-5">


    
<div class="signup-form" >
    <form action="getTransactionsPage" method="post">
     <div class="checkbox form-group d-flex align-items-left ">

			<input type="radio" name="getExcel" value="true" > get Excel sheet <br>
			</div>
			     <div class="checkbox form-group d-flex align-items-left ">

			<input type="radio" name="getExcel" value="false" > print transactions<br>
			</div>
        <div class="form-group">
        	<input type="date" data-date-format="YYYY/MM/DD" class="form-control" name="fromDate" placeholder="yyyy/MM/dd" required="required"/>
        </div>
		<div class="form-group">
            <input type="date" data-date-format="yyyy/MM/dd" class="form-control" name="toDate" placeholder="yyyy/MM/dd" required="required"/>
<div class="form-group">
</div>
<div style="margin-top: 60px">
            <button type="submit" value="Transfer" class="btn btn-success btn-md btn-block">Get Transactions</button>
     </div>
        </div>
        </form>



</div>
</div>
</div>



	<script type="text/javascript">
	    
        if("${error}"!=""){
        
        	alert("${error}");
        }	
	</script>
	</div>
	</header>
	
</body>
</html>