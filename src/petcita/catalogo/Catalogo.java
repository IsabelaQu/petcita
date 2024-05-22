
package petcita.catalogo;
//base de catalogo feita, podemos pensar em algo para polimorfismo 

import java.time.LocalDate;
import java.sql.SQLException;
import java.sql.Statement;
import petcita.DataBaseUtils;
import java.sql.Connection;

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
    
}



