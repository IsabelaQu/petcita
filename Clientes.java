package petcita;

public class Clientes extends usuario {
    private int id_cliente;
    private String cep;
    private int numero_residencia;

    // Método Construtor:
    public Clientes(int id_usuario, String nome, String telefone, String senha, String login, String email, int id_cliente, String cep, int numero_residencia) {
        super(id_usuario, nome, telefone, senha, login, email);    
        this.id_cliente = id_cliente;
        this.cep = cep;
        this.numero_residencia = numero_residencia;
    }
    
    // Getters e Setters:
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) throws Exception {
        if(cep.equals("") || cep.isEmpty()) {
            throw new Exception("O campo CEP não pode ser vazio");
        }
        this.cep = cep;
    }

    public int getNumeroResidencia() {
        return numero_residencia;
    }

    public void setNumeroResidencia(int numeroResidencia) throws Exception {
        if (numeroResidencia <= 0) {
            throw new Exception("O número da residência não pode ser negativo");
        }
        this.numero_residencia = numeroResidencia;
    }
}   
