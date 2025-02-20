import java.util.*;
class EleicaoProporcional extends Eleicao {
  private static final int TOTAL_VAGAS_DEPUTADO_FEDERAL = 10;
  private static final int TOTAL_VAGAS_DEPUTADO_ESTADUAL = 30;

  public EleicaoProporcional(int id, Date horarioLimite) {
      super(id, horarioLimite);
  }

  @Override
  public void gerarRelatorio() {
      System.out.println("Relatório da Eleição Proporcional ID: " + id);
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

      // Cálculo de vagas proporcionais
      for (Candidato candidato : candidatos) {
          System.out.println("Candidato: " + candidato.getNome() + " - Votos: " + candidato.getVotosRecebidos());
          // Lógica para calcular a distribuição de vagas pode ser implementada aqui
      }
  }
}
