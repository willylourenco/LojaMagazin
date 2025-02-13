import java.util.Scanner;

import dao.PedidoDAO;
import dao.ProdutoDAO;
import entidades.Cliente;
import entidades.Funcionario;
import entidades.Pedido;
import entidades.Produto;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ProdutoDAO produtoDAO = new ProdutoDAO();
        PedidoDAO pedidoDAO = new PedidoDAO();

        // Menu
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Cadastrar Cliente");
            System.out.println("3. Buscar Produto pelo ID");
            System.out.println("4. Listar Todos os Produtos");
            System.out.println("5. Efetuar Venda");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    
                    System.out.println("Digite o nome do produto:");
                    String nomeProduto = scanner.nextLine();
                    System.out.println("Digite o valor unitário:");
                    double valorUnitario = scanner.nextDouble();
                    System.out.println("Digite a quantidade em estoque:");
                    int quantidade = scanner.nextInt();
                    Produto produto = new Produto(nomeProduto, valorUnitario, quantidade);
                    produtoDAO.cadastrarProduto(produto);
                    break;

                case 5:
                   
                    System.out.println("Digite o CPF do cliente:");
                    String cpfCliente = scanner.nextLine();
                    Cliente cliente = new Cliente(cpfCliente); 

                    
                    System.out.println("Digite o CPF do vendedor:");
                    String cpfFuncionario = scanner.nextLine();
                    Funcionario funcionario = new Funcionario(cpfFuncionario); 
                    
                    List<Produto> produtosSelecionados = new ArrayList<>();
                    List<Integer> quantidades = new ArrayList<>();
                    double valorTotal = 0;

                    while (true) {
                        System.out.println("Digite o ID do produto para adicionar à venda (ou 0 para finalizar):");
                        int idProduto = scanner.nextInt();
                        if (idProduto == 0) {
                            break;
                        }

                        Produto produtoSelecionado = produtoDAO.buscarProdutoPorId(idProduto);
                        if (produtoSelecionado == null) {
                            System.out.println("Produto não encontrado!");
                            continue;
                        }

                        System.out.println("Digite a quantidade do produto:");
                        int quantidadeProduto = scanner.nextInt();

                        if (quantidadeProduto > produtoSelecionado.getQuantidade()) {
                            System.out.println("Quantidade insuficiente em estoque!");
                        } else {
                            produtosSelecionados.add(produtoSelecionado);
                            quantidades.add(quantidadeProduto);
                            valorTotal += produtoSelecionado.getValorUnitario() * quantidadeProduto;
                            produtoDAO.atualizarEstoque(idProduto, produtoSelecionado.getQuantidade() - quantidadeProduto);
                        }
                    }

                   
                    Pedido pedido = new Pedido(cliente, funcionario, valorTotal);
                    pedidoDAO.cadastrarPedido(pedido);

                    
                    for (int i = 0; i < produtosSelecionados.size(); i++) {
                        Produto p = produtosSelecionados.get(i);
                        int q = quantidades.get(i);
                        double valorProduto = p.getValorUnitario() * q;
                        pedidoDAO.cadastrarItemPedido(pedido.getId(), p.getId(), q, valorProduto);
                    }

                    System.out.println("Venda realizada com sucesso! Valor total: " + valorTotal);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
