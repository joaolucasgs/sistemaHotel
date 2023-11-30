package br.inatel.cdg;

public class Hotel{

    public int idHotel;
    private String nome;
    private int qntQuartos;

    Quarto q = new Quarto();



    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQntQuartos() {
        return qntQuartos;
    }

    public void setQntQuartos(int qntQuartos) {
        this.qntQuartos = qntQuartos;
    }
}
