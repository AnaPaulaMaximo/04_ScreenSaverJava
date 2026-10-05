package model;

import java.awt.Color;


public class Raquete extends Retangulo {
    private final int alturaTela;
    private final int velocidade;

    public Raquete(int x, int y, int largura, int altura, int velocidade, int alturaTela, Color cor) {
        super(x, y, largura, altura, cor);
        this.velocidade = velocidade;
        this.alturaTela = alturaTela;
    }

    public void moverCima() {
        y = Math.max(0, y - velocidade);
    }

    public void moverBaixo() {
        y = Math.min(alturaTela - altura, y + velocidade);
    }


    public void seguir(Forma alvo, int alvoCentroY) {
        int centro = y + altura / 2;
        if (Math.abs(alvoCentroY - centro) <= velocidade) return;
        if (alvoCentroY < centro) moverCima(); else moverBaixo();
    }

    public int getCentroY() { return y + altura / 2; }
}
