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
    private int IdUsuario; // Referência ao id_usuario que é o dono do animal

// Método Construtor:
    public Animal(int idAnimal, String nome, String especie, LocalDate dataNascimento, char porte, int idUsuario) {
        this.idAnimal = idAnimal;
        this.Nome = nome;
        this.Especie = especie;
        this.DataNascimento = dataNascimento;
        this.Porte = porte;
        this.IdUsuario = idUsuario;
    }
    
    public Animal()
    {}

    public Animal(int idUsuario)
    {
        this.IdUsuario = idUsuario;
    }

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
        porte = Character.toLowerCase(porte);
        if (porte != 'p' && porte != 'm' && porte != 'g') {
            throw new Exception("O campo de porte do animal deve ser 'P', 'M' ou 'G'");
        }
        this.Porte = porte;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) throws Exception {
        if (idUsuario <= 0) {
            throw new Exception("O ID do usuario deve ser um valor positivo");
        }
        this.IdUsuario = idUsuario;
    }
    
    public void criarAnimal(Connection conn) throws SQLException
    {
        String SQL = String.format("INSERT INTO animal (nome, especie, data_nascimento, porte, id_usuario) VALUES ('%s', '%s', '%s', '%c', %d)", this.getNome(), this.getEspecie(), this.getDataNascimento(), this.getPorte(), this.getIdUsuario());

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
                int idUsuario = rs.getInt("id_usuario");

                return new Animal(id_animal, nome, especie, dataNascimento, porte, idUsuario);
            } else {
                return null; // Animal não encontrado
            }
        }
    }

    public boolean atualizarAnimal(Connection conn) throws SQLException {
        String SQL = "UPDATE animal SET nome = '%s', especie = '%s', data_nascimento = '%s', porte = '%c' WHERE id_animal = %d";
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
            int usuario_id = retorno.getInt("usuario_id");
            
            return new Animal(id, nome, especie, dataNascimento, porte, usuario_id);
        } else {
            return null; // Animal não encontrado
        }
    } */
   
   public String lerAnimalUsuario(Connection conn) throws SQLException {
        String SQL = String.format("SELECT id_animal, nome, especie, data_nascimento, porte FROM animal WHERE id_usuario = %d", IdUsuario);
       
        StringBuilder table = new StringBuilder();
        
        
        try (ResultSet rs = DataBaseUtils.select(conn, SQL)) {
            if(!rs.next())
                return "Nenhum animal encontrado para o usuário\n";

            table.append("+----------------+----------------+----------------+-----------------+----------------+\n");
            table.append("|       ID       |     Nome       |    Espécie     |  Dt. Nascimento |      Porte     |\n");
            table.append("+----------------+----------------+----------------+-----------------+----------------+\n");
            while (rs.next()) {
                    int idAnimal = rs.getInt("id_animal");
                    String nome = rs.getString("nome");
                    String especie = rs.getString("especie");
                    String porte = rs.getString("porte");
                    LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();

                    table.append(String.format("|%15d|%15s|%15s|%15tD|%15s|\n", idAnimal, nome, especie, dataNascimento, porte));
            }
            table.append("+----------------+----------------+----------------+-----------------+----------------+\n");
            table.append("Esolha dentre os animais acima:\n");
            
        }

       
        return table.toString();
    }
    
   //public void atualizarAnimal(Connection conn) throws SQLException {
   //     String SQL = String.format("UPDATE animal SET nome = '%s', especie = '%s', data_nascimento = '%s', porte = '%c', usuario_id = %d WHERE id_animal = %d",
   //                                 this.getNome(), this.getEspecie(), this.getDataNascimento(), this.getPorte(), this.getIdUsuario(), this.getId_animal());
        
   //     Statement stmt = conn.createStatement();
   //     stmt.executeUpdate(SQL);
   // }

   // public void deletarAnimal(Connection conn, int id_animal) throws SQLException {
   //     String SQL = String.format("DELETE FROM animal WHERE id_animal = %d", id_animal);
   //     Statement stmt = conn.createStatement();
   //     stmt.executeUpdate(SQL);
   // }

}
