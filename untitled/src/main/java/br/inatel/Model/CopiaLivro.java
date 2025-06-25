package br.inatel.Model;

public class CopiaLivro {
    int idcopiaLivro;
    int Livro_idLivro;
    String editoraCopia;
    int edicaoCopia;
    int editora_ideditora;
    String CopiaDisponivel;

    public CopiaLivro(int idcopiaLivro, int livro_idLivro, String editoraCopia, int edicaoCopia, int editora_ideditora, String copiaDisponivel) {
        this.idcopiaLivro = idcopiaLivro;
        Livro_idLivro = livro_idLivro;
        this.editoraCopia = editoraCopia;
        this.edicaoCopia = edicaoCopia;
        this.editora_ideditora = editora_ideditora;
        CopiaDisponivel = copiaDisponivel;
    }

    public CopiaLivro(int livro_idLivro, String editoraCopia, int edicaoCopia, int editora_ideditora, String copiaDisponivel) {
        Livro_idLivro = livro_idLivro;
        this.editoraCopia = editoraCopia;
        this.edicaoCopia = edicaoCopia;
        this.editora_ideditora = editora_ideditora;
        CopiaDisponivel = copiaDisponivel;
    }

    public int getIdcopiaLivro() {
        return idcopiaLivro;
    }

    public int getLivro_idLivro() {
        return Livro_idLivro;
    }

    public String getEditoraCopia() {
        return editoraCopia;
    }

    public int getEdicaoCopia() {
        return edicaoCopia;
    }

    public int getEditora_ideditora() {
        return editora_ideditora;
    }

    public String getCopiaDisponivel() {
        return CopiaDisponivel;
    }
}
