app.controller("SuccessfullPaymentController", ["$scope", "$http", "$rootScope", "$window", function ($scope, $http, $rootScope, $window) {
        $('header').hide();
        $scope.onClickButtonDescargar = function () {
            alto = 30;
            ancho = -10;

            var doc = new jsPDF();

            doc.setFontSize(30);
            doc.text(ancho + 30, 20, 'Factura de pago');

            doc.setFontSize(16);
            doc.text(ancho + 30.5, 27, 'Datos emitidos por Tramerbank, "El primer banco Jedi".');
            doc.setFontSize(14);


            doc.text((ancho + 30), (alto + 20), 'Cuenta');
            doc.text((ancho + 55), (alto + 20), 'IdCompra');
            doc.text((ancho + 90), (alto + 20), 'Importe');
            doc.text((ancho + 120), (alto + 20), 'Concepto');

            doc.setFontSize(12);
            doc.text((ancho + 30), (alto + 32), $rootScope.peticionPago.codigoCuentaClienteOrigen.toString());
            doc.text((ancho + 55), (alto + 32), $rootScope.peticionPago.idCarrito);
            doc.text((ancho + 90), (alto + 32), $rootScope.peticionPago.importe);
            doc.text((ancho + 120), (alto + 32), $rootScope.peticionPago.concepto);


            doc.line(ancho + 28, alto + 25, ancho + 150, alto + 25);
            doc.line(ancho + 52, alto + 15, ancho + 52, alto + 50);
            doc.line(ancho + 88, alto + 15, ancho + 88, alto + 50);
            doc.line(ancho + 118, alto + 15, ancho + 118, alto + 50);

            doc.save('datauri');
        };
    }]);

