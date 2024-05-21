package petcita;

public class CatProduto extends catalogo{

	public CatProduto() {
	    private int idCatProduto;
	    private String fornecedor;
	    private Date dtValidade;
	    private Date dtRegistro;
	    
	    public CatProduto(int id_catalogo, Boolean disponivel, Double valor, String descricao, String categoria, int idCatProduto, String fornecedor, Date dtValidade, Date dtRegistro) {
	        super(id_catalogo, disponivel, valor, descricao, categoria);
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

	    public Date getDtValidade() {
	        return dtValidade;
	    }

	    public void setDtValidade(Date dtValidade) throws Exception {
	        if(dtValidade == null) {
	            throw new Exception("O campo de data de validade não pode ser vazio");
	        }
	        this.dtValidade = dtValidade;
	    }

	    public Date getDtRegistro() {
	        return dtRegistro;
	    }

	    public void setDtRegistro(Date dtRegistro) throws Exception {
	        if(dtRegistro == null) {
	            throw new Exception("O campo de data de registro não pode ser vazio");
	        }
	        this.dtRegistro = dtRegistro;
	    }

	    public int getIdCatalogo() {
	        return idCatalogo;
	    }
	    
	    public void criarCatProduto(Connection conn) throws SQLException
	    {
	        criarCatalogo(conn);
	        
	        String SQL = String.format("INSERT INTO cat_produto (id_catalogo, fornecedor, dt_validade, dt_registro) VALUES (%d,%s,%f)", this.getId_catalogo(), this.getFornecedor(), this.getDtValidade(), this.getDtRegistro());
	        
	        this.getIdCatProduto(DataBaseUtils.insert(conn, SQL, "id_cat_produto"));
	    }
	    
	}
}
