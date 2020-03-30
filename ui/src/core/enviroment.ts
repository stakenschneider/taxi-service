const baseUrl = 'http://localhost:8080/api/';

export function createEnv() {
  return {
    signIn: baseUrl + 'sign-in',
    signUp: baseUrl + 'sign-up',
    getPaymentMethod: baseUrl + 'getPaymentMethods',
    getRate: baseUrl + 'getRate',
    getPersonById: baseUrl + 'getClientById',
    signOut: baseUrl + 'sign-out',
    getHistoryOfTips: baseUrl +'getHistoryOfTrips'
  };
}

export let environment = createEnv();
