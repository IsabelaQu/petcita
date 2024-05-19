
package petcita;
//base de catalogo feita, podemos pensar em algo para polimorfismo 

public class catalogo {
    private int  id_catalogo;
    private Boolean disponivel;
    private Double valor;
    private String descricao;
    private String categoria;

    public catalogo (int id_catalogo, Boolean disponivel, Double valor, String descricao, String categoria) {
        this.id_catalogo = id_catalogo;
        this.disponivel = disponivel;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
}

    public int getId_catalogo() {
        return id_catalogo;
    }

    public void setId_catalogo(int id_catalogo) {
        this.id_catalogo = id_catalogo;
    }

    // ajustar o boolean conforme a necessidade e chamar na main a função
    public String getDisponivel() {
        if (disponivel) {
            return "Produto disponível";
        } else {
            return "Produto indisponível";
        }
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
    
}



