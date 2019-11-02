import {Component,OnInit,OnChanges,OnDestroy} from '@angular/core';
import {AdminService} from '../service/app.adminservice';
import { Router } from '@angular/router';


@Component({
    selector:'admin',
    templateUrl:'./app.adminPage.html',
    styleUrls:["./app.adminPage.css"]
})
/**
	 *author: Venkatesh
	 *Description : This class calls the service functions for admin operations  
	 *created Date: 19/10/2019
	 *last modified : 19/10/2019            
	 */
export class AdminComponent implements OnInit{

    accountsToBeApproved:any={}

    constructor(private service:AdminService,private router:Router){
        console.log("NIn in constructor admin constructor service");
        this.service.getAccountsToBeApproved().subscribe((data)=>{this.accountsToBeApproved=data;
        }
        ,(err) => {
            console.log("inside error");
          });
    }

    ngOnInit(): void {
        console.log("inside registration component ")
        if(sessionStorage.getItem('username')==''){
            this.router.navigate(['login'])
        }
    }

    //this function updates returns the accounts to be approved
    getAccounts():any{
        return this.accountsToBeApproved;
    }
    //this function is used to approve account
    approveAccount(accountNo):any{
        this.service.approveAccount(accountNo).subscribe((data)=>console.log(data))
        this.router.routeReuseStrategy.shouldReuseRoute = function () {
            return false;
          };
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigate(['/adminPage']);
    }

}