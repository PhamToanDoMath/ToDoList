package com.hcmut.challenge;

public class ToDo {
    String titledoes;
    String datedoes;
    String desdoes;
    String keydoes;

    public ToDo() {
    }

    public ToDo(String titledoes, String datedoes, String desdoes, String keydoes) {
        this.titledoes = titledoes;
        this.datedoes = datedoes;
        this.desdoes = desdoes;
        this.keydoes = keydoes;
    }

    public String getKeydoes() {
        return keydoes;
    }

    public void setKeydoes(String keydoes) {
        this.keydoes = keydoes;
    }
    public String getTitledoes() {
        return titledoes;
    }

    public void setTitledoes(String titledoes) {
        this.titledoes = titledoes;
    }

    public String getDesdoes() {
        return desdoes;
    }

    public void setDesdoes(String desdoes) {
        this.desdoes = desdoes;
    }

    public String getDatedoes() {
        return datedoes;
    }

    public void setDatedoes(String datedoes) {
        this.datedoes = datedoes;
    }
}
