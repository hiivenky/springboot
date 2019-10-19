import {Component,OnInit,OnChanges,OnDestroy} from '@angular/core';
import {AdminService} from './service/app.adminservice';


@Component({
    selector:'admin',
    templateUrl:'./app.adminPage.html'
})

export class AdminComponent implements OnInit{

    accountsToBeApproved:any={}

    constructor(private service:AdminService){
        console.log("NIn in constructor");
        this.service.getAccountsToBeApproved().subscribe((data)=>this.accountsToBeApproved=data);
    }

    ngOnInit(): void {
        console.log("inside registration component ")
    }

    getAccounts():any{
        return this.accountsToBeApproved;
    }

    approveAccount(accountNo):any{
        alert("this is apprve account page in component class")
        this.service.approveAccount(accountNo).subscribe((data)=>console.log(data))
    }

}