import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/model/User';
import { FormsService } from 'src/app/services/forms.service';
import { DialogOpener } from '../dialog/dialog.opener';

@Component({
  selector: 'app-login',
  templateUrl: 'login.component.html',
  styleUrls: ['login.component.scss']
})

export class LoginComponent implements OnInit {
  form: FormGroup;
  hide = true;
  loading = false;
  public loginInvalid: boolean;
  private formSubmitAttempt: boolean;
  private returnUrl: string;

  constructor(
    private formService: FormsService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private dialog: DialogOpener
    // private authService: AuthService
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      username: ['', Validators.email],
      password: ['', Validators.required]
    });

    this.returnUrl = this.route.snapshot.queryParams['url'] || '/home';

    localStorage.setItem('user', null);
  }

  async login() {
    this.loginInvalid = false;
    this.formSubmitAttempt = false;
    this.loading = true;
    if (this.form.valid) {
      const username = this.form.get('username').value;
      const password = this.form.get('password').value;

      this.formService.login(username, password).toPromise().then((resp: any) => {
        console.log("LOGIN");
        const user = new User();
        user.id = resp['id'];
        user.nombres = resp['nombres'];
        user.apellidos = resp['apellidos'];
        user.email = resp['email'];
        user.userType = resp['userType'];
        user.authenticatedAt = Date.now();

        localStorage.setItem('user', JSON.stringify(user));

        console.log(user);
        this.loading = false;
        this.router.navigateByUrl(this.returnUrl);
      }).catch((err: HttpErrorResponse)=>{
        if(err.status == 404){
          this.loginInvalid = true;
          this.dialog.snack("El usuario y/o la contraseña son inválidos.");
        }
        this.loading = false;
      });
    } else {
      this.formSubmitAttempt = true;
    }
  }
}