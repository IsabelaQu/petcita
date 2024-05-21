
package petcita;

import java.sql.Connection;
import java.sql.SQLException;

public class Funcionario extends Usuario {
    int IdFuncionario;
    String Cargo;
    Double Salario;

    public int getIdFuncionario() {
        return IdFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.IdFuncionario = idFuncionario;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        this.Cargo = cargo;
    }

    public Double getSalario() {
        return Salario;
    }

    public void setSalario(Double salario) {
        this.Salario = salario;
    }

    public Funcionario(int id_funcionario, String cargo, Double salario, String nome, String telefone, String senha, String login, String email) {
        super( nome, telefone, senha, login, email);
        this.IdFuncionario = id_funcionario;
        this.Cargo = cargo;
        this.Salario = salario;
    }
    
    public void criarFuncionario(Connection conn) throws SQLException
    {
        criarUsuario(conn);
        
        String SQL = String.format("INSERT INTO funcionario (id_usuario, cargo, salario) VALUES (%d,%s,%f)", this.getIdUsuario(), this.getCargo(), this.getSalario());
        
        this.setIdFuncionario(DataBaseUtils.insert(conn, SQL, "id_funcionario"));
    }
    
    
    
}
