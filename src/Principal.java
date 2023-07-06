import Classes.Controle;
import Classes.Jogador;

import java.util.ArrayList;

public class Principal {

    public static void main(String[] args) {

        Controle controle = new Controle();

        ArrayList<Jogador> jogadores = new ArrayList<>();

        Jogador j = new Jogador(controle.getAtual().getNome());

        jogadores.add(j);

        boolean continuar = true;
        while (continuar){

            controle.jogo();

            j.atualizarRecordes(controle.getCorreta().length() - 1);

            continuar = controle.errou();

            if(continuar){
                controle.setAtual(controle.bemvindo());
                j = new Jogador(controle.getAtual().getNome());
                jogadores.add(j);
            }
        }

        Jogador maior = maiorSessao(jogadores);

        controle.bye(maior.getNome(),maior.getPontos());

    }

    public static Jogador maiorSessao(ArrayList<Jogador> jogadores){
        Jogador jogador = jogadores.get(0);
        for (Jogador j : jogadores) {
            if (j.getPontos() > jogador.getPontos()) jogador = j;
        }
        return jogador;
    }

}
