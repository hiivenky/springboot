import {Injectable} from '@angular/core';
import {HttpClient,HttpParams, HttpHeaders} from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
   providedIn:'root' 
})

export class AdminService{

    headers:any 

    constructor(private myhttp:HttpClient,private router:Router){
        this.headers = new HttpHeaders().set("Authorization",sessionStorage.getItem("token"));
        if(sessionStorage.getItem('username')==''){
            alert('inside cons user')
            this.router.navigate(['login'])
        }
    
    }

    getAccountsToBeApproved(){
        let username=sessionStorage.getItem('username');
        let token=sessionStorage.getItem('token');
  console.log(token+"inside getaccounts");
       return this.myhttp.get('http://localhost:9050/viewAccountsToBeApproved',{headers:this.headers});
}

    approveAccount(accountNo):any{
        let body = new HttpParams();
        body = body.set('accountNo', accountNo);
        return this.myhttp.post("http://localhost:9050/getApproveAccountNo?",body,{headers:this.headers}
        );
    }
    logOut(){
        sessionStorage.removeItem('username');
        sessionStorage.removeItem('token')
    }

}