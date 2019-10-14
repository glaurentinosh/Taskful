package com.example.laurentino.taskful;

public class Tarefa {
    private String nome;
    private String descricao;
    private boolean urgente;
    private String tag;

    public Tarefa(String nome, String descricao, boolean urgente, String tag){
        this.setNome(nome);
        this.setDescricao(descricao);
        this.setUrgente(urgente);
        this.setTag(tag);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isUrgente() {
        return urgente;
    }

    public void setUrgente(boolean urgente) {
        this.urgente = urgente;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String toString() {
        String str = "Título:\t\t\t\t\t\t\t\t\t\t" + this.nome + "\nCategoria:\t\t\t\t\t\t\t" + this.tag + "\n\nDescrição:\t" + this.descricao;
        if (urgente) {
            str = "\t\t\t\t\t\t\t\t~~~ URGENTE ~~~\n\n" + str;
        }
        return str;
    }
}
