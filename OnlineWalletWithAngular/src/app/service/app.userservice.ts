import {Injectable} from '@angular/core';
import {HttpClient,HttpParams} from '@angular/common/http';

@Injectable({
   providedIn:'root' 
})

export class UserService{

    constructor(private myhttp:HttpClient){}

    getAccountsToBeApproved(){
        alert("inside user page")
        return this.myhttp.get("http://localhost:9050/userfunctionalitiespage");
    }

    gettransactions(accountNo,fromDate,toDate):any{
        alert("inside table of transactions"+accountNo)
        let body = new HttpParams();
        body = body.set('accountNo', accountNo);
        return this.myhttp.post("http://localhost:9050/gettransactions?",body
        );
    }

}