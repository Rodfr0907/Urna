import java.util.*;
class EleicaoProporcional extends Eleicao {
    private static final int TOTAL_VAGAS_DEPUTADO_FEDERAL = 10;
    private static final int TOTAL_VAGAS_DEPUTADO_ESTADUAL = 30;

    public EleicaoProporcional(int id, Date horarioLimite) {
        super(id, horarioLimite);
    }

    public void gerarRelatorioFinal() {
        gerarRelatorio();
    }

    @Override
    public void gerarRelatorio() {
        if (votos.isEmpty()) {
            return;
        }
        
        System.out.println("\nRelatório da Eleição Proporcional:");
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

        if (totalVotosValidos > 0 || totalVotosNulos > 0 || totalVotosBrancos > 0) {
            System.out.println("\nEstatísticas gerais:");
            System.out.println("Total de Votos Válidos: " + totalVotosValidos);
            System.out.println("Total de Votos Nulos: " + totalVotosNulos);
            System.out.println("Total de Votos Brancos: " + totalVotosBrancos);
            
            System.out.println("\nResultados por candidato:");
            for (Candidato candidato : candidatos) {
                if ((candidato.getCargo().equals("Deputado Federal") || 
                     candidato.getCargo().equals("Deputado Estadual")) &&
                    candidato.getVotosRecebidos() > 0) {
                    System.out.println(candidato.getNome() + " (" + candidato.getPartido() + 
                                     ") - Votos: " + candidato.getVotosRecebidos());
                }
            }
        }
    }
}