package camila.login;

import java.sql.*;

public class DAO {
    private Connection conexao;

    DAO() {
        conexao = null;
    }

    public boolean conectar() {
    	 String driveName = "org.postgresql.Driver";
         String serverName = "localhost";
         String mydatabase = "camila";
         int porta = 5432;
         String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
         String username = "admin";
         String password = "admin";
         boolean status = false;

        try {
            Class.forName(driveName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao == null);
            System.out.println("A conexao foi um sucesso!");
        } catch (ClassNotFoundException e) {
            System.err.println("A conexao falhou -- Driver nao encontrado " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("A conexao falhou -- " + e.getMessage());
        }

        return status;
    }

    public boolean close() {
        boolean status = false;

        try {
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return status;
    }

    public boolean inserirLogin(Login login) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("INSERT INTO login (id, usuario, senha, sexo, cidade, estado)"
                    + "Values (" + login.getId() + ", " + login.getUsuario() + ", '" + login.getSenha() + "', '"
                    + login.getSexo() + ", " + login.getCidade() + ", " + login.getEstado());
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public boolean atualizarLogin(Login login) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE login SET id = '" + login.getId() + "', Usuario = '" + login.getUsuario()
                    + "', Senha = '" + login.getUsuario() + "', Sexo =  '" + login.getSexo() + "', Cidade = '"
                    + login.getCidade() + "', Estado = '" + login.getEstado() + "'" + " WHERE Id = "
                    + login.getId();
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }

        return status;
    }

    public boolean excluirLogin(int id) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM login WHERE id = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public Login[] getLogins() {
        Login[] logins = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM login");

            if (rs.next()) {
                rs.last();
                logins = new Login[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    logins[i] = new Login(rs.getInt("ID"), rs.getString("Usuario"), rs.getString("Senha"),
                            rs.getString("Sexo").charAt(0), rs.getString("Cidade"), rs.getString("Estado"));
                }
                st.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return logins;
    }
}
