
public class Voto {
    private int idEleitor;
    private int idCandidato;
    private String tipo; // "Válido", "Nulo", "Branco"

    public Voto(int idEleitor, int idCandidato, String tipo) {
        this.idEleitor = idEleitor;
        this.idCandidato = idCandidato;
        this.tipo = tipo;
    }

    public int getIdEleitor() {
        return idEleitor;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public String getTipo() {
        return tipo;
    }
}
