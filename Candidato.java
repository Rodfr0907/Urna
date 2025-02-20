import java.util.*;

public class Candidato {
  private int id;
  private String nome;
  private String partido;
  private int votosRecebidos;

  public Candidato(int id, String nome, String partido) {
      this.id = id;
      this.nome = nome;
      this.partido = partido;
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

  public void receberVoto() {
      this.votosRecebidos++;
  }

  public int getVotosRecebidos() {
      return votosRecebidos;
  }
}