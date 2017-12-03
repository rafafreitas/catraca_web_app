package request;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rafael on 24/11/17.
 */

public class BaseRequester extends AsyncTask<BaseRequester, Object, String> {

    private String url;
    private JSONObject jsonObject;
    private Method method;
    private Context context;
    private String strReturn;
    private String jsonString;
    private static String authorization;

    public BaseRequester() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getStrReturn() {
        return strReturn;
    }

    public void setStrReturn(String strReturn) {
        this.strReturn = strReturn;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public static String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    @Override
    protected String doInBackground(BaseRequester... baseRequesters) {
        try {
            return sendGson(this.url, this.method, this.jsonString, this.context);
        } catch (JSONException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    private static String sendGson(String apiUrl, Method method, String gsonString, Context context) throws JSONException, IOException {

        URL url;
        String returnStr = "";

        try {
            url = new URL(apiUrl);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("invalid url: " + apiUrl);
        }

        try {
            if (method == Method.POST) {


                /*
                HTTP
                 */
                HttpURLConnection conn = null;
                conn = (HttpURLConnection) url.openConnection();

                /*
                HTTPS
                 */
                //HttpsURLConnection conn = null;
                //conn = (HttpsURLConnection) url.openConnection();
                //conn.setSSLSocketFactory(generateCertificate(context).getSocketFactory());

                byte[] bytes = null;


                String body = "";
                if (gsonString != null) {
                    body = gsonString;
                }

                //String teste = getAuthorization();

                bytes = body.getBytes();
                //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", getAuthorization() );

                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod(String.valueOf(method));
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream out = conn.getOutputStream();
                out.write(bytes);
                out.close();

                int status = conn.getResponseCode();

                InputStream is;
                String convertStreamToString = "";

                if (status == 400 || status == 500) {
                    //return MessageText.ERROR_SERVER.toString();
                    convertStreamToString = convertStreamToString(conn.getErrorStream(), "UTF-8");
                } else {
                    convertStreamToString = convertStreamToString(conn.getInputStream(), /*HTTP.UTF_8*/"UTF-8");
                    conn.disconnect();
                }
                //returnStr = convertStreamToString;
                return convertStreamToString;

            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return returnStr;
    }

    private static String convertStreamToString(InputStream is, String enc) throws UnsupportedEncodingException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, enc));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try
        {
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }


}
