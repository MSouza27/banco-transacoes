package br.com.banco.transacoes.repository;

import br.com.banco.transacoes.model.Transacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {

    @Query("SELECT b.bancoOrigem, COUNT(b) FROM Transacoes b GROUP BY b.bancoOrigem")
    List<Object[]>contagemBancoDeOrigem();

    @Query("SELECT d.bancoDeDestino, COUNT(d) FROM Transacoes d GROUP BY d.bancoDeDestino")
    List<Object[]>contagemBandoDeDestino();

    @Query("SELECT t FROM Transacoes t WHERE t.saldoTransferido > :minimo AND t.bancoOrigem = :banco")
    List<Transacoes> buscarTransacoesPorBancoEValorMinimo(
            @Param("minimo") BigDecimal minimo,
            @Param("banco") String banco);


    @Query("SELECT t FROM Transacoes t WHERE t.saldoDaContaOrigem = :saldoDaContaOrigem OR t.saldoTransferido = :saldoTransferido")
    List<Transacoes> buscarPorSaldoESaldoTransferido(
            @Param("saldoDaContaOrigem") BigDecimal saldoDaContaOrigem,
            @Param("saldoTransferido") BigDecimal saldoTransferido);

}
