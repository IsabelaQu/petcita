package petcita.catalogo;

import java.sql.SQLException;
import java.sql.Statement;
import petcita.DataBaseUtils;
import java.sql.Connection;
import java.time.LocalDate;

public class CatProduto extends Catalogo{
	private int idCatProduto;
	private String fornecedor;
	private LocalDate dtValidade;
	private LocalDate dtRegistro;
	
	public CatProduto(Boolean disponivel, Double valor, String descricao, String categoria, int idCatProduto, String fornecedor, LocalDate dtValidade, LocalDate dtRegistro) {
		super(disponivel, valor, descricao, categoria);
		this.idCatProduto = idCatProduto;
		this.fornecedor = fornecedor;
		this.dtValidade = dtValidade;
		this.dtRegistro = dtRegistro;
	} 

	// Getters e Setters
	public int getIdCatProduto() {
		return idCatProduto;
	}

	public void setIdCatProduto(int idCatProduto) {
		this.idCatProduto = idCatProduto;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) throws Exception {
		if(fornecedor.equals("") || fornecedor.isEmpty()) {
			throw new Exception("O campo fornecedor não pode ser vazio");
		}
		this.fornecedor = fornecedor;
	}

	public LocalDate getDtValidade() {
		return dtValidade;
	}

	public void setDtValidade(LocalDate dtValidade) throws Exception {
		if(dtValidade == null) {
			throw new Exception("O campo de data de validade não pode ser vazio");
		}
		this.dtValidade = dtValidade;
	}

	public LocalDate getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(LocalDate dtRegistro) throws Exception {
		if(dtRegistro == null) {
			throw new Exception("O campo de data de registro não pode ser vazio");
		}
		this.dtRegistro = dtRegistro;
	}

	public void criarCatProduto(Connection conn) throws SQLException
	{
		criarCatalogo(conn);
		
		String SQL = String.format("INSERT INTO cat_produto (id_catalogo, fornecedor, dt_validade, dt_registro) VALUES (%d,%s,%tD,%f)", this.getIdCatalogo(), this.getFornecedor(), this.getDtValidade(), this.getDtRegistro());
		
		this.setIdCatProduto(DataBaseUtils.insertRetornaId(conn, SQL));
	}
	
}
