package services;
import classes.Multa;
import classes.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MultaService extends Multa{

    public void create(Multa m) throws Exception {
        String sql = "INSERT INTO Multa (emprestimoId, valor, motivo, pago, dataGeracao, dataPagamento) VALUES (?,?,?,?,?,?)";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setLong(1, m.getEmprestimoId());
        stmt.setDouble(2, m.getValor());
        stmt.setString(3, m.getMotivo());
        stmt.setBoolean(4, m.isPago());
        stmt.setLong(5, m.getDataGeracao());
        stmt.setObject(6, m.getDataPagamento());

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
            m.setDataGeracao(rs.getLong("dataGeracao"));
            m.setDataPagamento(rs.getLong("dataPagamento"));
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
            m.setDataGeracao(rs.getLong("dataGeracao"));
            m.setDataPagamento(rs.getLong("dataPagamento"));
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
        stmt.setLong(5, m.getDataGeracao());
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
