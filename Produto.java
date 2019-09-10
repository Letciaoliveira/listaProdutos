package com.example.aula3;

import java.io.Serializable;

public class Produto implements Serializable {

    private int cod;
    private String nome;
    private String desc;

    public Produto(int cod, String nome, String desc) {
        this.cod = cod;
        this.nome = nome;
        this.desc = desc;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
