package Classes;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Controle {

    private ArrayList<Jogador> jogadores;
    private Jogador atual;
    private String correta;

    public Controle() {
        this.correta = "";
        this.jogadores = new ArrayList<>();
        this.carregarArq();
        this.atual = this.bemvindo();
    }

    public ArrayList<Jogador> getJogadores() {
        return this.jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public Jogador getAtual() {
        return this.atual;
    }

    public void setAtual(Jogador atual) {
        this.atual = atual;
    }

    public String getCorreta() {
        return this.correta;
    }

    public void setCorreta(String correta) {
        this.correta = correta;
    }

    public int sortear() {

        Random r = new Random();

        int numeroSorteado = r.nextInt(9) + 1;

        this.correta += numeroSorteado;

        return numeroSorteado;
    }

    public Jogador localizarJogador(String nome) {

        // Confere na lista de jogadores se o jogador têm o nome do
        // parâmetro.
        for (Jogador j : this.jogadores) {
            if (j.getNome().equals(nome)) return j;
        }


        // Caso não encontre, se instancia um novo jogador com esse nome.
        Jogador novo = new Jogador(nome);

        // Ele é posto na lista de jogadores
        this.jogadores.add(novo);

        // E retorna o novo jogador
        return novo;
    }

    public void salvarArq() {
        try {

            FileWriter f = new FileWriter("arquivo.txt");
            BufferedWriter b = new BufferedWriter(f);

            b.write(this.jogadores.size() + "\n");

            for (Jogador j : this.jogadores) {
                j.salvarArq(b);
            }

            b.close();

        } catch (IOException e) {

            System.out.println("Erro na saída de dados.");

        }
    }

    public void carregarArq() {
        try {

            FileReader f = new FileReader("arquivo.txt");
            BufferedReader b = new BufferedReader(f);

            int x = Integer.parseInt(b.readLine());

            for (int i = 0; i < x; i++) {
                this.jogadores.add(new Jogador(b));
            }

            b.close();

        } catch (IOException e) {

            System.out.println("Erro na entrada de dados.");

        }
    }

    public Jogador bemvindo() {

        String nome = JOptionPane.showInputDialog(null, "Qual o seu nome?", "Bem vindo!", JOptionPane.PLAIN_MESSAGE);

        /* Enquanto o nome for nulo, ser apertado sem nenhum elemento
         *  dentro ou  for colocado espaço branco dentro, irá se repetir.*/
        while (nome == null || nome.isBlank() || nome.isEmpty()) {
            nome = JOptionPane.showInputDialog(null, "Qual o seu nome?", "Nome inválido!", JOptionPane.PLAIN_MESSAGE);
        }

        return localizarJogador(nome); // Chama o localizar Jogador

    }

    public boolean errou() {

        int acerto = this.correta.length() - 1; //Pega o valor de acertos

        this.setCorreta(""); // Limpa o atributo "correta"

        this.atual.atualizarRecordes(acerto); // Chama o atualizar recordes do atual dando o acerto como atributo

        int op = JOptionPane.showConfirmDialog(null, "Você acertou " + acerto + " números. Deseja começar um novo jogo?", "Fim de jogo", JOptionPane.YES_NO_OPTION);

        if (op == 0) { // Se for SIM é verdadeiro, caso contrário é falso.
            return true;
        } else {
            return false;
        }
    }

    public Jogador recordista() {

        //É instanciado o primeiro jogador da lista.
        Jogador jogador = this.jogadores.get(0);

        // É procurado na lista pelo jogador com o maior numero de pontos
        // , sendo cada jogador com maior pontuação no objeto "jogador"
        for (Jogador j : this.jogadores) {
            if (j.getPontos() > jogador.getPontos()) jogador = j;
        }

        // Retorna o jogador (O de maior pontuação na lista)
        return jogador;
    }

    public void bye(String nome, int pontos) {

        // É salvo o arquivo.txt com os elementos na lista.
        this.salvarArq();

        //É mostrado o recordista da sessão e o recordista geral.
        JOptionPane.showMessageDialog(null, "Recorde da sessão: " + nome + " - " + pontos + " ponto(s).Geral: " + recordista().getNome() + " - " + recordista().getPontos() + " ponto(s).", "RECORDES", JOptionPane.PLAIN_MESSAGE);

    }

    public void jogo() {

        String sequencia; // Instancia uma String

        //Enquanto a sequencia condizer com a "correta", continua.
        do{
            sequencia = JOptionPane.showInputDialog(null, "O novo número é: " + this.sortear(), "Digite a sequência completa", JOptionPane.PLAIN_MESSAGE);
        } while (this.correta.equals(sequencia));

    }
}
