import {Injectable} from '@angular/core';
import {HttpClient,HttpParams} from '@angular/common/http';

@Injectable({
   providedIn:'root' 
})

export class AdminService{

    constructor(private myhttp:HttpClient){}

    getAccountsToBeApproved(){
        alert("inside get accounts to be approved service class")
        return this.myhttp.get("http://localhost:9050/viewAccountsToBeApproved");
    }

    approveAccount(accountNo):any{
        alert("inside approve account of admin service"+accountNo)
        let body = new HttpParams();
        body = body.set('accountNo', accountNo);
        return this.myhttp.post("http://localhost:9050/getApproveAccountNo?",body
        );
    }

}