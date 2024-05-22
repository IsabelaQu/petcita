package petcita.pedido;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import petcita.DataBaseUtils;

public class PedidoItem extends Pedido{
	
	private int	IdPedidoItem;
	private int 	IdCatProduto;
	private int	Quantidade;
	private Date	DataPedido;
	
	public PedidoItem(int idFuncionario, int idCliente, boolean finalizado, int idPedidoItem, int idCatProduto, int quantidade, Date dataPedido) {
		super(idFuncionario, idCliente, finalizado);
		IdPedidoItem = idPedidoItem;
		IdCatProduto = idCatProduto;
		Quantidade = quantidade;
		DataPedido = dataPedido;
	}

	public PedidoItem()
	{}
	
	public int getIdPedidoItem() {
		return IdPedidoItem;
	}

	public void setIdPedidoItens(int idPedidoItens) {
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
	
	public Date getDataPedido() {
		return DataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		DataPedido = dataPedido;
	}

	public void criarPedidoItens(Connection conn) throws SQLException
	{
            criarPedido(conn);

            String SQL = String.format("INSERT INTO pedido_item (id_pedido, id_funcionario, id_cliente, finalizado, id_cat_produto, id_pedido, quantidade, data_pedido) "
                            + "VALUES ( %d, %d, %d, %b, %d, %d, %tD)" , this.getIdPedido(), this.getIdFuncionario(), this.getIdCliente(), this.isFinalizado(), this.getIdCatProduto(), this.getQuantidade(), this.getDataPedido());

            this.setIdPedidoItens(DataBaseUtils.insertRetornaId(conn, SQL));
	}
}
	
	
	
	
