
public class Eleitor {
    private int id;
    private String nome;
    private String titulo;

    public Eleitor(int id, String nome, String titulo) {
        this.id = id;
        this.nome = nome;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTitulo() {
        return titulo;
    }
}
