app.config(['$routeProvider',function($routeProvider) {
  
  $routeProvider.when('/entidadbancaria/list', {
    templateUrl: "entidadbancaria/entidadbancaria-list.html",
    controller: "EntidadBancariaListController"
  });
  
  $routeProvider.when('/entidadbancaria/detail/:id', {
    templateUrl: "entidadbancaria/entidadbancaria-detail.html",
    controller: "EntidadBancariaDetailController"
  });
  
   $routeProvider.when('/entidadbancaria/list/sucursales/:id', {
    templateUrl: "entidadbancaria/entidadbancaria-list-sucursales.html",
    controller: "EntidadBancariaListSucursalesController"
  });
}]);


