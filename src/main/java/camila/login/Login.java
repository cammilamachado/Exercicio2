package camila.login;

public class Login {
    private int id;
    private String usuario;
    private String senha;
    private char sexo;
    private String cidade;
    private String estado;

    public Login() {
        this.id = -1;
        this.usuario = "";
        this.senha = "";
        this.sexo = '*';
        this.cidade = "*";
        this.estado = "*";
    }

    public Login(int id, String usuario, String senha, char sexo, String cidade, String estado) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.sexo = sexo;
        this.cidade = cidade;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Login: [id: " + id + " Usuário para login: " + usuario + " Senha do usuário: " + senha
                + "Sexo: " + sexo + "Cidade: " + cidade + " Estado: " + estado + "]";

    }

}
