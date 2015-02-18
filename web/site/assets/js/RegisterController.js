app.controller("RegisterController", ["$scope", "$http",function($scope,$http){
        
        $scope.sendData = function() {
            $http({
                method: 'POST',
                url: contextPath +'/api/Usuario/',
                data: $scope.datos
            }).success(function(data, status, headers, config) {
                $window.location.href = contextPath +'/tramerbank/site/index.html#login'
                alert("Se ha registrado de manera satisfactoria, revisa tu email de activación");
            }).error(function(data, status, headers, config) {
                alert("Usuario NO registrado, ocurrió un problema");
            });
        };
}])

