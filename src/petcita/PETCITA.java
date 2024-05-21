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


                    while(!respostaInicio.equals("L") && !respostaInicio.equals("N") && !respostaInicio.equals("S")){

                        System.out.println("Deseja fazer Login, Criar um novo Usuário ou Sair? Login -> L, Novo Usuário -> N, Sair -> S");
                        System.out.print("Resposta: ");
                        respostaInicio = leitor.nextLine();

                    }

                    if(respostaInicio.equals("S"))
                        break;
                    
                    Connection conn = DataBaseUtils.getConnection("PETCITA");
                    
                    if(respostaInicio.equals("N"))
                    {
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
                    
                    if(respostaInicio.equals("L"))
                    {
                        
                         System.out.println("Faça login:");
                         break;
                    }
            }
            catch(Exception ex)
            {
                System.out.println("Erro ao entrar no programa: "+ ex.getMessage() + "  StackTrace: " +Arrays.toString(ex.getStackTrace()));
            }
        }while(tentativas < 3);
        
        
    }
    
}
