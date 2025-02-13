package entidades;

import java.util.List;

public class Pedido {
    private int id;
    private String cpfCliente;
    private String cpfFuncionario;
    private double valorTotal;
    private List<ItemPedido> itens;

    public Pedido(Cliente cliente, Funcionario funcionario, double valorTotal2) {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Object getCliente() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getCliente'");
    }

    public Object getFuncionario() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getFuncionario'");
    }
}
