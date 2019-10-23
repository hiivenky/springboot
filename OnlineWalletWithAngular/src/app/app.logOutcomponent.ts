import { Component,OnInit} from '@angular/core';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { HttpClient } from 'selenium-webdriver/http';
import { Router } from '@angular/router';

@Component({
    selector: 'app',
    templateUrl: 'app.component.html',
    
})


export class logOut implements OnInit{

    ngOnInit(): void {
        this.logOut();
    }
    constructor(private router: Router){
        console.log("NIn in constructor")
    }
    logOut(){
        alert("inside logout")
        sessionStorage.removeItem('username');
        sessionStorage.removeItem('token');
        this.router.navigate(['login'])
    }

}
