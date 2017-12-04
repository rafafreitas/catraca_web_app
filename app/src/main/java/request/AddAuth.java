package request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import basic.Auth;
import basic.Responsaveis;
import basic.Usuario;
import basic.VisitaMotivo;

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

        //Alimentar Array Responsáveis
        JSONArray jsonArrayResponsaveis = jsonObjectAuth.getJSONArray("responsaveis");
        ArrayList<Responsaveis> responsaveisArrayList = new ArrayList<Responsaveis>();

        for (int i = 0; i < jsonArrayResponsaveis.length(); i++) {

            JSONObject jsonObjectResponsavel = jsonArrayResponsaveis.getJSONObject(i);
            Responsaveis responsavel = new Responsaveis();
            responsavel.setResp_id(Integer.parseInt(jsonObjectResponsavel.get("resp_id").toString()));
            responsavel.setResp_nome(jsonObjectResponsavel.get("resp_nome").toString());
            responsaveisArrayList.add(responsavel);
        }
        auth.setResponsaveis(responsaveisArrayList);

        //Alimentar Array Motivos
        JSONArray jsonArrayMotivos = jsonObjectAuth.getJSONArray("motivos");
        ArrayList<VisitaMotivo> visitaMotivosArrayList = new ArrayList<VisitaMotivo>();

        for (int i = 0; i < jsonArrayMotivos.length(); i++) {

            JSONObject jsonObjectMotivo = jsonArrayMotivos.getJSONObject(i);
            VisitaMotivo visitaMotivo = new VisitaMotivo();
            visitaMotivo.setVisita_motivo_id(Integer.parseInt(jsonObjectMotivo.get("visita_motivo_id").toString()));
            visitaMotivo.setVisita_motivo_desc(jsonObjectMotivo.get("visita_motivo_desc").toString());
            visitaMotivosArrayList.add(visitaMotivo);
        }
        auth.setVisitaMotivos(visitaMotivosArrayList);

    }

}
