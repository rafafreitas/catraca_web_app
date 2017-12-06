package request;

import android.content.Context;

import basic.Auth;
import basic.Veiculos;

/**
 * Created by rafael on 06/12/17.
 */

public class RequesterVeiculo {

    private Auth auth;
    private Context context;
    private Veiculos veiculos;

    public RequesterVeiculo() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Veiculos checkVeiculoByPlaca(String placaSearch){

        return veiculos;
    }
}
