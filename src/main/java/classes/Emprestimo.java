package classes;
public class Emprestimo {
    private Long exemplarId;
    private Long utilizadorId;
    private Long dataEmprestimo;
    private Long dataPrevistaDevolucao;
    private Long dataDevolucao;
    private String estado; // ENUM

    // Getters e setters

    public Long getExemplarId() {
        return exemplarId;
    }

    public void setExemplarId(Long exemplarId) {
        this.exemplarId = exemplarId;
    }

    public Long getUtilizadorId() {
        return utilizadorId;
    }

    public void setUtilizadorId(Long utilizadorId) {
        this.utilizadorId = utilizadorId;
    }

    public Long getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Long dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Long getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(Long dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public Long getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Long dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
