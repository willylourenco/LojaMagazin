package dao;

import java.sql.*;

import entidades.Produto;

public class ProdutoDAO {
    private Connection conn;

    public ProdutoDAO() {
        
        this.conn = Conexao.getConnection();
        if (this.conn == null) {
            System.out.println("Erro de conexão com o banco de dados!");
        }
    }

    
    public void cadastrarProduto(Produto produto) {
        if (conn == null) {
            System.out.println("Erro: conexão não estabelecida!");
            return;
        }

        String sql = "INSERT INTO PRODUTO (NOME, VALOR_UNIT, QUANTIDADE) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getValorUnitario());
            stmt.setInt(3, produto.getQuantidade());
            stmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public Produto buscarProdutoPorId(int id) {
        if (conn == null) {
            System.out.println("Erro: conexão não estabelecida!");
            return null;
        }

        String sql = "SELECT * FROM PRODUTO WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Produto(rs.getInt("ID"), rs.getString("NOME"), rs.getDouble("VALOR_UNIT"), rs.getInt("QUANTIDADE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public void atualizarEstoque(int id, int novaQuantidade) {
        if (conn == null) {
            System.out.println("Erro: conexão não estabelecida!");
            return;
        }

        String sql = "UPDATE PRODUTO SET QUANTIDADE = ? WHERE ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}