package request;


import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import basic.Auth;
import basic.Usuario;
import basic.Veiculos;
import util.Util;

/**
 * Created by rafael on 06/12/17.
 */

public class RequesterVeiculo {

    private Auth auth;
    private Context context;

    public RequesterVeiculo(){

    }

    public Context getContext(){
        return this.context;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public String saveVeiculo(Veiculos veiculo) throws JSONException, InterruptedException, ExecutionException{

        final JSONObject json = new JSONObject();
        auth = Auth.getInstance();

        json.put("user_email", auth.getUsuario().getUser_email());
        json.put("user_senha", auth.getUsuario().getUser_senha());
        json.put("veic_placa", veiculo.getVeic_placa());
        json.put("veic_modelo", veiculo.getVeic_modelo());
        json.put("veic_foto", veiculo.getVeic_foto());
        if(veiculo.getUser_id() > 0){
            json.put("veic_id", veiculo.getVeic_id());
        }else{
            json.put("veic_id", "");
        }


        BaseRequester baseRequester = new BaseRequester();
        baseRequester.setUrl(Requester.API_URL + "/veiculo/cadastro");
        baseRequester.setAuthorization(auth.getToken());
        baseRequester.setMethod(Method.POST);
        baseRequester.setJsonString(json.toString());
        baseRequester.setContext(context);

        String jsonResponse = baseRequester.execute(baseRequester).get();
        Log.d("API", jsonResponse);

        JSONObject jsonObjectResponse = new JSONObject(jsonResponse);
        auth.setStatusAPI(jsonObjectResponse.get("status").toString());
        auth.setMessage(jsonObjectResponse.get("message").toString());

        String errorMessage = jsonObjectResponse.get("result").toString();

        if(jsonObjectResponse.get("message").toString().equals("ERROR")){
            //Informar o usuário

            auth.setMensagemErroApi(errorMessage);
            Log.d("API",errorMessage);
            return errorMessage;
        }else{
            //Atualizar o objeto de informações principais
            AddAuth a = new AddAuth();
            a.feedAuth(jsonObjectResponse);
            return errorMessage;
        }
    }

    public Veiculos checkVeiculoByPlaca(String placa)throws JSONException, InterruptedException, ExecutionException{

        final JSONObject json = new JSONObject();
        auth = Auth.getInstance();
        Usuario user = auth.getUsuario();

        json.put("user_nome", user.getUser_nome());
        json.put("user_email", user.getUser_email());
        json.put("user_senha", user.getUser_senha());
        json.put("user_cpf", user.getUser_cpf());
        json.put("user_data_nasc", user.getUser_data_nasc());
        json.put("veic_placa", placa);

        BaseRequester baseRequester = new BaseRequester();
        baseRequester.setUrl(Requester.API_URL + "/veiculo/consulta");
        baseRequester.setAuthorization(auth.getToken());
        baseRequester.setMethod(Method.POST);
        baseRequester.setJsonString(json.toString());
        baseRequester.setContext(context);

        String jsonResponse = baseRequester.execute(baseRequester).get();
        Log.d("API", jsonResponse);

        JSONObject jsonObjectResponse = new JSONObject(jsonResponse);
        auth.setStatusAPI(jsonObjectResponse.get("status").toString());
        auth.setMessage(jsonObjectResponse.get("message").toString());

        if(jsonObjectResponse.get("message").toString().equals("ERROR")){
            //Informar o usuário
            String errorMessage = jsonObjectResponse.get("result").toString();
            auth.setMensagemErroApi(errorMessage);
            Log.d("API",errorMessage);
            return null;
        }else{
            //Atualizar o objeto de informações principais
            AddAuth a = new AddAuth();
            a.feedAuth(jsonObjectResponse.getJSONObject("gerais"));
            JSONObject vj = jsonObjectResponse.getJSONObject("veiculo");
            return  getFromJson(vj);
        }
    }

    private Veiculos getFromJson(JSONObject objectVeiculo) throws JSONException{

        Veiculos v = new Veiculos();
        v.setVeic_id(Integer.parseInt(objectVeiculo.get("veic_id").toString()));
        v.setVeic_placa(Util.formatVeiculoPlaca(objectVeiculo.get("veic_placa").toString()));
        v.setVeic_modelo(objectVeiculo.get("veic_modelo").toString());
        v.setVeic_foto(objectVeiculo.get("veic_foto").toString());
        v.setUser_id(Integer.parseInt(objectVeiculo.get("user_id").toString()));

        return v;
    }

}
