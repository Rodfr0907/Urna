import java.util.*;
import java.util.Scanner;

public class Main {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EleicaoMajoritaria eleicaoMajoritaria = new EleicaoMajoritaria(1, new Date());
        EleicaoProporcional eleicaoProporcional = new EleicaoProporcional(2, new Date());
        List<Eleitor> eleitores = new ArrayList<>();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Cadastrar Candidato");
            System.out.println("2. Cadastrar Eleitor");
            System.out.println("3. Registrar Voto");
            System.out.println("4. Gerar Relatório");
            System.out.println("5. Sair");
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
                    eleicaoMajoritaria.cadastrarCandidato(new Candidato(idCandidato, nomeCandidato, partidoCandidato));
                    eleicaoProporcional.cadastrarCandidato(new Candidato(idCandidato, nomeCandidato, partidoCandidato));
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
                    String tipoVoto = scanner.nextLine();
                    Voto voto = new Voto(idEleitor, idCandidatoVoto, tipoVoto);
                    eleicaoMajoritaria.registrarVoto(voto);
                    eleicaoProporcional.registrarVoto(voto);
                    clearScreen();
                    break;

                case 4:
                    eleicaoMajoritaria.gerarRelatorio();
                    eleicaoProporcional.gerarRelatorio();
                    System.out.println("\nPressione ENTER para continuar...");
                    scanner.nextLine();
                    clearScreen();
                    break;

                case 5:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}