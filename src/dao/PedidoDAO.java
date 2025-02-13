package dao;

import java.sql.*;

import entidades.Pedido;

public class PedidoDAO {
    private Connection conn;

    public PedidoDAO() {
        this.conn = Conexao.getConnection();
    }

    
    public void cadastrarPedido(Pedido pedido) {
        String sql = "INSERT INTO PEDIDO (CPF_CLIENTE_FK, CPF_FUNCIONARIO_FK, VALOR_TOTAL) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ((Pedido) pedido.getCliente()).getCpfCliente());
            stmt.setString(2, ((Pedido) pedido.getFuncionario()).getCpfFuncionario());
            stmt.setDouble(3, pedido.getValorTotal());
            stmt.executeUpdate();

            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                pedido.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void cadastrarItemPedido(int pedidoId, int produtoId, int quantidade, double valor) {
        String sql = "INSERT INTO ITEM_PEDIDO (ID_PEDIDO_FK, ID_PRODUTO_FK, QUANTIDADE, VALOR) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedidoId);
            stmt.setInt(2, produtoId);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, valor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
