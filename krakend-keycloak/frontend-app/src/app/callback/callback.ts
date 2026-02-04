import { Component, OnInit, inject } from '@angular/core';
import Keycloak from 'keycloak-js';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-callback',
  standalone: true,
  imports: [],
  template: `<p>Finishing login...</p>`,
})
export class CallbackComponent implements OnInit {

  private http = inject(HttpClient);
  private readonly keycloak = inject(Keycloak);
  private router = inject(Router);

  private tokensForwarded = false

  async ngOnInit() {
    console.log('CallbackComponent loaded');

    if(!this.keycloak.authenticated) {
        this.tokensForwarded = false
        console.log('Clean up session')
    }

    if(!this.tokensForwarded && this.keycloak.authenticated) {

        const accessToken = this.keycloak.token
        const idToken = this.keycloak.idToken
        const profile = this.keycloak.profile
    
        const payload = {
            accessToken,
            idToken,
            profile
        }
    
        const response = await this.http.post(`http://movies-api:3000/auth/callback`, payload).toPromise();
          
        console.log('Tokens successfully forwarded to backend:', response);

        this.tokensForwarded = true
    }


  }
}
