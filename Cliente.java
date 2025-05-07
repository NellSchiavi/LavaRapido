package model;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String placa;

    public Cliente(String nome, String cpf, String telefone, String email, String placa) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.placa = placa;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public String getPlaca() { return placa; }

    @Override
    public String toString() {
        return nome + " (" + placa + ")";
    }
}
