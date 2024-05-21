package petcita;

// classe
public class cat_servico extends catalogo {

    //atributos
    private int min_duracao;
    private String servico_interno;

    //construtor
    public cat_servico(int id_catalogo, Boolean disponivel, Double valor, String descricao, String categoria, int min_duracao, String servico_interno) {
        super(id_catalogo, disponivel, valor, descricao, categoria);
        this.min_duracao = min_duracao;
        this.servico_interno = servico_interno;
    }

    // getters & setters
    public int getMin_duracao() {
        return min_duracao;
    }

    public void setMin_duracao(int min_duracao) {
        this.min_duracao = min_duracao;
    }

    public String getServico_interno() {
        return servico_interno;
    }

    public void setServico_interno(String servico_interno) {
        this.servico_interno = servico_interno;
    }

    // inserção no banco
    public void criarCatServico(Connection conn) throws SQLException {
               
        // insere valores
        String SQL = String.format(
                "INSERT INTO cat_servico (id_catalogo, min_duracao, servico_interno) VALUES (%d, %d, '%s')",
                this.getId_catalogo(), this.getMin_duracao(), this.getServico_interno()
        );
        DataBaseUtils.insert(conn, SQL, "id_cat_servico");
    }
}
