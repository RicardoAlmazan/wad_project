import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Config } from 'src/app/configuration/config';
import { User } from 'src/app/model/User';
import { FormsService } from 'src/app/services/forms.service';

@Component({
  selector: 'app-create-solicitud',
  templateUrl: 'create-solicitud.component.html',
  styleUrls: ['create-solicitud.component.scss']
})

export class CreateSolicitudComponent implements OnInit {
  form: FormGroup;
  mismaPersona: boolean = false;
  estados = [];
  municipios = [];
  tipoProblemas = [];
  loading: boolean;

  user: User;

  constructor(
    private formBuilder: FormBuilder,
    private formsService: FormsService
  ) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      nombres: [null, Validators.required],
      apellidos: [null, Validators.required],
      nombresSolicitante: [null, null],
      apellidosSolicitante: [null, null],
      estado: [null, Validators.required],
      municipio: [null, Validators.required],
      direccion: [null, Validators.required],
      tipoProblema: [null, Validators.required],
      comentarios: [null, null]
    });

    this.user = User.load();

    this.loadData();
  }

  async loadData() {
    this.loading = true;
    this.form.controls['nombresSolicitante'].setValue(this.user.nombres);
    this.form.controls['apellidosSolicitante'].setValue(this.user.apellidos);

    this.estados = await this.formsService.getEstados().toPromise() as any[];
    this.tipoProblemas = Config.tipoProblemas;

    this.loading = false;
  }

  changeMismaPersona(event: any) {
    this.mismaPersona = event.checked;
    if (this.mismaPersona) {
      this.form.controls['nombres'].setValue(this.user.nombres);
      this.form.controls['apellidos'].setValue(this.user.apellidos);
    } else {
      this.form.controls['nombres'].setValue(null);
      this.form.controls['apellidos'].setValue(null);
    }
  }

  async changeEstado(val: any) {
    this.municipios = await this.formsService.getMunicipiosEstado(val.id).toPromise() as any[];
  }

  sendReport() {
    this.loading = true;
    const data = this.form.getRawValue();

    const date = new Date();
    const body = {
      idSolicitante: this.user.id,
      numeroSolicitud: date.toISOString().split("T")[0].replace("-", "").replace("-", ""),
      nombresOwner: data['nombres'],
      apellidosOwner: data['apellidos'],
      fechaSolicitud: date.toISOString(),
      tipoProblema: data['tipoProblema'].id,
      estado: data['estado'].id,
      municipio: data['municipio'].id,
      direccion: data['direccion'],
      comentario: data['comentarios']
    };

    this.formsService.registrarReporte(body).toPromise().then((resp: any) => {
      this.loading = false;
      console.log(resp);
    }).catch((err: any) => {
      this.loading = false;
      console.log(err);
    });


  }
}