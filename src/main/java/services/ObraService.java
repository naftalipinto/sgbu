package services;

import classes.Obra;
import classes.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObraService extends Obra {

    public void create(Obra o) throws Exception {
        String sql = "INSERT INTO Obra (titulo, isbn, ano, edicao, idioma, sinopse, editoraId) VALUES (?,?,?,?,?,?,?)";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, o.getTitulo());
        stmt.setString(2, o.getIsbn());
        stmt.setObject(3, o.getAno());
        stmt.setString(4, o.getEdicao());
        stmt.setString(5, o.getIdioma());
        stmt.setString(6, o.getSinopse());
        stmt.setObject(7, o.getEditoraId());

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public Obra read(long id) throws Exception {
        String sql = "SELECT * FROM Obra WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();
        Obra o = null;

        if (rs.next()) {
            o = new Obra();
            o.setId(rs.getLong("Id"));
            o.setTitulo(rs.getString("titulo"));
            o.setIsbn(rs.getString("isbn"));
            o.setAno(rs.getInt("ano"));
            o.setEdicao(rs.getString("edicao"));
            o.setIdioma(rs.getString("idioma"));
            o.setSinopse(rs.getString("sinopse"));
            o.setEditoraId(rs.getLong("editoraId"));
        }

        rs.close();
        stmt.close();
        con.close();
        return o;
    }
      public Long readByNome(String nome) throws Exception {
        String sql = "SELECT * FROM Obra WHERE titulo=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);

        ResultSet rs = stmt.executeQuery();
        Obra o = null;

        if (rs.next()) {
            o = new Obra();
            o.setId(rs.getLong("Id"));
            o.setTitulo(rs.getString("titulo"));
            o.setIsbn(rs.getString("isbn"));
            o.setAno(rs.getInt("ano"));
            o.setEdicao(rs.getString("edicao"));
            o.setIdioma(rs.getString("idioma"));
            o.setSinopse(rs.getString("sinopse"));
            o.setEditoraId(rs.getLong("editoraId"));
        }

        rs.close();
        stmt.close();
        con.close();
        return o.getId();
    }

    public List<Obra> readAll() throws Exception {
        List<Obra> lista = new ArrayList<>();
        String sql = "SELECT * FROM Obra";

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Obra o = new Obra();
            o.setId(rs.getLong("Id"));
            o.setTitulo(rs.getString("titulo"));
            o.setIsbn(rs.getString("isbn"));
            o.setAno(rs.getInt("ano"));
            o.setEdicao(rs.getString("edicao"));
            o.setIdioma(rs.getString("idioma"));
            o.setSinopse(rs.getString("sinopse"));
            o.setEditoraId(rs.getLong("editoraId"));
            lista.add(o);
        }

        rs.close();
        stmt.close();
        con.close();

        return lista;
    }

    public void update(Obra o) throws Exception {
        String sql = "UPDATE Obra SET titulo=?, isbn=?, ano=?, edicao=?, idioma=?, sinopse=?, editoraId=? WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, o.getTitulo());
        stmt.setString(2, o.getIsbn());
        stmt.setObject(3, o.getAno());
        stmt.setString(4, o.getEdicao());
        stmt.setString(5, o.getIdioma());
        stmt.setString(6, o.getSinopse());
        stmt.setObject(7, o.getEditoraId());
        stmt.setObject(8, o.getId());

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public void delete(long id) throws Exception {
        String sql = "DELETE FROM Obra WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setLong(1, id);
        stmt.executeUpdate();

        stmt.close();
        con.close();
    }
}
