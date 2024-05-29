/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petcita;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

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
        boolean sair = false, sairInterno = false;
        PedidoItem carrinhoItem = new PedidoItem();
        PedidoAgendamento carrinhoAgendamento = new PedidoAgendamento();
        
        carrinhoItem.setIdUsuario(Cliente.getIdUsuario());
        carrinhoAgendamento.setIdUsuario(Cliente.getIdUsuario());
        
        int idCatServico = 0, idAnimalEscolhido = 0, idCatProduto = 0, quantidadeEscolhida = 0, idPedidoAgendamento = 0, idPedidoItem = 0;

        do {
            System.out.println("");
            System.out.println("---------------------PetCita----------------------");
            System.out.println("--------------------------------------------------");
            System.out.println("1. Escolher servico"); 
            System.out.println("2. Escolher produto");
            System.out.println("3. Cadastre ou altere alguma informa��o do seu Animalzinho!");
            System.out.println("4. Ver carrinho");
            System.out.println("5. Remover produtos do carrinho");
            System.out.println("6. Cancelar agendamentos no carrinho");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = leitor.nextLine();

            switch (opcao) {
                case "1":
                    //1. Escolher servico
                    CatServico catalogoServicos = new CatServico();
                    System.out.println(catalogoServicos.exibirCatalogo(conn));
                    
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
                                   
                    carrinhoAgendamento.setIdUsuario(this.Cliente.getIdUsuario());
                    carrinhoAgendamento.setDataAgendamento(ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toLocalDate());
                    carrinhoAgendamento.setIdCatServico(idCatServico);
                    carrinhoAgendamento.setIdAnimal(idAnimalEscolhido);
                    
                    carrinhoAgendamento.criarPedidoAgendamento(conn);
                    
                    System.out.println("Servi�o adicionado ao Carrinho com sucesso!!");
                    
                    break;
                case "2":
                    //2. Escolher produto
                    CatProduto catalogoProdutos = new CatProduto();
                    
                    System.out.println(catalogoProdutos.exibirCatalogo(conn));                    
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
                    
                    carrinhoItem.setIdUsuario(this.Cliente.getIdUsuario());
                    carrinhoItem.setDataPedido(ZonedDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()).toLocalDate());
                    carrinhoItem.setIdCatProduto(idCatProduto);
                    carrinhoItem.setQuantidade(quantidadeEscolhida);
                    
                    carrinhoItem.criarPedidoItens(conn);
                    
                    System.out.println("Produto adicionado ao Carrinho com sucesso!!");
                    
                    break;
                    
                    
                case "3":
                    //3. Cadastrando o Animal                  
                    String opcaoAnimal;
                    do {
                        System.out.println("");
                        System.out.println("---------------------PetCita----------------------");
                        System.out.println("-------------GERENCIAR--SEUS--PETS----------------");
                        System.out.println("1. Cadastrar seu Pet!");
                        System.out.println("2. Alterar Animal");
                        System.out.println("3. Remover Animal");
                        System.out.println("4. Voltar");
                        System.out.print("Escolha uma opcao: ");
                        opcaoAnimal = leitor.nextLine();

                        switch (opcaoAnimal) {
                            case "1":
                                // Cadastrar Animal
                                Animal cadastroAnimal = new Animal();
                                cadastroAnimal.setIdCliente(this.Cliente.getIdUsuario());

                                System.out.print("Digite o nome do seu Pet: ");
                                cadastroAnimal.setNome(leitor.nextLine());

                                System.out.print("Digite a esp�cie do seu Pet: ");
                                cadastroAnimal.setEspecie(leitor.nextLine());

                                System.out.print("Digite a data de nascimento do seu aumigo (AAAA-MM-DD): ");
                                cadastroAnimal.setDataNascimento(LocalDate.parse(leitor.nextLine()));

                                System.out.print("Digite o porte do seu Pet (P/M/G): ");
                                cadastroAnimal.setPorte(leitor.nextLine().charAt(0));

                                cadastroAnimal.criarAnimal(conn);

                                System.out.println("Seu Pet foi cadastrado com sucesso! Bem-vindo, aumigo!");
                                break;

                            case "2":
                                    // Alterar Animal
                                    System.out.print("Digite o ID do animal que deseja alterar: ");
                                    int idAnimalAlterar = Integer.parseInt(leitor.nextLine());

                                    Animal animalAlterar = new Animal();
                                    animalAlterar = animalAlterar.buscarAnimalPorId(conn, idAnimalAlterar);

                                    if (animalAlterar != null) {
                                        String opcaoAlterar;
                                        do {
                                            System.out.println("");
                                            System.out.println("-------------ALTERAR-INFORMA��ES-ANIMAL----------------");
                                            System.out.println("1. Alterar Nome");
                                            System.out.println("2. Alterar Esp�cie");
                                            System.out.println("3. Alterar Data de Nascimento");
                                            System.out.println("4. Alterar Porte");
                                            System.out.println("5. Voltar");
                                            System.out.print("Escolha uma opcao: ");
                                            opcaoAlterar = leitor.nextLine();

                                            switch (opcaoAlterar) {
                                                case "1":
                                                    System.out.print("Digite o novo nome do seu Pet: ");
                                                    String novoNome = leitor.nextLine();
                                                    if (!novoNome.isEmpty()) {
                                                        animalAlterar.setNome(novoNome);
                                                    }
                                                    break;

                                                case "2":
                                                    System.out.print("Digite a nova esp�cie do seu Pet: ");
                                                    String novaEspecie = leitor.nextLine();
                                                    if (!novaEspecie.isEmpty()) {
                                                        animalAlterar.setEspecie(novaEspecie);
                                                    }
                                                    break;

                                                case "3":
                                                    System.out.print("Digite a nova data de nascimento do seu aumigo (AAAA-MM-DD): ");
                                                    String novaDataNascimento = leitor.nextLine();
                                                    if (!novaDataNascimento.isEmpty()) {
                                                        animalAlterar.setDataNascimento(LocalDate.parse(novaDataNascimento));
                                                    }
                                                    break;

                                                case "4":
                                                    System.out.print("Digite o novo porte do seu Pet (P/M/G): ");
                                                    String novoPorte = leitor.nextLine();
                                                    if (!novoPorte.isEmpty()) {
                                                        animalAlterar.setPorte(novoPorte.charAt(0));
                                                    }
                                                    break;

                                                case "5":
                                                    // Voltar
                                                    break;

                                                default:
                                                    System.out.println("Op��o inv�lida! Tente novamente.");
                                            }
                                        } while (!opcaoAlterar.equals("5"));

                                        animalAlterar.atualizarAnimal(conn);

                                        System.out.println("Informa��es do Pet alteradas com sucesso!");
                                    } else {
                                        System.out.println("Animal n�o encontrado.");
                                    }
                                    break;
                                    
                            case "3":
                                // Remover Animal
                                System.out.print("Digite o ID do animal que deseja remover: ");
                                int idAnimalRemover = Integer.parseInt(leitor.nextLine());

                                Animal animalRemover = new Animal();
                                if (animalRemover.deletarAnimal(conn, idAnimalRemover)) {
                                    System.out.println("Animal removido com sucesso!");
                                } else {
                                    System.out.println("Animal n�o encontrado ou erro ao remover.");
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
                    
                    break;  
                case "4":
                    //4. Ver carrinho
                    carrinhoItem.listarPedidoItens(conn);
                    
                    System.out.println("");
                    
                    System.out.println(carrinhoAgendamento.listarPedidoAgendamentos(conn));
                    
                    break;
                    
                case "5":
                    //5. Remover produtos do carrinho
                    carrinhoItem.listarPedidoItens(conn);
                    
                    System.out.println("");
                    System.out.println("Qual produto voc� gostaria de remover do carrinho?");
                    
                    try
                    {
                        idPedidoItem = Integer.parseInt(leitor.nextLine());
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Digite um n�mero(ID) presente na lista acima!");
                        break;
                    }
                    
                    carrinhoItem.setIdPedidoItem(idPedidoItem);
                    
                    carrinhoItem.deletarPedidoItem(conn);
                    
                    System.out.println("Produto removido do carrinho com sucesso!!");
                    break;
                case "6":
                    //6. Cancelar agendamentos no carrinho
                    carrinhoAgendamento.listarPedidoAgendamentos(conn);
                    System.out.println("");
                    System.out.println("Qual agendamento voc� gostaria de cancelar?");
                    
                    try
                    {
                        idPedidoAgendamento = Integer.parseInt(leitor.nextLine());
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Digite um n�mero(ID) presente na lista acima!");
                        break;
                    }
                    
                    carrinhoAgendamento.setIdPedidoAgendamento(idPedidoAgendamento);
                    
                    carrinhoAgendamento.deletarPedidoAgendamento(conn);
                    
                    System.out.println("Agendamento cancelado com sucesso!!");
                    break;
                case "7":
                    //7. Sair
                    System.out.println("Saindo...");
                    sair = true;
                    break;
                default:
                    System.out.println("Op��o invalida! Tente novamente.");
            }
        } while (!sair);
    }
    
    
}
