
    private static void clienteMainMenu(Cliente cliente, Scanner leitor, Connection conn) {
        String opcao = "";

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
                    escolherServico(cliente, leitor, conn);
                    break;
                case "2":
                    verCatProduto(cliente, leitor, conn);
                    break;
                case "3":
                    verCarrinho(cliente, conn);
                    break;
                case "4":
                    removerProdutoCarrinho(cliente, leitor, conn);
                    break;
                case "5":
                    cancelarAgendamentoCarrinho(cliente, leitor, conn);
                    break;
                case "6":
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (!opcao.equals("8"));
    }
