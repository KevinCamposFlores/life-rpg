package com.springreactivo.reactivepractice.Pedido.entity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PedidoPractice {

    public record Pedido(
            Long id,
            Long clienteId,
            Double total,
            boolean pagado
    ){}

    public record Cliente(
            Long id,
            String nombre,
            String email
    ){}

    public static Mono<Cliente> buscarCliente(Long clienteId) {
        return Mono.fromSupplier(() -> {
            System.out.println("Consultando cliente: " + clienteId);

            return switch (clienteId.intValue()) {
                case 101 -> new Cliente(101L, "Ana", "ana@gmail.com");
                case 102 -> new Cliente(102L, "Luis", "luis@gmail.com");
                case 103 -> new Cliente(103L, "Carlos", "carlos@gmail.com");
                case 104 -> new Cliente(104L, "Maria", "maria@gmail.com");
                default -> throw new IllegalArgumentException("Cliente no encontrado");
            };
        });

    }

    public static  String obtenerNombre(Cliente cliente) {
        return cliente.nombre();
    }

    public static Mono<String> obtenerEmail(Cliente cliente) {
        return Mono.just(cliente.email());
    }

    public static void main(String[] args){

        Mono<Cliente> clientes = buscarCliente(101L);

       Mono<String> resultado = clientes.flatMap(cliente -> {
                return obtenerEmail(cliente);
       });

       resultado.subscribe(System.out::println);


     /*   Flux<Pedido> pedidos = Flux.just(
                new Pedido(1L, 101L, 150.0, true),
                new Pedido(2L, 102L, 80.0, false),
                new Pedido(3L, 103L, 250.0, true),
                new Pedido(4L, 104L, 90.0, true)
        );

        PedidoPractice pedidopractice = new PedidoPractice();

       Flux<String> nombres = pedidos
        .filter(n -> n.pagado())
               .flatMap(pedido -> {
                 return  pedidopractice.buscarCliente(pedido.clienteId());
               })
               .map(cliente -> cliente.nombre());

         nombres.subscribe(System.out::println); */

    }


}
