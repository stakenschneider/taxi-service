const baseUrl = 'http://localhost:8080/api/';

export function createEnv() {
  return {
    signIn:  baseUrl + 'sign-in',
    signUp:  baseUrl + 'sign-up'};
}

export let environment = createEnv();
