import {Injectable} from '@angular/core';
import {HttpClient,HttpParams, HttpHeaders} from '@angular/common/http';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { Router } from '@angular/router';
// import * as FileSaver from 'file-saver';
// import * as XLSX from 'xlsx';

const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';
@Injectable({
   providedIn:'root'
})

export class UserService{
  headers:any

  constructor(private myhttp:HttpClient,private router:Router){
    alert("inside user service "+sessionStorage.getItem("token"))
    this.headers = new HttpHeaders().set("Authorization",sessionStorage.getItem("token"));
    if(sessionStorage.getItem('username').length==0){
      alert('inside cons user')
      router.navigate(['login'])
    }
  }

  getAmount(amount):any{
    alert(amount+"inside service")
    let body = new HttpParams();
    body = body.set('amount', amount);

    return this.myhttp.post("http://localhost:9050/getAmount?",body,{headers:this.headers}
    );
}

transferAmount(phoneNo,amount):any{
  alert(phoneNo +" "+ amount)
  let body2 = new FormData();
  
  body2.append("phoneNo",phoneNo);
  body2.append("amount",amount);
  return this.myhttp.post("http://localhost:9050/transferAmount",body2,{headers:this.headers});
                                                                     
}
  
  getTransactions(fromDate,toDate):any{
    alert("inside userservice")
    alert("print "+fromDate+" "+toDate)
  let form = new HttpParams();
  form=form.set("fromDate",fromDate);
  form=form.set("toDate",toDate);
  return this.myhttp.post("http://localhost:9050/getTransactionsPage?",form,{headers:this.headers});

}

// public exportAsExcelFile(json: any[], excelFileName: string): void {
//   alert("excel")
//   const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(json);
//   const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
//   const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
//   this.saveAsExcelFile(excelBuffer, excelFileName);
// }
// public saveAsExcelFile(buffer: any, fileName: string): void {
//    const data: Blob = new Blob([buffer], {type: EXCEL_TYPE});
//    FileSaver.saveAs(data, fileName + '_export_' + new  Date().getTime() + EXCEL_EXTENSION);
// }
}





















































