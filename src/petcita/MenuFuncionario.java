/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petcita;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
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
public class MenuFuncionario {
    private Usuario Funcionario;

    public Usuario getFuncionario() {
        return Funcionario;
    }

    public void setFuncionario(Usuario funcionario) {
        this.Funcionario = funcionario;
    }
    
    public MenuFuncionario(Usuario funcionario)
    {
        this.Funcionario = funcionario;
    }
    
    public void clienteMainMenu(Connection conn) throws SQLException, Exception {
        String opcao = "", opcaoIterno = "";
        Scanner leitor = new Scanner(System.in);
        boolean sair = false, sairInterno = false;
        PedidoItem carrinhoItem = new PedidoItem();
        PedidoAgendamento carrinhoAgendamento = new PedidoAgendamento();
        int idCatServico = 0, idCatProduto = 0, quantidadeEscolhida = 0, idPedidoAgendamento = 0, idPedidoItem = 0;

        do {
            System.out.println("");
            System.out.println("---------------------PetCita----------------------");
            System.out.println("---------------FUNÇÔES--ESPECIAIS-----------------");
            System.out.println("1. Alterar Serviços"); 
            System.out.println("2. Alterar Produtos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = leitor.nextLine();

            switch (opcao) {
                case "1":
                    //1. Alterar Serviço
                    do {
                    System.out.println("");
                    System.out.println("---------------------PetCita----------------------");
                    System.out.println("---------------FUNÇÔES--ESPECIAIS-----------------");
                    System.out.println("1. Adicionar Serviço"); 
                    System.out.println("2. Editar Produto");
                    System.out.println("3. Remover Serviços");
                    System.out.println("5. Sair");
                    System.out.print("Escolha uma opcao: ");
                    opcaoIterno = leitor.nextLine();
                    
                    switch(opcaoIterno){
                        case "1":
                            
                        break;
                        case "2":
                            break;
                        case "3":
                            break;
                        case "4":
                            break;
                        default:
                            System.out.println("Opção invalida! Tente novamente.");

                    }
                    
                    } while (!sairInterno);
                    
                    break;
                case "2":
                    //2. Alterar Produto
                    
                    
                    break;
                case "3":
                    //3. Sair
                    System.out.println("Saindo...");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção invalida! Tente novamente.");
            }
        } while (!sair);
    }
}
