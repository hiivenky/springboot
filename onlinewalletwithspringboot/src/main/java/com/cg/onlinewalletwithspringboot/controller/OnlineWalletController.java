package com.cg.onlinewalletwithspringboot.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.cg.onlinewalletwithspringboot.dto.TransactionHistory;
import com.cg.onlinewalletwithspringboot.dto.WalletAccount;
import com.cg.onlinewalletwithspringboot.dto.WalletUser;
import com.cg.onlinewalletwithspringboot.excelview.TransactionsExcel;
import com.cg.onlinewalletwithspringboot.exception.MyException;
import com.cg.onlinewalletwithspringboot.service.OnlineWalletService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Controller
public class OnlineWalletController {
	
	//Logger instance from logger factory to trace events happening in the website
	private static final Logger logger = LoggerFactory.getLogger(OnlineWalletController.class);
	
	@Autowired
	private OnlineWalletService service;
	
	@GetMapping(value="/")
	public String getHome() {
		System.out.println("good u are there");
		logger.info("Home page");
		return "home";
	}
	@GetMapping(value="/login")
	public String loginPage() {
		return "login";
	}
	/**
	 *author: Venkatesh
	 *Description : This method maps the incoming /registration url to 
	 *              registration page  
	 *created Date: 08/10/2019
	 *last modified : 08/10/2019     
	 *Input : WalletUser Object,Map<String,Object>
	 *Output : registration.jsp         
	 */
	@GetMapping(value="/registration")
	public String registrationPage(@ModelAttribute("registrationForm") WalletUser user,Map<String,Object> model) {
		model.put("error", "");
		logger.info("registration page");
		return "registration";
	}
	/**
	 *author: Venkatesh
	 *Description : This method maps the incoming /getRegistrationDetails url to 
	 *              registration page  
	 *created Date: 08/10/2019
	 *last modified : 08/10/2019     
	 *Input : WalletUser Object,Map<String,Object>,String confirmpassword
	 *Output : login.jsp if registration successful else registration.jsp         
	 */
	@PostMapping(value="/getRegistrationDetails")
	public String getRegistrationDetails(@ModelAttribute("registrationForm") WalletUser user
			,@RequestParam("confirmpassword") String confirmpassword,Map<String,Object> model){
		
		System.out.println("hii");
		
		if(confirmpassword.equals(user.getUserPassword())) {
			try {
				user.setAccount(new WalletAccount());
				logger.trace(user.getPhoneNo()+"registered");
				user=service.addWalletUser(user);
				
			} catch (MyException e) {
				System.out.println("exception arose");
				model.put("error",e.getMessage());
				logger.error("Invalid details entered by "+System.getProperties());
				return "registration";
			}
		}
		else {
			return "registration";
		}
		return "login";
	}
	/**
	 *author: Venkatesh
	 *Description : This method maps the incoming /userPage url to 
	 *              respective page  
	 *created Date: 08/10/2019
	 *last modified : 08/10/2019     
	 *Input : WalletUser Object,Map<String,Object>,String userPassowrd,userType
	 *Output : UserFunctionalities.jsp if user is trying to login 
	 *         else AdminFunctionalitiesPage if admin tries to login         
	 */
	@GetMapping(value="/userPage")
	public String userPage(Map<String,Object> model,HttpServletRequest req,HttpServletResponse res){
		Cookie cookie = new Cookie("status","loggedin");
		res.addCookie(cookie);
		WalletUser user;
		try {
			model.put("error", "");
			HttpSession session = req.getSession();
			user = (WalletUser) session.getAttribute("user"); 
			System.out.println(user.getLoginName());
			System.out.println("u enter the login ");
			model.put("status", "loggedin");
			session.setAttribute("user",user);
			model.put("user",user);
			model.put("error", "");
			logger.trace(user.getLoginName()+"logged in");
			if(user.getRoles().equals("ROLE_CUSTOMER")) {
				return "UserFunctionalitiesPage";
			}
			else {
				List<WalletAccount> accounts = service.getAccountsToApprove();
			model.put("accounts", accounts);
			logger.trace("view accounts to be approved page opened by admin");
			return "accountsToBeApprovedPage";
				
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	/**
	 *author: Venkatesh
	 *Description : This method maps the incoming /approveAccount url to 
	 *              approveAccount page  
	 *created Date: 08/10/2019
	 *last modified : 08/10/2019     
	 *Input : Map<String,Object>
	 *Output : approveAccount.jsp         
	 */
	@GetMapping(value="/approveAccount")
	public String approveAccountPage(Map<String,Object> model) {
		model.put("error", "");
		logger.trace("approve account page opened by admin");
		return "approveAccount";
	}
	/**
	 *author: Venkatesh
	 *Description : This method maps the incoming /getapproveAccountNo url to 
	 *              approveAccount page.This method is used by admin  
	 *created Date: 08/10/2019
	 *last modified : 08/10/2019     
	 *Input : WalletUser Object,Map<String,Object>,Integer accountNo
	 *Output : AdminFunctionalities.jsp         
	 */
	@PostMapping(value="/getApproveAccountNo")
	public String getApproveAccountNo(@RequestParam("accountNo") Integer accountNo,Map<String,Object> model,HttpServletRequest req,HttpServletResponse res) {
		WalletUser user;
		try {
			service.approveAccount(accountNo);
			logger.trace("admin approved account no "+accountNo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.put("error", "Enter a Valid account No");
			logger.error("invalid account no entered by admin");
			
		}
		Cookie cookie = new Cookie("status","loggedin");
		res.addCookie(cookie);
		model.put("error", "");
		HttpSession session = req.getSession();
		user = (WalletUser) session.getAttribute("user"); 
		System.out.println(user.getLoginName());
		System.out.println("u enter the login ");
		model.put("status", "loggedin");
		session.setAttribute("user",user);
		model.put("user",user);
		model.put("error", "");
		List<WalletAccount> accounts = service.getAccountsToApprove();
		model.put("accounts", accounts);
		logger.trace("view accounts to be approved page opened by admin");
		return "accountsToBeApprovedPage";
	}
	/**
	 *author: Venkatesh
	 *Description : This method deletes or sets the life time of a 
	 *              particular user session to zero  
	 *created Date: 08/10/2019
	 *last modified : 08/10/2019     
	 *Input : HttpServletRequest Object,HttpServletResponse Object
	 *Output : home.jsp         
	 */
	@GetMapping(value="/signOut")
	public String signOut(HttpServletRequest req,HttpServletResponse res) {
		Cookie cookies[] = req.getCookies();
		for(Cookie c:cookies) {
			if(c.getName().equals("status")){
				System.out.println("out");
				System.out.println(c.getValue());
			}
		}
	    Cookie c = new Cookie("status","");
	    c.setMaxAge(0);
	    res.addCookie(c);
	    WalletUser user = (WalletUser)(req.getSession().getAttribute("user"));
	    if(user!=null) {
	    	logger.trace(user.getLoginName()+" signed out");
	    }
		return "home";
	}
	/**
	 *author: Venkatesh
	 *Description : This method maps the incoming /getapproveAccountNo url to 
	 *              approveAccount page.  
	 *created Date: 08/10/2019
	 *last modified : 08/10/2019     
	 *Input : WalletUser Object,Map<String,Object>,Integer accountNo
	 *Output : addAmount.jsp         
	 */
	@GetMapping(value="/addAmount")
	public String addAmount(HttpServletResponse res,HttpServletRequest request,Map<String,Object> model) {
		WalletUser user;
			HttpSession seesion = request.getSession();
			user = (WalletUser)seesion.getAttribute("user");
			System.out.println(user.getPhoneNo());
		    model.put("user",user);
		    model.put("error","");
		    logger.trace("add amount page requested by user "+user.getLoginName());
		return "addAmount";
	}
	/**
	 *author: Venkatesh
	 *Description : This method is called when the user adds amount to his wallet  
	 *created Date: 09/10/2019
	 *last modified : 09/10/2019     
	 *Input : Double amount,HttpServletResponse Object,HttpServletRequest Object,Map<String,Object> Object
	 *Output : addAmountConfirmationPage.jsp         
	 */
	@PostMapping(value="/getAmount")
	public String getAmountConfirmationPage(@RequestParam("amount") Double amount,HttpServletResponse res,HttpServletRequest request,Map<String,Object> model) {
		System.out.println("get amount Confirmation page");
		WalletUser user;
		HttpSession seesion = request.getSession();
		user = (WalletUser)seesion.getAttribute("user");
		System.out.println(user.getPhoneNo());
	    model.put("user",user);
	    model.put("error","");
		RazorpayClient razorpay;
		try {
			razorpay = new RazorpayClient("rzp_test_caWad8vPPWMbqH", "2FQ6zNZIuK46uVhdctzqjc50");
			  JSONObject orderRequest = new JSONObject();
			  orderRequest.put("amount", amount*100); // amount in the smallest currency unit
			  orderRequest.put("currency", "INR");
			  orderRequest.put("receipt", "order_rcptid_11");
			  orderRequest.put("payment_capture", true);
			  Order order = razorpay.Orders.create(orderRequest);
			  JSONObject jsonObject = new JSONObject(String.valueOf(order));
				String id = jsonObject.getString("id");
				model.put("order", id);
				model.put("amount",amount);
				try {
					service.addAmount(user.getAccount().getAccountNo(), amount);
					logger.trace("amount added successfully by "+user.getLoginName());
				} catch (MyException e) {
					
				}
			} catch (RazorpayException e) {
				logger.error("razorpay payment failed");
			}
		return "addAmountConfirmationPage";
	}
	/**
	 *author: Venkatesh
	 *Description : This method is called when the user adds amount to his wallet  
	 *created Date: 09/10/2019
	 *last modified : 09/10/2019     
	 *Input : Double amount,HttpServletResponse Object,HttpServletRequest Object,Map<String,Object> Object
	 *Output : UserFunctionalitiesPage.jsp         
	 */
	@GetMapping(value="/redirectAfterTransaction")
	public String redirectAfterTransaction(HttpServletRequest req,Map<String,Object> model) {
		HttpSession session = req.getSession();
		WalletUser user=(WalletUser)session.getAttribute("user");
		model.put("error", model.get("error"));
		try {
			req.setAttribute("user",service.getUser(user.getLoginName()));
			logger.trace(user.getLoginName()+"completed trasaction");
		} catch (MyException e) {
		   
		}
		return "UserFunctionalitiesPage";
	}
	/**
	 *author: Venkatesh
	 *Description : This method is called when user tranfers amount 
	 *created Date: 09/10/2019
	 *last modified : 09/10/2019     
	 *Input : Double amount,HttpServletResponse Object,HttpServletRequest Object,Map<String,Object> Object
	 *        String phoneNo
	 *Output : if payment is successful it is redirected to redirect:/redirectAfterTransaction       
	 */
	@PostMapping(value="/transferAmount")
	public String transferAmount(HttpServletResponse res,HttpServletRequest request,Map<String,Object> model,
			@RequestParam("accountType") String accountType,@RequestParam("phoneNo") String phoneNo
			,@RequestParam("amount") Double amount) {
		WalletUser user;
		HttpSession seesion = request.getSession();
		user = (WalletUser)seesion.getAttribute("user");
		System.out.println(user.getPhoneNo());
	    System.out.println("good u entered");
	    if(accountType.equals("same")) {
	    	try {
	    		if(!user.getAccount().getAccountStatus().toString().equals("WatingForApproval")) {
	    			service.transferAmount(user.getUserId(), phoneNo, amount);
	    			logger.trace(user.getLoginName()+" transferred amount to number"+phoneNo);
	    		}
	    		else {
	    			model.put("error", "Your account is waiting for approval");
	    			return "redirect:/redirectAfterTransaction";
	    		}
				
			} catch (MyException e) {
				System.out.println("hii u r there");
				model.put("error", e.getMessage());
				logger.error("tranferring amount failed for user: "+user.getLoginName());
				return "UserFunctionalitiesPage";
			}
	    }
	    else {
	    	try {
	    		if(!user.getAccount().getAccountStatus().toString().equals("WatingForApproval")) {
	    			service.transferAmount(user.getUserId(), phoneNo, amount);
	    		}
	    		else {
	    			model.put("error", "Your account is waiting for approval");
	    			return "redirect:/redirectAfterTransaction";
	    		}
				
			} catch (NumberFormatException | MyException e) {
				model.put("error", e.getMessage());
			}
	    }
	    return "redirect:/redirectAfterTransaction";
	}
	/*
	 *author: Utkarsh
	 *Description : This method maps the incoming url to the 
	 *               page where we get the accounts left to be approved in the
	 *               form of table
	 *created Date: 08/10/2019
	 *last modified : 08/10/2019     
	 *Input : Map<String,Object>
	 *Output : accountsToBeApprovedPage        
	 */
	@GetMapping(value="/viewAccountsToBeApproved")
	public String viewAccountsToBeApproved(Map<String,Object> model) {
		List<WalletAccount> accounts = service.getAccountsToApprove();
		model.put("accounts", accounts);
		logger.trace("view accounts to be approved page opened by admin");
		return "accountsToBeApprovedPage";
	}
	/*
	 *author: Utkarsh
	 *Description : This method maps the incoming url to the 
	 *               page where we get the accounts left to be approved in the
	 *               form of table
	 *created Date: 08/10/2019
	 *last modified : 08/10/2019     
	 *Input : boolean getExcel, Date fromDate,Date toDate,
	                        HttpServletRequest req,Map<String,Object> model
	 *Output : showTransactions       
	 */
	@PostMapping(value="/getTransactionsPage")
	public ModelAndView getTransactionsPage(@RequestParam("getExcel") boolean getExcel,@RequestParam("fromDate") Date fromDate,@RequestParam("toDate")Date toDate,
	                        HttpServletRequest req,Map<String,Object> model){
		
		WalletUser user;
		HttpSession seesion = req.getSession();
		user = (WalletUser)seesion.getAttribute("user");
		model.put("user",user);
		System.out.println(user.getPhoneNo());
	    System.out.println("U are in transactions page");
		
		if(!user.getAccount().getAccountStatus().toString().equals("WatingForApproval")){
			try {
				user=service.getUser(user.getUserName());
			} catch (MyException e1) {
				model.put("error", e1.getMessage());
			}
			LocalDate date1 = fromDate.toLocalDate();
			LocalDate date2 = toDate.toLocalDate();
			LocalDateTime fDate=LocalDateTime.of(date1, LocalTime.of(00, 00));
			LocalDateTime tDate=LocalDateTime.now();
			try {
				List<TransactionHistory> transactions =service.getTransactions(user.getAccount().getAccountNo(), fDate, tDate);
				System.out.println("ghcfycfgc"+transactions.get(0).getTransactionId());
			    if(getExcel) {
			    	logger.trace(" excel sheet requested by "+ user.getLoginName());
			    	return new ModelAndView(new TransactionsExcel(), "transactionList", transactions);
			    }
			    else {
			    	return new ModelAndView("showTransactions","transactions",transactions);
			    }
			} finally {}
		}
		else {
			model.put("error", "account waiting for approval");
		}
	    return null;
    }
	/**
	 *author: Venkatesh
	 *Description : This method is used to handle all exceptions generated by
	 *              MyException class
	 *created Date: 10/10/2019
	 *last modified : 10/10/2019     
	 *Input : MyException Object
	 *Output : ModelAndView       
	 */
	@ExceptionHandler(MyException.class)
	public ModelAndView handleCustomException(MyException ex) {

		ModelAndView model = new ModelAndView(ex.getMessage());
		model.addObject("exception", ex);
		return model;

	}
	/**
	 *author: Venkatesh
	 *Description : This method is used to handle all exceptions generated by
	 *              Exception class
	 *created Date: 10/10/2019
	 *last modified : 10/10/2019     
	 *Input : Exception Object
	 *Output : ModelAndView       
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {

		ModelAndView model = new ModelAndView("The page requested "
				+ "can not be accessed");
		return model;

	}
	
}
