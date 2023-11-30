package br.inatel.cdg;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    Connection connection;
    Statement statement;
    ResultSet result;
    PreparedStatement pst;

    static final String user = "root";
    static final String password = "Jlgs2003!";
    static final String database = "mydb";

    static final String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    private boolean check = false;

    public void connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        }
    }

    public boolean insertHotel(Hotel h) {
        connect();
        String sql = "INSERT INTO Hotel(nomeHotel,qntQuartos) VALUES(?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, h.getNome());
            pst.setInt(2, h.getQntQuartos());
            pst.execute();
            check = true;
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }

    public boolean insertPessoa(Pessoa p) {
        connect();
        String sql = "INSERT INTO Pessoa(nomePessoa, cpf, ocupacao, idade) VALUES(?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, p.getNome());
            pst.setString(2, p.getCpf());
            pst.setString(3, p.getOcupacao());
            pst.setInt(4, p.getIdade());
            pst.execute();
            check = true;
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }

    public boolean insertPHotel(int idPessoa, int idHotel) {
        connect();
        String sql = "INSERT INTO Pessoa_has_Hotel(Pessoa_idPessoa,Hotel_idHotel) VALUES(?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, idPessoa);
            pst.setInt(2, idHotel);
            pst.execute();
            check = true;
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
            check = false;
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return check;
    }

    //Pesquisa os hoteis disponiveis
    public ArrayList<Hotel> researchHotel() {
        connect();
        ArrayList<Hotel> hotel = new ArrayList<>();
        String sql = "SELECT * FROM hotel";
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                Hotel h = new Hotel();
                h.idHotel = result.getInt("idHotel");
                System.out.println("ID: " + h.idHotel);
                System.out.println("Nome Hotel: " + result.getString("nomeHotel"));
                System.out.println("Quartos: " + result.getInt("qntQuartos"));
                hotel.add(h);
            }
        } catch (SQLException e) {
            System.out.println(" Erro de operação: " + e.getMessage());
        } finally {
            try {
                connection.close();
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return hotel;
    }

    //Pesquisa os hospédes disponiveis
    public ArrayList<Pessoa> researchHospede() {
        connect();
        ArrayList<Pessoa> pessoa = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa WHERE ocupacao = 'Hospede'";
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                Hospede h = new Hospede(result.getString("nomePessoa"), result.getString("cpf"),result.getString("ocupacao"),result.getInt("idade"));
                h.idPessoa = result.getInt("idPessoa");
                System.out.println("ID: " + h.idPessoa);
                System.out.println("Nome: " + result.getString("nomePessoa"));
                pessoa.add(h);
            }
        } catch (SQLException e) {
            System.out.println(" Erro de operação: " + e.getMessage());
        } finally {
            try {
                connection.close();
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return pessoa;
    }

    //Pesquisar pessoas presente em certo Hotel
    public ArrayList<Pessoa> researchListaHotel() {
        connect();
        ArrayList<Pessoa> pessoa = new ArrayList<>();
        String sql = "SELECT hotel.nomeHotel,hospede.* FROM Pessoa_has_Hotel, hotel as hotel, pessoa as hospede WHERE Pessoa_has_Hotel.Pessoa_idPessoa = idPessoa and Hotel_idHotel = idHotel";
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                Hospede hos = new Hospede(result.getString("nomePessoa"),result.getString("cpf"),result.getString("ocupacao"),result.getInt("idade"));
                Hotel hotel = new Hotel();
                System.out.println("Nome: " + result.getString("nomePessoa") + " esta no Hotel " + result.getString("nomeHotel") + " como " + result.getString("ocupacao"));
                pessoa.add(hos);
            }
        } catch (SQLException e) {
            System.out.println(" Erro de operação: " + e.getMessage());
        } finally {
            try {
                connection.close();
                statement.close();
                result.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
        return pessoa;
    }

    //Atualizar dados do Hospede
    public boolean updateHospede(int id, String nome, String cpf, int idade){
        connect();
        String sql = "UPDATE pessoa SET nomePessoa = ?, cpf = ?, idade = ? WHERE id = ?";
        try{
            pst = connection.prepareStatement(sql);
            pst.setString(1,nome);
            pst.setString(2,cpf);
            pst.setInt(3,idade);
            pst.setInt(4,id);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de operacao: " + e.getMessage());
            check = false;
        }finally {
            try{
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexao: " + e.getMessage());
            }
        }
        return check;
    }

    public boolean deletePessoa(int id){
        connect();
        String sql = "DELETE FROM pessoa WHERE idPessoa = ?";

        try{
            pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.execute();
            check = true;
        }catch (SQLException e){
            System.out.println("Erro de operacao: " + e.getMessage());
            check = false;
        }finally {
            try{
                connection.close();
                pst.close();
            }catch (SQLException e){
                System.out.println("Erro ao fechar a conexao: " + e.getMessage());
            }
        }
        return check;
    }
}
