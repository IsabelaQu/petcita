
package petcita; //definir o nome da main para importar os packages

public class usuario {
    private int id_usuario;
    private String nome;
    private String telefone;
    private String senha;
    private String login;
    private String email;

    public usuario (int id_usuario,String nome, String telefone, String senha, String login, String email) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.telefone = telefone;
        this.senha = senha;
        this.login = login;
        this.email = email;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception {
        if(nome.equals("") || nome.isEmpty()) {
            throw new Exception("O campo nome não pode ser vazio");
        }
        
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
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
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) throws Exception {
        if (login.equals("") || login.isEmpty()) {
            throw new Exception("O campo login não pode ser vazio");
           
        } else if (login.length() < 6 || login.length() > 15) {
            throw new Exception("O login deve ter entre 6 e 15 caracteres");
        }
        
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        if (email.equals("") || email.isEmpty()) {
            throw new Exception("O campo email não pode ser vazio");
        }
        
        this.email = email;
    }
}