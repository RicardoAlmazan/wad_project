import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Config } from '../configuration/config';

@Injectable()
export class FormsService {
  private baseUrl: string = Config.env.apiUrl;

  constructor(
    private http: HttpClient
  ) { }
  
  login(username: string, password: string){
    return this.http.post(`${this.baseUrl}/users/login`, {email: username, contrasenia: password});
  }
}