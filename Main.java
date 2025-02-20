import java.util.*;
import java.util.Scanner;
import java.util.Calendar;

public class Main {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 8); 
        Date horarioLimite = calendar.getTime();
        
        EleicaoMajoritaria eleicaoMajoritaria = new EleicaoMajoritaria(1, horarioLimite);
        EleicaoProporcional eleicaoProporcional = new EleicaoProporcional(2, horarioLimite);
        List<Eleitor> eleitores = new ArrayList<>();

        
        Candidato[] candidatosPadrao = {
            
            new Candidato(1, "Yuri Alberto", "Partido: Corinthians", "Presidente"),
            new Candidato(2, "Rodrigo Garro", "Partido: Corinthians 2", "Presidente"),
            
            new Candidato(3, "Pedro Oliveira", "Partido: PSDB", "Governador"),
            new Candidato(4, "Ana Costa", "Partido: PCD", "Governador"),
            
            new Candidato(5, "Carlos Souza", "Loas", "Prefeito"),
            new Candidato(6, "Julia Lima", "BPC", "Prefeito"),
            
            new Candidato(7, "Roberto Martins", "PSI", "Senador"),
            new Candidato(8, "Patricia Gomes", "Bar Sem Lona", "Senador"),
            
            new Candidato(9, "Fernando Costa", "Tô sem ideia", "Deputado Federal"),
            new Candidato(10, "Mariana Silva", "Insira aqui um nome para partido", "Deputado Federal")
        };

        for (Candidato c : candidatosPadrao) {
            eleicaoMajoritaria.cadastrarCandidato(c);
            eleicaoProporcional.cadastrarCandidato(c);
        }

        Date currentTime = new Date();
        boolean allVotersVoted = false;
        boolean presidentEndedElection = false;
        
        while (!presidentEndedElection && currentTime.before(eleicaoMajoritaria.getHorarioLimite()) && !allVotersVoted) {
            currentTime = new Date();
            System.out.println("Menu:");
            System.out.println("1. Cadastrar Candidato");
            System.out.println("2. Cadastrar Eleitor");
            System.out.println("3. Registrar Voto");
            System.out.println("4. Gerar Relatório");
            System.out.println("5. Finalizar Eleição");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do Candidato: ");
                    int idCandidato = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Digite o Nome do Candidato: ");
                    String nomeCandidato = scanner.nextLine();
                    System.out.print("Digite o Partido do Candidato: ");
                    String partidoCandidato = scanner.nextLine();
                    System.out.print("Digite o Cargo do Candidato: ");
                    String cargoCandidato = scanner.nextLine();
                    eleicaoMajoritaria.cadastrarCandidato(new Candidato(idCandidato, nomeCandidato, partidoCandidato, cargoCandidato));
                    eleicaoProporcional.cadastrarCandidato(new Candidato(idCandidato, nomeCandidato, partidoCandidato, cargoCandidato));
                    clearScreen();
                    break;

                case 2:
                    System.out.print("Digite o ID do Eleitor: ");
                    int idEleitorCadastro = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Digite o Nome do Eleitor: ");
                    String nomeEleitor = scanner.nextLine();
                    System.out.print("Digite o Título de Eleitor: ");
                    String tituloEleitor = scanner.nextLine();
                    eleitores.add(new Eleitor(idEleitorCadastro, nomeEleitor, tituloEleitor));
                    clearScreen();
                    break;

                case 3:
                    System.out.print("Digite o ID do Eleitor: ");
                    int idEleitor = scanner.nextInt();
                    
                    
                    boolean eleitorEncontrado = false;
                    for (Eleitor e : eleitores) {
                        if (e.getId() == idEleitor) {
                            eleitorEncontrado = true;
                            break;
                        }
                    }
                    
                    if (!eleitorEncontrado) {
                        System.out.println("Erro: ID do eleitor não cadastrado!");
                        scanner.nextLine();
                        System.out.println("\nPressione ENTER para continuar...");
                        scanner.nextLine();
                        clearScreen();
                        continue;
                    }

                    System.out.print("Digite o ID do Candidato: ");
                    int idCandidatoVoto = scanner.nextInt();
                    
                    
                    boolean candidatoEncontrado = false;
                    for (Candidato c : eleicaoMajoritaria.getCandidatos()) {
                        if (c.getId() == idCandidatoVoto) {
                            candidatoEncontrado = true;
                            break;
                        }
                    }
                    
                    if (!candidatoEncontrado) {
                        System.out.println("Erro: ID do candidato não cadastrado!");
                        scanner.nextLine();
                        System.out.println("\nPressione ENTER para continuar...");
                        scanner.nextLine();
                        clearScreen();
                        continue;
                    }

                    System.out.print("Digite o tipo de voto (Válido, Nulo, Branco): ");
                    scanner.nextLine(); 
                    String tipoVoto = scanner.nextLine().trim();
                    // Normaliza o tipo de voto
                    if (tipoVoto.equalsIgnoreCase("valido") || tipoVoto.equalsIgnoreCase("válido")) {
                        tipoVoto = "Válido";
                    } else if (tipoVoto.equalsIgnoreCase("nulo")) {
                        tipoVoto = "Nulo";
                    } else if (tipoVoto.equalsIgnoreCase("branco")) {
                        tipoVoto = "Branco";
                    }
                    Voto voto = new Voto(idEleitor, idCandidatoVoto, tipoVoto);
                    
                    // Encontra o candidato para determinar o tipo de eleição
                    String cargoVotado = "";
                    for (Candidato c : eleicaoMajoritaria.getCandidatos()) {
                        if (c.getId() == idCandidatoVoto) {
                            cargoVotado = c.getCargo();
                            break;
                        }
                    }
                    
                    // Registra o voto na eleição apropriada
                    if (cargoVotado.equals("Deputado Federal") || cargoVotado.equals("Deputado Estadual")) {
                        eleicaoProporcional.registrarVoto(voto);
                    } else {
                        eleicaoMajoritaria.registrarVoto(voto);
                    }
                    clearScreen();
                    break;

                case 4:
                    clearScreen();
                    eleicaoMajoritaria.gerarRelatorio();
                    eleicaoProporcional.gerarRelatorio();
                    scanner.nextLine(); // Consumir nova linha pendente
                    System.out.println("\nPressione ENTER para voltar ao menu...");
                    scanner.nextLine(); // Aguardar ENTER do usuário
                    clearScreen();
                    break;

                case 5:
                    presidentEndedElection = true;
                    System.out.println("\nEleição finalizada para todos os cargos!");
                    System.out.println("\n=== RESULTADO FINAL ===");
                    eleicaoMajoritaria.gerarRelatorioFinal();
                    eleicaoProporcional.gerarRelatorioFinal();
                    scanner.nextLine(); // Consumir nova linha pendente
                    System.out.println("\nPressione ENTER para voltar ao início...");
                    scanner.nextLine();
                    clearScreen();
                    presidentEndedElection = false;
                    break;
                    
                case 6:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}