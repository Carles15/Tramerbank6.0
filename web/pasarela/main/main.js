app.controller("MainControllerPasarela", ["$scope", "$http", "$rootScope", "$routeParams", "$window", function($scope, $http, $rootScope, $routeParams, $window) {
        $('header').show();    
        $scope.mensajeComunicacion = {
            inicio:"Su compra está apunto de ser realizada, solo quedan unos pocos paso más. Acceda a su cuenta en 'El primer banco Jedi: Tramerbank' para finalizar el proceso.",
            error:"Su usuario o contraseña no coinciden"
        }
        
        $scope.mensajeMain = $scope.mensajeComunicacion.inicio;
        
        $rootScope.peticionPago = {
            codigoCuentaClienteOrigen: "",
            codigoCuentaClienteDestino: $routeParams.cuentaDestino,
            idCarrito: $routeParams.idCarrito,
            importe: $routeParams.importe,
            concepto: $routeParams.concepto
        }
        
        $rootScope.emailData = {
            email: ""
        }

        $scope.login = function(credential) {
            $http({
                method: 'POST',
                url: contextPath + '/api/session/empleado/',
                data: credential
            }).success(function(data, status, headers, config) {
                $rootScope.emailData.email = $scope.credential.email;
                $window.location.href = '#/accounts';
                
            }).error(function(data, status, headers, config) {
                alert("Ha fallado la petición. Estado HTTP:" + status);
                $scope.mensajeMain = $scope.mensajeComunicacion.error;
            });
        }


        //$rootScope.peticionPago.cuentaOrigen = $scope.selectorCuenta;
    }]);


