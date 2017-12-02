package request;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import basic.Auth;

/**
 * Created by rafael on 28/11/17.
 */

public class RequesterLogin {
    private Auth auth;
    private Context context;

    public RequesterLogin() {

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

        jsonPut.put("user_email", email);
        jsonPut.put("user_senha", senha);

        BaseRequester baseRequester = new BaseRequester();
        baseRequester.setUrl(Requester.API_URL + "/usuario/login");
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
