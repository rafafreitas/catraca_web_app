package basic;

import java.util.ArrayList;

/**
 * Created by rafael on 24/11/17.
 */

public class Visitas {

    private int visita_id;
    private String visita_chegada;
    private String visita_saida;
    private int visita_motivo_id;
    private int visita_responsavel;
    private int filial_id;
    private ArrayList<Visitantes> visitantes;
    private ArrayList<Veiculos> veiculos;
    private int user_id;

    public Visitas() {
    }

    public int getVisita_id() {
        return visita_id;
    }

    public void setVisita_id(int visita_id) {
        this.visita_id = visita_id;
    }

    public String getVisita_chegada() {
        return visita_chegada;
    }

    public void setVisita_chegada(String visita_chegada) {
        this.visita_chegada = visita_chegada;
    }

    public String getVisita_saida() {
        return visita_saida;
    }

    public void setVisita_saida(String visita_saida) {
        this.visita_saida = visita_saida;
    }

    public int getVisita_motivo_id() {
        return visita_motivo_id;
    }

    public void setVisita_motivo_id(int visita_motivo_id) {
        this.visita_motivo_id = visita_motivo_id;
    }

    public int getVisita_responsavel() {
        return visita_responsavel;
    }

    public void setVisita_responsavel(int visita_responsavel) {
        this.visita_responsavel = visita_responsavel;
    }

    public int getFilial_id() {
        return filial_id;
    }

    public void setFilial_id(int filial_id) {
        this.filial_id = filial_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public ArrayList<Visitantes> getVisitantes() {
        return visitantes;
    }

    public void setVisitantes(ArrayList<Visitantes> visitantes) {
        this.visitantes = visitantes;
    }

    public ArrayList<Veiculos> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculos> veiculos) {
        this.veiculos = veiculos;
    }
}
