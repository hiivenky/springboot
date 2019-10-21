import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import {FormsModule}      from '@angular/forms';
import {HttpClientModule} from '@angular/common/http'; 
import {RegistrationComponent} from './registrationPart/app.registrationcomponent'
import {AdminComponent}  from './adminPart/app.admincomponent';
import {LoginComponent} from './loginPart/app.logincomponent';
import {homePage} from './homePart/app.homecomponent';
import {Routes,RouterModule}  from '@angular/router'; 



const myroute:Routes=[
    {path:'registration',component:RegistrationComponent},
    {path:'login',component:LoginComponent},
    {path:'adminPage',component:AdminComponent},
    {path:'homePage',component:homePage},
    { path: '**', redirectTo: '/homePage', pathMatch: 'full' },
]

@NgModule({
    imports: [
        BrowserModule,FormsModule,HttpClientModule,RouterModule.forRoot(myroute)
        
    ],
    declarations: [
        AppComponent,RegistrationComponent,AdminComponent,LoginComponent,homePage
		],
    providers: [ ],
    bootstrap: [AppComponent]
})

export class AppModule { }