package services;

import classes.Editora;
import classes.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EditoraService extends Editora {

    public void create(Editora e) throws Exception {
        String sql = "INSERT INTO Editora (nome, email, telefone, endereco) VALUES (?,?,?,?)";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, e.getNome());
        stmt.setString(2, e.getEmail());
        stmt.setString(3, e.getTelefone());
        stmt.setString(4, e.getEndereco());

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public Editora read(long id) throws Exception {
        String sql = "SELECT * FROM Editora WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();
        Editora e = null;

        if (rs.next()) {
            e = new Editora();
            e.setId(rs.getLong("id"));
            e.setNome(rs.getString("nome"));
            e.setEmail(rs.getString("email"));
            e.setTelefone(rs.getString("telefone"));
            e.setEndereco(rs.getString("endereco"));

        }

        rs.close();
        stmt.close();
        con.close();
        return e;
    }
        public Long readByName(String nome) throws Exception {
        String sql = "SELECT * FROM Editora WHERE nome=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);

        ResultSet rs = stmt.executeQuery();
        Editora e = null;

        if (rs.next()) {
            e = new Editora();
            e.setId(rs.getLong("id"));
        }

        rs.close();
        stmt.close();
        con.close();
        return e.getId();
    }

    public List<Editora> readAll() throws Exception {
        String sql = "SELECT * FROM Editora";
        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Editora> lista = new ArrayList<>();

        while (rs.next()) {
            Editora e = new Editora();
            e.setId(rs.getLong("id"));
            e.setNome(rs.getString("nome"));
            e.setEmail(rs.getString("email"));
            e.setTelefone(rs.getString("telefone"));
            e.setEndereco(rs.getString("endereco"));
            lista.add(e);
        }

        rs.close();
        stmt.close();
        con.close();
        return lista;
    }

    public void update(Editora e) throws Exception {
        String sql = "UPDATE Editora SET nome=?, email=?, telefone=?, endereco=? WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, e.getNome());
        stmt.setString(2, e.getEmail());
        stmt.setString(3, e.getTelefone());
        stmt.setString(4, e.getEndereco());
        stmt.setLong(5, e.getId());
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public void delete(long id) throws Exception {
        String sql = "DELETE FROM Editora WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, id);
        stmt.executeUpdate();
        stmt.close();
        con.close();
    }
}
