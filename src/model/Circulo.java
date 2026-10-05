package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Circulo extends Forma {
    protected int diametro;

    public Circulo(int x, int y, int diametro, Color cor) {
        super(x, y, cor);
        this.diametro = diametro;
    }

    @Override
    public void desenhar(Graphics2D g2d) {
        g2d.setColor(cor);
        g2d.fillOval(x, y, diametro, diametro);
    }

    @Override
    public Rectangle getLimites() {
        return new Rectangle(x, y, diametro, diametro);
    }

    public int getDiametro() { return diametro; }
    public void setDiametro(int diametro) { this.diametro = diametro; }
}
