
package petcita.pedido;

import java.sql.Connection;
import java.sql.SQLException;
import petcita.DataBaseUtils;

public class Pedido {
    private int IdPedido;
    private int IdFuncionario;
    private int IdCliente;
    private boolean Finalizado;


    public Pedido( int IdFuncionario, int IdCliente, boolean Finalizado) {
        this.IdFuncionario = IdFuncionario;
        this.IdCliente = IdCliente;
        this.Finalizado = Finalizado;
    }

    public Pedido()
    {}

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int IdPedido) {
        this.IdPedido = IdPedido;
    }

    public int getIdFuncionario() {
        return IdFuncionario;
    }

    public void setIdFuncionario(int IdFuncionario) {
        this.IdFuncionario = IdFuncionario;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public boolean isFinalizado() {
        return Finalizado;
    }

    public void setFinalizado(boolean Finalizado) {
        this.Finalizado = Finalizado;
    }

    public void criarPedido(Connection conn) throws SQLException {
        String SQL = String.format(
            "INSERT INTO Pedido (id_funcionario, id_cliente, finalizado) VALUES (%d, %d, %b)", 
            this.getIdFuncionario(), this.getIdCliente(), this.isFinalizado()
        );

        this.setIdPedido(DataBaseUtils.insertRetornaId(conn, SQL));
    }
    
}