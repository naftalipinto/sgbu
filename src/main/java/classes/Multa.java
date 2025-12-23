package classes;
public class Multa {
    private Long emprestimoId;
    private double valor;
    private String motivo; // ENUM
    private boolean pago;
    private Long dataGeracao;
    private Long dataPagamento;


    public Long getEmprestimoId() {
        return emprestimoId;
    }

    public void setEmprestimoId(Long emprestimoId) {
        this.emprestimoId = emprestimoId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public Long getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(Long dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public Long getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Long dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
 
    // Getters e setters
}
