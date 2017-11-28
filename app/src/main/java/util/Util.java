package util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.os.Handler;

/**
 * Created by rafael on 28/11/17.
 */

public class Util {
    private static Context ctxAtual;
    private static Message message = null;
    private static ProgressDialog pd = null;

    public static void setCtxAtual(Context ctx)
    {
        ctxAtual = ctx;
    }

    public static Context getCtxAtual()
    {
        return ctxAtual;
    }


    public static void AtivaDialogHandler(int Evento, String Titulo, String Mensagem)
    {
        message = new Message();
        message.what = Evento;
        message.obj = Titulo+";"+Mensagem;

        dialogHandler.sendMessage(message);
    }

    final static Handler dialogHandler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            String texto = (String) msg.obj;
            String[] Queb = texto.split(";");

            if(msg.what == 1)//Dialog
            {
                Util.showMessage(Queb[1], Queb[0], Util.getCtxAtual(), 0);
            }
            else if(msg.what == 4)//Dialog close app
            {
                Util.showMessage(Queb[1], Queb[0], Util.getCtxAtual(), 1);
            }
            else if(msg.what == 9)//Dialog repetir pedidos
            {
                Util.showMessage(Queb[1], Queb[0], Util.getCtxAtual(), 2);
            }
            else if(msg.what == 10)//Dialog repetir pedidos mensagem de item  no carrinho
            {
                Util.showMessage(Queb[1], Queb[0], Util.getCtxAtual(), 3);
            }
            else if(msg.what == 2)//Progress Dialog /* Title;Mensagem */
            {
                Util.startProgressDialog(Queb[0], Queb[1]);
            }
            else if(msg.what == 5)//Fecha Progress Dialog
            {
                Util.stopProgressDialog();
            }
        }
    };

    public static void startProgressDialog(String Title, String Message)
    {
        pd = new ProgressDialog(Util.getCtxAtual());
        pd.setTitle(Title);
        pd.setMessage(Message);
        pd.setCancelable(false);
        pd.setIndeterminate(true);
        pd.show();
    }

    public static void stopProgressDialog()
    {
        pd.dismiss();
    }

    public static void showMessage(String Mensagem, String Titulo, final Context Activity, int acao)
    {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(Activity);
        dialogo.setTitle(Titulo);
        dialogo.setMessage(Mensagem);
        dialogo.setCancelable(false);
        if(acao == 1)
        {
            dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    int pid = android.os.Process.myPid();
                    android.os.Process.killProcess(pid);
                    System.exit(0);
                }
            });
        }
        else if(acao == 2)
        {
            dialogo.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    //Intent it = new Intent(Activity, PedidoActivity.class);
                    //it.putExtra("id_produto_temp", 999999);
                    //Activity.startActivity(it);
                }
            });
        }
        else if(acao == 3)
        {
            dialogo.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            dialogo.setPositiveButton("Você confirma?", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {

                    new AlertDialog.Builder(Activity)
                            .setCancelable(false)
                            .setMessage("Carrinho limpado com sucesso.")
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();

                }
            });
        }
        else
        {
            dialogo.setNeutralButton("OK",null);
        }


        dialogo.show();
    }

}
