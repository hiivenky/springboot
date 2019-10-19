import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
   providedIn:'root' 
})

export class RegistrationService{

    constructor(private myhttp:HttpClient){}

    registerUser(data:any){
       alert("inside registerservice")
      let form = new FormData();
      form.append("userName",data.userName);
      form.append("userPassword",data.userPassword);
      form.append("phoneNo",data.phoneNo);
      return this.myhttp.post("http://localhost:9050/getRegistrationDetails",form);
    }

}