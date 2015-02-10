app.config(['$routeProvider',function($routeProvider) {
  
  $routeProvider.when('/movimientobancario/list', {
    templateUrl: "movimientobancario/movimientobancario-list.html",
    controller: "MovimientoBancarioListController"
  });
  
  $routeProvider.when('/movimientobancario/detail/:id', {
    templateUrl: "movimientobancario/movimientobancario-detail.html",
    controller: "MovimientoBancarioDetailController"
  });
}]);

