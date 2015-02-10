app.config(['$routeProvider',function($routeProvider) {
  
  $routeProvider.when('/cuentabancaria/list', {
    templateUrl: "cuentabancaria/cuentabancaria-list.html",
    controller: "CuentaBancariaListController"
  });

  $routeProvider.when('/cuentabancaria/list/movimientosbancarios/:id',{
      templateUrl: "cuentabancaria/cuentabancaria-list-movimientobancario.html",
      controller: "CuentaBancariaListMovimientoBancarioController"
  });
}]);


