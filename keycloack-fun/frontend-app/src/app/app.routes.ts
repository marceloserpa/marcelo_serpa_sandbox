import { Routes } from '@angular/router';

import { BookList } from './book-list/book-list';
import { canActivateAuthRole } from './auth-guard';


export const routes: Routes = [
    {
        path: 'books',
        component: BookList,
        canActivate: [canActivateAuthRole],
        data: { role: 'view-books' }
      },
];
