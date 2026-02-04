import { provideRouter } from '@angular/router';
import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { includeBearerTokenInterceptor, KeycloakService } from 'keycloak-angular';

import { provideKeycloakAngular } from './keycloak.config';
import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideKeycloakAngular(),
    KeycloakService,
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(withInterceptors([includeBearerTokenInterceptor]))
  ]
};
