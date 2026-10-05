package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public abstract class Forma {
    protected int x;
    protected int y;
    protected Color cor;

    public Forma(int x, int y, Color cor) {
        this.x = x;
        this.y = y;
        this.cor = cor;
    }


    public abstract void desenhar(Graphics2D g2d);

  
    public abstract Rectangle getLimites();

 
    public void mover() {
    }

    public boolean colideCom(Forma outra) {
        return getLimites().intersects(outra.getLimites());
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public Color getCor() { return cor; }
    public void setCor(Color cor) { this.cor = cor; }
}
