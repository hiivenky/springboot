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
import {UserComponent} from './userPart/app.usercomponent';
import {ShowTransactionsComponenent}  from './userPart/app.showtransactionscomponent';
import {logOut}  from './app.logOutcomponent'
    import { from } from 'rxjs';
//mapping of urls to components
const myroute:Routes=[
    {path:'registration',component:RegistrationComponent},
    {path:'login',component:LoginComponent},
    {path:'adminPage',component:AdminComponent},
    {path:'homePage',component:homePage},
    {path:'userPage',component:UserComponent},
    {path:'showtransaction',component:ShowTransactionsComponenent},
    {path:'logOut',component:logOut},
    { path: '**', redirectTo: '/homePage', pathMatch: 'full' },
]

@NgModule({
    imports: [
        BrowserModule,FormsModule,HttpClientModule,RouterModule.forRoot(myroute)
        
    ],
    declarations: [
        AppComponent,RegistrationComponent,AdminComponent,LoginComponent,homePage
        ,UserComponent,ShowTransactionsComponenent,logOut
		],
    providers: [ ],
    bootstrap: [AppComponent]
})

export class AppModule { }