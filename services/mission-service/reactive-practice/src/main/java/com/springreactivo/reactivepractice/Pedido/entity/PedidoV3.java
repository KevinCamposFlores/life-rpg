package com.springreactivo.reactivepractice.Pedido.entity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PedidoV3 {

    public record Pedido(
            Long id,
            Long clienteId,
            double total,
            boolean pagado
    ) {}

    public record Cliente(
            Long id,
            String nombre
    ) {}

    public record PedidoResumen(
            Long pedidoId,
            String cliente,
            double totalFinal
    ) {}

    public static Mono<Cliente> buscarCliente(Long clienteId) {
        return Mono.fromSupplier(() -> {
            System.out.println("Consultando cliente: " + clienteId);

            return switch (clienteId.intValue()) {
                case 101 -> new Cliente(101L, "Ana");
                case 102 -> new Cliente(102L, "Luis");
                case 103 -> new Cliente(103L, "Carlos");
                case 104 -> new Cliente(104L, "Maria");
                default -> throw new IllegalArgumentException("Cliente no encontrado");
            };
        });
    }

    public static Mono<Double> aplicarDescuento(double total) {
        return Mono.fromSupplier(() -> {
            System.out.println("Aplicando descuento a: " + total);
            return total * 0.90;
        });
    }

    public static void main(String[] args) {

        Flux<Pedido> pedidos = Flux.just(
                new Pedido(1L, 101L, 150.0, true),
                new Pedido(2L, 102L, 80.0, false),
                new Pedido(3L, 103L, 200.0, true),
                new Pedido(4L, 104L, 300.0, true)
        );

      Flux<PedidoResumen> pedidoResumenFlux =  pedidos
                .filter(Pedido::pagado)
                .flatMap(pedido -> {
                  Mono<Cliente> cliente =  buscarCliente(pedido.clienteId());
                  return  cliente.map( valor -> {
                          String nombre = valor.nombre();

                          Mono<Double> descuento = aplicarDescuento(pedido.total());
                          return descuento.map( des -> {
                                    return  new PedidoResumen(pedido.id(),nombre, des);
                                  }
                          );
                  });


                } );

    }
}
