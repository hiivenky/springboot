import {Component,OnInit,OnChanges,OnDestroy} from '@angular/core';
import {RegistrationService} from './service/app.registrationservice';
import {WalletUser} from './_model/app.usermodel';

@Component({
    selector:'registration',
    templateUrl:'./app.registrationPage.html'
})

export class RegistrationComponent implements OnInit{

    model:any={};

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

}