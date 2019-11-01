import {Component,OnInit,OnChanges,OnDestroy} from '@angular/core';
import {UserService} from '../service/app.userservice';
import { Router } from '@angular/router';


@Component({
    selector:'showtransaction',
    templateUrl:'./app.showtransaction.html',
    styleUrls:["../homePart/app.homepagecomponent.css"]
})

export class ShowTransactionsComponenent implements OnInit{

    transactionHistory:any={};
   // transactionHistory.accountNo:Integer ;
  //  transactionHistory.fromDate:Date;
    //transactionHistory.toDate:Date;

    constructor(private service:UserService,private router:Router){
        console.log("NIn in constructor admin constructor service");
        console.log(this.transactionHistory);
        
        
    }

    ngOnInit(): void {
        console.log("inside transaction component ")
        if(sessionStorage.getItem('username')==''){
            alert('inside cons user')
            this.router.navigate(['login'])
        }
    }

    getTransactions(accountNo,fromDate,toDate):any{
        alert(accountNo+" "+fromDate+" "+toDate)
        return this.service.getTransactions(fromDate,toDate).subscribe((data)=>this.transactionHistory=data);
    }
}