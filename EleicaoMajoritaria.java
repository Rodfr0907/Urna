
import java.util.*;

class EleicaoMajoritaria extends Eleicao {
    public EleicaoMajoritaria(int id, Date horarioLimite) {
        super(id, horarioLimite);
    }

    public void gerarRelatorioFinal() {
        gerarRelatorio();
        mostrarVencedores();
    }

    private Map<String, List<Candidato>> candidatosPorCargo = new HashMap<>();

    @Override
    public void gerarRelatorio() {
        System.out.println("\nRelatório da Eleição Majoritária ID: " + id);
        System.out.println("Data/Hora: " + new Date());
        
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

        System.out.println("\nEstatísticas gerais:");
        System.out.println("Total de Votos Válidos: " + totalVotosValidos);
        System.out.println("Total de Votos Nulos: " + totalVotosNulos);
        System.out.println("Total de Votos Brancos: " + totalVotosBrancos);
        System.out.println("Total de Votantes: " + votos.size());

        System.out.println("\nResultados por cargo:");
        Map<String, List<Candidato>> candidatosPorCargo = new HashMap<>();
        
        // Agrupa candidatos por cargo
        for (Candidato candidato : candidatos) {
            candidatosPorCargo.computeIfAbsent(candidato.getCargo(), k -> new ArrayList<>()).add(candidato);
        }
        
        // Mostra resultados para cada cargo
        for (Map.Entry<String, List<Candidato>> entry : candidatosPorCargo.entrySet()) {
            System.out.println("\n=== " + entry.getKey() + " ===");
            Candidato vencedor = null;
            for (Candidato candidato : entry.getValue()) {
                System.out.println("Candidato: " + candidato.getNome() + 
                                 " (Partido: " + candidato.getPartido() + ") - " +
                                 "Votos: " + candidato.getVotosRecebidos());
                if (vencedor == null || candidato.getVotosRecebidos() > vencedor.getVotosRecebidos()) {
                    vencedor = candidato;
                }
            }

            }
    }

    private void mostrarVencedores() {
        for (Map.Entry<String, List<Candidato>> entry : candidatosPorCargo.entrySet()) {
            Candidato vencedor = null;
            for (Candidato candidato : entry.getValue()) {
                if (vencedor == null || candidato.getVotosRecebidos() > vencedor.getVotosRecebidos()) {
                    vencedor = candidato;
                }
            }
            
            if (vencedor != null && vencedor.getVotosRecebidos() > 0) {
                System.out.println("\nVencedor " + entry.getKey() + ": " + vencedor.getNome() + 
                                 " (Partido: " + vencedor.getPartido() + ") - " +
                                 "Votos: " + vencedor.getVotosRecebidos());
            }
        }
    }
}
