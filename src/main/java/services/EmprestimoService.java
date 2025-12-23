package services;
import classes.Emprestimo;
import classes.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class EmprestimoService extends Emprestimo {

    public void create(Emprestimo e) throws Exception {
        String sql = "INSERT INTO Emprestimo (exemplarId, utilizadorId, dataEmprestimo, dataPrevistaDevolucao, dataDevolucao, estado) VALUES (?,?,?,?,?,?)";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setLong(1, e.getExemplarId());
        stmt.setLong(2, e.getUtilizadorId());
        stmt.setLong(3, e.getDataEmprestimo());
        stmt.setLong(4, e.getDataPrevistaDevolucao());
        stmt.setObject(5, e.getDataDevolucao());
        stmt.setString(6, e.getEstado());

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public Emprestimo read(long id) throws Exception {
        String sql = "SELECT * FROM Emprestimo WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();
        Emprestimo e = null;

        if (rs.next()) {
            e = new Emprestimo();
            e.setExemplarId(rs.getLong("exemplarId"));
            e.setUtilizadorId(rs.getLong("utilizadorId"));
            e.setDataEmprestimo(rs.getLong("dataEmprestimo"));
            e.setDataPrevistaDevolucao(rs.getLong("dataPrevistaDevolucao"));
            e.setDataDevolucao(rs.getLong("dataDevolucao"));
            e.setEstado(rs.getString("estado"));
        }

        rs.close();
        stmt.close();
        con.close();
        return e;
    }

    public List<Emprestimo> readAll() throws Exception {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Emprestimo";

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Emprestimo e = new Emprestimo();
            e.setExemplarId(rs.getLong("exemplarId"));
            e.setUtilizadorId(rs.getLong("utilizadorId"));
            e.setDataEmprestimo(rs.getLong("dataEmprestimo"));
            e.setDataPrevistaDevolucao(rs.getLong("dataPrevistaDevolucao"));
            e.setDataDevolucao(rs.getLong("dataDevolucao"));
            e.setEstado(rs.getString("estado"));
            lista.add(e);
        }

        rs.close();
        stmt.close();
        con.close();
        return lista;
    }

    public void update(Emprestimo e) throws Exception {
        String sql = "UPDATE Emprestimo SET exemplarId=?, utilizadorId=?, dataEmprestimo=?, dataPrevistaDevolucao=?, dataDevolucao=?, estado=? WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setLong(1, e.getExemplarId());
        stmt.setLong(2, e.getUtilizadorId());
        stmt.setLong(3, e.getDataEmprestimo());
        stmt.setLong(4, e.getDataPrevistaDevolucao());
        stmt.setObject(5, e.getDataDevolucao());
        stmt.setString(6, e.getEstado());

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public void delete(long id) throws Exception {
        String sql = "DELETE FROM Emprestimo WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setLong(1, id);
        stmt.executeUpdate();

        stmt.close();
        con.close();
    }
}
