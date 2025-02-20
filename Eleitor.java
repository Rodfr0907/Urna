public class Eleitor {
    private int id;
    private String nome;
    private String tituloEleitor;

    public Eleitor(int id, String nome, String tituloEleitor) {
        this.id = id;
        this.nome = nome;
        this.tituloEleitor = tituloEleitor;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTituloEleitor() {
        return tituloEleitor;
    }
}