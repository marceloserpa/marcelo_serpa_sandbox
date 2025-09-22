import { Component, OnInit, inject } from '@angular/core';
import { AuthForwardService } from '../auth-forward';

@Component({
  selector: 'app-callback',
  standalone: true,
  template: `<p>Finishing login...</p>`,
})
export class CallbackComponent implements OnInit {
  private authForward = inject(AuthForwardService);

  ngOnInit() {
    console.log('CallbackComponent loaded ✅');
    //this.authForward.forwardTokensToBackend();
  }
}
