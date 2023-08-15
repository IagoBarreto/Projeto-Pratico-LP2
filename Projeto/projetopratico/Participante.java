package projetopratico;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Participante {

    //Atributos
    private String nome;
    private String cpf;
    private String sexo;
    private LocalDate nascimento;
    private String faixaEtaria;
    private ArrayList<Oficina> oficinasCadastrada;

    public Participante(String nome, String cpf, String sexo, LocalDate nascimento) {
        this.nome = nome;
        this.oficinasCadastrada = new ArrayList();
        this.cpf = cpf;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.setFaixaEtaria();
    }

    //Metodos especiais
    public ArrayList<Oficina> getOficinasCadastradas() {
        return oficinasCadastrada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    private void setFaixaEtaria() {
        int idade = (Period.between(this.nascimento, LocalDate.now())).getYears();
        if (idade < 18) {
            this.faixaEtaria = "Menor de idade";
        } else {
            this.faixaEtaria = "Maior de idade";
        }
    }

    public boolean inscreverOficina(Oficina o) {
        if (!o.isCheia()) {
            oficinasCadastrada.add(o);
            o.setInscritos(o.getInscritos() + 1);
            return true;
        } else {
            return false;
        }

    }

    public boolean oficinaJaCadastrada(Oficina o) {
        return this.getOficinasCadastradas().contains(o);
    }

}
