
package petcita.catalogo;

import java.sql.SQLException;
import petcita.DataBaseUtils;
import java.sql.Connection;

public class Catalogo {
    private int  IdCatalogo;
    private Boolean Disponivel;
    private Double Valor;
    private String Descricao;
    private String Categoria;
    private String Nome;
    
    public Catalogo ()
    {}

    public Catalogo (String nome, Boolean disponivel, Double valor, String descricao, String categoria) 
    {
        this.Nome = nome;
        this.Disponivel = disponivel;
        this.Valor = valor;
        this.Descricao = descricao;
        this.Categoria = categoria;
    }
    
    public int getIdCatalogo() {
        return IdCatalogo;
    }

    public void setIdCatalogo(int idCatalogo) {
        this.IdCatalogo = idCatalogo;
    }

    public Boolean getDisponivel() {
        return Disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.Disponivel = disponivel;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double valor) {
        if(valor < 0)
        {
            throw new IllegalArgumentException("O valor não pode ser negativo");
        }
        this.Valor = valor;
    }
    
    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        if(nome.equals("") || nome.isEmpty())
        {
            throw new IllegalArgumentException("O nome não pode ser vazio");
        }
        this.Nome = nome;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        if(descricao.equals("") || descricao.isEmpty())
        {
            throw new IllegalArgumentException("A descrição não pode ser vazia");
        }
        this.Descricao = descricao;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        if(categoria.equals("") || categoria.isEmpty())
        {
            throw new IllegalArgumentException("A categoria não pode ser vazia");
        }
        this.Categoria = categoria;
    }

    public void criarCatalogo(Connection conn) throws SQLException
    {		
        String SQL = String.format("INSERT INTO catalogo (disponivel, valor, descricao, categoria) VALUES (%d,%s,%s,%s)", this.getDisponivel(), this.getValor(), this.getDescricao(), this.getCategoria());

        this.setIdCatalogo(DataBaseUtils.insertRetornaId(conn, SQL));
    }

    public void alterarItemCatalogo(Connection conn) throws SQLException
    {
        String SQL = String.format("UPDATE catalogo SET disponivel = %b, valor = %f, descricao = '%s', categoria = '%s' WHERE id_catalogo = %d", this.getDisponivel(), this.getValor(), this.getDescricao(), this.getCategoria(), this.getIdCatalogo());
        
        DataBaseUtils.insertRetornaId(conn, SQL);
    }

    public void atualizarDisponibilidade(Connection conn, boolean disponivel) throws SQLException {
        String SQL = String.format("UPDATE catalogo SET disponivel = %b WHERE id_catalogo = %d", disponivel, this.getIdCatalogo());
        DataBaseUtils.insertRetornaId(conn, SQL);
    }
    
}



