package Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Jogador {

    private String nome;
    private int pontos;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontos = 0;
    }

    public Jogador(BufferedReader b) throws IOException{
        this.nome = b.readLine();
        this.pontos = Integer.parseInt(b.readLine());
    }

    public String getNome() {
        return this.nome;
    }
    public int getPontos() {
        return this.pontos;
    }

    public void atualizarRecordes(int p) {
        if (this.pontos < p) this.pontos = p;
    }

    public void salvarArq(BufferedWriter b) throws IOException {
        b.write(this.nome+"\n");
        b.write(this.pontos+"\n");
    }
}
