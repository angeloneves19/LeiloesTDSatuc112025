/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet rs;

    public void cadastrarProduto(ProdutosDTO produto) {
        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar produto.");
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro.getMessage());
        } finally {
            try {
                prep.close();
                conn.close();
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + erro.getMessage());
            }
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        ArrayList<ProdutosDTO> lista = new ArrayList<>();
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";

        try {
            prep = conn.prepareStatement(sql);
            rs = prep.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                lista.add(produto);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
        } finally {
            try {
                rs.close();
                prep.close();
                conn.close();
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + erro.getMessage());
            }
        }

        return lista;
    }
}