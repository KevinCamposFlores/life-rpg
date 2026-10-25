package com.springreactivo.reactivepractice.Pedido.entity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PedidoPracticeV2 {


    public record Pedido(
            Long id,
            String cliente,
            double total,
            boolean pagado
    ){}

    public record PedidoDescuento(
      Long pedidoId,
      String cliente,
      double totalFinal
    ){}

    public static Mono<Double> aplicarDescuento(double total){
        return Mono.fromSupplier(() -> {
            System.out.println("Calculando descuento " + total);
            return total * 0.90;
        });

    }

    public static void main(String[] args){

        Flux<Pedido> pedidos = Flux.just(
                new Pedido(1L, "Ana", 150.0, true),
                new Pedido(2L, "Luis", 80.0, false),
                new Pedido(3L, "Carlos", 250.0, true),
                new Pedido(4L, "Maria", 90.0, true),
                new Pedido(5L, "Pedro", 300.0, false)
        );

      Flux<PedidoDescuento> pedidosDescuento =  pedidos
                .filter(Pedido::pagado)
                .filter(pedido -> {
                    if(pedido.total() > 100){
                        return true;
                    }
                    return false;
                })
                .flatMap(pedido -> {
                   Mono<Double> descuento = aplicarDescuento(pedido.total());
                   return descuento.map(valor -> {
                      return new PedidoDescuento(pedido.id(), pedido.cliente(), valor);
                   });
                });

      pedidosDescuento.subscribe(System.out::println);
    }

}


