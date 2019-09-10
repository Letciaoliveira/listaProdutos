package com.example.aula3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ProdutoBD extends SQLiteOpenHelper {

    //DEFINE O NOME E A VERSAO DO BD
    private static String name = "lista";
    private static int version = 3;


    public ProdutoBD(Context context) {
        super(context, name, null, version);
    }

    //CRIAR O BD
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE PRODUTO " +
                "(CODIGO INTEGER NOT NULL, NOME TEXT, DESCRICAO TEXT, " +
                "PRIMARY KEY(CODIGO));";
        db.execSQL(sql);
    }

    //INVOCADO AO ATUALIZAR O BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS PRODUTO";
        db.execSQL(sql);
        onCreate(db);
    }

    //SALVAR UM NOVO PRODUTO
    public void salvarProduto(Produto p) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = obterDados(p);
        db.insert("produto", null, dados);
    }

    //OBTER DADOS DO OBJ PRODUTO
    private ContentValues obterDados(Produto p){
        ContentValues dados = new ContentValues();
        dados.put("CODIGO", p.getCod());
        dados.put("NOME", p.getNome());
        dados.put("DESCRICAO", p.getDesc());

        return  dados;
    }

    //RECUPERAR TODOS OS PRODUTOS SALVOS
    public List<Produto> listProdutos() {

        String sql = "SELECT * FROM PRODUTO;";
        List<Produto> list = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int codigo = cursor.getInt(cursor.getColumnIndex("CODIGO"));
            String nome = cursor.getString(cursor.getColumnIndex("NOME"));
            String descricao = cursor.getString(cursor.getColumnIndex("DESCRICAO"));

            list.add(new Produto(codigo, nome, descricao));
        }

        //Encerrar e liberar o cursor
        cursor.close();
        db.close();

        return list;
    }

    //EDITAR PRODUTO
    public void atualizarProduto(Produto p){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = obterDados(p);

        String[] params = {String.valueOf(p.getCod())};
        db.update("produto", dados, "CODIGO=?", params);

        db.close();
    }

    public void apagarProduto (Produto p){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = obterDados(p);

        String[] params = {String.valueOf(p.getCod())};
       db.delete("produto", "CODIGO=?", params);

        db.close();
    }




}
