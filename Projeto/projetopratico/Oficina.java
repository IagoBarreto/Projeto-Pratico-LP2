package projetopratico;

public class Oficina {

    //Atributos
    private String nome;
    private int inscritos;
    private boolean cheia;
    private int tamanho;

    //Construtor
    public Oficina(String nome, int tamanho) {
        this.nome = nome;
        this.inscritos = 0;
        this.cheia = false;
        this.tamanho = tamanho;
    }

    //Metodos especiais
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getInscritos() {
        return inscritos;
    }

    public void setInscritos(int inscritos) {
        this.inscritos = inscritos;
    }

    public boolean isCheia() {
        this.setCheia(this.inscritos == this.tamanho);
        return cheia;
    }

    private void setCheia(boolean cheia) {
        this.cheia = cheia;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
    
    public int getVagas() {
        return this.tamanho - this.inscritos;
    }
    

}
