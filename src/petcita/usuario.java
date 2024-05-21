
package petcita; //definir o nome da main para importar os packages

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Usuario {
    private int IdUsuario;
    private String Nome;
    private String Telefone;
    private String Senha;
    private String Login;
    private String Email;
    
    public Usuario()
    {}

    public Usuario (String nome, String telefone, String senha, String login, String email) {
        this.Nome = nome;
        this.Telefone = telefone;
        this.Senha = senha;
        this.Login = login;
        this.Email = email;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) throws Exception {
        if(nome.equals("") || nome.isEmpty()) {
            throw new Exception("O campo nome não pode ser vazio");
        }
        
        this.Nome = nome;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        this.Telefone = telefone;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) throws Exception { 
        if (senha.equals("") || senha.isEmpty()) {
            throw new Exception("O campo senha não pode ser vazio"); //Se a condição acima for verdadeira, uma exceção é lançada com a mensagem
        
        //if (senha.equals("") || senha.isEmpty()):
        //Esta linha verifica se a senha é uma string vazia ("") ou se está vazia (isEmpty()).
        //A diferença sutil aqui é que equals("") verifica se a senha é exatamente uma string vazia
        //enquanto isEmpty() verifica se a string tem comprimento zero.
        
        } else if (senha.length() < 6 || senha.length() > 15) { //Esta linha verifica se o comprimento da senha é menor que 6 caracteres ou maior que 15 caracteres.
            throw new Exception("A senha deve ter entre 6 e 15 caracteres"); 
    }
        this.Senha = senha;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) throws Exception {
        if (login.equals("") || login.isEmpty()) {
            throw new Exception("O campo login não pode ser vazio");
           
        } else if (login.length() < 6 || login.length() > 15) {
            throw new Exception("O login deve ter entre 6 e 15 caracteres");
        }
        
        this.Login = login;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) throws Exception {
        if (email.equals("") || email.isEmpty()) {
            throw new Exception("O campo email não pode ser vazio");
        }
        
        this.Email = email;
    }
    
    public void criarUsuario(Connection conn) throws SQLException
    {        
        String SQL = String.format("INSERT INTO usuario (nome, telefone, senha, login, email) VALUES ('%s','%s','%s','%s','%s')", this.getNome(), this.getTelefone(), this.getSenha(), this.getLogin(), this.getEmail());
        
        this.setIdUsuario(DataBaseUtils.insertRetornaId(conn, SQL));
    }
}