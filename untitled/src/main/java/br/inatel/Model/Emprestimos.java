package br.inatel.Model;

public class Emprestimos {
    int idemprestimos;
    String dataRetirada;
    String dataDevolucao;
    int membro_idmembro;
    int copiaLivro_idcopiaLivro;

    public Emprestimos(int idemprestimos, String dataRetirada, String dataDevolucao, int membro_idmembro, int copiaLivro_idcopiaLivro) {
        this.idemprestimos = idemprestimos;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.membro_idmembro = membro_idmembro;
        this.copiaLivro_idcopiaLivro = copiaLivro_idcopiaLivro;
    }

    public Emprestimos(String dataRetirada, String dataDevolucao, int membro_idmembro, int copiaLivro_idcopiaLivro) {
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.membro_idmembro = membro_idmembro;
        this.copiaLivro_idcopiaLivro = copiaLivro_idcopiaLivro;
    }

    public int getIdemprestimos() {
        return idemprestimos;
    }

    public String getDataRetirada() {
        return dataRetirada;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public int getMembro_idmembro() {
        return membro_idmembro;
    }

    public int getCopiaLivro_idcopiaLivro() {
        return copiaLivro_idcopiaLivro;
    }
}
