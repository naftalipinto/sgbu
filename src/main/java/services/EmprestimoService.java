package services;

import classes.Emprestimo;
import classes.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoService {
    
    public void create(Emprestimo e) throws Exception {
        String sql = "INSERT INTO Emprestimo (exemplarId, utilizadorId, dataEmprestimo, dataPrevistaDevolucao, dataDevolucao, estado) VALUES (?,?,?,?,?,?)";
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setLong(1, e.getExemplarId());
        stmt.setLong(2, e.getUtilizadorId());
        
        // Converter java.util.Date para java.sql.Date
        stmt.setDate(3, new java.sql.Date(e.getDataEmprestimo().getTime()));
        stmt.setDate(4, new java.sql.Date(e.getDataPrevistaDevolucao().getTime()));
        
        // dataDevolucao pode ser nula
        if (e.getDataDevolucao() != null) {
            stmt.setDate(5, new java.sql.Date(e.getDataDevolucao().getTime()));
        } else {
            stmt.setNull(5, Types.DATE);
        }
        
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
            e.setId(rs.getLong("id")); // Faltava definir o ID
            e.setExemplarId(rs.getLong("exemplarId"));
            e.setUtilizadorId(rs.getLong("utilizadorId"));
            
            // Converter java.sql.Date para java.util.Date
            java.sql.Date sqlDateEmprestimo = rs.getDate("dataEmprestimo");
            if (sqlDateEmprestimo != null) {
                e.setDataEmprestimo(new java.util.Date(sqlDateEmprestimo.getTime()));
            }
            
            java.sql.Date sqlDatePrevista = rs.getDate("dataPrevistaDevolucao");
            if (sqlDatePrevista != null) {
                e.setDataPrevistaDevolucao(new java.util.Date(sqlDatePrevista.getTime()));
            }
            
            java.sql.Date sqlDateDevolucao = rs.getDate("dataDevolucao");
            if (sqlDateDevolucao != null) {
                e.setDataDevolucao(new java.util.Date(sqlDateDevolucao.getTime()));
            }
            
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
            e.setId(rs.getLong("id"));
            e.setExemplarId(rs.getLong("exemplarId"));
            e.setUtilizadorId(rs.getLong("utilizadorId"));
            
            // Converter java.sql.Date para java.util.Date
            java.sql.Date sqlDateEmprestimo = rs.getDate("dataEmprestimo");
            if (sqlDateEmprestimo != null) {
                e.setDataEmprestimo(new java.util.Date(sqlDateEmprestimo.getTime()));
            }
            
            java.sql.Date sqlDatePrevista = rs.getDate("dataPrevistaDevolucao");
            if (sqlDatePrevista != null) {
                e.setDataPrevistaDevolucao(new java.util.Date(sqlDatePrevista.getTime()));
            }
            
            java.sql.Date sqlDateDevolucao = rs.getDate("dataDevolucao");
            if (sqlDateDevolucao != null) {
                e.setDataDevolucao(new java.util.Date(sqlDateDevolucao.getTime()));
            }
            
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
        
        // Converter java.util.Date para java.sql.Date
        stmt.setDate(3, new java.sql.Date(e.getDataEmprestimo().getTime()));
        stmt.setDate(4, new java.sql.Date(e.getDataPrevistaDevolucao().getTime()));
        
        // dataDevolucao pode ser nula
        if (e.getDataDevolucao() != null) {
            stmt.setDate(5, new java.sql.Date(e.getDataDevolucao().getTime()));
        } else {
            stmt.setNull(5, Types.DATE);
        }
        
        stmt.setString(6, e.getEstado());
        stmt.setLong(7, e.getId()); // Faltava definir o parâmetro para WHERE id=?

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