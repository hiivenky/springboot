import {Component,OnInit,OnChanges,OnDestroy} from '@angular/core';
import {RegistrationService} from './service/app.registrationservice';
import {WalletUser} from './_model/app.usermodel';

//to do list create a templateUrl for admin login component 
@Component({
    selector:'',
    templateUrl:''
})

export class LoginComponent implements OnInit{

    model:any={};      

    constructor(private service:RegistrationService){
        console.log("NIn in constructor")
    }

    ngOnInit(): void {
        console.log("inside login component ")
    }

}