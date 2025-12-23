package classes;
public class Exemplar {
    private Long id;
    private Long obraId;
    private String cota;
    private String codigoBarras;
    private String estado; // ENUM
    private Long aquisicaoData;

    // Getters e setters

    public Long getObraId() {
        return obraId;
    }

    public void setObraId(Long obraId) {
        this.obraId = obraId;
    }

    public String getCota() {
        return cota;
    }

    public void setCota(String cota) {
        this.cota = cota;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getAquisicaoData() {
        return aquisicaoData;
    }

    public void setAquisicaoData(Long aquisicaoData) {
        this.aquisicaoData = aquisicaoData;
    }
}
