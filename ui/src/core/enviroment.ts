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
    getCarModels: baseUrl + 'getCarModels',
    getActiveTrip: baseUrl + 'getActiveTrip',
    getFreeTrips: baseUrl + 'getFreeTrips',
    takeTrip: baseUrl + 'takeTrip',
    endTrip: baseUrl + 'endTrip',
    setGrade: baseUrl + 'setGrade',
    getCurrentTrip: baseUrl + 'getCurrentTrip',
    getHistory: baseUrl + 'getHistory',
    getAllTrips: baseUrl + 'getAllTrips',
    getAllClients: baseUrl + 'getAllClients',
    getAllDrivers: baseUrl + 'getAllDrivers',
    deletePerson: baseUrl + 'deletePerson'
  };
}

export let environment = createEnv();
