app.controller("MainControllerPasarela", function ($scope, $http, $window) {

    $scope.sendData = function (credential) {
        $http({
            method: 'POST',
            url: contextPath + '/api/session/empleado/',
            data: credential
        }).success(function (data, status, headers, config) {
            $window.location.href = contextPath + '/empleado/index.html'
        }).error(function (data, status, headers, config) {
            alert("A tu casa create un user y vuelve so imperial");
        });
    };

});

