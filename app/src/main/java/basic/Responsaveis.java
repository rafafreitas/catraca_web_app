package basic;

/**
 * Created by rafael on 24/11/17.
 */

public class Responsaveis {

    private int resp_id;
    private String resp_nome;
    private String resp_email;
    private int filial_id;

    public Responsaveis() {

    }

    public int getResp_id() {
        return resp_id;
    }

    public void setResp_id(int resp_id) {
        this.resp_id = resp_id;
    }

    public String getResp_nome() {
        return resp_nome;
    }

    public void setResp_nome(String resp_nome) {
        this.resp_nome = resp_nome;
    }

    public String getResp_email() {
        return resp_email;
    }

    public void setResp_email(String resp_email) {
        this.resp_email = resp_email;
    }

    public int getFilial_id() {
        return filial_id;
    }

    public void setFilial_id(int filial_id) {
        this.filial_id = filial_id;
    }
}
