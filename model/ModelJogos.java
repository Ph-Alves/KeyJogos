package model;

import java.util.UUID;

public class ModelJogos {
	private String nome;
    private double preco;
    private String imagemCaminho;

    public ModelJogos(String nome, double preco, String imagemCaminho) {
        this.nome = nome;
        this.preco = preco;
        this.imagemCaminho = imagemCaminho;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getImagemCaminho() {
        return imagemCaminho;
    }

    public String gerarKey() {
        return UUID.randomUUID().toString().toUpperCase();
    }
}
