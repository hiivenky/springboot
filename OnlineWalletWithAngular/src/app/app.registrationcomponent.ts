import {Component,OnInit,OnChanges,OnDestroy} from '@angular/core';
import {RegistrationService} from './service/app.registrationservice';
import {WalletUser} from './_model/app.usermodel';

@Component({
    selector:'registration',
    templateUrl:'./app.registrationPage.html',
    styleUrls:['./app.loginandregister.css']
})

export class RegistrationComponent implements OnInit{

    model:any={};
    confirmpassword:string='';
    buttondisable=true;
    nameStatus=false;
    phoneNoStatus=false
    passwordStatus=false

    constructor(private service:RegistrationService){
        console.log("NIn in constructor")
    }

    ngOnInit(): void {
        console.log("inside registration component ")
    }

    registerUser():any{
        alert("register");
        console.log(this.model);
        this.service.registerUser(this.model).subscribe((data)=>console.log(data));
    }

    validate(){
        let numRegex = new RegExp('^[0-9]{10}$')
        if(this.model.userName.match("[a-zA-Z\\s]")&&this.model.userName.length>=4){
            console.log("entered")
            this.nameStatus=true;
        }
        else{
            console.log("false block")
            this.nameStatus=false;
            return true
        }
        if(this.model.userPassword.length>=8){
            console.log("inside password validation")
            this.passwordStatus=true
        }
        else{
            console.log("inside password validation false")
            this.passwordStatus=false
            return true;
        }
        if(this.model.phoneNo.match("\\d{10}")){
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