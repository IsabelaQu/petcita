/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package petcita;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Scanner;
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
                    System.out.println("----------Fa�a Login ou Crie seu usu�rio----------");
                    System.out.println("");

                    while(!respostaInicio.equalsIgnoreCase("L") && !respostaInicio.equalsIgnoreCase("N") && !respostaInicio.equalsIgnoreCase("S")){

                        System.out.println("Deseja fazer Login, Criar um novo Usu�rio ou Sair? Login -> L, Novo Usu�rio -> N, Sair -> S");
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
                        
                        Usuario user = new Usuario();
                        
                        System.out.println("Digite seu login: ");
                        user.setLogin(leitor.nextLine());
                        
                        System.out.println("Digite sua senha: ");
                        user.setSenha(leitor.nextLine());
                        
                        if(user.validaLogin(conn))
                        {
                            System.out.println("Bem vindo ao PetCita!");
                            System.out.println(String.format("O que deseja fazer hoje %s?", user.getNome()));
                            if(user.getFuncionario())
                            {
                                MenuFuncionario menuFuncionario = new MenuFuncionario(user);
                                menuFuncionario.funcionarioMainMenu(conn);
                            }
                            else
                            {
                                MenuCliente menuCliente = new MenuCliente(user);
                                menuCliente.clienteMainMenu(conn);
                            }

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
