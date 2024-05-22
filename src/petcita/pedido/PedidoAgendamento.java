
package petcita.pedido;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import petcita.DataBaseUtils;

public class PedidoAgendamento {
	private int     IdUsuario;
	private int 	IdPedidoAgendamento;
	private int 	IdCatServico;
	private int 	IdAnimal;
	private Date 	DataAgendamento;

	public PedidoAgendamento(int idUsuario, int idCatServico, int idAnimal, Date dataAgendamento) {
		IdCatServico = idCatServico;
		IdAnimal = idAnimal;
                DataAgendamento = dataAgendamento;
	}

	public PedidoAgendamento()
	{}
        
        public int getIdUsuario()
        {
            return IdUsuario;
        }
        
        public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}
	
	public int getIdPedidoAgendamento() {
		return IdPedidoAgendamento;
	}

	public void setIdPedidoAgendamento(int idPedidoAgendamento) {
		IdPedidoAgendamento = idPedidoAgendamento;
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

	public void criarPedidoAgendamento(Connection conn) throws SQLException
	{            
	    String SQL = String.format("INSERT INTO pedido_agendamento (id_usuario, id_cat_servico, id_animal, data_agendamento) "
	    		+ "VALUES ( %d, %d, %d,%tD)" , this.getIdUsuario(), this.getIdCatServico(), this.getIdAnimal(), this.getDataAgendamento());
	    
	    this.setIdPedidoAgendamento(DataBaseUtils.insertRetornaId(conn, SQL));

	}
	
}
