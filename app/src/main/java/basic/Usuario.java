package basic;

/**
 * Created by rafael on 24/11/17.
 */

public class Usuario {

    private int user_id;
    private String user_nome;
    private String user_email;
    private String user_senha;
    private String user_cpf;
    private String user_data_nasc;
    private int tipo_id;
    private int filial_id;

    public Usuario() {

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_nome() {
        return user_nome;
    }

    public void setUser_nome(String user_nome) {
        this.user_nome = user_nome;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_senha() {
        return user_senha;
    }

    public void setUser_senha(String user_senha) {
        this.user_senha = user_senha;
    }

    public String getUser_cpf() {
        return user_cpf;
    }

    public void setUser_cpf(String user_cpf) {
        this.user_cpf = user_cpf;
    }

    public String getUser_data_nasc() {
        return user_data_nasc;
    }

    public void setUser_data_nasc(String user_data_nasc) {
        this.user_data_nasc = user_data_nasc;
    }

    public int getTipo_id() {
        return tipo_id;
    }

    public void setTipo_id(int tipo_id) {
        this.tipo_id = tipo_id;
    }

    public int getFilial_id() {
        return filial_id;
    }

    public void setFilial_id(int filial_id) {
        this.filial_id = filial_id;
    }
}
