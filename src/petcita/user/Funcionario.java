package petcita.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import petcita.DataBaseUtils;

public class Funcionario extends Usuario {
    int IdFuncionario;
    String Cargo;
    Double Salario;

    public Funcionario() {
        
    }

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
        
        this.setIdFuncionario(DataBaseUtils.insertRetornaId(conn, SQL));
    }
    
    @Override
    public boolean validaLogin(Connection conn) throws SQLException
    {
        super.validaLogin(conn);
        
        String SQL = String.format("SELECT * FROM funcionario WHERE id_usuario = %d", this.getIdUsuario());
        
        ResultSet resposta = DataBaseUtils.select(conn, SQL);
        
        if(!resposta.next())
            throw new SQLException("Nenhum cliente encontrado, login e/ou senha incorretos");
        
        this.Cargo = resposta.getString("cargo");
        this.Salario = resposta.getDouble("salario");
       
        return true;
    }
    
    
    
}
