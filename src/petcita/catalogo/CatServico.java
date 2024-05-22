package petcita.catalogo;

import java.sql.SQLException;
import petcita.DataBaseUtils;
import java.sql.Connection;

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
}
