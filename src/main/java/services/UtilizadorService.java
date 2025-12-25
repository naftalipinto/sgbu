package services;
import classes.Utilizador;
import classes.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UtilizadorService extends Utilizador {

    public void create(Utilizador u) throws Exception {
        String sql = "INSERT INTO Utilizador (nomeCompleto, email, telefone, endereco, perfil, estado, senhaHash) VALUES (?,?,?,?,?,?,?)";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, u.getNomeCompleto());
        stmt.setString(2, u.getEmail());
        stmt.setString(3, u.getTelefone());
        stmt.setString(4, u.getEndereco());
        stmt.setString(5, u.getPerfil());
        stmt.setString(6, u.getEstado());
        stmt.setString(7, u.getSenhaHash());
        

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public Utilizador read(long id) throws Exception {
        String sql = "SELECT * FROM Utilizador WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setLong(1, id);

        ResultSet rs = stmt.executeQuery();
        Utilizador u = null;

        if (rs.next()) {
            u = new Utilizador();
            u.setId(rs.getLong("id"));
            u.setNomeCompleto(rs.getString("nomeCompleto"));
            u.setEmail(rs.getString("email"));
            u.setTelefone(rs.getString("telefone"));
            u.setEndereco(rs.getString("endereco"));
            u.setPerfil(rs.getString("perfil"));
            u.setEstado(rs.getString("estado"));
            u.setSenhaHash(rs.getString("senhaHash"));
        }

        rs.close();
        stmt.close();
        con.close();
        return u;
    }

    public Utilizador readByEmail(String email) throws Exception {
        String sql = "SELECT * FROM Utilizador WHERE email=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();
        Utilizador u = null;

        if (rs.next()) {
            u = new Utilizador();
            u.setId(rs.getLong("id"));
            u.setNomeCompleto(rs.getString("nomeCompleto"));
            u.setEmail(rs.getString("email"));
            u.setTelefone(rs.getString("telefone"));
            u.setEndereco(rs.getString("endereco"));
            u.setPerfil(rs.getString("perfil"));
            u.setEstado(rs.getString("estado"));
            u.setSenhaHash(rs.getString("senhaHash"));
        }

        rs.close();
        stmt.close();
        con.close();
        return u;
    }
    
     public Long readByNome(String nome) throws Exception {
        String sql = "SELECT * FROM Utilizador WHERE nomeCompleto=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);

        ResultSet rs = stmt.executeQuery();
        Utilizador u = null;

        if (rs.next()) {
            u = new Utilizador();
            u.setId(rs.getLong("id"));
            u.setNomeCompleto(rs.getString("nomeCompleto"));
            u.setEmail(rs.getString("email"));
            u.setTelefone(rs.getString("telefone"));
            u.setEndereco(rs.getString("endereco"));
            u.setPerfil(rs.getString("perfil"));
            u.setEstado(rs.getString("estado"));
            u.setSenhaHash(rs.getString("senhaHash"));
        }

        rs.close();
        stmt.close();
        con.close();
        return u.getId();
    }

    public List<Utilizador> readAll() throws Exception {
        List<Utilizador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Utilizador";

        Connection con = Database.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            Utilizador u = new Utilizador();
            u.setId(rs.getLong("id"));
            u.setNomeCompleto(rs.getString("nomeCompleto"));
            u.setEmail(rs.getString("email"));
            u.setTelefone(rs.getString("telefone"));
            u.setEndereco(rs.getString("endereco"));
            u.setPerfil(rs.getString("perfil"));
            u.setEstado(rs.getString("estado"));
            u.setSenhaHash(rs.getString("senhaHash"));
            lista.add(u);
        }

        rs.close();
        stmt.close();
        con.close();
        return lista;
    }

    public List<Utilizador> readByPerfil(String perfil) throws Exception {
        List<Utilizador> lista = new ArrayList<>();
        String sql = "SELECT * FROM Utilizador WHERE perfil=?";
        
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, perfil);
        
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Utilizador u = new Utilizador();
            u.setId(rs.getLong("id"));
            u.setNomeCompleto(rs.getString("nomeCompleto"));
            u.setEmail(rs.getString("email"));
            u.setTelefone(rs.getString("telefone"));
            u.setEndereco(rs.getString("endereco"));
            u.setPerfil(rs.getString("perfil"));
            u.setEstado(rs.getString("estado"));
            u.setSenhaHash(rs.getString("senhaHash"));
            lista.add(u);
        }

        rs.close();
        stmt.close();
        con.close();
        return lista;
    }

    public void update(Utilizador u) throws Exception {
        String sql = "UPDATE Utilizador SET nomeCompleto=?, email=?, telefone=?, endereco=?, perfil=?, estado=?, senhaHash=? WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, u.getNomeCompleto());
        stmt.setString(2, u.getEmail());
        stmt.setString(3, u.getTelefone());
        stmt.setString(4, u.getEndereco());
        stmt.setString(5, u.getPerfil());
        stmt.setString(6, u.getEstado());
        stmt.setString(7, u.getSenhaHash());
        stmt.setLong(8, u.getId());

        stmt.executeUpdate();
        stmt.close();
        con.close();
    }

    public void delete(long id) throws Exception {
        String sql = "DELETE FROM Utilizador WHERE id=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setLong(1, id);
        stmt.executeUpdate();

        stmt.close();
        con.close();
    }

    // Método adicional para autenticação
    public Utilizador autenticar(String email, String senhaHash,String Perfil) throws Exception {
        String sql = "SELECT * FROM Utilizador WHERE email=? AND senhaHash=? AND Perfil=? AND  estado='ATIVO'";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, senhaHash);
        stmt.setString(3, Perfil);
        ResultSet rs = stmt.executeQuery();
        Utilizador u = null;

        if (rs.next()) {
            u = new Utilizador();
            u.setId(rs.getLong("id"));
            u.setNomeCompleto(rs.getString("nomeCompleto"));
            u.setEmail(rs.getString("email"));
            u.setTelefone(rs.getString("telefone"));
            u.setEndereco(rs.getString("endereco"));
            u.setPerfil(rs.getString("perfil"));
            u.setEstado(rs.getString("estado"));
            u.setSenhaHash(rs.getString("senhaHash"));
        }

        rs.close();
        stmt.close();
        con.close();
        return u;
    }

    // Método para verificar se email já existe
    public boolean emailExiste(String email) throws Exception {
        String sql = "SELECT COUNT(*) FROM Utilizador WHERE email=?";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();
        boolean existe = false;

        if (rs.next()) {
            existe = rs.getInt(1) > 0;
        }

        rs.close();
        stmt.close();
        con.close();
        return existe;
    }
}