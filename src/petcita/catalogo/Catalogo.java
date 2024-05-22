
package petcita.catalogo;
//base de catalogo feita, podemos pensar em algo para polimorfismo 

import java.time.LocalDate;
import java.sql.SQLException;
import java.sql.Statement;
import petcita.DataBaseUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private int  idCatalogo;
    private Boolean disponivel;
    private Double valor;
    private String descricao;
    private String categoria;

    public Catalogo ( Boolean disponivel, Double valor, String descricao, String categoria) 
    {
        this.disponivel = disponivel;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
    }
    
    //TODO: ADICIONAR EXCEPTIONS DE PREENCHIMENTO + COLOCAR TRY CATCH NO METODO DE EXIBIR

    public int getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(int id_catalogo) {
        this.idCatalogo = idCatalogo;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void criarCatalogo(Connection conn) throws SQLException
    {		
        String SQL = String.format("INSERT INTO catalogo (disponivel, valor, descricao, categoria) VALUES (%d,%s,%s,%s)", this.getDisponivel(), this.getValor(), this.getDescricao(), this.getCategoria());

        this.setIdCatalogo(DataBaseUtils.insertRetornaId(conn, SQL));
    }
    
    public List<String> exibirCatalogo(Connection conn) throws SQLException
    {
        List<String> linhas = new ArrayList<>();
        
        String Sql = String.format("SELECT id_catalogo, disponivel, valor, descricao, categoria"
                                        + " FROM catalogo");

        ResultSet resposta = DataBaseUtils.select(conn, Sql);
       
        while(resposta.next()){
            this.setIdCatalogo(resposta.getInt("id_catalogo"));
            this.setDisponivel(resposta.getBoolean("disponivel"));
            this.setValor(resposta.getDouble("valor"));
            this.setDescricao(resposta.getString("descricao"));
            this.setCategoria(resposta.getString("categoria"));

            linhas.add("ID: " + this.getIdCatalogo());
            linhas.add(", Descricao: " + this.getDescricao());
            linhas.add(", Categoria: " + this.getCategoria());
            linhas.add(", Valor: " + this.getValor());
            linhas.add(", Disponivel: " + this.getDisponivel());
            linhas.add("\n-------------------------\n");
        }
        
        return linhas;
    
    }
    
}



