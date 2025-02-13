package dao;

import java.sql.*;
import entidades.Cliente;

public class ClienteDAO {

    public Cliente buscarClientePorCpf(String cpf) {
        String sql = "SELECT * FROM CLIENTE WHERE CPF = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("CPF"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setEndereco(rs.getString("ENDERECO"));
                cliente.setTelefone(rs.getString("TELEFONE"));
                return cliente;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
