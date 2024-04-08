import {CanActivateFn} from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
    console.debug('authGuard: ', route, state);
    return true;
};
