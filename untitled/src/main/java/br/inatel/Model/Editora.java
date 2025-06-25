package br.inatel.Model;

public class Editora {
    int ideditora;
    String nomeEditora;
    String telefoneEditora;
    String emailEditora;

    public Editora(int ideditora, String nomeEditora, String telefoneEditora, String emailEditora) {
        this.ideditora = ideditora;
        this.nomeEditora = nomeEditora;
        this.telefoneEditora = telefoneEditora;
        this.emailEditora = emailEditora;
    }

    public Editora(String nomeEditora, String telefoneEditora, String emailEditora) {
        this.nomeEditora = nomeEditora;
        this.telefoneEditora = telefoneEditora;
        this.emailEditora = emailEditora;
    }

    public int getIdeditora() {
        return ideditora;
    }

    public String getNomeEditora() {
        return nomeEditora;
    }

    public String getTelefoneEditora() {
        return telefoneEditora;
    }

    public String getEmailEditora() {
        return emailEditora;
    }
}
