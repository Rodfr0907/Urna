
import java.util.*;

public class Candidato {
    private int id;
    private String nome;
    private String partido;
    private String cargo;
    private int votosRecebidos;

    public Candidato(int id, String nome, String partido, String cargo) {
        this.id = id;
        this.nome = nome;
        this.partido = partido;
        this.cargo = cargo;
        this.votosRecebidos = 0;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getPartido() {
        return partido;
    }

    public String getCargo() {
        return cargo;
    }

    public void receberVoto() {
        this.votosRecebidos++;
    }

    public int getVotosRecebidos() {
        return votosRecebidos;
    }
}
