package br.inatel.cdg;

import checkInOut.FazCheckIn;
import checkInOut.FazCheckOut;

import java.sql.SQLOutput;

public class Hospede extends Pessoa implements FazCheckIn, FazCheckOut {
    public Hospede(String nome, String cpf,String ocupacao, int idade) {
        super(nome, cpf,ocupacao, idade);
    }

    public Hospede(){

    }
    @Override
    public void fazCheckIn(){
        System.out.println("Check-in feito no hotel!");
    }

    @Override
    public void fazCheckOut() {
        System.out.println("Check-out feito no hotel!");
    }
}
