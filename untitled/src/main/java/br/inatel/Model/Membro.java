package br.inatel.Model;

public class Membro {
    int idmembro;
    String nomeMembro;
    String enderecoMembro;
    String telefoneMembro;
    String emailMembro;
    String status;

    public Membro(int idmembro, String nomeMembro, String enderecoMembro, String telefoneMembro, String emailMembro, String status) {
        this.idmembro = idmembro;
        this.nomeMembro = nomeMembro;
        this.enderecoMembro = enderecoMembro;
        this.telefoneMembro = telefoneMembro;
        this.emailMembro = emailMembro;
        this.status = status;
    }

    public Membro(String nomeMembro, String enderecoMembro, String telefoneMembro, String emailMembro, String status) {
        this.nomeMembro = nomeMembro;
        this.enderecoMembro = enderecoMembro;
        this.telefoneMembro = telefoneMembro;
        this.emailMembro = emailMembro;
        this.status = status;
    }

    public int getIdmembro() {
        return idmembro;
    }

    public String getNomeMembro() {
        return nomeMembro;
    }

    public String getEnderecoMembro() {
        return enderecoMembro;
    }

    public String getTelefoneMembro() {
        return telefoneMembro;
    }

    public String getEmailMembro() {
        return emailMembro;
    }

    public String getStatus() {
        return status;
    }
}
