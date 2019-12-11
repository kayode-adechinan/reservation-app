import { AbstractControl, FormControl } from '@angular/forms';
import { UserService } from '../../user/user.service';
import { timer } from 'rxjs';
import { switchMap ,map } from 'rxjs/operators'; 


export function UniqueEmail(control: AbstractControl) {
  if (!control.value.includes('io')) {
    console.log(control.value);
    return { uniqueEmail: true };
  }
  return null;
}


export const emailExist = 
  (userService: UserService, time: number = 500) => {
    return (input: FormControl) => {
      return timer(time).pipe(
        switchMap(() => userService.checkIfEmalAlreadyExists(input.value)),
        map(res => {
          console.log(res);
          return !res.status ? null : {emailExist: true};
        })
      );
    };
  };