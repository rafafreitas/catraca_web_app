package util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Rafael Freitas on 27/11/2017.
 */

public class Internet {
    private Context context;

    public Internet(Context context) {
        this.context = context;
    }

    public Internet() {

    }

    public boolean verificarConexao()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
