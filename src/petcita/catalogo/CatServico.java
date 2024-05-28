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
    public CatServico(String nome, Boolean disponivel, Double valor, String descricao, String categoria, int minDuracao, boolean servicoInterno) {
        super(nome, disponivel, valor, descricao, categoria);
        this.MinDuracao = minDuracao;
        this.ServicoInterno = servicoInterno;
    }
    
    public CatServico()
    {}
   
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

    @Override
    public void criarCatalogo(Connection conn) throws SQLException {
        // Criação do catálogo
        super.criarCatalogo(conn);

        // Geração do insert para a tabela cat_servico
        String SQL = String.format("INSERT INTO cat_servico (id_catalogo, min_duracao, servico_interno) VALUES (%d, %d, %b)",
                this.getIdCatalogo(), this.getMinDuracao(), this.getServicoInterno());

        this.setIdCatServico(DataBaseUtils.insertRetornaId(conn, SQL));
    }

    @Override
    public void alterarItemCatalogo(Connection conn) throws SQLException {
        // Atualiza��oo do servi�o na tabela cat_servico
        String SQL = String.format("UPDATE cat_servico SET min_duracao = %d, servico_interno = %b WHERE id_cat_servico = %d",
                this.getMinDuracao(), this.getServicoInterno(), this.getIdCatServico());

        DataBaseUtils.update(conn, SQL);

        // Chamada ao m�todo de altera��o da classe super
        super.alterarItemCatalogo(conn);
    }

    public CatServico buscarPorId(Connection conn, int id) throws SQLException {
        String SQL = String.format("SELECT catalogo.id_catalogo, catalogo.disponivel, catalogo.valor, catalogo.descricao, catalogo.categoria,"
                + "    cat_servico.id_cat_servico, cat_servico.min_duracao, cat_servico.servico_interno"
                + " FROM catalogo "
                + "     INNER JOIN cat_servico "
                + "         ON cat_servico.id_catalogo = catalogo.id_catalogo"
                + " WHERE cat_servico.id_cat_servico = %d", id);

        ResultSet resposta = DataBaseUtils.select(conn, SQL);

        if (resposta.next()) {
            try {
                    CatServico catServico = new CatServico();
                    catServico.setIdCatalogo(resposta.getInt("id_catalogo"));
                    catServico.setDisponivel(resposta.getBoolean("disponivel"));
                    catServico.setValor(resposta.getDouble("valor"));
                    catServico.setDescricao(resposta.getString("descricao"));
                    catServico.setCategoria(resposta.getString("categoria"));
                    catServico.setIdCatServico(resposta.getInt("id_cat_servico"));
                    catServico.setMinDuracao(resposta.getInt("min_duracao"));
                    catServico.setServicoInterno(resposta.getBoolean("servico_interno"));
                    return catServico;
                } catch (Exception ex) {
                    throw new SQLException("Não foi possível preencher o produto");
            }
        } else {
                throw new SQLException("Produto não encontrado");
        }
    }
    
    public String exibirCatalogo(Connection conn) throws SQLException {
        StringBuilder table = new StringBuilder();

        String sql = "SELECT catalogo.id_catalogo, catalogo.disponivel, catalogo.valor, catalogo.descricao, catalogo.categoria,"
                + "    cat_servico.id_cat_servico, cat_servico.min_duracao, cat_servico.servico_interno"
                + " FROM catalogo "
                + "     INNER JOIN cat_servico "
                + "         ON cat_servico.id_catalogo = catalogo.id_catalogo";

        ResultSet resposta = DataBaseUtils.select(conn, sql);

        table.append("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n");
        table.append("|   ID-Servico    |    Descricao    |    Categoria    |      Valor      |    Disponivel   |     Duracao     | Servico Interno |\n");
        table.append("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n");

        while (resposta.next()) {
            this.setIdCatalogo(resposta.getInt("id_catalogo"));
            this.setDisponivel(resposta.getBoolean("disponivel"));
            this.setValor(resposta.getDouble("valor"));
            this.setDescricao(resposta.getString("descricao"));
            this.setCategoria(resposta.getString("categoria"));
            this.setIdCatServico(resposta.getInt("id_cat_servico"));
            this.setMinDuracao(resposta.getInt("min_duracao"));
            this.setServicoInterno(resposta.getBoolean("servico_interno"));

            table.append(String.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
                    this.getIdCatServico(), this.getDescricao(), this.getCategoria(), this.getValor(),
                    this.getDisponivel(), this.getMinDuracao(), this.getServicoInterno()));
        }

        table.append("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n");

        return table.toString();
    }

    public String exibirItemCatalogo() {
        StringBuilder table = new StringBuilder();

        table.append("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n");
        table.append("|   ID-Servico    |    Descricao    |    Categoria    |      Valor      |    Disponivel   |     Duracao     | Servico Interno |\n");
        table.append("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n");
        table.append(String.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
                this.getIdCatServico(), this.getDescricao(), this.getCategoria(), this.getValor(),
                this.getDisponivel(), this.getMinDuracao(), this.getServicoInterno()));
        table.append("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n");

        return table.toString();
    }


}
