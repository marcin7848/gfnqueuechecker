import {Injectable} from "@angular/core";
import {tap} from "rxjs/operators";
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from "@angular/common/http";
import {Observable} from "rxjs";
import {CookieService} from 'ngx-cookie-service';
import {Router} from "@angular/router";

@Injectable()
export class Interceptor implements HttpInterceptor {
  constructor(private cookieService: CookieService,
              private router: Router) {
  }

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    if(this.cookieService.get("Authorization")){
      request = request.clone({
        headers: request.headers.append('Authorization', this.cookieService.get("Authorization"))
      });
    }

    return next.handle(request).pipe(
      tap(
        event => {
        },
        error => {
          if (error['status'] == 403) {
            this.cookieService.delete("Authorization", "/");
          }
        }
      )
    );
  }
}
