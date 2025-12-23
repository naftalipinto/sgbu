package services;
import classes.Exemplar;
import classes.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExemplarService extends Exemplar{

    public void create(Exemplar ex) throws Exception {
        String sql = "INSERT INTO Exemplar (obraId, cota, codigoBarras, estado) VALUES (?,?,?,?)";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, ex.getObraId());
        stmt.setString(2, ex.getCota());
        stmt.setString(3, ex.getCodigoBarras());
        stmt.setString(4, ex.getEstado());
       

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public Exemplar read(long id) throws Exception {
        String sql = "SELECT * FROM Exemplar WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();
        Exemplar ex = null;

        if (rs.next()) {
            ex = new Exemplar();
            ex.setId(rs.getLong("Id"));
            ex.setObraId(rs.getLong("obraId"));
            ex.setCota(rs.getString("cota"));
            ex.setCodigoBarras(rs.getString("codigoBarras"));
            ex.setEstado(rs.getString("estado"));
         
        }

        rs.close();
        stmt.close();
        con.close();
        return ex;
    }

    public List<Exemplar> readAll() throws Exception {
        List<Exemplar> lista = new ArrayList<>();
        String sql = "SELECT * FROM Exemplar";

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Exemplar ex = new Exemplar();
            ex.setId(rs.getLong("Id"));
            ex.setObraId(rs.getLong("obraId"));
            ex.setCota(rs.getString("cota"));
            ex.setCodigoBarras(rs.getString("codigoBarras"));
            ex.setEstado(rs.getString("estado"));
            lista.add(ex);
        }

        rs.close();
        stmt.close();
        con.close();

        return lista;
    }

    public void update(Exemplar ex) throws Exception {
        String sql = "UPDATE Exemplar SET obraId=?, cota=?, codigoBarras=?, estado=? WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setLong(1, ex.getObraId());
        stmt.setString(2, ex.getCota());
        stmt.setString(3, ex.getCodigoBarras());
        stmt.setString(4, ex.getEstado());
        stmt.setObject(5, ex.getId());
   

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public void delete(long id) throws Exception {
        String sql = "DELETE FROM Exemplar WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setLong(1, id);
        stmt.executeUpdate();

        stmt.close();
        con.close();
    }
}
