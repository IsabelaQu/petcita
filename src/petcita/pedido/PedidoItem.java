package petcita.pedido;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import petcita.DataBaseUtils;

public class PedidoItem{
	private int     IdUsuario;
	private int	IdPedidoItem;
	private int 	IdCatProduto;
	private int	Quantidade;
	private Date	DataPedido;
	
	public PedidoItem(int idUsuario, boolean finalizado, int idPedidoItem, int idCatProduto, int quantidade, Date dataPedido) {
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

            String SQL = String.format("INSERT INTO pedido_item (id_usuario, id_cat_produto, quantidade, data_pedido) "
                            + "VALUES ( %d, %d, %d, %tD)" , this.getIdUsuario(), this.getIdCatProduto(), this.getQuantidade(), this.getDataPedido());

            this.setIdPedidoItens(DataBaseUtils.insertRetornaId(conn, SQL));
	}
}
	
	
	
	
