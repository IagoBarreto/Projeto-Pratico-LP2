package projetopratico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ProjetoPratico {

    public static void main(String[] args) {

        //Criação da lista das oficinas existentes
        OficinasPossiveis oficinasExistentes = new OficinasPossiveis();

        //Instaciação da lista de participantes
        ListaInscritos listaInscritos = new ListaInscritos();

        //Instanciação de Scanner para entrada de dados pelo usuario
        Scanner teclado = new Scanner(System.in);

        //Declaração de variavel para ser utlizado no switch
        int opc;

        //Declaração de variavel para saber posição em que participante ira ser cadastrado
        int numParticipante = 0;

        //Laço de repetição para programa só ser encerrado caso usuario deseje
        do {
            
            

            //Apresentação de Menu
            System.out.println("------------------- Inscrições -------------------");
            System.out.println("[1] Inscrever novo participante");
            System.out.println("[2] Consultar quantidade de vagas das oficinas");
            System.out.println("[3] Consultar nomes de menores de idade inscritos em uma oficina");
            System.out.println("[4] Consultar inscrição a partir de CPF");
            System.out.println("[5] Consultar estatísticas das inscrições");
            System.out.println("[6] Encerrar o programa");
            System.out.println("---------------------------------------------------");
            System.out.print("Escolha uma das opções: ");

            //Leitura da entrada do usuario para escolha do menu
            opc = teclado.nextInt();
            teclado.nextLine();
            System.out.println();

            switch (opc) {

                //Inscrever novo participante
                case 1:

                    //Declaração de variaveis para receber os atributos
                    String nome;
                    String cpf;
                    String sexo;
                    int dia;
                    int mes;
                    int ano;
                    int finalizado = 0;

                    //Loop de repetição para entrada 
                    do {
                        System.out.println("Informe os dados do participante");

                        System.out.print("Nome: ");

                        nome = teclado.nextLine();

                        System.out.print("CPF: ");

                        cpf = teclado.next();

                        System.out.print("Sexo (Masculino/Feminino): ");

                        sexo = teclado.next();

                        System.out.print("Dia de nascimento(1-31): ");

                        dia = teclado.nextInt();

                        System.out.print("Mês de nascimento: ");

                        mes = teclado.nextInt();

                        System.out.print("Ano de nascimento: ");

                        ano = teclado.nextInt();

                        if (listaInscritos.isCadastrado(cpf)) {
                            System.out.println("Participante com este CPF já cadastrado");
                        } else {

                            LocalDate nascimento = LocalDate.of(ano, mes, dia);
                            listaInscritos.adicionar(new Participante(nome, cpf, sexo, nascimento));
                            String selecionarNovamente;

                            int vezesInscrito = 0;
                            int opcOficina;

                            do {

                                System.out.println("\nEscolha uma oficina em que deseja inscrever o participante");
                                
                                ArrayList<String> combo = new ArrayList<>();

                                for (int i = 1; i <= oficinasExistentes.getNumerosOficinas(); i++) {
                                    combo.add("[" + i + "] " + oficinasExistentes.getOficina((i - 1)).getNome());
                                }
                                
                                System.out.println(combo);

                                System.out.print("Escolher opção: ");

                                opcOficina = teclado.nextInt();

                                if ((opcOficina - 1) <= oficinasExistentes.getNumerosOficinas()) {
                                    if (!listaInscritos.getParticipante(numParticipante).oficinaJaCadastrada(oficinasExistentes.getOficina((opcOficina - 1)))) {
                                        if (!listaInscritos.getParticipante(numParticipante).inscreverOficina(oficinasExistentes.getOficina((opcOficina - 1)))) {
                                            System.out.println("Oficina já esta cheia");
                                            vezesInscrito--;
                                        } else {

                                        }
                                    } else {
                                        System.out.println("Usuário ja cadastrado nessa oficina");
                                        vezesInscrito--;
                                    }

                                } else {
                                    System.out.println("Escolha um opção valida");
                                    vezesInscrito--;
                                }

                                if (vezesInscrito < 2 && vezesInscrito >= 0) {
                                    System.out.println("Desejar inscrever o usuário em outra oficina? (s/n)");
                                    selecionarNovamente = teclado.next();
                                } else if (vezesInscrito < 0) {
                                    selecionarNovamente = "s";
                                } else {
                                    selecionarNovamente = "n";

                                }

                                vezesInscrito++;

                            } while (selecionarNovamente.equalsIgnoreCase("s"));

                            System.out.println("\nO Participante foi cadastrado com sucesso!\n");

                        }

                        finalizado = 1;

                    } while (finalizado != 1);
                    numParticipante++;

                    break;

                //Consultar quantidade de vagas das oficinas    
                case 2:
                    System.out.println("-------------- Vagas disponiveis em cada oficina --------------");
                    for (int i = 0; i < oficinasExistentes.getNumerosOficinas(); i++) {
                        System.out.println(" A oficina " + oficinasExistentes.getOficina(i).getNome()
                                + " contém " + oficinasExistentes.getOficina(i).getVagas() + " vagas");
                    }
                    System.out.println("---------------------------------------------------------------\n");

                    break;

                //Consultar nome de menores de idade inscritos em uma oficina    
                case 3:

                    System.out.println("-------------- Escolha uma das oficinas --------------");

                    for (int i = 1; i <= oficinasExistentes.getNumerosOficinas(); i++) {
                        System.out.println("[" + i + "] " + oficinasExistentes.getOficina((i - 1)).getNome());
                    }

                    System.out.print("Escolher opção: ");

                    int opcOficina = teclado.nextInt();

                    if ((opcOficina - 1) <= oficinasExistentes.getNumerosOficinas()) {

                        boolean contemMenor = false;
                        
                        System.out.println("------------------------------------------------------");
                        System.out.println("Lista de participantes menores de idade nessa oficina:");

                        for (int num = 0; num < listaInscritos.tamanho(); num++) {

                            if (listaInscritos.getParticipante(num).getOficinasCadastradas().contains(oficinasExistentes.getOficina(opcOficina - 1))) {
                                if (listaInscritos.getParticipante(num).getFaixaEtaria().equalsIgnoreCase("Menor de idade")) {
                                    System.out.println(listaInscritos.getParticipante(num).getNome());
                                    contemMenor = true;
                                }
                            }

                        }
                        
                        if (!contemMenor) {
                            System.out.println("Essa oficina não tem nenhum participante menor de idade");
                        }

                        System.out.println("------------------------------------------------------\n");

                    } else {
                        System.out.println("Digite uma opção válida");
                    }

                    break;

                //Consultar inscrição a partir de CPF   
                case 4:

                    System.out.println("---------------- Buscar Participante ----------------");
                    System.out.print("Cpf do participante: ");

                    String cpfBuscado = teclado.next();

                    Participante pB = listaInscritos.buscarParticipante(cpfBuscado);

                    if (pB == null) {
                        System.out.println("Não foi encontrado nenhum participante com este cpf");
                    } else {
                        System.out.println("\n------------ Informações do Participante ------------");
                        System.out.println("Nome: " + pB.getNome());
                        System.out.println("Sexo: " + pB.getSexo());
                        System.out.println("Faixa Etária: " + pB.getFaixaEtaria());
                        System.out.println("Oficinas onde está inscrito:");
                        for (Oficina p : pB.getOficinasCadastradas()) {
                            System.out.println(p.getNome());
                        }
                    }

                    System.out.println("--------------------------------------------------------\n");

                    break;

                //Consultar estatísticas das inscrições    
                case 5:

                    if (listaInscritos.tamanho() == 0) {
                        System.out.println("------------------ Estatísticas ------------------");
                        System.out.println("Não existe nenhum participante ainda");
                        System.out.println("--------------------------------------------------\n");
                    } else {
                        System.out.println("------------------ Estatísticas ------------------");
                        System.out.println("Percentual dos inscritos a partir do sexo:");
                        System.out.printf("%.2f", listaInscritos.percentualM());
                        System.out.println("% dos participantes são do sexo masculino");
                        System.out.printf("%.2f", listaInscritos.percentualF());
                        System.out.println("% dos participantes são do sexo feminino\n");
                        System.out.println("Estatisticas de numeros de inscritos e faixa etária de cada oficina\n");
                        for (Oficina cont : oficinasExistentes.getListaOficinas()) {

                            if (cont.getInscritos() > 0) {

                                float numMenoresOficina = 0;
                                float numMaioresOficina = 0;

                                System.out.println("Oficina: " + cont.getNome());

                                System.out.println("Contém " + cont.getInscritos() + " inscrições");

                                for (int num = 0; num < listaInscritos.tamanho(); num++) {
                                    if (listaInscritos.getParticipante(num).getOficinasCadastradas().contains(cont)) {
                                        if (listaInscritos.getParticipante(num).getFaixaEtaria().equalsIgnoreCase("Menor de idade")) {
                                            numMenoresOficina++;
                                        }
                                        if (listaInscritos.getParticipante(num).getFaixaEtaria().equalsIgnoreCase("Maiores de idade")) {
                                            numMaioresOficina++;
                                        }
                                    }
                                }

                                float pMenores = (numMenoresOficina / cont.getInscritos()) * 100;
                                float pMaiores = (numMaioresOficina / cont.getInscritos()) * 100;

                                System.out.printf("Contém " + "%.2f", pMenores);
                                System.out.println("% dos participantes menores de idade");
                                System.out.printf("Contém " + "%.2f", pMaiores);
                                System.out.println("% dos participantes maiores de idade\n");

                            } else {
                                System.out.println("A oficina " + cont.getNome() + " não tem nenhum inscrito\n");
                            }

                        }
                    }

                    break;

                case 6:
                    System.out.println("Programa encerrado!");
                    break;

                default:
                    System.out.println("Selecione uma opção válida\n");
                    break;
            }

        } while (opc != 6);

    }

}
