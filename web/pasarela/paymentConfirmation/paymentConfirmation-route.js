app.config(['$routeProvider',function($routeProvider) {
  
  $routeProvider.when('/paymentConfirmation', {
    templateUrl: "paymentConfirmation/paymentConfirmation.html",
    controller: "paymentConfirmationController"
  });
}]);


