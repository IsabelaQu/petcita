package petcita;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * 
 * CREATE TABLE pedido_itens (
	id_pedido_itens int auto_increment NOT NULL,
	id_cat_produto int NOT NULL,
	id_pedido int NOT NULL,
	quantidade int NOT NULL,
	data_pedido date NOT NULL,
	PRIMARY KEY (id_pedido_itens),
    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    FOREIGN KEY (id_cat_produto) REFERENCES cat_produto(id_cat_produto)
);

);

 * 
 */
public class PedidoItens extends Pedido{
	
	private int		IdPedidoItens;
	private int 	IdCatProduto;
	private int		Quantidade;
	private Date	DataPedido;
	
	
	
	
	public int getIdPedidoItens() {
		return IdPedidoItens;
	}




	public void setIdPedidoItens(int idPedidoItens) {
		IdPedidoItens = idPedidoItens;
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



public PedidoItens(int id_pedido, int id_funcionario, int id_cliente, boolean finalizado, int idPedidoItens,
		int idCatProduto, int quantidade, Date dataPedido) {
	super(id_pedido, id_funcionario, id_cliente, finalizado);
	IdPedidoItens = idPedidoItens;
	IdCatProduto = idCatProduto;
	Quantidade = quantidade;
	DataPedido = dataPedido;
}


public void criarPedidoItens(Connection conn) throws SQLException
{
    criarPedido(conn);
    
    String SQL = String.format("INSERT INTO PedidoItens idPedido, idFuncionario, idCliente, finalizado, idPeidoItens, idCatProdutos, idPedido, quantidade, dataPedido) "
    		+ "VALUES ( %i, %i, %i, %b, %i, %i, %i, %?)" , this.getIdPedido(), this.getIdFuncionario(), this.getIdCliente(), this.isFinalizado(), this.getIdPedidoItens(), this.getIdCatProduto(), this.getQuantidade(), this.getDataPedido());
    
    this.setIdPedido(DataBaseUtils.insert(conn, SQL, "idPedido"));


	}}
	
	
	
	
