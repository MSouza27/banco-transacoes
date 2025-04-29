package br.com.banco.transacoes.dto;

import br.com.banco.transacoes.enums.TipoDeTransacao;
import br.com.banco.transacoes.model.Transacoes;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransacoesDTO(
        String bancoOrigem,
        String bancoDeDestino,
        BigDecimal saldoDaContaOrigem,
        BigDecimal saldoTransferido,
        BigDecimal limiteChequeEspecial,
        BigDecimal saldoDaContaDepoisDoSaque,
        BigDecimal saldoDaContaDestino,
        BigDecimal saldoFinalDaContaDestino,
        LocalDateTime dataTransacoes,

        @Enumerated
        TipoDeTransacao tipoDeTransacao


) {

        public TransacoesDTO(Transacoes transacoes) {
                this(
                        transacoes.getBancoOrigem(),
                        transacoes.getBancoDeDestino(),
                        transacoes.getSaldoDaContaOrigem(),
                        transacoes.getSaldoTransferido(),
                        transacoes.getLimiteChequeEspecial(),
                        transacoes.getSaldoDaContaDepoisDoSaque(),
                        transacoes.getSaldoDaContaDestino(),
                        transacoes.getSaldoFinalDaContaDestino(),
                        transacoes.getDataTransacoes(),
                        transacoes.getTipoDeTransacao()
                );
        }



}
