import java.util.*;

class EleicaoMajoritaria extends Eleicao {
    public EleicaoMajoritaria(int id, Date horarioLimite) {
        super(id, horarioLimite);
    }

    @Override
    public void gerarRelatorio() {
        System.out.println("Relatório da Eleição Majoritária ID: " + id);
        System.out.println("Total de Votos:");
        int totalVotosValidos = 0;
        int totalVotosNulos = 0;
        int totalVotosBrancos = 0;

        for (Voto voto : votos) {
            if (voto.getTipo().equals("Válido")) {
                totalVotosValidos++;
            } else if (voto.getTipo().equals("Nulo")) {
                totalVotosNulos++;
            } else if (voto.getTipo().equals("Branco")) {
                totalVotosBrancos++;
            }
        }

        System.out.println("Votos Válidos: " + totalVotosValidos);
        System.out.println("Votos Nulos: " + totalVotosNulos);
        System.out.println("Votos Brancos: " + totalVotosBrancos);

        Candidato vencedor = null;
        for (Candidato candidato : candidatos) {
            System.out.println("Candidato: " + candidato.getNome() + " - Votos: " + candidato.getVotosRecebidos());
            if (vencedor == null || candidato.getVotosRecebidos() > vencedor.getVotosRecebidos()) {
                vencedor = candidato;
            }
        }

        if (vencedor != null) {
            System.out.println("Vencedor: " + vencedor.getNome());
        }
    }
}
