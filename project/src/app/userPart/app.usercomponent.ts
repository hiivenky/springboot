import {Component,OnInit,OnChanges,OnDestroy} from '@angular/core';
import {UserService} from '../service/app.userservice';
import { Router } from '@angular/router';


@Component({
    selector:'addamount',
    templateUrl:'./app.userpage.html',
    styleUrls:["../homePart/app.homepagecomponent.css"]
})

export class UserComponent implements OnInit{
    user:any={}
    getAmount:any={}
    transferAmount:any={}
    accountNo:any
    accountBalance:any
    phoneNumber:any
    username:any
    
    transactionHistory:any={}

    constructor(private service:UserService,private router:Router){
        console.log("NIn in constructor admin constructor service");
    }

    ngOnInit(): void {
        console.log("inside getAmount component ");
            if(sessionStorage.getItem('username')==''){
                this.router.navigate(['login'])
            }
            this.service.getDbAccount(sessionStorage.getItem("username")).subscribe(
                (data)=>{
                  this.accountNo="accountNo :"+data["userName"];
                  this.accountBalance="accountBalance :"+data["userPassword"]
                  this.phoneNumber="phoneNumber :"+data["phoneNo"]
                  this.username=data["loginName"];
              });
    }

    getAmountValue(amount):any{
        console.log(amount);
        location.reload();
        return this.service.getAmount(amount).subscribe((data)=>this.getAmount=data);   
    }
    transferAmountValue(phoneNo,amount):any{
        console.log(phoneNo,amount);
        location.reload();
        return this.service.transferAmount(phoneNo,amount).subscribe((data)=>this.transferAmount=data);
    }
    
    getTransactions(type,accountNo,fromDate,toDate):any{
        console.log(type,accountNo,fromDate,toDate);
        location.reload();
       // if(type.value==="on")
       return this.service.getTransactions(fromDate,toDate).subscribe((data)=>this.transactionHistory=data);
      //  }
       // else{
            //this.service.exportAsExcelFile(this.transactionHistory, 'sample');
      //  }
    } 
}