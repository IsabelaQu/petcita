package petcita.pedido;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import petcita.DataBaseUtils;

public class PedidoItem{
	private int     IdUsuario;
	private int	IdPedidoItem;
	private int 	IdCatProduto;
	private int	Quantidade;
	private LocalDate	DataPedido;
	
	public PedidoItem(int idUsuario, boolean finalizado, int idPedidoItem, int idCatProduto, int quantidade, LocalDate dataPedido) {
		IdUsuario = idUsuario;
                IdPedidoItem = idPedidoItem;
		IdCatProduto = idCatProduto;
		Quantidade = quantidade;
		DataPedido = dataPedido;
	}

	public PedidoItem()
	{}
        
	public int getIdUsuario()
	{
		return IdUsuario;
	}
	
	public void setIdUsuario(int idUsuario) {
	IdUsuario = idUsuario;
	}
            
	public int getIdPedidoItem() {
		return IdPedidoItem;
	}

	public void setIdPedidoItem(int idPedidoItens) {
		IdPedidoItem = idPedidoItens;
	}

	public int getIdCatProduto() {
		return IdCatProduto;
	}

	public void setIdCatProduto(int idCatProduto) {
		IdCatProduto = idCatProduto;
	}

	public int getQuantidade() {
		return Quantidade;
	}

	public void setQuantidade(int quantidade) throws Exception {
		Quantidade = quantidade;
		if(Quantidade <= 0);
		throw new Exception("Quantidade não pode ser vazia.");
	}
	
	public String getDataPedido() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(this.DataPedido);
	}

	public void setDataPedido(LocalDate dataPedido) {
		DataPedido = dataPedido;
	}

	public void criarPedidoItens(Connection conn) throws SQLException
	{
		String SQL = String.format("INSERT INTO pedido_item (id_usuario, id_cat_produto, quantidade, data_pedido) "
						+ "VALUES ( %d, %d, %d, %tD)" , this.getIdUsuario(), this.getIdCatProduto(), this.getQuantidade(), this.getDataPedido());

		this.setIdPedidoItem(DataBaseUtils.insertRetornaId(conn, SQL));
	}

	public void deletarPedidoItem(Connection conn)
	{
		String SQL = String.format("DELETE FROM pedido_item WHERE id_pedido_item = %d", this.getIdPedidoItem());

		try {
			DataBaseUtils.delete(conn, SQL);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar pedido item", e);
		}		

	}

	public String listarPedidoItens(Connection conn) throws SQLException {
		StringBuilder table = new StringBuilder();
		
		String SQL = String.format(" SELECT "+ 
						" pedido_item.id_pedido_item, "+ 
						" catalogo.nome, "+ 
						" catalogo.descricao, "+ 
						" catalogo.categoria, "+ 
						" catalogo.valor, "+ 
						" pedido_item.quantidade, "+ 
						" cat_produto.dt_validade, "+ 
						" cat_produto.fornecedor, "+ 
						" pedido_item.data_pedido "+ 
					" FROM pedido_item "+ 
						" INNER JOIN cat_produto "+ 
							" ON pedido_item.id_cat_produto = cat_produto.id_cat_produto "+ 
						" INNER JOIN catalogo "+  
							" ON cat_produto.id_catalogo = catalogo.id_catalogo"+
						" WHERE pedido_item.id_usuario = %d", IdUsuario);

		try (ResultSet rs = DataBaseUtils.select(conn, SQL)) {
			if(!rs.next())
				return ("Nenhum pedido encontrado.");

			table.append("+----------------+----------------+----------------+----------------+----------------+----------------+----------------+----------------+----------------+\n");
			table.append("|       ID       |     Nome       |   Descrição    |    Categoria   |    Valor Un.   |  quantidade    |  Dt. Validade  |   Fornecedor   |   Dt. Pedido   |\n");
			table.append("+----------------+----------------+----------------+----------------+----------------+----------------+----------------+----------------+----------------+\n");

			while (rs.next()) {
				int idPedidoItem = rs.getInt("id_pedido_item");
				int quantidade = rs.getInt("quantidade");
				LocalDate dataPedido = rs.getDate("data_pedido").toLocalDate(); 
				String nome = rs.getString("nome");
				String descricao = rs.getString("descricao");
				String categoria = rs.getString("categoria");
				double valorUnidade = rs.getDouble("valor");
				LocalDate dataValidade = rs.getDate("dt_validade").toLocalDate(); 
				String fornecedor = rs.getString("fornecedor");

				table.append(String.format("|%15d|%15s|%15s|%15s|%15.2f|%15d|%15tD|%15s|%15tD|\n", idPedidoItem, nome, descricao, categoria, valorUnidade, quantidade, dataValidade, fornecedor, dataPedido));
			}

			table.append("+----------------+----------------+----------------+----------------+----------------+----------------+----------------+----------------+\n");
		}

		return table.toString();
	}
}
	
	
	
	
