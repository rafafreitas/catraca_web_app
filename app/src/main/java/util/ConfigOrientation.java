package util;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;

/**
 * Created by matheus.silva on 05/12/2017.
 */

public class ConfigOrientation {

    public static void setaRetrato(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public static void setaPaisagem(Activity activity) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public static String verificaOrientacao(Activity activity) {
        int orientacao = activity.getResources().getConfiguration().orientation;
        String retornoOrientacao = "";
        if (orientacao == Configuration.ORIENTATION_PORTRAIT) {
            retornoOrientacao = "retrato";
        } else if (orientacao == Configuration.ORIENTATION_LANDSCAPE) {
            retornoOrientacao = "paisagem";
        } else if (orientacao == Configuration.ORIENTATION_SQUARE) {
            retornoOrientacao = "quadro";
        }
        return retornoOrientacao;
    }
}
