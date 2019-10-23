import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

export class User{
  constructor(
    public status:string,
     ) {}
  
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  user:any={}
  role:string

  constructor(
    private httpClient:HttpClient,private router: Router
  ) { 
     }

     authenticate(username, password) {
       alert(username+"good"+password)
        // return this.httpClient.post<any>('http://localhost:9050/authenticate',{username,password}).pipe(
        //  map(
        //    userData => {
             
        //     sessionStorage.setItem('username',username);
        //     let tokenStr= 'Bearer '+userData.token;
        //     alert(tokenStr);
        //     sessionStorage.setItem('token', tokenStr);
        //     return userData;
        //    }
        //  )
    
        // );
      //
      return this.httpClient.post<any>('http://localhost:9050/authenticate',{username,password}) .subscribe
      (
        (data)=>{
          sessionStorage.setItem('username',username);
          let tokenStr='Bearer '+data.token;
          alert(tokenStr);
          sessionStorage.setItem('token',tokenStr);
          this.user=data;
          return data;
        },(error) => {
          this.router.navigate(['login'])
        }
      )
      
    }
  getDbUser(loginName):any{
    alert(loginName);
    this.user=this.httpClient.get("http://localhost:9050/getUser?loginName="+loginName).subscribe(
      (data)=>{alert(data["roles"])
      if(data["roles"]=='ROLE_ADMIN'){
        this.router.navigate(['/adminPage'])
      }
      else{
        this.router.navigate(['/userPage'])
      }
    
    })
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
  }
  getUser(){
    
    return this.role;
  }
}