package petcita;

public class Funcionario extends usuario {
    private int idFuncionario;
    private String cargo;
    private double salario;
    
// Método Construtor:
    public Funcionario(int idUsuario, String nome, String telefone, String senha, String login, String email, int idFuncionario, String cargo, double salario) {
        super(idUsuario, nome, telefone, senha, login, email);
        this.idFuncionario = idFuncionario;
        this.cargo = cargo;
        this.salario = salario;
    }

// Getters e Setters:
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) throws Exception {
        if(cargo.equals("") || cargo.isEmpty()) {
            throw new Exception("O campo cargo não pode ser vazio");
        }
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) throws Exception {
        if (salario == null) {
            throw new Exception("O campo salário não pode ser vazio");
        }
        this.salario = salario;
    }
}
