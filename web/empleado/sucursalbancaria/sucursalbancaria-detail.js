app.controller("SucursalBancariaDetailController", ["$scope", "$http", "$routeParams", function($scope, $http, $routeParams) {

        $http({
            method: 'GET',
            url: contextPath + '/api/SucursalBancaria/' + $routeParams.id
        }).success(function(data, status, headers, config) {
            $scope.datos = data;
        }).error(function(data, status, headers, config) {
            alert("Ha fallado la petición. Estado HTTP:" + status);
        });

        $scope.get = function() {
            $http({
                method: 'GET',
                url: contextPath + '/api/SucursalBancaria/' + $scope.datos.id
            }).success(function(data, status, headers, config) {
                $scope.datos = data;
            }).error(function(data, status, headers, config) {
                alert("Ha fallado la petición. Estado HTTP:" + status);
            });
        };
        
    }]);