package services;

import classes.*;
import classes.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MultaService extends Multa {

    private static final double VALOR_DIA = 200.0; // 200 Kz por dia
    private static final double LIMITE_BLOQUEIO = 1000.0; // Bloqueia acima de 1000 Kz

    public Multa processarMulta(Emprestimo emprestimo) throws Exception {
        long diasAtraso = calcularDiasAtrasoComDate(emprestimo);

        if (diasAtraso > 0) {
            double valorMulta = diasAtraso * VALOR_DIA;
            Multa multa = new Multa();
            multa.setEmprestimoId(emprestimo.getId());
            multa.setValor(valorMulta);
            multa.setMotivo("ATRASO");
            multa.setPago(false);
            multa.setDataPagamento(null);
            create(multa);
            verificarBloqueioUsuario(emprestimo.getUtilizadorId(), valorMulta);
            return multa;
        }
        return null; // Sem multa
    }

    private long calcularDiasAtrasoComDate(Emprestimo emprestimo) {
        // Supondo que Emprestimo tenha métodos getDataPrevistaDevolucaoDate()
        Date dataDevolucao = new java.sql.Date(
                emprestimo.getDataDevolucao().getTime()
        );

        Date dataPrevista = new java.sql.Date(
                emprestimo.getDataPrevistaDevolucao().getTime()
        );

        if (dataDevolucao.after(dataPrevista)) {
            long diferencaMs = dataDevolucao.getTime() - dataPrevista.getTime();
            return diferencaMs / (1000 * 60 * 60 * 24);
        }
        return 0;
    }

    private void verificarBloqueioUsuario(Long utilizadorId, double valorMulta) throws Exception {
        if (valorMulta > LIMITE_BLOQUEIO) {
            double totalDevendo = calcularTotalMultasNaoPagas(utilizadorId);
            if (totalDevendo > LIMITE_BLOQUEIO) {
                bloquearUsuario(utilizadorId);
            }
        }
    }

    private double calcularTotalMultasNaoPagas(Long utilizadorId) throws Exception {
        // Implementação usando Date
        String sql = "SELECT SUM(valor) as total FROM Multa m "
                + "JOIN Emprestimo e ON m.emprestimoId = e.id "
                + "WHERE e.utilizadorId = ? AND m.pago = false";

        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, utilizadorId);

        ResultSet rs = stmt.executeQuery();
        double total = 0.0;

        if (rs.next()) {
            total = rs.getDouble("total");
        }

        rs.close();
        stmt.close();
        con.close();

        return total;
    }

    private void bloquearUsuario(Long utilizadorId) throws Exception {
        String sql = "UPDATE Utilizador SET estado = 'INATIVO' WHERE id = ?";

        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, utilizadorId);
        stmt.executeUpdate();

        stmt.close();
        con.close();
    }

    public void create(Multa m) throws Exception {
        String sql = "INSERT INTO Multa (emprestimoId, valor, motivo, pago, dataPagamento) VALUES (?,?,?,?,?)";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, m.getEmprestimoId());
        stmt.setDouble(2, m.getValor());
        stmt.setString(3, m.getMotivo());
        stmt.setBoolean(4, m.isPago());
        stmt.setObject(5, m.getDataPagamento());

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public Multa read(long id) throws Exception {
        String sql = "SELECT * FROM Multa WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();
        Multa m = null;

        if (rs.next()) {
            m = new Multa();
            m.setEmprestimoId(rs.getLong("emprestimoId"));
            m.setValor(rs.getDouble("valor"));
            m.setMotivo(rs.getString("motivo"));
            m.setPago(rs.getBoolean("pago"));
            m.setDataGeracao(rs.getDate("dataGeracao"));
            m.setDataPagamento(rs.getDate("dataPagamento"));
        }

        rs.close();
        stmt.close();
        con.close();
        return m;
    }

    public List<Multa> readAll() throws Exception {
        List<Multa> lista = new ArrayList<>();
        String sql = "SELECT * FROM Multa";

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Multa m = new Multa();
            m.setEmprestimoId(rs.getLong("emprestimoId"));
            m.setValor(rs.getDouble("valor"));
            m.setMotivo(rs.getString("motivo"));
            m.setPago(rs.getBoolean("pago"));
            m.setDataGeracao(rs.getDate("dataGeracao"));
            m.setDataPagamento(rs.getDate("dataPagamento"));
            lista.add(m);
        }

        rs.close();
        stmt.close();
        con.close();
        return lista;
    }

    public void update(Multa m) throws Exception {
        String sql = "UPDATE Multa SET emprestimoId=?, valor=?, motivo=?, pago=?, dataGeracao=?, dataPagamento=? WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setLong(1, m.getEmprestimoId());
        stmt.setDouble(2, m.getValor());
        stmt.setString(3, m.getMotivo());
        stmt.setBoolean(4, m.isPago());
        stmt.setDate(5, m.getDataGeracao());
        stmt.setObject(6, m.getDataPagamento());

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public void delete(long id) throws Exception {
        String sql = "DELETE FROM Multa WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setLong(1, id);
        stmt.executeUpdate();

        stmt.close();
        con.close();
    }

}
