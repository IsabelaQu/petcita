package petcita.catalogo;

import java.sql.SQLException;
import petcita.DataBaseUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CatProduto extends Catalogo{
    private int IdCatProduto;
    private String Fornecedor;
    private Date DtValidade;
    private Date DtRegistro;
    
    public CatProduto()
    {}

    public CatProduto(String nome, Boolean disponivel, Double valor, String descricao, String categoria, int idCatProduto, String fornecedor, Date dtValidade, Date dtRegistro) {
            super(nome, disponivel, valor, descricao, categoria);
            this.IdCatProduto = idCatProduto;
            this.Fornecedor = fornecedor;
            this.DtValidade = dtValidade;
            this.DtRegistro = dtRegistro;
    } 

    // Getters e Setters
    public int getIdCatProduto() {
            return IdCatProduto;
    }

    public void setIdCatProduto(int idCatProduto) {
            this.IdCatProduto = idCatProduto;
    }

    public String getFornecedor() {
            return Fornecedor;
    }

    public void setFornecedor(String fornecedor) throws Exception {
            if(fornecedor.equals("") || fornecedor.isEmpty()) {
                    throw new Exception("O campo fornecedor não pode ser vazio");
            }
            this.Fornecedor = fornecedor;
    }

    public Date getDtValidade() {
            return DtValidade;
    }

    public void setDtValidade(String dtValidade) throws Exception {
            if(dtValidade == null) {
                    throw new Exception("O campo de data de validade não pode ser vazio");
            }
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date dataConvertida = formatter.parse(dtValidade);
            this.DtValidade = dataConvertida;
    }

    public Date getDtRegistro() {
            return DtRegistro;
    }

    public void setDtRegistro(Date dtRegistro) throws Exception {
            if(dtRegistro == null) {
                    throw new Exception("O campo de data de registro não pode ser vazio");
            }
            this.DtRegistro = dtRegistro;
    }

    @Override
    public void criarCatalogo(Connection conn) throws SQLException
    {
        // Criação do catálogo
        super.criarCatalogo(conn);

        // Geração do insert para a tabela cat_servico
        String SQL = String.format("INSERT INTO cat_produto (id_catalogo, fornecedor, dt_validade, dt_registro) VALUES (%d,%s,%tD,%f)", 
                this.getIdCatalogo(), this.getFornecedor(), this.getDtValidade(), this.getDtRegistro());

        this.setIdCatProduto(DataBaseUtils.insertRetornaId(conn, SQL));
    }

    public CatProduto buscarPorId(Connection conn, int id) throws SQLException {
        String SQL = String.format("SELECT catalogo.id_catalogo, catalogo.nome, catalogo.disponivel, catalogo.valor, catalogo.descricao, catalogo.categoria,"
                        + "    cat_produto.id_cat_produto, cat_produto.fornecedor, cat_produto.dt_validade, cat_produto.dt_registro"
                        + " FROM catalogo "
                        + "     INNER JOIN cat_produto "
                        + "         ON cat_produto.id_catalogo = catalogo.id_catalogo"
                        + " WHERE cat_produto.id_cat_produto = %d", id);

        ResultSet resposta = DataBaseUtils.select(conn, SQL);

        if (resposta.next()) {
                try {
                        CatProduto produto = new CatProduto();
                        produto.setIdCatalogo(resposta.getInt("id_catalogo"));
                        produto.setNome(resposta.getString("nome"));
                        produto.setDisponivel(resposta.getBoolean("disponivel"));
                        produto.setValor(resposta.getDouble("valor"));
                        produto.setDescricao(resposta.getString("descricao"));
                        produto.setCategoria(resposta.getString("categoria"));
                        produto.setIdCatProduto(resposta.getInt("id_cat_produto"));
                        produto.setFornecedor(resposta.getString("fornecedor"));
                        produto.setDtValidade(resposta.getString("dt_validade"));
                        produto.setDtRegistro(resposta.getDate("dt_registro"));
                        return produto;
                } catch (Exception ex) {
                        throw new SQLException("Não foi possível preencher o produto");
                }
        } else {
                throw new SQLException("Produto não encontrado");
        }
    }

    @Override
    public void alterarItemCatalogo(Connection conn) throws SQLException {
        // Atualiza��oo do servi�o na tabela cat_servico
        String SQL = String.format("UPDATE cat_produto SET fornecedor = %s, dt_validade = %tD, dt_registro = %tD WHERE id_cat_produto = %d",
                        this.getFornecedor(), this.getDtValidade(), this.getDtRegistro(), this.getIdCatProduto());

        DataBaseUtils.update(conn, SQL);

        // Chamada ao m�todo de altera��o da classe super
        super.alterarItemCatalogo(conn);
    }

    public String exibirCatalogo(Connection conn) throws SQLException {
        StringBuilder table = new StringBuilder();

        String sql = "SELECT catalogo.id_catalogo, catalogo.disponivel, catalogo.valor, catalogo.descricao, catalogo.categoria,"
                        + "    cat_produto.id_cat_produto, cat_produto.fornecedor, cat_produto.dt_validade, cat_produto.dt_registro"
                        + " FROM catalogo "
                        + "     INNER JOIN cat_produto "
                        + "         ON cat_produto.id_catalogo = catalogo.id_catalogo";

        ResultSet resposta = DataBaseUtils.select(conn, sql);

        table.append("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+------------------+------------------+\n");
        table.append("|    ID-Produto   |    Descricao    |    Categoria    |      Valor      |    Disponivel   |    Fornecedor   | Data de validade | Data de Registro | \n");
        table.append("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+------------------+------------------+\n");

        while (resposta.next()) {
                try {
                        this.setIdCatalogo(resposta.getInt("id_catalogo"));
                        this.setDisponivel(resposta.getBoolean("disponivel"));
                        this.setValor(resposta.getDouble("valor"));
                        this.setDescricao(resposta.getString("descricao"));
                        this.setCategoria(resposta.getString("categoria"));
                        this.setIdCatProduto(resposta.getInt("id_cat_produto"));
                        this.setFornecedor(resposta.getString("fornecedor"));
                        this.setDtValidade(resposta.getString("dt_validade"));
                        this.setDtRegistro(resposta.getDate("dt_registro"));

                        table.append(String.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
                                        this.getIdCatProduto(), this.getDescricao(), this.getCategoria(), this.getValor(),
                                        this.getDisponivel(), this.getFornecedor(), this.getDtValidade(), this.getDtRegistro()));
                } catch (Exception ex) {
                        throw new SQLException("Não foi possível preencher o produto para exibição");
                }
        }

        table.append("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n");

        return table.toString();
    }
        public String exibirItemCatalogo() {
                StringBuilder table = new StringBuilder();

                table.append("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+------------------+------------------+\n");
                table.append("|    ID-Produto   |    Descricao    |    Categoria    |      Valor      |    Disponivel   |    Fornecedor   | Data de validade | Data de Registro | \n");
                table.append("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+------------------+------------------+\n");
                table.append(String.format("| %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s | %-15s |\n",
                                this.getIdCatProduto(), this.getDescricao(), this.getCategoria(), this.getValor(),
                                this.getDisponivel(), this.getFornecedor(), this.getDtValidade(), this.getDtRegistro()));
                table.append("+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+-----------------+\n");

                return table.toString();
        }
}
