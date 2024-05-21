
package petcita;

import java.sql.Connection;
import java.sql.SQLException;

public class Cliente extends Usuario {
    int IdCliente;
    String Cep;
    int NumeroResidencia;
    
    public Cliente(String cep, int numeroResidencia, String nome, String telefone, String senha, String login, String email) {
        super(nome, telefone, senha, login, email);
        this.Cep = cep;
        this.NumeroResidencia = numeroResidencia;
    }
    
    public Cliente()
    {}

    public int getIdCliente() {
        return IdCliente;
    }
    
    public void setIdCliente(int idCliente) {
        this.IdCliente = idCliente;
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
    
    public void criarCliente(Connection conn) throws SQLException
    {
        criarUsuario(conn);
        
        String SQL = String.format("INSERT INTO cliente (id_usuario, cep, numero_residencia) VALUES (%d,'%s',%d)", this.getIdUsuario(), this.getCep(), this.getNumeroResidencia());
        
        this.setIdCliente(DataBaseUtils.insertRetornaId(conn, SQL));
    }
    
}
