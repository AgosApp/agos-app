import {Injectable} from "@angular/core";
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {AuthService} from "../services/auth_service/auth.service";
import {MatSnackBar} from "@angular/material/snack-bar";


@Injectable({
  providedIn: 'any'
})
export class AuthorizationInterceptor implements HttpInterceptor {

  constructor(public router: Router, public authService : AuthService, private matSnackBar : MatSnackBar) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (localStorage.getItem('token')) {
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${AuthService.getToken()}`
        }
      });
    }
     return next.handle(request)//.subscribe(data => {
    // }, error => {
    //   this.matSnackBar.open("Vous devez vous reconnecter!", "Fermer", {
    //     duration: 6000,
    //   }),
    //     this.router.navigateByUrl("login")
    // }, () => {
    // })
  }

}
