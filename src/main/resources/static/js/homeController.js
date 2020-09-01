var app = angular.module('myApp', []);
var url = "http://localhost:8080";
var carrito = [];
var productos = [];
var items = [];

var ESTADO_FINALIZADO = 'FINALIZADO';
var ESTADO_PENDIENTE = 'PENDIENTE';

var DEFAULT_ESTADO_CARRITO = 'PENDIENTE';

var todosFinalizados = false;

app.factory('Fact', function () {

    var data = {
        IdCliente: ''
    };
    return {
        getIdCliente: function () {
            return data.IdCliente;
        },
        setIdCliente: function (idC) {
            data.IdCliente = idC;
        }
    };
})

app.controller('homeController', function($scope, $http,Fact, $rootScope, $window, $location) {
    $scope.contador= 0;
    $scope.contadorPesos= 00;
    $scope.cliente = [];
    $scope.carritosDelCliente = [];
    $scope.carritoVigente = [];
    $scope.idCliente = Fact.getIdCliente();

    $http.get(url + "/productosRest/listar").then(
        function(response) {
            $scope.datos = response.data;
    });

    $scope.incrementarContadores= function(precio){
        $scope.contador += 1;
        $scope.contadorPesos += precio;
    }

    $scope.verCarrito = function(){
        $window.location.href = "carrito?id="+ 1;       
    }

    $scope.crearCarrito = function(producto,idCliente){


        
        var buscoCliente = $http({
            url: url + '/clienteRest/getCliente', 
            method: "POST",
            data: idCliente
        }).then(
            function(response) {
                $scope.cliente = response.data;
                // obtengo el cliente para insertar en carrito
                
                var carritoJson = {
                    "fechaCreacion": new Date(),
                    "estado":DEFAULT_ESTADO_CARRITO,
                    "cliente": $scope.cliente,
                    "tipo": null,
                    "promo": null
                }

                var callCrearCarrito = $http({
                    url: url + '/carritoRest/crearCarrito', 
                    method: "POST",
                    data: carritoJson
                }).then(
                    function(response) {
                       console.log("se creo un nuevo carrito status : OK"); 

                       $scope.cargarItems(response,producto);

                });

        });
       
    }

    $scope.cargarItems = function(carrito,producto){        
        //JSON ITEMS crear 
        var itemJson = {
            "cantidad": null, // se carga en la base una vez se concrete la compra 
            "subtotal": null,
            "producto": producto,
            "carrito": carrito 
        }

        var callCrearItems = $http({
            url: url + '/itemsRest/updateItem', 
            method: "POST",
            data: itemJson
        }).then(
            function(response) {
               console.log("se cargo un item status : OK"); 

        });
                
    }

   

    $scope.agregarCarritoSegunEstado = function(idCliente,producto){

        // consulto si el cliente tiene carritos hoy 
        var evaluarCarritosDelCliente = $http({
            url: url + '/carritoRest/getCarritos', 
            method: "POST",
            data: idCliente
        }).then(
            function(response) {
            $scope.carritosDelCliente = response.data;

            
            // crear el carrito segun estado de los carritos del cliente en el dia de la fecha 

            // si el cliente no tiene carritos le creo uno 
            if($scope.carritosDelCliente.length === 0){
                $scope.crearCarrito(producto,idCliente);
            }else{ // si el cliente tiene carritos me fijo que esten todos con estado "finalizado" asi le creo uno 
                
                var contadorFinalizados = 0;

                angular.forEach($scope.carritosDelCliente, function(carrito,key){ 
                    // cuento 1 si esta finalizado
                    if(carrito.estado == ESTADO_FINALIZADO){
                        contadorFinalizados += 1;
                    }if(carrito.estado == ESTADO_PENDIENTE){
                        $scope.carritoVigente = carrito;
                    }
                });

                // si estan todos finalizados le creo uno 
                if($scope.carritosDelCliente.length == contadorFinalizados){
                    todosFinalizados = true;
                    $scope.crearCarrito(producto,idCliente);
                }

            }
        });

      
    }

    $scope.agregarAlCarrito = function(producto){
        $scope.incrementarContadores(producto.precio);

        // ver como traer este dato del otro controlador
        idCliente = 1      
        
        $scope.agregarCarritoSegunEstado(idCliente,producto);

    }

})

    app.controller('loginFakeController', function($scope,Fact,$window) {  
        $scope.cliente = [];
        $scope.total= 00;
        $scope.ingresar = function(idCliente){
            Fact.setIdCliente(idCliente);
            $window.location.href = "home";
            
           
        }
    
      
    })

    app.controller('carritoController', function($scope, $http,Fact) {  
        $scope.carrito = [];
        //$scope.master = {};
       

       $http.get(url + "/itemsRest/listarPorCarrito/"+1).then(
            function(response) {
                $scope.items = response.data;
        });

        $http.get(url + "/carritoRest/listarCarritosFinalizados/"+1).then(
            function(response) {
                $scope.carritosFinalizados = response.data;
        });
        
        /*
        $scope.actualizarCantidad = function(items){
            console.log(items);
            $scope.i = angular.copy($scope.master);
            console.log($scope.master);
            
        }*/


        $scope.actualizarItems = function(items){

            angular.forEach(items, function(i,key){ 
                $scope.itemDetalle = i;      
            });


            
    
        }

        $scope.efectuarCompra = function(){
            
            var callCrearItems = $http({
                url: url + '/itemsRest/updateItemAndBuy', 
                method: "POST",
                data: $scope.items
            }).then(
                function(response) {
                console.log("se actualizo un item status : OK"); 
               
    
            });
        }



        $scope.eliminarProductoEncarrito = function(idItem){
            var eliminar = $http({
                url: url + '/itemsRest/eliminar/'+ idItem, 
                method: "DELETE"
            }).then(
                function(response) {
                    $http.get(url + "/itemsRest/listarPorCarrito/"+1).then(
                        function(response) {
                            $scope.items = response.data;
                            
                    });
                    
            });
        }

        $scope.obtenerCarrito = function(items){
            angular.forEach(items, function(i,key){ 
                $scope.carrito = i.carrito;
            });
        }

        $scope.eliminarCarritoVigente = function(){
            $scope.obtenerCarrito($scope.items);

            var eliminar = $http({
                    url: url + '/carritoRest/eliminar/'+ $scope.carrito.id, 
                    method: "DELETE"
                }).then(
                    function(response) {
                        console.log("delete ok");
                        $http.get(url + "/itemsRest/listarPorCarrito/"+1).then(
                            function(response) {
                                $scope.items = response.data;
                                
                        });
                        
                });
        }

      
    });