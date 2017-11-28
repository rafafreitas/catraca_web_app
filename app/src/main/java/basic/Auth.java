package basic;

import java.util.ArrayList;

/**
 * Created by rafael on 27/11/2017.
 */

public class Auth {


    private static Auth instance = new Auth();
    private String status;
    private String mensagem;
    private String token;
    private String statusAPI;
    private String mensagemErroApi;
    private String login;
    private String senha;
    private Usuario usuario;
    private ArrayList<Responsaveis> responsaveis;
    private ArrayList<VisitaMotivo> visitaMotivos;

    public Auth() {
    }


    public static synchronized Auth getInstance() {
        if (instance == null) {
            instance = new Auth();
        }

        return instance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatusAPI() {
        return statusAPI;
    }

    public void setStatusAPI(String statusAPI) {
        this.statusAPI = statusAPI;
    }

    public String getMensagemErroApi() {
        return mensagemErroApi;
    }

    public void setMensagemErroApi(String mensagemErroApi) {
        this.mensagemErroApi = mensagemErroApi;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Responsaveis> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(ArrayList<Responsaveis> responsaveis) {
        this.responsaveis = responsaveis;
    }

    public ArrayList<VisitaMotivo> getVisitaMotivos() {
        return visitaMotivos;
    }

    public void setVisitaMotivos(ArrayList<VisitaMotivo> visitaMotivos) {
        this.visitaMotivos = visitaMotivos;
    }





}


