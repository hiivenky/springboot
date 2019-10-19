import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import {FormsModule}      from '@angular/forms';
import {HttpClientModule} from '@angular/common/http'; 
import {RegistrationComponent} from './app.registrationcomponent'
import {AdminComponent}  from './app.admincomponent';

@NgModule({
    imports: [
        BrowserModule,FormsModule,HttpClientModule
        
    ],
    declarations: [
        AppComponent,RegistrationComponent,AdminComponent
		],
    providers: [ ],
    bootstrap: [AppComponent]
})

export class AppModule { }