package trabalhojava;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class TrabalhoJAVA {

    public static void main(String[] args) throws FileNotFoundException, IOException {


        int[][] vitgolos = new int[40][7]; //matriz inteiros
        String[][] Sequipas = new String[40][2];// matriz texto
        int nElens = 0, op; // variaveis equipas
        final int Tamanho = 40;

        do { // menu
            op = menu();
            switch (op) {


                case 1:
                    nElens = Ler(Sequipas, vitgolos); // vai buscar o subprograma ler
                    break; // pausa
                case 2:
                    listar(Sequipas, vitgolos, nElens); // vai buscar o  listar
                    break;
                case 3:
                    if (nElens < Tamanho) { // se houver espaço disponivel
                        inserir(Sequipas, vitgolos, nElens); // vai buscar o  o inserir
                        nElens++; // conta mais uma equipa
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Atingida a capacidase máxima da matriz"); //senao mostra mensagem
                    }
                    break;
                case 4:
                    if (nElens > 0) { // verifica se ha equipas
                        String equipa = JOptionPane.showInputDialog("Qual a equipa que pretende eliminar?");
                        int pos = pesquisa(Sequipas, nElens, equipa); // vai buscar o pesquisa para verificar qual é o nome igual
                        if (pos != -1) { 
                            for (int x = pos; x < nElens - 1; x++) { // vai verificar as linhas
                                Sequipas[x] = Sequipas[x + 1];
                                vitgolos[x] = vitgolos[x + 1];
                            }
                            nElens--; // desconta uma equipa
                        } else {
                            JOptionPane.showMessageDialog(null, "Equipa não existe"); 
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Não existem equipas");
                    }
                    break;
                case 5:
                    atualizar(Sequipas, vitgolos, nElens); // atualiza a equipa
                    break;
                case 6:
                    if (nElens > 0) { // se houver equipas
                        classificacao(Sequipas, vitgolos, nElens); // vai buscar o classificaçao
                        ordenarGolos(Sequipas, vitgolos, nElens); // vai buscar o ordenar golos
                        listar(Sequipas, vitgolos, nElens); // vai buscar o  listar
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Não existem funcionários"); // aparece que nao ha funcionarios
                    }
                    break;
                case 7:
                    maisvogais(Sequipas, vitgolos, nElens); // vai buscar o  mais vogais
                    break;

                case 8:
                    //clubstring();
                    break;
                case 9:
                    maisDerrotas(vitgolos, Sequipas, nElens); // vai buscar a equipa com mais derrotas
                    break;

                case 10:
                    guardar(Sequipas, vitgolos, nElens); // guarda no ficheiro 
                    break;
                case 11:
                    extra(Sequipas, vitgolos, nElens); // um extra do programa
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "Fim de programa..."); // aparece ao sair do programa

            }
        } while (op != 0);
    }

    private static int menu() { // programa do menu
        String msgMenu =
                "0  - Sair\n"
                + "1  - Ler\n"
                + "2  - Ver\n"
                + "3  - Inserir informação da equipa\n"
                + "4  - Apagar informação da equipa\n"
                + "5  - Atualizar informação da equipa\n"
                + "6  - Classificação total\n"
                + "7  - Maior nome com mais Vogais\n"
                + "8  - ClubeString\n"
                + "9  - Mais derrotas\n"
                + "10 - Guardar\n"
                + "11 - Informações extra"
                + "\n\nEscolha a opção:";
        int op = Integer.parseInt(JOptionPane.showInputDialog(msgMenu));
        return op; // retorna o valor inserido para o menu
    }

    private static int Ler(String[][] Sequipas, int[][] vitgolos) throws FileNotFoundException {
        Scanner tjava = new Scanner(new File("Tabela.txt")); // le o ficheiro
        int nElens1 = 0, aux = 0; // criou se uma variavel para guardar na memoria
        nElens1 = aux;

        while (tjava.hasNext()) {
            String vettemp = tjava.nextLine(); // proxima linha
            String[] temp = vettemp.split(":"); // divide as palavras ou algarismos por :
            Sequipas[nElens1][0] = temp[0];// vai passar para a matriz o que foi guardado na memoria
            Sequipas[nElens1][1] = temp[1];
            vitgolos[nElens1][0] = Integer.parseInt(temp[2]);
            vitgolos[nElens1][1] = Integer.parseInt(temp[3]);
            vitgolos[nElens1][2] = Integer.parseInt(temp[4]);
            vitgolos[nElens1][3] = Integer.parseInt(temp[5]);
            vitgolos[nElens1][4] = Integer.parseInt(temp[6]);
            vitgolos[nElens1][5] = vitgolos[nElens1][0] * 3 + vitgolos[nElens1][1];

            nElens1++; // adiciona uma equipa por ciclo
        }
        JOptionPane.showMessageDialog(null, "Foram carregadas " + (nElens1 - aux) + " Equipas"); // indica as equipas carregadas
        return nElens1;


    }

    private static void listar(String[][] Sequipas, int[][] vitgolos, int nelens) {

        String msg = "Iniciais    Equipa      V   E  D GM GS  PTS\n"; //mensagem

        for (int i = 0; i < nelens; i++) {
            msg = msg + String.format("%-12s%-11s%3d%3d%3d%3d%3d%4d%n", //formatar o texto
                    Sequipas[i][0], Sequipas[i][1], vitgolos[i][0], vitgolos[i][1], vitgolos[i][2], vitgolos[i][3],
                    vitgolos[i][4], vitgolos[i][5], vitgolos[i][6]);// imprime no ecra as equipas e resultados
        }
        JTextArea jt = new JTextArea(msg); // criação de um local para o texto
        jt.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14)); // formatação do texto

        JOptionPane.showMessageDialog(null, jt);
    }

    private static int inserir(String[][] Sequipas, int[][] vitgolos, int nElens) {
        Sequipas[nElens][0] = JOptionPane.showInputDialog("Iniciais?"); // pergunta e insere na matriz
        Sequipas[nElens][1] = JOptionPane.showInputDialog("Nome?");
        vitgolos[nElens][0] = Integer.parseInt(JOptionPane.showInputDialog("Vitórias?"));
        vitgolos[nElens][1] = Integer.parseInt(JOptionPane.showInputDialog("Empates?"));
        vitgolos[nElens][2] = Integer.parseInt(JOptionPane.showInputDialog("Derrotas?"));
        vitgolos[nElens][3] = Integer.parseInt(JOptionPane.showInputDialog("Golos marcados?"));
        vitgolos[nElens][4] = Integer.parseInt(JOptionPane.showInputDialog("Golos sofridos?"));
        vitgolos[nElens][5] = vitgolos[nElens][0] * 3 + vitgolos[nElens][1];

        return ++nElens; //retorna mais uma equipa 
    }

    private static void atualizar(String[][] Sequipas, int[][] vitgolos, int nElens) throws FileNotFoundException {
        if (nElens > 0) { // se houver equipas
            Scanner atu = new Scanner(new File("atualizar.txt"));// le o ficheiro atualizar.txt

            while (atu.hasNextLine()) { 
                String vettemp = atu.nextLine(); // passa a linha
                String[] temp = vettemp.split(":"); // divide as palavras com :
                int pos = pesquisa(Sequipas, nElens, temp[1]); // vai buscar o ficheiro do pesquisa atraves do nome
                if (pos != -1) { 
                    Sequipas[pos][0] = temp[0]; // vai substituir a equipa 
                    vitgolos[pos][0] = Integer.parseInt(temp[2]);
                    vitgolos[pos][1] = Integer.parseInt(temp[3]);
                    vitgolos[pos][2] = Integer.parseInt(temp[4]);
                    vitgolos[pos][3] = Integer.parseInt(temp[5]);
                    vitgolos[pos][4] = Integer.parseInt(temp[6]);
                    vitgolos[pos][5] = vitgolos[pos][0] * 3 + vitgolos[pos][1];
                }

            }
            atu.close();
        }
    }

    private static int pesquisa(String[][] Sequipas, int nElens, String equipa) {
        int pos = 0;
        while (pos < nElens && Sequipas[pos][1].equalsIgnoreCase(equipa) == false) {
            pos++;
        }
        if (pos < nElens) {
            return pos;
        } else {
            return -1;
        }
    }

    private static void classificacao(String[][] Sequipas, int[][] vitgolos, int nElens) {
        int x, y;
        int[] aux2;
        String[] aux;

        for (x = 0; x < nElens - 1; x++) { // percorre todas as equipas
            for (y = x + 1; y < nElens; y++) { // percorre os pontos
                if (vitgolos[x][5] < vitgolos[y][5]) { // troca quem tiver mais pontos

                    aux2 = vitgolos[x];
                    vitgolos[x] = vitgolos[y];
                    vitgolos[y] = aux2;

                    aux = Sequipas[x];
                    Sequipas[x] = Sequipas[y];
                    Sequipas[y] = aux;


                }
            }
        }

    }

    private static void ordenarGolos(String[][] Sequipas, int[][] vitgolos, int nElens) {
        int x, y;
        String[] aux1;
        int[] aux2;

        for (x = 0; x < nElens - 1; x++) { // percorre todas as equipas
            for (y = x + 1; y < nElens; y++) { // percorre os golos
                if (vitgolos[x][5] == vitgolos[y][5]) {//se tiverem os mesmos pontos
                    int difer1 = vitgolos[x][3] - vitgolos[x][4];
                    int difer2 = vitgolos[y][3] - vitgolos[y][4];

                    if (difer1 < difer2) {//troca-se as que tem diferenças diferentes
                        aux2 = vitgolos[x];
                        vitgolos[x] = vitgolos[y];
                        vitgolos[y] = aux2;
                        aux1 = Sequipas[x];
                        Sequipas[x] = Sequipas[y];
                        Sequipas[y] = aux1;
                    }
                }
            }
        }
    }

    private static void maisDerrotas(int[][] vitgolos, String[][] Sequipas, int nElens) {
        int pos = 0, max = vitgolos[0][2];



        for (int x = 1; x < nElens; x++) { // percorre as equipas
            if (vitgolos[x][2] > max) { // indica se tem mais derrotas
                max = vitgolos[x][2];

            }
        }
        String msg = "";
        for (int x = 1; x < nElens; x++) {
            if (vitgolos[x][2] == max) {
                msg += Sequipas[x][1] + "\n";

            }
        }
        JOptionPane.showMessageDialog(null, msg);
    }

    private static void clubstring(String[][] Sequipas, int[][] vitgolos, int nElens) {
    }

    private static void maisvogais(String[][] Sequipas, int[][] vitgolos, int nElens) {
        int[][] vitgolosV = new int[nElens][5];
        String[][] SequipasV = new String[nElens][2];
        int contV, maxV = 0, nElensV = 0;

        for (int x = 0; x < nElens; x++) {
            contV = 0;
            for (int y = 0; y < Sequipas[x][1].length(); y++) {
                if (vogal(Sequipas[x][1].charAt(y))) {
                    contV++;
                }
            }
            if (contV > maxV) {
                maxV = contV;
                vitgolosV = new int[nElens][5];
                SequipasV = new String[nElens][2];
                nElensV = 0;
                vitgolosV[nElensV] = vitgolos[x];
                SequipasV[nElensV] = Sequipas[x];
                nElensV++;
            } else if (contV == maxV) {
                vitgolosV[nElensV] = vitgolos[x];
                SequipasV[nElensV] = Sequipas[x];
                nElensV++;
            }
        }
    }

    private static boolean vogal(char c) {
        char[] vogais = {'a', 'á', 'à', 'ã', 'â', 'e', 'é', 'è', 'ê', 'i', 'í', 'ì', 'î', 'o', 'ó', 'ò', 'õ', 'ô', 'u', 'ú', 'ù', 'û'};

        for (int x = 0; x < vogais.length; x++) {
            if (vogais[x] == c) {
                return true;
            }
        }
        return false;
    }

    private static void guardar(String[][] Sequipas, int[][] vitgolos, int nElens) throws FileNotFoundException {
        Formatter FichFunc = new Formatter(new File("Tabela.txt"));  //abre o ficheiro

        for (int l = 0; l < nElens; l++) { //percorre todos os elementos
            for (int c = 0; c < Sequipas[0].length; c++) { // faz um ciclo que escreve todas as colunas de uma linha da matriz Sequipas
                FichFunc.format("%s:", Sequipas[l][c]);
            }
            for (int c = 0; c < vitgolos[0].length; c++) { // faz um ciclo que escreve todas as colunas de uma linha da matriz vitgolos
                FichFunc.format("%d", vitgolos[l][c]);
                if (c != (vitgolos[0].length - 1)) {
                    FichFunc.format(":"); // mete : caso nao seja o ultimo elemento
                }
            }
            if (l != (nElens - 1)) {
                FichFunc.format("\n"); // mete quebra de linha a nao ser que seja o ultimo elemento
            }
        }
        FichFunc.close(); // fecha o ficheiro
    }

    private static void extra(String[][] Sequipas, int[][] vitgolos, int nElens) {
      String msg="";
      String msg1="";
      String msg2="";
      int aux;
      aux = nElens;
      
        
      msg += Sequipas[0][1] + "\n" + Sequipas[1][1] + "\n\n"; // indica as equipas que vão a liga dos campeões
        msg1 += Sequipas[2][1] + "\n" + Sequipas[3][1] + "\n" + Sequipas[4][1] + "\n\n"; // indica as equipas que vão a liga europa
     
            
        msg2 += Sequipas[aux-2][1] + "\n" + Sequipas[aux-1][1]; // indica as equipas que descem de divisão
        
        JOptionPane.showMessageDialog(null, "Liga dos Campeões:\n" + msg +"Liga Europa\n" + msg1 + "Descida de divisão\n" + msg2);
    
}
}
