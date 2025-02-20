import java.util.*;

abstract class Eleicao {
    protected int id;
    protected Date horarioLimite;
    protected List<Candidato> candidatos;
    protected List<Voto> votos;

    public Eleicao(int id, Date horarioLimite) {
        this.id = id;
        this.horarioLimite = horarioLimite;
        this.candidatos = new ArrayList<>();
        this.votos = new ArrayList<>();
    }

    public void cadastrarCandidato(Candidato candidato) {
        candidatos.add(candidato);
    }

    public void registrarVoto(Voto voto) {
        votos.add(voto);
        if (voto.getTipo().equals("Válido")) {
            for (Candidato candidato : candidatos) {
                if (candidato.getId() == voto.getIdCandidato()) {
                    candidato.receberVoto();
                }
            }
        }
    }

    public abstract void gerarRelatorio();
    
    public List<Candidato> getCandidatos() {
        return candidatos;
    }
}
