import { provideHttpClient } from '@angular/common/http';
import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';

import { routes } from './app.routes';
import { AuthGaurdService } from './services/auth-gaurd.service';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideRouter(routes),
    provideHttpClient(),
    JwtHelperService,{ provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
   AuthGaurdService]
};
