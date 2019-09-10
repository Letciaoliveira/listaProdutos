package com.example.aula3;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    //CRIA O ADAPTER PARA RECEBER OS DADOS DO ARRAYLIST (INSERCAO DO USUARIO) E COLOCAR NO LISTVIEW
    //CRIA-SE FORA DO ON CREATE, PARA QUE SE POSSA ACESSAR EM OUTROS METODOS DA VIDA DA ACTIVITY, MAS NAO TEM INICIALIZACAO FORA DESSES METODOS
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CRIA O ADAPTER QUE SERA UTILIZADO
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);

        //RECUPERA A LISTVIEW E ADD O ADAPTER
        ListView listView = findViewById(R.id.listview_mainativity);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        //CONFIGURA A ACAO DO BOTAO
        FloatingActionButton fbutton = findViewById(R.id.floating_button);
        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        com.example.aula3.FormularioActivity.class);
                startActivity(intent);
            }
        });

        //RECUPERAR ITEM DA LISTA PARA ATUALIZAR

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produto = new ProdutoBD(getBaseContext()).listProdutos().get(position);

                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("chave", produto);

                startActivity(intent);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        //RECUPERA A LISTA DE PRODUTOS DO BD
        List<Produto> lista = new ProdutoBD(this).listProdutos();

        //LIMPAR A LISTA E ADCIONAR OS OBJETOS NOVAMENTE
        adapter.clear();
        adapter.addAll(lista);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menu = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Produto p = (Produto) adapter.getItem((menu.position));

        if (R.id.editar_menu == item.getItemId()){
            Intent i = new Intent(MainActivity.this, FormularioActivity.class);
            i.putExtra("chave", p);
            startActivity(i);

        }
        if (R.id.apagar_menu == item.getItemId()){
            ProdutoBD bd = new ProdutoBD(this);
            bd.apagarProduto(p);
            onResume();

        }

        return super.onContextItemSelected(item);
    }
}
