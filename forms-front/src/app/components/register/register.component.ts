import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormsService } from 'src/app/services/forms.service';
import { validarConfPsw, validarCorreoElectronico, validarCorreoElectronicoConf, validarPsw } from 'src/app/utils/validations';
import { DialogOpener } from '../dialog/dialog.opener';

@Component({
  selector: 'app-form-register',
  templateUrl: 'register.component.html',
  styleUrls: ['register.component.scss']
})

export class RegisterComponent implements OnInit {
  form: FormGroup;
  loading: boolean;

  hide = true;
  hideConf = true;

  constructor(
    private formBuilder: FormBuilder,
    private formsService: FormsService,
    private dialog: DialogOpener,
    private router: Router
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      email: [null, [Validators.required, validarCorreoElectronico]],
      emailConf: [null, [Validators.required, validarCorreoElectronicoConf]],
      nombres: [null, Validators.required],
      apellidos: [null, Validators.required],
      password: [null, [Validators.required, validarPsw]],
      passwordConf: [null, [Validators.required, validarConfPsw]],
    });
  }

  registrar() {
    this.loading = true;
    const data = this.form.value;

    const body = {
      nombres: data['nombres'],
      apellidos: data['apellidos'],
      email: data['email'],
      contrasenia: data['password'],
      userType: 2
    };

    this.formsService.registrarUsuario(body).toPromise().then((resp: any) => {
      console.log(resp);
      this.dialog.alert('Se ha registrado el usuario. Inicie sesión para continuar.', () => { this.router.navigate['/login'] });
    }).catch((err: HttpErrorResponse) => {
      if (err.status == 409) {
        this.dialog.alert('Ya existe un usuario registrado con ese correo electrónico.', null);
      } else {
        this.dialog.snack('Ocurrió un error, favor de contactar al administrador.');
      }

      this.loading = false;
    });
  }
}