import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { environment } from '../../environments/environment';
import { RequestData, ResponseData } from '../interfaces/response';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private readonly httpClient =  inject(HttpClient);
  private baseUrl: string = environment.baseUrl;

  public findMaxKGet(x:number,y:number, n:number) {
    const url = `${this.baseUrl}/api/findMaxK?x=${x}&y=${y}&n=${n}`;
    return this.httpClient.get<ResponseData>(url)
  }

  public findMaxKPost(data: RequestData) {
    const url = `${this.baseUrl}/api/findMaxK`;
    return this.httpClient.post<ResponseData>(url,data)
  }

}
