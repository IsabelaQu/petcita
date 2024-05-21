
package petcita;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class PedidoAgendamento extends Pedido {
	
	private int 	IdPedidoAgendamentos;
	private int 	IdCatServico;
	private int 	IdAnimal;
	private Date 	DataAgendamento;
	
	
	public int getIdPedidoAgendamentos() {
		return IdPedidoAgendamentos;
	}





	public void setIdPedidoAgendamentos(int idPedidoAgendamentos) {
		IdPedidoAgendamentos = idPedidoAgendamentos;
	}





	public int getIdCatServico() {
		return IdCatServico;
	}





	public void setIdCatServico(int idCatServico) {
		IdCatServico = idCatServico;
	}





	public int getIdAnimal() {
		return IdAnimal;
	}





	public void setIdAnimal(int idAnimal) {
		IdAnimal = idAnimal;
	}





	public Date getDataAgendamento() {
		return DataAgendamento;
	}





	public void setDataAgendamento(Date dataAgendamento) {
		DataAgendamento = dataAgendamento;
	}



	public PedidoAgendamento(int id_pedido, int id_funcionario, int id_cliente, boolean finalizado,
			int idPedidoAgendamentos, int idCatServico, int idAnimal, Date dataAgendamento) {
		super(id_pedido, id_funcionario, id_cliente, finalizado);
		IdPedidoAgendamentos = idPedidoAgendamentos;
		IdCatServico = idCatServico;
		IdAnimal = idAnimal;
	}
	
	public void criarPedidoAgendamento(Connection conn) throws SQLException
	{
	    criarPedido(conn);
	    
	    String SQL = String.format("INSERT INTO PedidoItens idPedido, idFuncionario, idCliente, finalizado, idPeidoAgendado, idCatServico, idAnimal, dataAgendamento) "
	    		+ "VALUES ( %i, %i, %i, %b, %i, %i, %i, %?)" , this.getIdPedido(), this.getIdFuncionario(), this.getIdCliente(), this.isFinalizado(), this.getIdPedidoAgendamentos(), this.getIdCatServico(), this.getIdAnimal(), this.getDataAgendamento());
	    
	    this.setIdPedido(DataBaseUtils.insert(conn, SQL, "idPedido"));


		}
	
}
