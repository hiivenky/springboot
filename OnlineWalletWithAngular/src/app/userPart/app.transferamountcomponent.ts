import {Component,OnInit,OnChanges,OnDestroy} from '@angular/core';
import {UserService} from '../service/app.userservice';
import { Router } from '@angular/router';


@Component({
    selector:'transferamount',
    templateUrl:'./app.transferAmountPage.html',
    styleUrls:["../homePart/app.homepagecomponent.css"]
})

export class TransferAmountComponenent implements OnInit{

    transferAmount:any={}


    constructor(private service:UserService,private router:Router){
        console.log("NIn in constructor admin constructor service");
        
       
    }

    ngOnInit(): void {
        console.log("inside transferamount component ");
        if(sessionStorage.getItem('username')==''){
            alert('inside cons user')
            this.router.navigate(['login'])
        }
    }

    transferAmountValue(phoneNo,amount):any{
        console.log(phoneNo,amount);
        return this.service.transferAmount(phoneNo,amount).subscribe((data)=>this.transferAmount=data);
    }
}