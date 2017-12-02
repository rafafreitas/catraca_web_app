package request;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import basic.Auth;
import basic.Usuario;

/**
 * Created by rafael on 28/11/17.
 */

public class UserRequester {
    private Auth auth;
    private Context context;

    public UserRequester() {

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void loadAuth(String email, String senha, String token) throws JSONException, InterruptedException, ExecutionException {

        final JSONObject jsonPut = new JSONObject();
        auth =  Auth.getInstance();

        //String teste = "teste@outlook.com";

        jsonPut.put("email", email);
        jsonPut.put("senha", senha);

        BaseRequester baseRequester = new BaseRequester();
        baseRequester.setUrl(Requester.API_URL + "/usuario/login");
        baseRequester.setMethod(Method.POST);
        baseRequester.setJsonString(jsonPut.toString());
        baseRequester.setContext(context);

        String jsonReturn = baseRequester.execute(baseRequester).get();
        Log.d("API", jsonReturn);

        JSONObject jsonObjectAuth = new JSONObject(jsonReturn);

        auth.setStatusAPI(jsonObjectAuth.get("status").toString());
        auth.setMensagemErroApi(jsonObjectAuth.get("message").toString());

        if (jsonObjectAuth.get("message").toString().equals("ERROR")) {
            //informar ao usuario
            auth.setMessage(jsonObjectAuth.get("result").toString());
            String mensagemErro = jsonObjectAuth.get("result").toString();
            Log.d("API", mensagemErro);
        }else {

            auth.setToken(jsonObjectAuth.get("token").toString());
            auth.setLogin(email);
            auth.setSenha(senha);

            JSONObject jsonObjectUsuario = jsonObjectAuth.getJSONObject("usuario");
            //operador

            Usuario usuario = new Usuario();
            usuario.setUser_id(Integer.parseInt(jsonObjectUsuario.get("user_id").toString()));
            usuario.setUser_email(jsonObjectUsuario.get("user_email").toString());
            usuario.setUser_nome(jsonObjectUsuario.get("user_nome").toString());
            usuario.setUser_data_nasc(jsonObjectUsuario.get("dateFormat").toString());
            usuario.setUser_cpf(jsonObjectUsuario.get("user_cpf").toString());
            usuario.setTipo_id(Integer.parseInt(jsonObjectUsuario.get("tipo_id").toString()));
            usuario.setFilial_id(Integer.parseInt(jsonObjectUsuario.get("filial_id").toString()));
            auth.setUsuario(usuario);

        }

    }

}
