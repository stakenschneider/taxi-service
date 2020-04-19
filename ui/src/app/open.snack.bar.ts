import {SnackBarComponent} from './snack-bar/snack-bar.component';
import {MatSnackBar} from '@angular/material/snack-bar';

export const openSnackBar = (snackBar: MatSnackBar, message: string, durationSecond: number) => {
    snackBar.openFromComponent(SnackBarComponent, {
      duration: durationSecond * 1000,
      data: message
    });
};
