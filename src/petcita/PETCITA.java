/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package petcita;

import java.sql.Connection;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import petcita.catalogo.CatServico;
import petcita.pedido.PedidoAgendamento;
import petcita.pedido.PedidoItem;
import petcita.user.Usuario;

/**
 *
 * @author Giovanni
 */
public class PETCITA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String respostaInicio= "";
        
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
                        
                        Usuario user = new Usuario();
                        
                        System.out.println("Digite seu nome:");
                        user.setNome(leitor.nextLine());
                        
                        System.out.println("Digite seu telefone:");
                        user.setTelefone(leitor.nextLine());
                        
                        System.out.println("Digite seu email:");
                        user.setEmail(leitor.nextLine());
                        
                        System.out.println("Digite seu CEP:");
                        user.setCep(leitor.nextLine());
                        
                        System.out.println("Digite o numero da sua Residencia:");
                        user.setNumeroResidencia(leitor.nextInt());
                        
                        leitor.nextLine();
                        
                        System.out.println("Digite o login desejado:");
                        user.setLogin(leitor.nextLine());
                        
                        System.out.println("Digite o senha:");
                        user.setSenha(leitor.nextLine());
                        
                        user.setFuncionario(false);
                        
                        try
                        {
                            user.criarUsuario(conn);
                            
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
                            Usuario userCliente = new Usuario();
                            
                            System.out.println("Digite seu login: ");
                            userCliente.setLogin(leitor.nextLine());
                            
                            System.out.println("Digite sua senha: ");
                            userCliente.setSenha(leitor.nextLine());
                            
                            if(userCliente.validaLogin(conn))
                            {
                                MenuCliente menuCliente = new MenuCliente(userCliente);
                                
                                menuCliente.clienteMainMenu(conn);
                            }
                        }
                        
                        if(respostaLogin.equalsIgnoreCase("f"))
                        {
                            Usuario userFuncionario = new Usuario();
                            
                            System.out.println("Digite seu login: ");
                            userFuncionario.setLogin(leitor.nextLine());
                            
                            System.out.println("Digite sua senha: ");
                            userFuncionario.setSenha(leitor.nextLine());
                            
                            if(userFuncionario.validaLogin(conn))
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
