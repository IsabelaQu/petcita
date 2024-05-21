/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package petcita;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Giovanni
 */
public class PETCITA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String user = "", password = "", respostaInicio= "";
        
        int tentativas = 0;
        
        Scanner leitor = new Scanner(System.in);
        
        do
        {
            try
            {
                    System.out.println("");
                    System.out.println("---------------------PetCita----------------------");
                    System.out.println("--------------------------------------------------");
                    System.out.println("----------Faça Login ou Crie seu usuário----------");
                    System.out.println("");

                    while(!respostaInicio.equalsIgnoreCase("L") && !respostaInicio.equalsIgnoreCase("N") && !respostaInicio.equalsIgnoreCase("S")){

                        System.out.println("Deseja fazer Login, Criar um novo Usuário ou Sair? Login -> L, Novo Usuário -> N, Sair -> S");
                        System.out.print("Resposta: ");
                        respostaInicio = leitor.nextLine();

                    }

                    if(respostaInicio.equalsIgnoreCase("S"))
                        break;
                    
                    Connection conn = DataBaseUtils.getConnection("PETCITA");
                    
                    if(respostaInicio.equalsIgnoreCase("n"))
                    {
                        respostaInicio = "";
                        
                        Cliente cli = new Cliente();
                        
                        System.out.println("Digite seu nome:");
                        cli.setNome(leitor.nextLine());
                        
                        System.out.println("Digite seu telefone:");
                        cli.setTelefone(leitor.nextLine());
                        
                        System.out.println("Digite seu email:");
                        cli.setEmail(leitor.nextLine());
                        
                        System.out.println("Digite seu CEP:");
                        cli.setCep(leitor.nextLine());
                        
                        System.out.println("Digite o numero da sua Residencia:");
                        cli.setNumeroResidencia(leitor.nextInt());
                        
                        leitor.nextLine();
                        
                        System.out.println("Digite o login desejado:");
                        cli.setLogin(leitor.nextLine());
                        
                        System.out.println("Digite o senha:");
                        cli.setSenha(leitor.nextLine());
                        
                        try
                        {
                            cli.criarCliente(conn);
                            
                            System.out.println("");
                            System.out.println("---------------------PetCita----------------------");
                            System.out.println("--------------------------------------------------");
                            System.out.println("-------------Usuario Criado com Sucesso-----------");
                            System.out.println("");
                            
                            //continue;
                            
                        }
                        catch(Exception ex)
                        {
                            System.out.println("Erro ao entrar no programa: "+ ex.getMessage() + "  StackTrace: " +Arrays.toString(ex.getStackTrace()));
                        }
                                           
                    }
                    
                    if(respostaInicio.equalsIgnoreCase("l"))
                    {
                        String respostaLogin = "";
                        
                        while(!respostaLogin.equalsIgnoreCase("c") && !respostaLogin.equalsIgnoreCase("f")){

                            System.out.println("Deseja fazer Login como Cliente ou Funcionário? Cliente -> C ou Funcionário -> F");
                            System.out.print("Resposta: ");
                            respostaLogin = leitor.nextLine();

                        }
                        
                        if(respostaLogin.equalsIgnoreCase("c"))
                        {
                            Cliente cli = new Cliente();
                            
                            System.out.println("Digite seu login: ");
                            cli.setLogin(leitor.nextLine());
                            
                            System.out.println("Digite sua senha: ");
                            cli.setSenha(leitor.nextLine());
                            
                            if(cli.validaLogin(conn))
                                System.out.println("Login Concluido!");
                                break;
                                // Continuar Main de cliente aqui
                                    // Ver catalogo de produtos
                                    // Ver catologo de serviços
                                    
                                    //Escolher serviço -> mostra serviços e pede pra digitar o id
                                        // insere na tabela de pedidos e pedido_agendamento
                                    //Escolher produto -> mostra produtos e pede pra digitar o id
                                        // insere na tabela de pedidos e pedido_item
                                        
                                    // Ver carrinho
                                        // Mostra produtos da tab pedido_itens
                                        // Mostra produtos da tab pedido_agendamentos
                                    // Remover Produtos do Carrinho
                                        // Mostra produtos da tab pedido_itens e pede o id desejado pra cancelar
                                    // Cancelar Agendamentos no Carrinho
                                        // Mostra produtos da tab pedido_agendamentos e pede o id desejado pra cancelar
                        }
                        
                        if(respostaLogin.equalsIgnoreCase("f"))
                        {
                            Funcionario fun = new Funcionario();
                            
                            System.out.println("Digite seu login: ");
                            fun.setLogin(leitor.nextLine());
                            
                            System.out.println("Digite sua senha: ");
                            fun.setSenha(leitor.nextLine());
                            
                            if(fun.validaLogin(conn))
                                System.out.println("Login Concluido!");
                                break;
                                // Continuar Main de funcionario aqui
                                    // Idem do cliente
                                    // Cadastrar Produto
                                    // Cadastrar Serviço
                                    // Cadastrar Animal
                                        // mostrar lista de clientes, pedir o id e dps demais infos.
                        }
                        
                    }
            }
            catch(Exception ex)
            {
                System.out.println("Erro ao entrar no programa: "+ ex.getMessage() + "  StackTrace: " +Arrays.toString(ex.getStackTrace()));
            }
        }while(tentativas < 3);
        
        
    }
    
}
