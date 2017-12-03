package request;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import basic.Auth;
import basic.Usuario;

/**
 * Created by rafael on 02/12/17.
 */

public class RequesterUpdateUser {

    private Auth auth;
    private Context context;

    public RequesterUpdateUser() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void loadAuth(Usuario usuario) throws JSONException, InterruptedException, ExecutionException {

        final JSONObject jsonPut = new JSONObject();
        auth =  Auth.getInstance();



        jsonPut.put("user_id", Integer.toString(auth.getUsuario().getUser_id()));
        jsonPut.put("user_nome", usuario.getUser_nome());
        jsonPut.put("user_email", usuario.getUser_email());
        jsonPut.put("user_cpf", usuario.getUser_cpf());
        jsonPut.put("user_data_nasc", usuario.getUser_data_nasc());

        BaseRequester baseRequester = new BaseRequester();
        baseRequester.setUrl(Requester.API_URL + "/usuario/alterar");
        baseRequester.setAuthorization(auth.getToken());
        baseRequester.setMethod(Method.POST);
        baseRequester.setJsonString(jsonPut.toString());
        baseRequester.setContext(context);

        String jsonReturn = baseRequester.execute(baseRequester).get();
        Log.d("API", jsonReturn);

        JSONObject jsonObjectAuth = new JSONObject(jsonReturn);

        auth.setStatusAPI(jsonObjectAuth.get("status").toString());
        auth.setMessage(jsonObjectAuth.get("message").toString());

        if (jsonObjectAuth.get("message").toString().equals("ERROR")) {
            //informar ao usuario
            auth.setMensagemErroApi(jsonObjectAuth.get("result").toString());
            String mensagemErro = jsonObjectAuth.get("result").toString();
            Log.d("API", mensagemErro);
        }else {
            AddAuth addAuth = new AddAuth();
            addAuth.feedAuth(jsonObjectAuth);
        }

    }
}
