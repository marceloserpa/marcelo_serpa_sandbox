import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root',
})
export class AuthForwardService {
  private http = inject(HttpClient);
  private keycloakService = inject(KeycloakService);

  async forwardTokensToBackend(): Promise<void> {
    const token = await this.keycloakService.getToken();
    const refreshToken = this.keycloakService.getKeycloakInstance().refreshToken;

    return this.http
      .post('http://localhost:3000/auth/tokens', { token, refreshToken })
      .toPromise()
      .then(() => console.log('Tokens sent ✅'))
      .catch((err) => console.error('Error sending tokens ❌', err));
  }
}
