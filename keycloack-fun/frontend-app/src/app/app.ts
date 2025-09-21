import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { Nav } from './nav/nav'

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Nav],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend-app');
}
