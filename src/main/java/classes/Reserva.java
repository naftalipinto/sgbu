package classes;
import java.sql.Date;

public class Reserva {
    private Long id;
    private Long obraId;
    private Long utilizadorId;
    private Date dataReserva; // MUDOU para java.sql.Date
    private String status;
    private Integer posicaoFila;

    // Construtores
    public Reserva() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getObraId() { return obraId; }
    public void setObraId(Long obraId) { this.obraId = obraId; }

    public Long getUtilizadorId() { return utilizadorId; }
    public void setUtilizadorId(Long utilizadorId) { this.utilizadorId = utilizadorId; }

    public Date getDataReserva() { return dataReserva; } // MUDOU
    public void setDataReserva(Date dataReserva) { this.dataReserva = dataReserva; } // MUDOU

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getPosicaoFila() { return posicaoFila; }
    public void setPosicaoFila(Integer posicaoFila) { this.posicaoFila = posicaoFila; }
}