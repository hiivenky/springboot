<%@page import="com.cg.onlinewalletwithspringboot.dto.WalletUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="registrationForm"
	uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/webjars/css/bootstrap.min.css"/>" > 
<script type="text/javascript" src="<c:url value="/webjars/js/bootstrap.min.js"/>"> </script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

<style type="text/css">
body{
background-size:cover
}
</style>
<style type="text/css">
	
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
<body background="<c:url value= "/resources/images/Simple.jpg"/>" class="bg-light" >

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
<div>

 <h1 style="font-size: 30px;font-family: fantasy;
 padding-left: 350px;color:white">Welcome <span style="font-family: sans-serif;">${user.userName}</span></h1>
 <div align="right">
<a href="signOut"><button type="button" class="btn btn-primary btn-med" >SignOut</button></a>
</div> 
 
</div>
<form class="bg-dark" style="margin-top:25px;" >
  <div class="form-row bg-info">
    <div class="col-md-4">
      <input type="text" class="form-control" placeholder="First name" disabled="disabled" 
      value="    account Number :   ${user.account.accountNo}">>
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
            <button type="submit" value="Transfer" class="btn btn-success btn-md btn-block"/>Transfer</button>
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
            <button type="submit" value="Transfer" class="btn btn-success btn-md btn-block"/>Get Transactions</button>
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
</body>
</html>