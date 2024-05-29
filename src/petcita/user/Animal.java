package petcita.user;

import java.time.LocalDate;
import java.sql.SQLException;
import java.sql.ResultSet;
import petcita.DataBaseUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Animal{
    private int idAnimal;
    private String Nome;
    private String Especie;
    private LocalDate DataNascimento;
    private char Porte;
    private int IdCliente; // Referência ao id_cliente que é o dono do animal

// Método Construtor:
    public Animal(int idAnimal, String nome, String especie, LocalDate dataNascimento, char porte, int idCliente) {
        this.idAnimal = idAnimal;
        this.Nome = nome;
        this.Especie = especie;
        this.DataNascimento = dataNascimento;
        this.Porte = porte;
        this.IdCliente = idCliente;
    }
    
    public Animal()
    {}

// Getters e Setters:
    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }
    
    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) throws Exception {
        if(nome.equals("") || nome.isEmpty()) {
            throw new Exception("O campo nome do animal não pode ser vazio");
        }
        this.Nome = nome;
    }
    
    public String getEspecie() {
        return Especie;
    }

    public void setEspecie(String especie) throws Exception {
        if(especie.equals("") || especie.isEmpty()) {
            throw new Exception("O campo de espécie do animal não pode ser vazio");
        }
        this.Especie = especie;
    }
    
    public LocalDate getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) throws Exception {
        if(dataNascimento == null) {
            throw new Exception("O campo de data de nascimento do animal não pode ser vazio");
        }
        this.DataNascimento = dataNascimento;
    }

    public char getPorte() {
        return Porte;
    }

    public void setPorte(char porte) throws Exception {
        if (porte != 'P' && porte != 'M' && porte != 'G') {
            throw new Exception("O campo de porte do animal deve ser 'P', 'M' ou 'G'");
        }
        this.Porte = porte;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int idCliente) throws Exception {
        if (idCliente <= 0) {
            throw new Exception("O ID do cliente deve ser um valor positivo");
        }
        this.IdCliente = idCliente;
    }
    
    public void criarAnimal(Connection conn) throws SQLException
    {
        String SQL = String.format("INSERT INTO animal (nome, especie, data_nascimento, porte, id_cliente) VALUES ('%s', '%s', '%s', '%c', %d)", this.getNome(), this.getEspecie(), this.getDataNascimento(), this.getPorte(), this.getIdCliente());

        this.setIdAnimal(DataBaseUtils.insertRetornaId(conn, SQL));
    }
    
    public Animal buscarAnimalPorId(Connection conn, int id_animal) throws SQLException {
        String SQL = "SELECT * FROM animal WHERE id_animal = %d";
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, id_animal);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String especie = rs.getString("especie");
                LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
                char porte = rs.getString("porte").charAt(0);
                int idCliente = rs.getInt("id_cliente");

                return new Animal(id_animal, nome, especie, dataNascimento, porte, idCliente);
            } else {
                return null; // Animal n�o encontrado
            }
        }
    }

    public boolean atualizarAnimal(Connection conn) throws SQLException {
        String SQL = "UPDATE animal SET nome = %s, especie = %s, data_nascimento = %s, porte = %c WHERE id_animal = %d";
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, this.getNome());
            stmt.setString(2, this.getEspecie());
            stmt.setDate(3, java.sql.Date.valueOf(this.getDataNascimento()));
            stmt.setString(4, String.valueOf(this.getPorte()));
            stmt.setInt(5, this.getIdAnimal());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean deletarAnimal(Connection conn, int id_animal) throws SQLException {
        String SQL = "DELETE FROM animal WHERE id_animal = %d";
        try (PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, id_animal);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
    
   /*public Animal lerAnimal(Connection conn, int id_animal) throws SQLException {
       String SQL = String.format("SELECT id_animal, nome, especie, data_nascimento, porte FROM animal WHERE id_animal = %d", id_animal);
       ResultSet retorno = DataBaseUtils.select(conn, SQL);
       
        if (retorno.next()) {
            int id = retorno.getInt("id_animal");
            String nome = retorno.getString("nome");
            String especie = retorno.getString("especie");
            LocalDate dataNascimento = retorno.getDate("data_nascimento").toLocalDate();
            char porte = retorno.getString("porte").charAt(0);
            int cliente_id = retorno.getInt("cliente_id");
            
            return new Animal(id, nome, especie, dataNascimento, porte, cliente_id);
        } else {
            return null; // Animal não encontrado
        }
    } */
   
   public String lerAnimalUsuario(Connection conn, int id_usuario) throws SQLException {
        String SQL = String.format("SELECT id_animal, nome, especie, data_nascimento, porte FROM animal WHERE id_usuario = %d", id_usuario);
       
        StringBuilder table = new StringBuilder();
        
        
        try (ResultSet rs = DataBaseUtils.select(conn, SQL)) {
            if(rs.next())
            {
                table.append("+----------------+----------------+----------------+-----------------+----------------+\n");
                table.append("|       ID       |     Nome       |    Esp�cie     |  Dt. Nascimento |      Porte     |\n");
                table.append("+----------------+----------------+----------------+-----------------+----------------+\n");
                while (rs.next()) {
                        int idAnimal = rs.getInt("id_animal");
                        String nome = rs.getString("nome");
                        String especie = rs.getString("especie");
                        char porte = rs.getString("porte").charAt(0);
                        LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();

                        table.append(String.format("|%15d|%15s|%15s|%15tD|%15s|\n", idAnimal, nome, especie, dataNascimento, porte));
                }
                table.append("+----------------+----------------+----------------+----------------+----------------+----------------+----------------+----------------+\n");
                table.append("Esolha dentre os animais acima:\n");
            }
            else
                return "0";
        }

       
        return table.toString();
    }
    
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
