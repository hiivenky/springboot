package com.cg.onlinewalletwithspringboot.controller;

import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.onlinewalletwithspringboot.dto.TransactionHistory;
import com.cg.onlinewalletwithspringboot.dto.WalletAccount;
import com.cg.onlinewalletwithspringboot.dto.WalletUser;
import com.cg.onlinewalletwithspringboot.exception.MyException;
import com.cg.onlinewalletwithspringboot.service.OnlineWalletService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Controller
public class OnlineWalletController {
	@Autowired
	private OnlineWalletService service;
	
	@GetMapping(value="/")
	public String getHome() {
		System.out.println("good u are there");
		return "home";
	}
	@GetMapping(value="/login")
	public String loginPage() {
		return "login";
	}
	/**
	 *author: Venkatesh
	 *Description : This method maps 
	 *created Date: 08/9/2019
	 *last modified : 08/09/2019              
	 */
	@GetMapping(value="/registration")
	public String registrationPage(@ModelAttribute("registrationForm") WalletUser user,Map<String,Object> model) {
		model.put("error", "");
		return "registration";
	}
	@PostMapping(value="/getRegistrationDetails")
	public String getRegistrationDetails(@ModelAttribute("registrationForm") WalletUser user
			,@RequestParam("confirmpassword") String confirmpassword,Map<String,Object> model){
		
		System.out.println("hii");
		
		if(confirmpassword.equals(user.getUserPassword())) {
			try {
				user.setAccount(new WalletAccount());
				user=service.addWalletUser(user);
				
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.out.println("exception arose");
				model.put("error",e.getMessage());
				return "registration";
			}
		}
		else {
			return "registration";
		}
		return "login";
	}
	@PostMapping(value="/userPage")
	public String userPage(@RequestParam("userName") String userId,
			@RequestParam("userPassword") String password,
			@RequestParam("userType") String userType,HttpServletRequest req,HttpServletResponse res
			,Map<String,Object> model) {
		model.put("error","");
		if(userType.equals("customer")) {
			if(service.validateLoginCredentials(userId, password)) {
				Cookie cookie = new Cookie("status","loggedin");
				res.addCookie(cookie);
				WalletUser user;
				try {
					user = service.getUser(userId);
					HttpSession session = req.getSession();
					session.setAttribute("user",user);
					model.put("user",user);
					model.put("error", "");
					return "UserFunctionalitiesPage";
				} catch (MyException e) {
					// TODO Auto-generated catch block
					model.put("error","Invalid login credentials");
					System.out.println(e.getMessage());
				}
				return "login";
			}
			else {
				model.put("error","Invalid Login Credentials");
				return "login";
			}
		}
		else{
			if(userId.equals("1")&&password.equals("venkatesh")) {
				Cookie cookie = new Cookie("status","loggedin");
				res.addCookie(cookie);
				model.put("name", "Venkatesh");
				return "AdminFunctionalitiesPage";
			}
			else {
				model.put("error","Invalid login credentials");
			}
		}
		return "login";
	}
	@GetMapping(value="/approveAccount")
	public String approveAccountPage(Map<String,Object> model) {
		model.put("error", "");
		return "approveAccount";
	}
	@PostMapping(value="/getApproveAccountNo")
	public String getApproveAccountNo(@RequestParam("accountNo") Integer accountNo,Map<String,Object> model) {
		try {
			service.approveAccount(accountNo);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.put("error", "Enter a Valid account No");
			return "approveAccount";
		}
		return "AdminFunctionalitiesPage";
	}
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
		return "home";
	}
	@GetMapping(value="/addAmount")
	public String addAmount(HttpServletResponse res,HttpServletRequest request,Map<String,Object> model) {
		WalletUser user;
			HttpSession seesion = request.getSession();
			user = (WalletUser)seesion.getAttribute("user");
			System.out.println(user.getPhoneNo());
		    model.put("user",user);
		    model.put("error","");
		return "addAmount";
	}
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
				} catch (MyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (RazorpayException e) {
			  // Handle Exception
			  System.out.println(e.getMessage());
			}
		return "addAmountConfirmationPage";
	}
	@GetMapping(value="/redirectAfterTransaction")
	public String redirectAfterTransaction(HttpServletRequest req,Map<String,Object> model) {
		HttpSession session = req.getSession();
		WalletUser user=(WalletUser)session.getAttribute("user");
		model.put("error", model.get("error"));
		try {
			req.setAttribute("user",service.getUser(user.getLoginName()));
		} catch (MyException e) {
			// TODO Auto-generated catch block
		   
		}
		return "UserFunctionalitiesPage";
	}
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
	    		}
	    		else {
	    			model.put("error", "Your account is waiting for approval");
	    			return "UserFunctionalitiesPage";
	    		}
				
			} catch (MyException e) {
				// TODO Auto-generated catch block
				System.out.println("hii u r there");
				model.put("error", e.getMessage());
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
	    			return "UserFunctionalitiesPage";
	    		}
				
			} catch (NumberFormatException | MyException e) {
				// TODO Auto-generated catch block
				model.put("error", e.getMessage());
			}
	    }
	    return "redirect:/redirectAfterTransaction";
	}
	
}
