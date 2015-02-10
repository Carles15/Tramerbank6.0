app.config(['$routeProvider',function($routeProvider) {
  
  $routeProvider.when('/accounts', {
    templateUrl: "accountSelection/accountSelection.html",
    controller: "AccountSelectionController"
  });
}]);


