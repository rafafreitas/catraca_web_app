package request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import basic.Auth;
import basic.Responsaveis;
import basic.Usuario;

/**
 * Created by rafael on 02/12/17.
 */

public class AddAuth {

    private Auth auth;


    public AddAuth() {
    }

    public void feedAuth(JSONObject jsonObjectAuth) throws JSONException {

        auth =  Auth.getInstance();
        auth.setToken(jsonObjectAuth.get("token").toString());

        JSONObject jsonObjectUsuario = jsonObjectAuth.getJSONObject("usuario");

        //Alimentar o Usuário
        Usuario usuario = new Usuario();
        usuario.setUser_id(Integer.parseInt(jsonObjectUsuario.get("user_id").toString()));
        usuario.setUser_email(jsonObjectUsuario.get("user_email").toString());
        usuario.setUser_nome(jsonObjectUsuario.get("user_nome").toString());
        usuario.setUser_data_nasc(jsonObjectUsuario.get("dateFormat").toString());
        usuario.setUser_cpf(jsonObjectUsuario.get("user_cpf").toString());
        usuario.setTipo_id(Integer.parseInt(jsonObjectUsuario.get("tipo_id").toString()));
        usuario.setFilial_id(Integer.parseInt(jsonObjectUsuario.get("filial_id").toString()));
        auth.setUsuario(usuario);

        //ArrayList<Responsaveis> responsaveisArrayList = new ArrayList<Responsaveis>();
        //Responsaveis responsaveis = new Responsaveis();
        //JSONObject jsonObjectResponsaveis = jsonObjectAuth.getJSONObject("responsaveis");





    }

}
