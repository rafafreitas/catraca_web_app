package banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.rafael.catraca_web_app.AvaliacaoActivity;

import java.util.ArrayList;
import java.util.List;

import basic.Avaliacao;
import basic.Usuario;

/**
 * Created by matheus.silva on 06/12/2017.
 */

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

    @NonNull
    private ContentValues pegaDadosDoUsuario(Avaliacao avaliacao) {
        ContentValues cv = new ContentValues();
        cv.put("USUARIOID", avaliacao.getUsuarioId());
        cv.put("USUARIONOME", avaliacao.getUsuarioNome());
        cv.put("DESCRICAO", avaliacao.getDescricao());
        cv.put("NOTA", avaliacao.getNota());
        return cv;
    }

    public String inserirDados(Avaliacao avaliacao) {

        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues cv = pegaDadosDoUsuario(avaliacao);
        long id = db.insert("avaliacao", null, cv);
        db.close();
        return Long.toString(id);
    }

    public void atualizar(Avaliacao avaliacao) {
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues cv = pegaDadosDoUsuario(avaliacao);
        String [] params = new String[]{ String.valueOf(avaliacao.getId())};
        db.update("avaliacao",cv, "ID = ?",params);
        db.close();
    }
    public void salvar(Avaliacao avaliacao) {
        if (avaliacao.getId() == 0) {
            inserirDados(avaliacao);
        } else {
            atualizar(avaliacao);
        }
    }
    public int excluir(Avaliacao avaliacao) {
        SQLiteDatabase db = banco.getWritableDatabase();
        int linhasAfetadas = db.delete(
                "avaliacao",
                "ID = ?",
                new String[]{ String.valueOf(avaliacao.getId())});
        db.close();
        return linhasAfetadas;
    }

    public List<Avaliacao> buscarAvaliacao(String filtro) {
        SQLiteDatabase db = banco.getReadableDatabase();
        String sql = "SELECT * FROM avaliacao ";
        String[] argumentos = null;
        if (filtro != null) {
            sql += " WHERE USUARIOID = ?";
            argumentos = new String[]{ filtro };
        }
        sql += " ORDER BY USUARIONOME ASC";
        Cursor cursor = db.rawQuery(sql, argumentos);
        List<Avaliacao> avaliacoes= new ArrayList<Avaliacao>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndex("ID"));
            int usuarioId = cursor.getInt(
                    cursor.getColumnIndex("USUARIOID"));
            String usuarioNome = cursor.getString(
                    cursor.getColumnIndex("USUARIONOME"));
            String descricao = cursor.getString(
                    cursor.getColumnIndex("DESCRICAO"));
            String nota = cursor.getString(
                    cursor.getColumnIndex("NOTA"));
            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setId(id);
            avaliacao.setUsuarioId(usuarioId);
            avaliacao.setUsuarioNome(usuarioNome);
            avaliacao.setDescricao(descricao);
            avaliacao.setNota(nota);

            avaliacoes.add(avaliacao);
        }
        cursor.close();
        db.close();
        return avaliacoes;
    }
}