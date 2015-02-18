app.controller("paymentConfirmationController", ["$scope", "$http", "$rootScope","$window", function ($scope, $http, $rootScope,$window) {
        //Cargar Datos de la factura
        $scope.datosConfirmacion = $rootScope.peticionPago;
        //        JQuery visual!
        $('document').ready(function () {
            $('div#buyEmphasisCover').mouseenter(function () {
                $('div#Imagenes').css('width','175px');
                $('div#Imagenes').css('height','174px');
                $('div#Imagenes').css('margin-left','15%');
                $('div#Imagenes').css('background-image','url("images/stormtrooperFaster.gif")');
                $('div#Imagenes').css('background-size','cover');
            });

            $('div#buyEmphasisCover').mouseleave(function () {
                $('div#Imagenes').css('width','450px');
                $('div#Imagenes').css('height','300px');
                $('div#Imagenes').css('margin-left','6.5%');
                $('div#Imagenes').css('background-image','url("images/correcto.jpg")');
            });
            
            $('a#EmphasisCore').mouseenter(function () {
                $('div#Imagenes').css('width','175px');
                $('div#Imagenes').css('height','174px');
                $('div#Imagenes').css('margin-left','15%');
                $('div#Imagenes').css('background-image','url("images/stormtrooperSlow.gif")');
                $('div#Imagenes').css('background-size','cover');
            });

            $('a#EmphasisCore').mouseleave(function () {
                $('div#Imagenes').css('width','175px');
                $('div#Imagenes').css('height','174px');
                $('div#Imagenes').css('margin-left','15%');
                $('div#Imagenes').css('background-image','url("images/stormtrooperFaster.gif")');
                $('div#Imagenes').css('background-size','cover');
            });
        });
        
        $scope.onClickButtonConfirmPayment = function () {
            $http({
                method: 'POST',
                url: contextPath + '/api/Transaccion',
                data: $rootScope.peticionPago
            }).success(function (data, status, headers, config) {
                $window.location.href ='#/successfullPayment';
            }).error(function (data, status, headers, config) {
                alert("Ha fallado la petición. Estado HTTP:" + status);
            });
        };
    }]);

