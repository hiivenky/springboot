import {Component,OnInit,OnChanges,OnDestroy} from '@angular/core';
import {RegistrationService} from '../service/app.registrationservice';
import {WalletUser} from '../_model/app.usermodel';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
    selector:'registration',
    templateUrl:'./app.registrationPage.html',
    styleUrls:['./app.loginandregister.css']
})
/**
	 *author: Venkatesh
	 *Description : This class calls the service functions for registration operations  
	 *created Date: 20/10/2019
	 *last modified : 20/10/2019            
	 */
export class RegistrationComponent implements OnInit{

    model:any={};
    confirmpassword:string='';
    buttondisable=true;
    nameStatus=false;
    phoneNoStatus=false
    passwordStatus=false
    registrationStatus:string='';

    constructor(private service:RegistrationService,private router:Router){
        console.log("NIn in constructor")
    }

    ngOnInit(): void {
        console.log("inside registration component ")
        if(sessionStorage.getItem('username')==''){
            this.router.navigate(['login'])
        }
    }

    registerUser(error: HttpErrorResponse):any{
        console.log(this.model);
        this.service.registerUser(this.model).subscribe((body)=>console.log("ssssss"),
        error=>{this.registrationStatus=error.error
            alert(error.error)
            this.router.navigate(['/registration'])
            this.router.navigate(['/login'])    
        
    });  
    }

    validate(){
        
        if(this.model.userName!=''&&this.model.userName.match("[a-zA-Z\\s]")&&this.model.userName.length>=4){
            console.log("entered")
            this.nameStatus=true;
        }
        else{
            console.log("false block")
            this.nameStatus=false;
            return true
        }
        if(this.model.userPassword!=''&& this.model.userPassword.length>=8){
            console.log("inside password validation")
            this.passwordStatus=true
        }
        else{
            console.log("inside password validation false")
            this.passwordStatus=false
            return true;
        }
        if(this.model.phoneNo!=''&&this.model.phoneNo.match("\\d{10}")){
            console.log("inside phoneNumber validation")
            this.phoneNoStatus=true
        }
        else{
            this.phoneNoStatus=false
            return true;
        }
        if(this.confirmpassword==''||this.confirmpassword!=this.model.userPassword){
            console.log("inside confirmpassword validation")
            return true;
        }
        return false;
    }
    getNameStatus():any{
        return this.nameStatus
    }

    getPasswordStatus():any{
        return this.passwordStatus
    }

}