import {Component,OnInit,OnChanges,OnDestroy} from '@angular/core';
import {RegistrationService} from '../service/app.registrationservice';
import {WalletUser} from '../_model/app.usermodel';
import { AuthenticationService } from '../service/authentication.service';

//to do list create a templateUrl for admin login component 
@Component({
    selector:'login',
    templateUrl:'./app.loginPage.html',
    styleUrls:["./app.loginPageCss.css"]
})

export class LoginComponent implements OnInit{

    model:any={};      

    constructor(private service:RegistrationService,private authService:AuthenticationService){
        console.log("NIn in constructor")
    }

    ngOnInit(): void {
        console.log("inside login component ")
    }
    authenticate(username,password):any{
        this.authService.authenticate(username,password);
    }

}