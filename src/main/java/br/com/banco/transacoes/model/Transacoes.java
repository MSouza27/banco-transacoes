package br.com.banco.transacoes.model;

import br.com.banco.transacoes.enums.TipoDeTransacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "dados_transacoes")
@Getter
@Setter
@Entity
public class Transacoes {

    private static final String CAMPO_OBRIGATORIO = "O campo não pode ser vazio!";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = CAMPO_OBRIGATORIO)
    private String bancoOrigem;

    @NotBlank(message = CAMPO_OBRIGATORIO)
    private String bancoDeDestino;

    //Banco Origem
    private BigDecimal saldoDaContaOrigem;
    private BigDecimal limiteChequeEspecial;
    private BigDecimal saldoTransferido;
    private BigDecimal saldoDaContaDepoisDoSaque;

    //Banco Destino
    private BigDecimal saldoDaContaDestino;
    private BigDecimal saldoFinalDaContaDestino;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataTransacoes;

    @Enumerated
    private TipoDeTransacao tipoDeTransacao;
}
