import {Component,OnInit,OnChanges,OnDestroy} from '@angular/core';
import {AdminService} from '../service/app.adminservice';
import { Router } from '@angular/router';


@Component({
    selector:'admin',
    templateUrl:'./app.adminPage.html',
    styleUrls:["./app.adminPage.css"]
})

export class AdminComponent implements OnInit{

    accountsToBeApproved:any={}

    constructor(private service:AdminService,private router:Router){
        console.log("NIn in constructor admin constructor service");
        alert("inside get accounts to be approved")
        this.service.getAccountsToBeApproved().subscribe((data)=>{this.accountsToBeApproved=data;
        alert(data);
        }
        ,(err) => {
            console.log("inside error");
          });
    }

    ngOnInit(): void {
        console.log("inside registration component ")
        if(sessionStorage.getItem('username')==''){
            alert('inside cons user')
            this.router.navigate(['login'])
        }
    }

    getAccounts():any{
        return this.accountsToBeApproved;
    }

    approveAccount(accountNo):any{
        this.service.approveAccount(accountNo).subscribe((data)=>console.log(data))
        this.router.routeReuseStrategy.shouldReuseRoute = function () {
            return false;
          };
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigate(['/adminPage']);
    }

}