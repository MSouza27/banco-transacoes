package br.com.banco.transacoes.service;

import br.com.banco.transacoes.dto.TransacoesDTO;
import br.com.banco.transacoes.model.Transacoes;
import br.com.banco.transacoes.repository.TransacoesRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransacoesService {
    private final TransacoesRepository repository;

    public TransacoesService(TransacoesRepository repository) {
        this.repository = repository;
    }

    public TransacoesDTO cadastrarTransacoes(TransacoesDTO dto){

        if (dto.saldoDaContaOrigem().compareTo(dto.saldoTransferido()) > 0
                || dto.limiteChequeEspecial().compareTo(dto.saldoTransferido()) > 0){

            Transacoes transferencia = new Transacoes();

            transferencia.setBancoOrigem(dto.bancoOrigem());
            transferencia.setBancoDeDestino(dto.bancoDeDestino());
            transferencia.setSaldoDaContaOrigem(dto.saldoDaContaOrigem());
            transferencia.setLimiteChequeEspecial(dto.limiteChequeEspecial());
            transferencia.setSaldoTransferido(dto.saldoTransferido());
            transferencia.setSaldoDaContaDepoisDoSaque(dto.saldoDaContaOrigem().subtract(dto.saldoTransferido()));
            transferencia.setSaldoDaContaDestino(dto.saldoDaContaDestino());
            transferencia.setSaldoFinalDaContaDestino(dto.saldoDaContaDestino().add(dto.saldoTransferido()));
            transferencia.setDataTransacoes(LocalDateTime.now());
            transferencia.setTipoDeTransacao(dto.tipoDeTransacao());

            var validar = repository.save(transferencia);

            return new TransacoesDTO(
                    validar.getBancoOrigem(),
                    validar.getBancoDeDestino(),
                    validar.getSaldoDaContaOrigem(),
                    validar.getSaldoTransferido(),
                    validar.getLimiteChequeEspecial(),
                    validar.getSaldoDaContaDepoisDoSaque(),
                    validar.getSaldoDaContaDestino(),
                    validar.getSaldoFinalDaContaDestino(),
                    validar.getDataTransacoes(),
                    validar.getTipoDeTransacao()
            );


        }
        throw new IllegalArgumentException("Saldo da conta é menor que o saldo solicitado para saque ou transfêrencia!");
    }

    public Map<String, Long>contagemOrigem(){
        var resultado = repository.contagemBancoDeOrigem();

        Map<String, Long> contagem = new HashMap<>();
        for (Object[] linha : resultado){
            String banco = (String) linha[0];
            Long transacao = (Long) linha[1];
            contagem.put(banco, transacao);
        }
        return contagem;
    }

    public Map<String, Long>contagemDestino(){
        var resultado = repository.contagemBandoDeDestino();

        Map<String, Long> contagem = new HashMap<>();
        for (Object[] linha : resultado){
            String banco = (String) linha[0];
            Long transacoes = (Long) linha[1];
            contagem.put(banco, transacoes);
        }
        return contagem;
    }

    public List<Transacoes>buscarTransacoesPorBancoEValorMinimo(BigDecimal minimo, String banco){
        return repository.buscarTransacoesPorBancoEValorMinimo(minimo, banco);
    }

    public List<Transacoes>buscarPorSaldoESaldoTransferido(BigDecimal saldoDaContaOrigem, BigDecimal saldoTransferido){
        return repository.buscarPorSaldoESaldoTransferido(saldoDaContaOrigem, saldoTransferido);
    }
}
