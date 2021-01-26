import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Config } from '../configuration/config';

@Injectable()
export class FormsService {
  private baseUrl: string = Config.env.apiUrl;

  constructor(
    private http: HttpClient
  ) { }

  login(username: string, password: string) {
    return this.http.post(`${this.baseUrl}/users/login`, { email: username, contrasenia: password });
  }

  registrarUsuario(data: any) {
    return this.http.post(`${this.baseUrl}/users`, data);
  }

  getEstados(){
    return this.http.get(`${this.baseUrl}/catalog/estados`);
  }

  getMunicipiosEstado(idEstado: number){
    return this.http.get(`${this.baseUrl}/catalog/estados/${idEstado}/municipios`);
  }

  registrarReporte(data: any){
    return this.http.post(`${this.baseUrl}/reports`, data);
  }
}