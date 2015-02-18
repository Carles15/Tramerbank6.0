app.controller("AccountSelectionController", ["$scope", "$http", "$rootScope","$window", function ($scope, $http, $rootScope,$window) {
        $http({
            method: 'GET',
            url: contextPath + '/api/Usuario/correo/' + $rootScope.emailData.email
        }).success(function (data, status, headers, config) {
            $http({
                    method: 'GET',
                    url: contextPath + '/api/CuentaBancaria/' + data.idCuentaBancaria
                }).success(function (data, status, headers, config) {
                    $scope.datos = data;
                }).error(function (data, status, headers, config) {
                    alert("Ha fallado la petición. Estado HTTP:" + status);
                });
        }).error(function (data, status, headers, config) {
            alert("Ha fallado la petición. Estado HTTP:" + status);
        });

        $scope.get = function () {
            $http({
                method: 'GET',
                url: contextPath + '/api/Usuario/correo/' + $rootScope.emailData.email
            }).success(function (data, status, headers, config) {
                $http({
                    method: 'GET',
                    url: contextPath + '/api/CuentaBancaria/' + data.idCuentaBancaria
                }).success(function (data, status, headers, config) {
                    $scope.datos = data;
                }).error(function (data, status, headers, config) {
                    alert("Ha fallado la petición. Estado HTTP:" + status);
                });

            }).error(function (data, status, headers, config) {
                alert("Ha fallado la petición. Estado HTTP:" + status);
            });
        };

        $scope.onClickButtonSelectAccount = function(objeto){
            $rootScope.peticionPago.codigoCuentaClienteOrigen = objeto.numCuenta;
            $window.location.href ='#/paymentConfirmation';
        };
    }]);
