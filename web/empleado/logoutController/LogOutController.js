app.controller("LogOutController", ["$scope", "$http","$window", function($scope,$http,$window){
        $scope.closeSession = function() {
            $http({
                method: 'DELETE',
                url: contextPath +'/api/session/empleado'
            }).success(function(data, status, headers, config) {
                $window.location.href = contextPath +'/site/index.html'
            }).error(function(data, status, headers, config) {
                alert("Ahora eres parte de la página para siempre!!");
            });
        };
}])

