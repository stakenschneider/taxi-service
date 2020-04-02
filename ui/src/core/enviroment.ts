const baseUrl = 'http://localhost:8080/api/';

export function createEnv() {
  return {
    signIn: baseUrl + 'sign-in',
    signUp: baseUrl + 'sign-up',
    getPaymentMethod: baseUrl + 'getPaymentMethods',
    getRate: baseUrl + 'getRate',
    getPersonById: baseUrl + 'getPersonById',
    signOut: baseUrl + 'sign-out',
    getHistoryOfTips: baseUrl + 'getHistoryOfTrips',
    requestCar: baseUrl + 'requestCar',
    denyTrip: baseUrl + 'denyTrip',
    getCarColor: baseUrl + 'getCarColor',
    setCar: baseUrl + 'setCar',
    setPassport: baseUrl + 'setPassport',
    getCarModels: baseUrl + 'getCarModels'
  };
}

export let environment = createEnv();
