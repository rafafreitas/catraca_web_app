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
    private static final String TABELA = "avaliacao";
    private static final String ID = "_id";
    private static final String AUTOR = "autor";
    private static final String DESCRICAO = "descricao";
    private static final String NOTA = "nota";
    private static final int VERSAO = 1;

    public CriaBanco(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    // é chamado quando a aplicação cria o banco de dados pela primeira vez.
    // Nesse método devem ter todas as diretrizes de criação e população inicial do banco.
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE"+TABELA+"("
                + ID + " integer primary key autoincrement,"
                + AUTOR + " text,"
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
