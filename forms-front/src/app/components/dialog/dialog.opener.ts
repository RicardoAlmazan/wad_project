import { Injectable } from '@angular/core';
import { MatDialog, MatDialogConfig, MatSnackBar } from '@angular/material';
import { DialogComponent } from './dialog.component';
// import { ConfirmationDialogComponent } from './confirmation-dialog.component';

@Injectable()
export class DialogOpener {
  title: string = 'Forms';

  constructor(
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
  ) {
  }

  alert(message: string, onAccept) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data =
    {
      title: this.title,
      message: message
    };

    const dialogRef = this.dialog.open(DialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(res => {
      if (onAccept) {
        onAccept();
      }
    });
  }

  snack(message: string) {
    this.snackBar.open(message, 'Aceptar',
      {
        duration: 4000,
      });
  }

  // confirm(message: string, onAccept, onDecline) {
  //   const dialogConfig = new MatDialogConfig();
  //   dialogConfig.data =
  //   {
  //     title: this.title,
  //     message: message
  //   };

  //   const dialogRef = this.dialog.open(ConfirmationDialogComponent, dialogConfig);

  //   dialogRef.afterClosed().subscribe(res => {
  //     if (res) {
  //       onAccept();
  //     } else {
  //       onDecline();
  //     }
  //   });
  // }
}