
import { environment } from 'src/environments/environment';

export class Config {
  static title = 'Forms';
  static env = environment;

  static sessionTimeMins: number = 30;

  static tipoProblemas = [
    { id: 1, nombre: 'SIN SERVICIO' },
    { id: 2, nombre: 'SERVICIO DEFICIENTE' }
  ]
}