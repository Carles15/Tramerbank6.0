app.config(['$routeProvider',function($routeProvider) {
  
  $routeProvider.when('/sucursalbancaria/list', {
    templateUrl: "sucursalbancaria/sucursalbancaria-list.html",
    controller: "SucursalBancariaListController"
  });
  
  $routeProvider.when('/sucursalbancaria/detail/:id', {
    templateUrl: "sucursalbancaria/sucursalbancaria-detail.html",
    controller: "SucursalBancariaDetailController"
  });
  
  $routeProvider.when('/sucursalbancaria/list/cuentasbancarias/:id',{
      templateUrl: "sucursalbancaria/sucursalbancaria-list-cuentasbancarias.html",
      controller: "SucursalBancariaListCuentasBancariasController"
  });
}]);


