package br.com.banco.transacoes.enums;

public enum TipoDeTransacao {

    EM_PROCESSAMENTO(1, "Em Processamento"),
    PROCESSADO(2, "Processado"),
    REMESSA_ENVIADA(3, "Remessa Enviada"),
    CREDITO_DEBITADO(4, "Crédito Débitado");

    private final Integer codigo;
    private final String descricao;

    TipoDeTransacao(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoDeTransacao fromCodigo(Integer codigo){
        for (TipoDeTransacao tipoDeTransacao : values()){
            if (tipoDeTransacao.getCodigo() == codigo){
                return tipoDeTransacao;
            }
        }

        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}
