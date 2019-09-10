package com.example.aula3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormularioActivity extends AppCompatActivity {

    private EditText editTextCod;
    private EditText editTextNome;
    private EditText editTextDesc;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        editTextCod = findViewById(R.id.edittext_cod);
        editTextNome = findViewById(R.id.edittext_nome);
        editTextDesc = findViewById(R.id.edittext_desc);

        Intent intent = getIntent();
        produto = (Produto) intent.getSerializableExtra("chave");

        if(produto != null){

            //RECUPERA O TEXTO
            editTextCod.setText(String.valueOf(produto.getCod()));
            editTextNome.setText(produto.getNome());
            editTextDesc.setText(produto.getDesc());


            Button button = findViewById(R.id.button_form);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //RECUPERA O TEXTO
                    int cod = Integer.parseInt(editTextCod.getText().toString());
                    String nome = editTextNome.getText().toString();
                    String desc = editTextDesc.getText().toString();

                    //CRIA O OBJETO PRODUTO
                    Produto p = new Produto(cod, nome, desc);

                    //SALVA O PRODUTO NO BD
                    ProdutoBD db = new ProdutoBD(FormularioActivity.this);
                    db.atualizarProduto(p);

                    finish();
                }
            });


        }

        else{
            //RECUPERA O BOTAO E ADICIONA ACAO AO CLICK
            Button button = findViewById(R.id.button_form);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //RECUPERA O TEXTO
                    int cod = Integer.parseInt(editTextCod.getText().toString());
                    String nome = editTextNome.getText().toString();
                    String desc = editTextDesc.getText().toString();

                    //CRIA O OBJETO PRODUTO
                    Produto p = new Produto(cod,nome,desc);

                    //SALVA O PRODUTO NO BD
                    ProdutoBD db = new ProdutoBD(FormularioActivity.this);
                    db.salvarProduto(p);

                    finish();
                }
            });
        }



    }




}






