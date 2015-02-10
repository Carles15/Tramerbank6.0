app.config(['$routeProvider',function($routeProvider) {
  
  $routeProvider.when('/successfullPayment', {
    templateUrl: "successfullPayment/successfullPayment.html",
    controller: "SuccessfullPaymentController"
  });
}]);

