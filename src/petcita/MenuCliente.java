/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petcita;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import petcita.catalogo.CatProduto;
import petcita.catalogo.CatServico;
import petcita.pedido.PedidoAgendamento;
import petcita.pedido.PedidoItem;
import petcita.user.Animal;
import petcita.user.Usuario;

/**
 *
 * @author Giovanni
 */
public class MenuCliente {
    
    private Usuario Cliente;

    public Usuario getCliente() {
        return Cliente;
    }

    public void setCliente(Usuario cliente) {
        this.Cliente = cliente;
    }
    
    public MenuCliente(Usuario cliente)
    {
        this.Cliente = cliente;
    }
    
    public void clienteMainMenu(Connection conn) throws SQLException, Exception {
        String opcao = "";
        Scanner leitor = new Scanner(System.in);
        boolean sair = false;

        do {
            System.out.println("");
            System.out.println("---------------------PetCita----------------------");
            System.out.println("--------------------------------------------------");
            System.out.println("1. Escolher servico"); //feito
            System.out.println("2. Escolher produto");
            System.out.println("3. Ver carrinho");
            System.out.println("4. Remover produtos do carrinho");
            System.out.println("5. Cancelar agendamentos no carrinho");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = leitor.nextLine();

            switch (opcao) {
                case "1":
                    //1. Escolher servico
                    CatServico catalogoServicos = new CatServico();
                    System.out.println(catalogoServicos.exibirCatalogo(conn));
                    int idCatServico = 0, idAnimalEscolhido = 0;
                    
                    System.out.println("");
                    System.out.println("Escolha um dos servi�os acima para adicionar ao seu Carrinho!");
                    System.out.print("Digite o numero(ID) do servi�o desejado:");
                    try
                    {
                        idCatServico = Integer.parseInt(leitor.nextLine());
                    }
                    catch(Exception ex)
                    {
                       System.out.println("Digite um n�mero(ID) presente na lista acima!");
                       break;
                    }
                    
                    Animal animalCliente = new Animal();
                    
                    String animaisExibir = animalCliente.lerAnimalUsuario(conn, this.Cliente.getIdUsuario());
                    
                    if(animaisExibir.equals("0"))
                    {
                        System.out.println("Nenhum encontrado para realiza��o do Servi�o.");
                        break;
                    }
                    
                    System.out.println(animaisExibir);
                    
                    try
                    {
                        idAnimalEscolhido = Integer.parseInt(leitor.nextLine());
                    }
                    catch(Exception ex)
                    {
                       System.out.println("Digite um n�mero(ID) presente na lista acima!");
                       break;
                    }
               
                    PedidoAgendamento carrinhoAgendamento = new PedidoAgendamento();
                    
                    carrinhoAgendamento.setIdUsuario(this.Cliente.getIdUsuario());
                    carrinhoAgendamento.setDataAgendamento(new Date());
                    carrinhoAgendamento.setIdCatServico(idCatServico);
                    carrinhoAgendamento.setIdAnimal(idAnimalEscolhido);
                    
                    carrinhoAgendamento.criarPedidoAgendamento(conn);
                    
                    System.out.println("Servi�o adicionado ao Carrinho com sucesso!!");
                    
                    break;
                case "2":
                    //1. Escolher CatProduto
                    CatProduto catalogoProdutos = new CatProduto();
                    
                    System.out.println(catalogoProdutos.exibirCatalogo(conn));
                    int idCatProduto = 0, quantidadeEscolhida = 0;
                    
                    System.out.println("");
                    System.out.println("Escolha um dos produtos acima para adicionar ao seu Carrinho!");
                    System.out.print("Digite o numero(ID) do produto desejado:");
                    try
                    {
                        idCatProduto = Integer.parseInt(leitor.nextLine());
                    }
                    catch(Exception ex)
                    {
                       System.out.println("Digite um n�mero(ID) presente na lista acima!");
                       break;
                    }
                    System.out.println("");
                    System.out.print("Digite a quantidade desejada:");
                    
                    try
                    {
                        quantidadeEscolhida = Integer.parseInt(leitor.nextLine());
                    }
                    catch(Exception ex)
                    {
                       System.out.println("Digite um n�mero(ID) presente na lista acima!");
                       break;
                    }
                    
                    PedidoItem carrinhoItem = new PedidoItem();
                    
                    carrinhoItem.setIdUsuario(this.Cliente.getIdUsuario());
                    carrinhoItem.setDataPedido(new Date());
                    carrinhoItem.setIdCatProduto(idCatProduto);
                    carrinhoItem.setQuantidade(quantidadeEscolhida);
                    
                    carrinhoItem.criarPedidoItens(conn);
                    
                    System.out.println("Produto adicionado ao Carrinho com sucesso!!");
                    
                    break;
                case "3":
                    //3. Ver carrinho
                    
                    
                    break;
                case "4":
                    //4. Remover produtos do carrinho
                    break;
                case "5":
                    //5. Cancelar agendamentos no carrinho
                    break;
                case "6":
                    //6. Sair
                    System.out.println("Saindo...");
                    sair = true;
                    break;
                default:
                    System.out.println("Op��o invalida! Tente novamente.");
            }
        } while (!sair);
    }
    
    
}