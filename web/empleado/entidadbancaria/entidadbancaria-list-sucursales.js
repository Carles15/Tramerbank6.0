app.controller("EntidadBancariaListSucursalesController", function($scope, $http, $routeParams) {
    $http({
        method: 'GET',
        url: contextPath + '/api/EntidadBancaria/' + $routeParams.id + '/SucursalesBancarias'
    }).success(function(data, status, headers, config) {
        $scope.totalSucursales = data[1].length;
        $scope.datos = data[0];
        $scope.sucursales = data[1];
    }).error(function(data, status, headers, config) {
        alert("Ha fallado la petición. Estado HTTP:" + status);
    });

    $http({
        method: 'GET',
        url: contextPath + '/api/EntidadBancaria'
    }).success(function(data, status, headers, config) {
        $scope.datos2 = data;
    }).error(function(data, status, headers, config) {
        alert("Ha fallado la petición. Estado HTTP:" + status);
    });

    $scope.findAll = function() {
        $http({
            method: 'GET',
            url: contextPath + '/api/EntidadBancaria/' + $routeParams.id + '/SucursalesBancarias'
        }).success(function(data, status, headers, config) {
            $scope.totalSucursales = data[1].length;
            $scope.datos = data[0];
            $scope.sucursales = data[1];
        }).error(function(data, status, headers, config) {
            alert("Ha fallado la petición. Estado HTTP:" + status);
        });
    };

    $scope.onClickButtonDelete = function(id) {
        $http({
            method: 'DELETE',
            url: contextPath + '/api/SucursalBancaria/' + id
        }).success(function(data, status, headers, config) {
            alert("El registro a sido borrado con exito");
            $scope.findAll();
        }).error(function(data, status, headers, config) {
            alert("Ha fallado la petición. Estado HTTP:" + status);
        });
    };

    $scope.onClickButtonUpdate = function(objeto) {
        $http({
            method: 'PUT',
            url: contextPath + '/api/SucursalBancaria/',
            data: objeto
        }).success(function(data, status, headers, config) {
            alert("El registro a sido actualizado con exito");
            $scope.findAll();
        }).error(function(data, status, headers, config) {
            if (status == 400) {
                mensaje = "";
                for (a in data) {
                    mensaje += data[a].message + '\n';
                }
                alert(mensaje);
            } else {
                alert("Ha fallado la petición. Estado HTTP:" + status);
            }
        });
    };

    $scope.onClickButtonInsert = function(listaInsertar) {
        $http({
            method: 'POST',
            url: contextPath + '/api/SucursalBancaria',
            data: listaInsertar
        }).success(function(data, status, headers, config) {
            $scope.findAll();
            alert("El registro a sido insertad con exito");
        }).error(function(data, status, headers, config) {
            if (status == 400) {
                mensaje = "";
                for (a in data) {
                    mensaje += data[a].message + '\n';
                }
                alert(mensaje);
            } else {
                alert("Ha fallado la petición. Estado HTTP:" + status);
            }
        });
    };
});