package banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.internal.db;

/**
 * Created by matheus.silva on 06/12/2017.
 */

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    public static final String TABELA = "avaliacao";
    public static final String ID = "id";
    public static final String USUARIOID = "usuarioId";
    public static final String USUARIONOME = "usuarioNome";
    public static final String DESCRICAO = "descricao";
    public static final String NOTA = "nota";
    public static final int VERSAO = 1;

    public CriaBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }


    @Override
    // é chamado quando a aplicação cria o banco de dados pela primeira vez.
    // Nesse método devem ter todas as diretrizes de criação e população inicial do banco.
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + USUARIOID + " integer,"
                + USUARIONOME + " text,"
                + DESCRICAO + " text,"
                + NOTA + " text"
                +")";
        db.execSQL(sql);
    }

    @Override
    //é o método responsável por atualizar o banco de dados com alguma informação estrutural que tenha sido alterada.
    // Ele sempre é chamado quando uma atualização é necessária,para não ter nenhum tipo de inconsistência de dados
    // entre o banco existente no aparelho e o novo que a aplicação irá utilizar.
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }
}
