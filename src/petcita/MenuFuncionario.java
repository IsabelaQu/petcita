/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petcita;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;
import petcita.catalogo.CatProduto;
import petcita.catalogo.CatServico;
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
    
    public void funcionarioMainMenu(Connection conn) throws SQLException, Exception {
        String opcao = "", opcaoIterno = "";
        Scanner leitor = new Scanner(System.in);
        boolean sair = false, sairInterno = false;
        CatProduto catalogoProduto = new CatProduto();
        CatServico catalogoServico = new CatServico();
        int idCatServico = 0, idCatProduto = 0;

        do {
            System.out.println("");
            System.out.println("---------------------PetCita----------------------");
            System.out.println("---------------FUNÇÕES--ESPECIAIS-----------------");
            System.out.println("1. Alterar Serviços"); 
            System.out.println("2. Alterar Produtos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = leitor.nextLine();

            switch (opcao) {
                case "1":
                    //1. Alterar Serviï¿½o
                    do {
                        System.out.println("");
                        System.out.println("---------------------PetCita----------------------");
                        System.out.println("-----------FUNÇÕES--ESPECIAIS--SERVIÇO------------");
                        System.out.println("1. Adicionar Serviço"); 
                        System.out.println("2. Editar Serviço");
                        System.out.println("3. Ativar ou Desativar Serviços");
                        System.out.println("4. Sair");
                        System.out.print("Escolha uma opcao: ");
                        opcaoIterno = leitor.nextLine();

                        switch(opcaoIterno){
                            case "1":
                                try
                                {
                                    CatServico itemCatalogoServico = new CatServico();
                                    System.out.println("Cadastro de novos Serviços");
                                    System.out.println("Nome:");
                                    itemCatalogoServico.setNome(leitor.nextLine());
                                    System.out.println("Descrição:");
                                    itemCatalogoServico.setDescricao(leitor.nextLine());
                                    System.out.println("Tempo de duração em minutos:");
                                    itemCatalogoServico.setMinDuracao(leitor.nextInt());
                                    leitor.nextLine();
                                    System.out.println("Categotia:");
                                    itemCatalogoServico.setCategoria(leitor.nextLine());
                                    System.out.println("Valor:");
                                    itemCatalogoServico.setValor(leitor.nextDouble());
                                    System.out.println("Serviço Interno:(true/false)");
                                    itemCatalogoServico.setServicoInterno(leitor.nextBoolean());
                                    leitor.nextLine();
                                    
                                    itemCatalogoServico.setDisponivel(true);

                                    itemCatalogoServico.criarCatalogo(conn);
                                    
                                    System.out.println("-----------------------------");

                                    System.out.println("Serviço criado com sucesso!!!");
                                
                                }  
                                catch(Exception ex)
                                {
                                    System.out.println("Problemas ao cadastrar o serviço! Causa: "+ ex.getMessage().toString());
                                }
                                
                                opcaoIterno = null;
                                break;
                            case "2":
                                try
                                {
                                    System.out.println(catalogoServico.exibirCatalogo(conn));
                                    System.out.println("");
                                    System.out.print("Digite o número do serviço que deseja alterar: ");
                                    int numeroServico = leitor.nextInt();
                                    leitor.nextLine(); 

                                    CatServico itemCatServico = catalogoServico.buscarPorId(conn, numeroServico);

                                    System.out.println(itemCatServico.exibirItemCatalogo());
                                    System.out.println("");

                                    System.out.println("Nome:");
                                    itemCatServico.setNome(leitor.nextLine());
                                    System.out.println("Descrição:");
                                    itemCatServico.setDescricao(leitor.nextLine());
                                    System.out.println("Tempo de duração em minutos:");
                                    itemCatServico.setMinDuracao(leitor.nextInt());
                                    leitor.nextLine();
                                    System.out.println("Categotia:");
                                    itemCatServico.setCategoria(leitor.nextLine());
                                    System.out.println("Valor:");
                                    itemCatServico.setValor(leitor.nextDouble());
                                    leitor.nextLine();
                                    System.out.println("Serviço Interno:(true/false)");
                                    itemCatServico.setServicoInterno(leitor.nextBoolean());
                                    leitor.nextLine();
                                    itemCatServico.setDisponivel(true);

                                    itemCatServico.alterarItemCatalogo(conn);

                                    System.out.println("Serviço alterado com sucesso!!!");

                                }
                                catch(Exception ex)
                                {
                                    System.out.println("Problemas ao alterar o serviço! Causa: "+ ex.getMessage().toString());
                                }
                                opcaoIterno = null;

                                break;
                            case "3":
                                try
                                {
                                    System.out.println(catalogoServico.exibirCatalogo(conn));
                                    System.out.println("");
                                    System.out.print("Digite o número do produto que deseja ATIVAR OU INATIVAR: ");
                                    CatServico itemCatServico = catalogoServico.buscarPorId(conn, leitor.nextInt());
                                    leitor.nextLine(); 
                                    System.out.print("ATIVAR OU INATIVAR?:");
                                    String acao = leitor.nextLine();

                                    boolean ativo = acao.equalsIgnoreCase("ativar");
                                    catalogoServico.setIdCatalogo(itemCatServico.getIdCatalogo());
                                    catalogoServico.atualizarDisponibilidade(conn, ativo);
                                    
                                    System.out.println("Alteração de disponibilidade concluida!");
                                }
                                catch(Exception ex)
                                {
                                     System.out.println("Problemas ao alterar a disponibilidade do serviço! Causa: "+ ex.getMessage().toString());
                                }
                                break;
                            case "4":
                                System.out.println("Saindo...");
                                sairInterno = true;
                                opcaoIterno = null;
                                break;
                            default:
                                System.out.println("Opção invalida! Tente novamente.");
                        }
                    
                    } while (!sairInterno);
                    
                    sairInterno = false;
                    opcao = "";
                    
                    break;
                    
                case "2":
                    //2. Alterar Produto
                    do {
                        System.out.println("");
                        System.out.println("---------------------PetCita----------------------");
                        System.out.println("-----------FUNÇÕES--ESPECIAIS--PRODUTO------------");
                        System.out.println("1. Adicionar Produto"); 
                        System.out.println("2. Editar Produto");
                        System.out.println("3. Ativar ou Desativar Produto");
                        System.out.println("4. Sair");
                        System.out.print("Escolha uma opcao: ");
                        opcaoIterno = leitor.nextLine();

                        switch(opcaoIterno){
                            case "1":
                                try
                                {
                                    CatProduto itemCatalogoProduto = new CatProduto();
                                    System.out.println("Cadastro de novos Produtos"); 
                                    System.out.println("Nome:");
                                    itemCatalogoProduto.setNome(leitor.nextLine());
                                    System.out.println("Descrição:");
                                    itemCatalogoProduto.setDescricao(leitor.nextLine());
                                    System.out.println("Fornecedor:");
                                    itemCatalogoProduto.setFornecedor(leitor.nextLine());
                                    System.out.println("Data de Validade:");
                                    itemCatalogoProduto.setDtValidade(leitor.nextLine());
                                    System.out.println("Categotia:");
                                    itemCatalogoProduto.setCategoria(leitor.nextLine());
                                    System.out.println("Valor:");
                                    itemCatalogoProduto.setValor(leitor.nextDouble());
                                    leitor.nextLine();
                                    itemCatalogoProduto.setDisponivel(true);
                                    
                                    itemCatalogoProduto.setDtRegistro(ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toLocalDate());

                                    itemCatalogoProduto.criarCatalogo(conn);

                                    System.out.println("Produto criado com sucesso!!!");

                                }  
                                catch(Exception ex)
                                {
                                    System.out.println("Problemas ao cadastrar o serviço! Causa: "+ ex.getMessage().toString());
                                }

                            break;
                            case "2":
                                try
                                {
                                    System.out.println(catalogoProduto.exibirCatalogo(conn));
                                    System.out.println("");
                                    System.out.print("Digite o número do produto que deseja alterar: ");

                                    CatProduto itemCatProduto = catalogoProduto.buscarPorId(conn, leitor.nextInt());
                                    leitor.nextLine(); 
                                    
                                    System.out.println(itemCatProduto.exibirItemCatalogo());
                                    System.out.println("");
                                    
                                    System.out.println("Abaixo coloque os novos valores desejados!"); 
                                    System.out.println("Nome:");
                                    itemCatProduto.setNome(leitor.nextLine());
                                    System.out.println("Descrição:");
                                    itemCatProduto.setDescricao(leitor.nextLine());
                                    System.out.println("Fornecedor:");
                                    itemCatProduto.setFornecedor(leitor.nextLine());
                                    System.out.println("Data de Validade:");
                                    itemCatProduto.setDtValidade(leitor.nextLine());
                                    System.out.println("Categotia:");
                                    itemCatProduto.setCategoria(leitor.nextLine());
                                    System.out.println("Valor:");
                                    itemCatProduto.setValor(leitor.nextDouble());
                                    leitor.nextLine();
                                    itemCatProduto.setDisponivel(true);

                                    itemCatProduto.alterarItemCatalogo(conn);

                                    System.out.println("Produto alterado com sucesso!!!");

                                }
                                catch(Exception ex)
                                {
                                     System.out.println("Problemas ao alterar o produto! Causa: "+ ex.getMessage().toString());
                                    break;
                                }
                                break;
                            case "3":
                                try
                                {
                                    System.out.println(catalogoProduto.exibirCatalogo(conn));
                                    System.out.println("");
                                    System.out.print("Digite o número do produto que deseja ATIVAR OU INATIVAR: ");
                                    CatProduto itemCatProduto = catalogoProduto.buscarPorId(conn, leitor.nextInt());
                                    leitor.nextLine(); 
                                    System.out.print("ATIVAR OU INATIVAR?:");
                                    String acao = leitor.nextLine();

                                    boolean ativo = acao.equalsIgnoreCase("ativar");
                                    catalogoProduto.setIdCatalogo(itemCatProduto.getIdCatalogo());
                                    catalogoProduto.atualizarDisponibilidade(conn, ativo);
                                    
                                    System.out.println("Alteração de disponibilidade concluida!");
                                }
                                catch(Exception ex)
                                {
                                     System.out.println("Problemas ao alterar a disponibilidade do produto! Causa: "+ ex.getMessage().toString());
                                    break;
                                }
                                break;
                            case "4":
                                System.out.println("Saindo...");
                                sairInterno = true;
                                break;
                            default:
                                System.out.println("Opção invalida! Tente novamente.");

                        }
                    
                    } while (!sairInterno);
                    
                    sairInterno = false;
                    opcao = "";
                    
                    break;
                case "3":
                    //3. Sair
                    System.out.println("Saindo...");
                    sair = true;
                    opcao = "";
                    break;
                default:
                    System.out.println("Opção invalida! Tente novamente.");
            }
        } while (!sair);
    }
}
