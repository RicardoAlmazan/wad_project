import { AbstractControl } from "@angular/forms";

const REGEX_EMAIL = RegExp('^([a-zA-Z0-9_\.-]+)@([a-zA-Z0-9_\.-]+)\.([a-zA-Z]{2,5})$');

export function validarCorreoElectronico(control: AbstractControl) {
  if (control && (control.value !== null || control.value === undefined)) {
    if (!REGEX_EMAIL.test(control.value)) {
      return {
        noFormato: true
      };
    } else {
      const correo = control.root.get('emailConf');
      if (correo) {
        if (correo.value === control.value) {
          if (correo.hasError('noCoinciden')) {
            correo.updateValueAndValidity();
          }
        } else {
          correo.updateValueAndValidity();
        }
      }
    }
  }
  return null;
}

export function validarCorreoElectronicoConf(control: AbstractControl) {
  if (control && (control.value !== null || control.value === undefined)) {
    const correo = control.root.get('emailConf');
    if (!REGEX_EMAIL.test(control.value)) {
      if (correo) {
        if (correo.value != control.value) {
          return {
            noCoinciden: true,
            noFormato: true
          };
        } else {
          return {
            noFormato: true
          }
        }
      }
    } else {
      if (correo) {
        if (correo.value !== control.value)
          return {
            noCoinciden: true
          };
      }
    }
  }
}

export function validarPsw(control: AbstractControl) {
  if (control && (control.value !== null || control.value === undefined)) {
    const confPsw = control.root.get('passwordConf');
    console.log(confPsw);
    if (confPsw) {
      if (confPsw.value === control.value) {
        if (confPsw.hasError('noCoinciden')) {
          confPsw.updateValueAndValidity();
        }
      } else {
        confPsw.updateValueAndValidity();
      }
    }
  }
  return null;
}

export function validarConfPsw(control: AbstractControl) {
  if (control && (control.value !== null || control.value === undefined)) {
    const psw = control.root.get('password');
    console.log(psw);
    if (psw) {
      if (psw.value !== control.value) {
        return {
          noCoinciden: true
        };
      }
    }
  }
  return null;
}