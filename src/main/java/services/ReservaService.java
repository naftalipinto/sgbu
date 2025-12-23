package services;

import classes.Reserva;
import classes.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaService extends Reserva {

    public void create(Reserva r) throws Exception {
        String sql = "INSERT INTO Reserva (obraId, utilizadorId, dataReserva, status, posicaoFila) VALUES (?,?,?,?,?)";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setLong(1, r.getObraId());
        stmt.setLong(2, r.getUtilizadorId());

        // Conversão automática para java.sql.Date
        java.sql.Date dataSql = null;
        if (r.getDataReserva() != null) {
            dataSql = new java.sql.Date(r.getDataReserva().getTime());
        }
        stmt.setDate(3, dataSql);

        stmt.setString(4, r.getStatus());
        stmt.setInt(5, r.getPosicaoFila());

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public Reserva read(long id) throws Exception {
        String sql = "SELECT * FROM Reserva WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();
        Reserva r = null;

        if (rs.next()) {
            r = new Reserva();
            r.setId(rs.getLong("Id"));
            r.setObraId(rs.getLong("obraId"));
            r.setUtilizadorId(rs.getLong("utilizadorId"));
            r.setDataReserva(rs.getDate("dataReserva"));
            r.setStatus(rs.getString("status"));
            r.setPosicaoFila(rs.getInt("posicaoFila"));
        }

        rs.close();
        stmt.close();
        con.close();
        return r;
    }

    public List<Reserva> readAll() throws Exception {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM Reserva";

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Reserva r = new Reserva();
            r.setId(rs.getLong("Id"));
            r.setObraId(rs.getLong("obraId"));
            r.setUtilizadorId(rs.getLong("utilizadorId"));
            r.setDataReserva(rs.getDate("dataReserva"));
            r.setStatus(rs.getString("status"));
            r.setPosicaoFila(rs.getInt("posicaoFila"));
            lista.add(r);
        }

        rs.close();
        stmt.close();
        con.close();
        return lista;
    }

    public void update(Reserva r) throws Exception {
        String sql = "UPDATE Reserva SET obraId=?, utilizadorId=?, dataReserva=?, status=? WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setLong(1, r.getObraId());
        stmt.setLong(2, r.getUtilizadorId());
        stmt.setDate(3, r.getDataReserva());
        stmt.setString(4, r.getStatus());
        stmt.setLong(5, r.getId());

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public void delete(long id) throws Exception {
        String sql = "DELETE FROM Reserva WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setLong(1, id);
        stmt.executeUpdate();

        stmt.close();
        con.close();
    }
}
