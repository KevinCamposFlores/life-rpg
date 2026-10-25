package com.springreactivo.reactivepractice.Pedido.dto;

public record Pedido(
        Long id,
        String cliente,
        double total,
        boolean pagado
) {
}
