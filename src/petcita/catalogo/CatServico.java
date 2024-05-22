package petcita.catalogo;

import java.sql.SQLException;
import petcita.DataBaseUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// classe
public class CatServico extends Catalogo {

    //atributos
    private int MinDuracao;
    private boolean ServicoInterno;
    private int IdCatServico;

    //construtor
    public CatServico(Boolean disponivel, Double valor, String descricao, String categoria, int minDuracao, boolean servicoInterno) {
        super(disponivel, valor, descricao, categoria);
        this.MinDuracao = minDuracao;
        this.ServicoInterno = servicoInterno;
    }
    
    //TODO: ADICIONAR EXCEPTIONS DE PREENCHIMENTO + COLOCAR TRY CATCH NO METODO DE EXIBIR

    // getters & setters
    public int getIdCatServico() {
        return IdCatServico;
    }

    public void setIdCatServico(int idCatServico) {
        this.IdCatServico = idCatServico;
    }

    // getters & setters
    public int getMinDuracao() {
        return MinDuracao;
    }

    public void setMinDuracao(int minDuracao) {
        this.MinDuracao = minDuracao;
    }

    public boolean getServicoInterno() {
        return ServicoInterno;
    }

    public void setServicoInterno(boolean servico_interno) {
        this.ServicoInterno = servico_interno;
    }

    // inserção no banco
    public void criarCatServico(Connection conn) throws SQLException {
        criarCatalogo(conn);
        
        // insere valores
        String SQL = String.format("INSERT INTO cat_servico (id_catalogo, min_duracao, servico_interno) VALUES (%d, %d, '%s')", this.getIdCatalogo(), this.getMinDuracao(), this.getServicoInterno());

        this.setIdCatServico(DataBaseUtils.insertRetornaId(conn, SQL));
    }
    
    @Override
    public List<String> exibirCatalogo(Connection conn) throws SQLException
    {
        List<String> linhas = new ArrayList<>();
        
        String Sql = String.format("SELECT catalogo.id_catalogo, catalogo.disponivel, catalogo.valor, catalogo.descricao, catalogo.categoria,"
                                    + "    cat_servico.id_cat_servico, cat_servico.min_duracao, cat_servico.servico_interno"
                                    + " FROM catalogo "
                                    + "     INNER JOIN cat_servico "
                                    + "         ON cat_servico.id_catalogo = catalogo.id_catalogo");

        ResultSet resposta = DataBaseUtils.select(conn, Sql);
       
        while(resposta.next()){
            this.setIdCatalogo(resposta.getInt("id_catalogo"));
            this.setDisponivel(resposta.getBoolean("disponivel"));
            this.setValor(resposta.getDouble("valor"));
            this.setDescricao(resposta.getString("descricao"));
            this.setCategoria(resposta.getString("categoria"));
            this.setIdCatServico(resposta.getInt("id_cat_servico"));
            this.setMinDuracao(resposta.getInt("min_duracao"));
            this.setServicoInterno(resposta.getBoolean("servico_interno"));

            linhas.add("ID-Servico: " + this.getIdCatServico());
            linhas.add(", Descricao: " + this.getDescricao());
            linhas.add(", Categoria: " + this.getCategoria());
            linhas.add(", Valor: " + this.getValor());
            linhas.add(", Disponivel: " + this.getDisponivel());
            linhas.add(", Duracao: " + this.getMinDuracao());
            linhas.add(", Servico Interno: " + this.getServicoInterno());
            linhas.add("\n-------------------------\n");
        }
        
        return linhas;
    
    }
}
