app.controller("CuentaBancariaListMovimientoBancarioController", function($scope, $http, $routeParams) {
    $scope.tipoMovimientos = [
        {
            idTipo: "DEBE",
            descripcion: "DEBE"
        },
        {
            idTipo: "HABER",
            descripcion: "HABER"
        }];
    $http({
        method: 'GET',
        url: contextPath + '/api/CuentaBancaria/' + $routeParams.id + '/MovimientoBancario'
    }).success(function(data, status, headers, config) {
        $scope.datos = data[0];
        for (var i = 0; i < data[1].length; i++) {
            data[1][i].fecha = new Date(data[1][i].fecha);
        }
        $scope.movimientos = data[1];
    }).error(function(data, status, headers, config) {
        alert("Ha fallado la petición. Estado HTTP:" + status);
    });
    
    $http({
        method: 'GET',
        url: contextPath + '/api/CuentaBancaria/'
    }).success(function(data, status, headers, config) {
        $scope.datos2 = data;
    }).error(function(data, status, headers, config) {
        alert("Ha fallado la petición. Estado HTTP:" + status);
    });

    $scope.findAll = function() {
        $http({
            method: 'GET',
            url: contextPath + '/api/CuentaBancaria/' + $routeParams.id + '/MovimientoBancario'
        }).success(function(data, status, headers, config) {
            $scope.datos = data[0];
            for (var i = 0; i < data[1].length; i++) {
                data[1][i].fecha = new Date(data[1][i].fecha);
            }
            $scope.movimientos = data[1];
        }).error(function(data, status, headers, config) {
            alert("Ha fallado la petición. Estado HTTP:" + status);
        });
    };

    $scope.onClickButtonDelete = function(id) {
        $http({
            method: 'DELETE',
            url: contextPath + '/api/MovimientoBancario/' + id
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
            url: contextPath + '/api/MovimientoBancario/',
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
            url: contextPath + '/api/MovimientoBancario',
            data: listaInsertar
        }).success(function(data, status, headers, config) {
            $scope.findAll();
            alert("El registro a sido insertado con exito");
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