package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Retangulo extends Forma {
    protected int largura;
    protected int altura;

    public Retangulo(int x, int y, int largura, int altura, Color cor) {
        super(x, y, cor);
        this.largura = largura;
        this.altura = altura;
    }

    @Override
    public void desenhar(Graphics2D g2d) {
        g2d.setColor(cor);
        g2d.fillRect(x, y, largura, altura);
    }

    @Override
    public Rectangle getLimites() {
        return new Rectangle(x, y, largura, altura);
    }

    public int getLargura() { return largura; }
    public void setLargura(int largura) { this.largura = largura; }
    public int getAltura() { return altura; }
    public void setAltura(int altura) { this.altura = altura; }
}
