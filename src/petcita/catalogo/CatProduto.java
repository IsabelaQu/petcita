package petcita.catalogo;

import java.sql.SQLException;
import petcita.DataBaseUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CatProduto extends Catalogo{
    private int idCatProduto;
    private String fornecedor;
    private Date dtValidade;
    private Date dtRegistro;

    public CatProduto(Boolean disponivel, Double valor, String descricao, String categoria, int idCatProduto, String fornecedor, Date dtValidade, Date dtRegistro) {
            super(disponivel, valor, descricao, categoria);
            this.idCatProduto = idCatProduto;
            this.fornecedor = fornecedor;
            this.dtValidade = dtValidade;
            this.dtRegistro = dtRegistro;
    } 

    // Getters e Setters
    public int getIdCatProduto() {
            return idCatProduto;
    }

    public void setIdCatProduto(int idCatProduto) {
            this.idCatProduto = idCatProduto;
    }

    public String getFornecedor() {
            return fornecedor;
    }

    public void setFornecedor(String fornecedor) throws Exception {
            if(fornecedor.equals("") || fornecedor.isEmpty()) {
                    throw new Exception("O campo fornecedor não pode ser vazio");
            }
            this.fornecedor = fornecedor;
    }

    public Date getDtValidade() {
            return dtValidade;
    }

    public void setDtValidade(Date dtValidade) throws Exception {
            if(dtValidade == null) {
                    throw new Exception("O campo de data de validade não pode ser vazio");
            }
            this.dtValidade = dtValidade;
    }

    public Date getDtRegistro() {
            return dtRegistro;
    }

    public void setDtRegistro(Date dtRegistro) throws Exception {
            if(dtRegistro == null) {
                    throw new Exception("O campo de data de registro não pode ser vazio");
            }
            this.dtRegistro = dtRegistro;
    }

    public void criarCatProduto(Connection conn) throws SQLException
    {
            criarCatalogo(conn);

            String SQL = String.format("INSERT INTO cat_produto (id_catalogo, fornecedor, dt_validade, dt_registro) VALUES (%d,%s,%tD,%f)", this.getIdCatalogo(), this.getFornecedor(), this.getDtValidade(), this.getDtRegistro());

            this.setIdCatProduto(DataBaseUtils.insertRetornaId(conn, SQL));
    }

    @Override
    public List<String> exibirCatalogo(Connection conn) throws SQLException
    {
        List<String> linhas = new ArrayList<>();

        String Sql = String.format("SELECT catalogo.id_catalogo, catalogo.disponivel, catalogo.valor, catalogo.descricao, catalogo.categoria,"
                                    + "    cat_produto.id_cat_produto, cat_produto.fornecedor, cat_produto.dt_validade, cat_produto.dt_registro"
                                    + " FROM catalogo "
                                    + "     INNER JOIN cat_produto "
                                    + "         ON cat_produto.id_catalogo = catalogo.id_catalogo");

        ResultSet resposta = DataBaseUtils.select(conn, Sql);

        while(resposta.next()){
            try {
                this.setIdCatalogo(resposta.getInt("id_catalogo"));
                this.setDisponivel(resposta.getBoolean("disponivel"));
                this.setValor(resposta.getDouble("valor"));
                this.setDescricao(resposta.getString("descricao"));
                this.setCategoria(resposta.getString("categoria"));
                this.setIdCatProduto(resposta.getInt("id_cat_produto"));
                this.setFornecedor(resposta.getString("fornecedor"));
                this.setDtValidade(resposta.getDate("dt_validade"));
                this.setDtRegistro(resposta.getDate("dt_registro"));
                
                
                linhas.add("ID-Produto: " + this.getIdCatProduto());
                linhas.add(", Descricao: " + this.getDescricao());
                linhas.add(", Categoria: " + this.getCategoria());
                linhas.add(", Valor: " + this.getValor());
                linhas.add(", Disponivel: " + this.getDisponivel());
                linhas.add(", Fornecedor: " + this.getFornecedor());
                linhas.add(", Data de validade: " + this.getDtValidade());
                linhas.add(", Data de Registro: " + this.getDtRegistro());
                linhas.add("\n-------------------------\n");
            } catch (Exception ex) {
                throw new SQLException("N�o foi possivel preencher o produto para exibicao");
            }
        }

        return linhas;

    }
	
}
