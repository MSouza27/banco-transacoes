package br.com.banco.transacoes.controller;

import br.com.banco.transacoes.dto.TransacoesDTO;
import br.com.banco.transacoes.model.Transacoes;
import br.com.banco.transacoes.service.TransacoesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {
    private final TransacoesService service;

    public TransacoesController(TransacoesService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TransacoesDTO> salvar(@RequestBody @Valid TransacoesDTO dto){
        var dados = service.cadastrarTransacoes(dto);
        return ResponseEntity.status(201).body(dados);
    }

    @GetMapping("/origem")
    @Transactional
    public ResponseEntity<Map<String, Long>>contarOrigem(){
        return ResponseEntity.ok(service.contagemOrigem());
    }

    @GetMapping("/destino")
    @Transactional
    public ResponseEntity<Map<String, Long>>contarDestino(){
        return ResponseEntity.ok(service.contagemDestino());
    }

    @GetMapping("/buscar-por-banco-e-minimo")
    @Transactional
    public ResponseEntity<List<TransacoesDTO>> buscarMinimo(
            @RequestParam("minimo")BigDecimal minimo,
            @RequestParam("banco") String banco ){
        List<Transacoes> transacoes = service.buscarTransacoesPorBancoEValorMinimo(minimo, banco);

        List<TransacoesDTO> dtos = transacoes.stream()
                .map(TransacoesDTO::new)
                .toList();

        return ResponseEntity.ok(dtos);
    }
    @GetMapping("/buscar-por-saldo-transferido")
    @Transactional
    public ResponseEntity<List<TransacoesDTO>>buscarSaldoTransferido(
            @RequestParam("saldoDaContaOrigem") BigDecimal saldoDaContaOrigem,
            @RequestParam("saldoTransferido") BigDecimal saldoTransferido){

        List<Transacoes> transacoes = service.buscarPorSaldoESaldoTransferido(saldoDaContaOrigem, saldoTransferido);

        List<TransacoesDTO> dtos = transacoes.stream()
                .map(TransacoesDTO::new)
                .toList();

        return ResponseEntity.ok(dtos);
    }


}
