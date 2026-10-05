package model;

import java.awt.Color;


public class Bola extends Circulo {
    private static final double VEL_INICIAL = 5;
    private static final double VEL_MAXIMA = 12;

    private final int larguraTela;
    private final int alturaTela;
    private double posX;
    private double posY;
    private double velocidadeX;
    private double velocidadeY;

    public Bola(int diametro, int larguraTela, int alturaTela, Color cor) {
        super(0, 0, diametro, cor);
        this.larguraTela = larguraTela;
        this.alturaTela = alturaTela;
        resetar(1);
    }


    public void resetar(int direcao) {
        posX = (larguraTela - diametro) / 2.0;
        posY = (alturaTela - diametro) / 2.0;
        velocidadeX = VEL_INICIAL * direcao;
        velocidadeY = (Math.random() * 6) - 3;
        x = (int) posX;
        y = (int) posY;
    }

    @Override
    public void mover() {
        posX += velocidadeX;
        posY += velocidadeY;

        if (posY <= 0) {
            posY = 0;
            velocidadeY = Math.abs(velocidadeY);
        } else if (posY + diametro >= alturaTela) {
            posY = alturaTela - diametro;
            velocidadeY = -Math.abs(velocidadeY);
        }
        x = (int) posX;
        y = (int) posY;
    }


    public void rebater(Raquete raquete, int direcao) {
        double relativo = ((y + diametro / 2.0) - raquete.getCentroY()) / (raquete.getAltura() / 2.0);
        relativo = Math.max(-1, Math.min(1, relativo));
        double novaVel = Math.min(VEL_MAXIMA, Math.abs(velocidadeX) * 1.07);
        velocidadeX = novaVel * direcao;
        velocidadeY = relativo * 6;

        posX = direcao > 0 ? raquete.getX() + raquete.getLargura() : raquete.getX() - diametro;
        x = (int) posX;
    }

    public int getCentroY() { return y + diametro / 2; }
    public double getVelocidadeX() { return velocidadeX; }
    public boolean saiuPelaEsquerda() { return x + diametro < 0; }
    public boolean saiuPelaDireita() { return x > larguraTela; }
}
