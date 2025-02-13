package dao;

import java.sql.*;
import entidades.Funcionario;

public class FuncionarioDAO {

    public Funcionario buscarFuncionarioPorCpf(String cpf) {
        String sql = "SELECT * FROM FUNCIONARIO WHERE CPF = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(rs.getString("CPF"));
                funcionario.setNome(rs.getString("NOME"));
                funcionario.setEndereco(rs.getString("ENDERECO"));
                funcionario.setTelefone(rs.getString("TELEFONE"));
                return funcionario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
