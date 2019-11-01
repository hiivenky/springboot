package com.cg.onlinewalletwithspringbootrest.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cg.onlinewalletwithspringbootrest.dto.TransactionHistory;
import com.cg.onlinewalletwithspringbootrest.dto.WalletAccount;
import com.cg.onlinewalletwithspringbootrest.dto.WalletUser;
import com.cg.onlinewalletwithspringbootrest.dto.WalletUserDetails;
import com.cg.onlinewalletwithspringbootrest.exception.MyException;
import com.cg.onlinewalletwithspringbootrest.service.OnlineWalletService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

/**
 *author: Utkarsh,Venkatesh
 *Description : This class acts as a RestController for providing web services 
 *created Date: 12/10/2019
 *last modified : 13/10/2019            
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OnlineWalletController {
	
	@Autowired
	private OnlineWalletService service;
	private static final Logger logger = LoggerFactory.getLogger(OnlineWalletController.class);
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	/**
	 *author: Venkatesh
	 *Description : This method is used for adding the new registered user 
	 *              to database  
	 *created Date: 08/10/2019
	 *last modified : 19/10/2019     
	 *Input : WalletUser Object,Map<String,Object>,String confirmpassword
	 *Output : ResponseEntity         
	 */
	@PostMapping(value="/getRegistrationDetails")
	public ResponseEntity<String> getRegistrationDetails(@ModelAttribute WalletUser user
			,Map<String,Object> model){
		
		System.out.println("hii");
	
		
		if(1==1) {
			try {
				user.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));
				user.setAccount(new WalletAccount());
				logger.trace(user.getPhoneNo()+"registered");
				user=service.addWalletUser(user);
				
			} catch (MyException e) {
				System.out.println("exception arose");
				model.put("error",e.getMessage());
				logger.error("Invalid details entered by "+System.getProperties());
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else {
			System.out.println("hello no matching");
			return new ResponseEntity<String>("Registration is not Successfull try again later",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Registration Successfull",HttpStatus.OK);
	}
	/**
	 *author: Utkarsh
	 *Description : This method is used to provide all the accounts that are to be approved
	 *              by admin
	 *created Date: 08/10/2019
	 *last modified : 13/10/2019     
	 *Input : Map<String,Object>
	 *Output :         
	 **/
	@GetMapping(value="/viewAccountsToBeApproved")
	public ResponseEntity<List<WalletAccount>> viewAccountsToBeApproved(Map<String,Object> model,
			Authentication authentication) {
		System.out.println("inside getaccounts to be approved page");
		List<WalletAccount> accounts = service.getAccountsToApprove();
		model.put("accounts", accounts);
		logger.trace("view accounts to be approved page opened by admin");
		return new ResponseEntity<List<WalletAccount>>(accounts,HttpStatus.OK);
	}
	/**
	 *author: Venkatesh
	 *Description : This method is used to approve account of an user by the admin  
	 *created Date: 08/10/2019
	 *last modified : 13/10/2019     
	 *Input : WalletUser Object,Map<String,Object>,Integer accountNo
	 *Output : AdminFunctionalities.jsp         
	 */
	@PostMapping(value="/getApproveAccountNo")
	public String getApproveAccountNo(@RequestParam("accountNo") Integer accountNo,Map<String,Object> model,Authentication authentication) {
		System.out.println("approve account "+accountNo);
		try {
			service.approveAccount(accountNo);
			logger.trace("admin approved account no "+accountNo);
			
		} catch (Exception e) {
			model.put("error", "Enter a Valid account No");
			logger.error("invalid account no entered by admin");
			return "not done";
		}
		return "Done";
	}
	/**
	 *author: Venkatesh
	 *Description : This method is used to add the amount to the user's account  
	 *created Date: 09/10/2019
	 *last modified : 13/10/2019     
	 *Input : Double amount,HttpServletResponse Object,HttpServletRequest Object,Map<String,Object> Object
	 *Output :          
	 * @throws MyException 
	 */
	@GetMapping("/getUser")
	public WalletUser getUser(@RequestParam String loginName) throws MyException {
		System.out.println("Inside get user");
		WalletUser user = null;
		try {
			user = service.getUser(loginName);
			user.setUserPassword("1");
			user.setAccount(null);
			System.out.println(user.getPhoneNo()+"called from angular");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			throw new MyException("User not present");
		}
		return user;
	}
	
	
	/**
	 *author: Venkatesh
	 *Description : This method is used to add the amount to the user's account  
	 *created Date: 09/10/2019
	 *last modified : 13/10/2019     
	 *Input : Double amount,HttpServletResponse Object,HttpServletRequest Object,Map<String,Object> Object
	 *Output :          
	 */
	@PostMapping(value="/getAmount")
	public String getAmountConfirmationPage(@RequestParam("amount") Double amount,HttpServletResponse res,
			HttpServletRequest request,Map<String,Object> model,Authentication authentication) {
		System.out.println("get amount Confirmation page");
		WalletUserDetails userDetails;
		HttpSession seesion = request.getSession();
		userDetails = (WalletUserDetails)authentication.getPrincipal();
		WalletUser user=null;
		try {
			user = service.getUser(userDetails.getUsername());
			System.out.println(user.getPhoneNo());
		} catch (MyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(user.getPhoneNo());
	    model.put("user",user);
	    model.put("error","");
		RazorpayClient razorpay;
		try {
			System.out.println("inside razorpay");
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
	 *Description : This method is used to transfer amount for one user to other user
	 *              who is also registered in the wallet 
	 *created Date: 09/10/2019
	 *last modified : 13/10/2019     
	 *Input : Double amount,HttpServletResponse Object,HttpServletRequest Object,Map<String,Object> Object
	 *        String phoneNo
	 *Output :        
	 */
	@PostMapping(value="/transferAmount")
	public String transferAmount(HttpServletResponse res,HttpServletRequest request,Map<String,Object> model,@RequestParam("phoneNo") String phoneNo
			,@RequestParam("amount") Double amount,Authentication authentication) {
		String accountType="same";
		WalletUser user=null;
		WalletUserDetails userDetails;
		userDetails = (WalletUserDetails)authentication.getPrincipal();
		HttpSession seesion = request.getSession();
		try {
			user = service.getUser(userDetails.getUsername());
			System.out.println(user.getPhoneNo());
		} catch (MyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	/**
	 *author: Utkarsh
	 *Description : This method is used to get the transactions from a particular date 
	 *              to other date
	 *created Date: 08/10/2019
	 *last modified : 13/10/2019     
	 *Input : boolean getExcel, Date fromDate,Date toDate,
	                        HttpServletRequest req,Map<String,Object> model
	 *Output :        
	 **/
	@PostMapping(value="/getTransactionsPage")
	public List<TransactionHistory> getTransactionsPage(@RequestParam("fromDate") Date fromDate,@RequestParam("toDate")Date toDate,
	                        HttpServletRequest req,Map<String,Object> model
	                        ,Authentication authentication){
		System.out.println("Inside get Transactions");
		WalletUser user=null;
		WalletUserDetails userDetails;
		userDetails = (WalletUserDetails)authentication.getPrincipal();
		HttpSession session = req.getSession();
		try {
			user = service.getUser(userDetails.getUsername());
			System.out.println(user.getPhoneNo());
		} catch (MyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		model.put("user",user);
		System.out.println(user.getPhoneNo());
	    System.out.println("U are in transactions page");
		
		if(!user.getAccount().getAccountStatus().toString().equals("WatingForApproval")){
			try {
				user=(WalletUser) service.getUser(user.getUserName());
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
			    
			    	return transactions;
			    
			} finally {}
		}
		else {
			model.put("error", "account waiting for approval");
		}
	    return null;
    }
	/**
	 *author: Venkatesh
	 *Description : This method is used to pass account of an user by the admin  
	 *created Date: 23/10/2019
	 *last modified : 23/10/2019     
	 *Input : WalletUser Object,Map<String,Object>,Integer accountNo
	 *Output : AdminFunctionalities.jsp         
	 */
	@GetMapping("/getAccount")
	public WalletUser getAccount(String loginName) throws MyException {
		System.out.println("Inside get Account");
		WalletUser user = null;
		try {
			user = service.getUser(loginName);
			user.setUserName(""+user.getAccount().getAccountNo());
			user.setUserPassword(""+user.getAccount().getBalance());
			user.setAccount(null);
			System.out.println(user.getUserName()+"called from angular");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			throw new MyException("User not present");
		}
		return user;
	}
	

}
