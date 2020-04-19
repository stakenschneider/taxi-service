const baseUrl = 'http://localhost:8080/api/';

export function createEnv() {
  return {
    signIn: baseUrl + 'sign-in',
    signUp: baseUrl + 'sign-up',
    getPaymentMethod: baseUrl + 'getPaymentMethods',
    getRate: baseUrl + 'getRate',
    getPersonById: baseUrl + 'getPersonById',
    signOut: baseUrl + 'sign-out',
    requestCar: baseUrl + 'requestCar',
    denyTrip: baseUrl + 'denyTrip',
    getCarColor: baseUrl + 'getCarColor',
    setCar: baseUrl + 'setCar',
    setPassport: baseUrl + 'setPassport',
    getCarModels: baseUrl + 'getCarModels',
    getActiveTrip: baseUrl + 'getActiveTrip',
    takeTrip: baseUrl + 'takeTrip',
    endTrip: baseUrl + 'endTrip',
    setGrade: baseUrl + 'setGrade',
    getCurrentTrip: baseUrl + 'getCurrentTrip',
    generate: baseUrl + 'generate',
    deletePerson: baseUrl + 'deletePerson',
    getGridData: baseUrl + 'getGridData',
    getTripById: baseUrl + 'getTripById'
  };
}

export let environment = createEnv();
