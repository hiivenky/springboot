import {Injectable} from '@angular/core';
import {HttpClient,HttpParams, HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
   providedIn:'root' 
})
/**
	 *author: Venkatesh
	 *Description : This class calls connects to the database for admin operations  
	 *created Date: 20/10/2019
	 *last modified : 20/10/2019            
	 */
export class AdminService{

    headers:any 

    constructor(private myhttp:HttpClient,private router:Router){
        this.headers = new HttpHeaders().set("Authorization",sessionStorage.getItem("token"));
        if(sessionStorage.getItem('username')==''){
            alert('inside cons user')
            this.router.navigate(['login'])
        }
    
    }
    //this function is calls the database for getting accouonts to be approved
    //this function uses JWT tokens for connecting to the database
    getAccountsToBeApproved(){
        let username=sessionStorage.getItem('username');
        let token=sessionStorage.getItem('token');
       return this.myhttp.get('http://13.233.48.144:9050/viewAccountsToBeApproved',{headers:this.headers});
    }
    //this function is calls the database for approving account
    //this function uses JWT tokens for connecting to the databas
    approveAccount(accountNo):any{
        let body = new HttpParams();
        body = body.set('accountNo', accountNo);
        return this.myhttp.post("http://13.233.48.144:9050/getApproveAccountNo?",body,{headers:this.headers}
        );
    }
    //this function removes the session
    logOut(){
        sessionStorage.removeItem('username');
        sessionStorage.removeItem('token')
    }

}