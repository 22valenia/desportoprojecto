/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import conexao.FactorDeConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Estefânia Essendje
 */
public class DesportoDAO {

    public void create(Desporto P) throws SQLException, ClassNotFoundException {
        Connection con = FactorDeConexao.getConnection("com.mysql.cj.jdbc.Driver");
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO desporto (nome, genero, tipo, duracao)VALUES(?,?,?,?)");
            stmt.setString(1, P.getNome());
            stmt.setString(2, P.getGenero());
            stmt.setString(3, P.getTipo());
            stmt.setFloat(4, P.getDuracao());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
        } catch (SQLClientInfoException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar:" + ex.getMessage());
        }

    }

    public void update(Desporto P) throws SQLException, ClassNotFoundException {
        Connection con = FactorDeConexao.getConnection("com.mysql.cj.jdbc.Driver");
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Update desporto set nome = ?, genero = ?, tipo = ?, duracao = ? Where id = "+P.getId());
            stmt.setString(1, P.getNome());
            stmt.setString(2, P.getGenero());
            stmt.setString(3, P.getTipo());
            stmt.setFloat(4, P.getDuracao());
            

            stmt.executeUpdate();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (SQLClientInfoException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar:" + ex.getMessage());
        }

    }

    public Desporto findById(int id) throws SQLException, ClassNotFoundException {
        Connection con = FactorDeConexao.getConnection("com.mysql.cj.jdbc.Driver");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Desporto desporto = new Desporto();

        try {
            stmt = con.prepareStatement("Select * from desporto where id =" + id);

            rs = stmt.executeQuery();
            if (rs.next()) {

                desporto.setNome(rs.getString("nome"));
                desporto.setGenero(rs.getString("genero"));
                desporto.setTipo(rs.getString("tipo"));
                desporto.setDuracao(Float.parseFloat(rs.getString("duracao")));

            } else {
                JOptionPane.showMessageDialog(null, "Não existe nenhum registro com ID:" + id);
            }

            stmt.close();

            return desporto;

        } catch (SQLClientInfoException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar:" + ex.getMessage());
            return null;
        }

    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection con = FactorDeConexao.getConnection("com.mysql.cj.jdbc.Driver");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Desporto desporto = new Desporto();

        try {
            stmt = con.prepareStatement("Delete from desporto where id =" + id);

            stmt.execute();
            stmt.close();

        } catch (SQLClientInfoException ex) {
            JOptionPane.showMessageDialog(null, "Erro na operação:" + ex.getMessage());

        }

    }

}
