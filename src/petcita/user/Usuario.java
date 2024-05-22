
package petcita.user; //definir o nome da main para importar os packages

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import petcita.DataBaseUtils;

public class Usuario {
    private int IdUsuario;
    private String Nome;
    private String Telefone;
    private String Senha;
    private String Login;
    private String Email;
    private String Cep;
    private int NumeroResidencia;
    private boolean Funcionario;
    
    public Usuario()
    {}

    public Usuario (String nome, String telefone, String senha, String login, String email, String cep, int numeroResidencia, boolean funcionario) {
        this.Nome = nome;
        this.Telefone = telefone;
        this.Senha = senha;
        this.Login = login;
        this.Email = email;
        this.Cep = cep;
        this.NumeroResidencia = numeroResidencia;
        this.Funcionario = funcionario;
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
    
    public String getCep() {
        return Cep;
    }

    public void setCep(String Cep) {
        this.Cep = Cep;
    }

    public int getNumeroResidencia() {
        return NumeroResidencia;
    }

    public void setNumeroResidencia(int NumeroResidencia) {
        this.NumeroResidencia = NumeroResidencia;
    }
    
    public boolean getFuncionario() {
        return Funcionario;
    }

    public void setFuncionario(boolean funcionario) {
        this.Funcionario = funcionario;
    }
    
    public void criarUsuario(Connection conn) throws SQLException
    {        
        String SQL = String.format("INSERT INTO usuario (nome, telefone, senha, login, email, cep, numero_residencia, funcionario) VALUES ('%s','%s','%s','%s','%s', '%s', '%d', %b)", this.getNome(), this.getTelefone(), this.getSenha(), this.getLogin(), this.getEmail(), this.getCep(), this.getNumeroResidencia(), this.getFuncionario());
        
        this.setIdUsuario(DataBaseUtils.insertRetornaId(conn, SQL));
    }
    
    /**
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public boolean validaLogin(Connection conn) throws SQLException
    {
        String SQL = String.format("SELECT * FROM usuario WHERE login = '%s' AND senha = '%s'", this.getLogin(), this.getSenha());
        
        ResultSet resposta = DataBaseUtils.select(conn, SQL);
        
        if(!resposta.next())
            throw new SQLException("Nenhum usu�rio encontrado, login e/ou senha incorretos");
        
        this.Nome = resposta.getString("nome");
        this.Telefone = resposta.getString("telefone");
        this.Email = resposta.getString("email");
        this.IdUsuario = resposta.getInt("id_usuario");
       
        return true;
    }
}