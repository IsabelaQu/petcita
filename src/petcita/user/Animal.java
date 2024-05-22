package petcita.user;

import java.time.LocalDate;
import java.sql.SQLException;
import java.sql.Statement;
import petcita.DataBaseUtils;
import java.sql.Connection;

public class Animal{
    private int id_animal;
    private String nome;
    private String especie;
    private LocalDate data_nascimento;
    private char porte;
    private int cliente_id; // Referência ao id_cliente que é o dono do animal

// Método Construtor:
    public Animal(int id_animal, String nome, String especie, LocalDate data_nascimento, char porte, int cliente_id) {
        this.id_animal = id_animal;
        this.nome = nome;
        this.especie = especie;
        this.data_nascimento = data_nascimento;
        this.porte = porte;
        this.cliente_id = cliente_id;
    }

// Getters e Setters:
    public int getIdAnimal() {
        return id_animal;
    }

    public void setIdAnimal(int id_animal) {
        this.id_animal = id_animal;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception {
        if(nome.equals("") || nome.isEmpty()) {
            throw new Exception("O campo nome do animal não pode ser vazio");
        }
        this.nome = nome;
    }
    
    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) throws Exception {
        if(especie.equals("") || especie.isEmpty()) {
            throw new Exception("O campo de espécie do animal não pode ser vazio");
        }
        this.especie = especie;
    }
    
    public LocalDate getDataNascimento() {
        return data_nascimento;
    }

    public void setDataNascimento(LocalDate data_nascimento) throws Exception {
        if(data_nascimento == null) {
            throw new Exception("O campo de data de nascimento do animal não pode ser vazio");
        }
        this.data_nascimento = data_nascimento;
    }

    public char getPorte() {
        return porte;
    }

    public void setPorte(char porte) throws Exception {
        if (porte != 'P' && porte != 'M' && porte != 'G') {
            throw new Exception("O campo de porte do animal deve ser 'P', 'M' ou 'G'");
        }
        this.porte = porte;
    }

    public int getIdCliente() {
        return cliente_id;
    }

    public void setIdCliente(int cliente_id) throws Exception {
        if (cliente_id <= 0) {
            throw new Exception("O ID do cliente deve ser um valor positivo");
        }
        this.cliente_id = cliente_id;
    }
    
    public void criarAnimal(Connection conn) throws SQLException
    {
        String SQL = String.format("INSERT INTO animal (nome, especie, data_nascimento, porte, id_cliente) VALUES ('%s', '%s', '%s', '%c', %d)", this.getNome(), this.getEspecie(), this.getDataNascimento(), this.getPorte(), this.getIdCliente());

        this.setIdAnimal(DataBaseUtils.insertRetornaId(conn, SQL));
    }
    
   // public Animal lerAnimal(Connection conn, int id_animal) throws SQLException {
   //    String SQL = String.format("SELECT * FROM animal WHERE id_animal = %d", id_animal);
   //     Statement stmt = conn.createStatement();
   //     ResultSet rs = stmt.executeQuery(SQL);
              
   //     if (rs.next()) {
   //         int id = rs.getInt("id_animal");
   //         String nome = rs.getString("nome");
   //         String especie = rs.getString("especie");
   //         LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
   //         char porte = rs.getString("porte").charAt(0);
   //         int cliente_id = rs.getInt("cliente_id");
            
   //         return new Animal(id, nome, especie, dataNascimento, porte, cliente_id);
   //     } else {
   //         return null; // Animal não encontrado
   //     }
   // }
    
   //public void atualizarAnimal(Connection conn) throws SQLException {
   //     String SQL = String.format("UPDATE animal SET nome = '%s', especie = '%s', data_nascimento = '%s', porte = '%c', cliente_id = %d WHERE id_animal = %d",
   //                                 this.getNome(), this.getEspecie(), this.getDataNascimento(), this.getPorte(), this.getIdCliente(), this.getId_animal());
        
   //     Statement stmt = conn.createStatement();
   //     stmt.executeUpdate(SQL);
   // }

   // public void deletarAnimal(Connection conn, int id_animal) throws SQLException {
   //     String SQL = String.format("DELETE FROM animal WHERE id_animal = %d", id_animal);
   //     Statement stmt = conn.createStatement();
   //     stmt.executeUpdate(SQL);
   // }

}
