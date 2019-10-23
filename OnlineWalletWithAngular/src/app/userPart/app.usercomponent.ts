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
    
    transactionHistory:any={}

    constructor(private service:UserService,private router:Router){
        console.log("NIn in constructor admin constructor service");
         
      
    }

    ngOnInit(): void {
        console.log("inside getAmount component ");
            if(sessionStorage.getItem('username')==''){
                alert('inside cons user')
                this.router.navigate(['login'])
            }
    }

    getAmountValue(amount):any{
        console.log(amount);
        return this.service.getAmount(amount).subscribe((data)=>this.getAmount=data);
       
    }
    transferAmountValue(phoneNo,amount):any{
        console.log(phoneNo,amount);
        return this.service.transferAmount(phoneNo,amount).subscribe((data)=>this.transferAmount=data);
    }
    
    getTransactions(type,accountNo,fromDate,toDate):any{
        console.log(type,accountNo,fromDate,toDate);
        alert(type.value)
       // if(type.value==="on")
       return this.service.getTransactions(fromDate,toDate).subscribe((data)=>this.transactionHistory=data);
      //  }
       // else{
            //this.service.exportAsExcelFile(this.transactionHistory, 'sample');
      //  }
    } 
}