
package petcita.pedido;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import petcita.DataBaseUtils;
import java.time.format.DateTimeFormatter;

public class PedidoAgendamento {
	private int     IdUsuario;
	private int 	IdPedidoAgendamento;
	private int 	IdCatServico;
	private int 	IdAnimal;
	private LocalDate 	DataAgendamento;

	public PedidoAgendamento(int idUsuario, int idCatServico, int idAnimal, LocalDate dataAgendamento) {
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
	
	public String getDataAgendamento() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return DataAgendamento.format(formatter);
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		DataAgendamento = dataAgendamento;
	}

	public void criarPedidoAgendamento(Connection conn) throws SQLException
	{            
	    String SQL = String.format("INSERT INTO pedido_agendamento (id_usuario, id_cat_servico, id_animal, data_agendamento) "
	    		+ "VALUES ( %d, %d, %d,'%s')" , this.getIdUsuario(), this.getIdCatServico(), this.getIdAnimal(), LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	    
	    this.setIdPedidoAgendamento(DataBaseUtils.insertRetornaId(conn, SQL));

	}

	public void deletarPedidoAgendamento(Connection conn)
	{
		String SQL = String.format("DELETE FROM pedido_agendamento WHERE id_pedido_agendamento = %d", this.getIdPedidoAgendamento());

		try {
			DataBaseUtils.delete(conn, SQL);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar pedido agendamento", e);
		}		

	}

	public String listarPedidoAgendamentos(Connection conn) throws SQLException {
		StringBuilder table = new StringBuilder();
		table.append("+----------------+----------------+----------------+----------------+----------------+-------------------+----------------+----------------+------------------+------------------+\n");
		table.append("|       ID       |     Nome       |   Descri��o    |    Categoria   |      Valor     |  Dt. Agendamento  |   Nome Animal  |     Esp�cie    |   Porte Animal   |   Servi�o Local? |\n");
		table.append("+----------------+----------------+----------------+----------------+----------------+-------------------+----------------+----------------+------------------+------------------+\n");

		String SQL = String.format(" SELECT "+
						 " pedido_agendamento.id_pedido_agendamento, "+ 
						 " catalogo.nome, "+
						 " catalogo.descricao, "+ 
						 " catalogo.categoria, "+
						 " catalogo.valor, "+
						 " pedido_agendamento.data_agendamento, "+
						 " cat_servico.min_duracao, "+
						 " cat_servico.servico_interno, "+
						 " animal.nome AS nome_animal, "+
						 " animal.especie, "+
						 " animal.porte "+
					 " FROM pedido_agendamento "+
						" INNER JOIN cat_servico "+
							" ON pedido_agendamento.id_cat_servico = cat_servico.id_cat_servico "+
						" INNER JOIN catalogo "+
							" ON cat_servico.id_catalogo = catalogo.id_catalogo "+
						" INNER JOIN animal "+
							" ON pedido_agendamento.id_animal = animal.id_animal "+
					 " WHERE pedido_agendamento.id_usuario = %d", IdUsuario);

		try (ResultSet rs =  DataBaseUtils.select(conn, SQL)) {
			while (rs.next()) {
				int idPedidoAgendamento = rs.getInt("id_pedido_agendamento");
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				String categoria = rs.getString("categoria");
				double valorUnidade = rs.getDouble("valor");
				int quantidade = rs.getInt("min_duracao");
				LocalDate dataAgendamento = rs.getDate("data_agendamento").toLocalDate();
				boolean servicoInterno = rs.getBoolean("servico_interno");
				String nomeAnimal = rs.getString("nome_animal");
				String especieAnimal = rs.getString("especie");
				String porteAnimal = rs.getString("porte");

				table.append(String.format("|%15d|%15s|%15s|%15s|%15.2f|%15tD|%15s|%15s|%15b|\n", idPedidoAgendamento, nome, descricao, categoria, valorUnidade, dataAgendamento, nomeAnimal, especieAnimal, porteAnimal, servicoInterno));}
		}

		table.append("+----------------+----------------+----------------+----------------+----------------+-------------------+----------------+----------------+------------------+------------------+\n");
		return table.toString();
	}
	
	
}
