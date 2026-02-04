import { Routes } from '@angular/router';

import { BookList } from './book-list/book-list';
import { canActivateAuthRole } from './auth-guard';
import { CallbackComponent } from './callback/callback';


export const routes: Routes = [
  { path: 'callback', component: CallbackComponent },
  {
    path: 'books',
    component: BookList,
    canActivate: [canActivateAuthRole],
    data: { role: 'view-books' }
  },

];
