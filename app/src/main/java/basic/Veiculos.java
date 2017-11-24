package basic;

/**
 * Created by rafael on 24/11/17.
 */

public class Veiculos {
    private int veic_id;
    private String veic_placa;
    private String veic_modelo;
    private String veic_foto;
    private int veic_chegada;
    private int veic_saida;
    private int user_id;


    public Veiculos() {
    }

    public int getVeic_id() {
        return veic_id;
    }

    public void setVeic_id(int veic_id) {
        this.veic_id = veic_id;
    }

    public String getVeic_placa() {
        return veic_placa;
    }

    public void setVeic_placa(String veic_placa) {
        this.veic_placa = veic_placa;
    }

    public String getVeic_modelo() {
        return veic_modelo;
    }

    public void setVeic_modelo(String veic_modelo) {
        this.veic_modelo = veic_modelo;
    }

    public String getVeic_foto() {
        return veic_foto;
    }

    public void setVeic_foto(String veic_foto) {
        this.veic_foto = veic_foto;
    }

    public int getVeic_chegada() {
        return veic_chegada;
    }

    public void setVeic_chegada(int veic_chegada) {
        this.veic_chegada = veic_chegada;
    }

    public int getVeic_saida() {
        return veic_saida;
    }

    public void setVeic_saida(int veic_saida) {
        this.veic_saida = veic_saida;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
