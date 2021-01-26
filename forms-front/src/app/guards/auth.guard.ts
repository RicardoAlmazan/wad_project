import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Config } from '../configuration/config';
import { User } from '../model/User';


@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (localStorage.getItem('user')) {
      const user: User = User.load();

      console.log(user);
      if (user && user.authenticatedAt) {
        console.log((user.authenticatedAt + (Config.sessionTimeMins * 60 * 1000)));
        console.log(Date.now(), (user.authenticatedAt + (Config.sessionTimeMins * 60 * 1000)));
        if (Date.now() < (user.authenticatedAt + (Config.sessionTimeMins * 60 * 1000))) {
          console.log("returning true")
          return true;
        }
      }
    }

    this.router.navigate(['/login'], { queryParams: { url: state.url } });

    return false;
  }
}