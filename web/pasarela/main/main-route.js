app.config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/', {
            templateUrl: "main/main.html",
            controller: "MainControllerPasarela"
        });
        
        $routeProvider.when('/pasarelapago/:cuentaDestino/:idCarrito/:importe/:concepto', {
            templateUrl: "main/main.html",
            controller: "MainControllerPasarela"
        });
    }]);
