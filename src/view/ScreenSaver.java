package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.*;

public class ScreenSaver extends JFrame {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;
    private static final int DELAY_MS = 16; 

    private ScreenSaverPanel canvas;

    public ScreenSaver() {
        super("Protetor de Tela - Demonstração de POO");

        this.canvas = new ScreenSaverPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setContentPane(canvas);

 
        this.pack();

        this.setLocationRelativeTo(null);
    }

    public void start() {
        this.setVisible(true);
        this.canvas.startAnimation();
        

        this.canvas.requestFocusInWindow();
    }

    private class ScreenSaverPanel extends JPanel implements ActionListener {

        private Timer timer;

        /*********************************
        ** ↓ Declare suas formas aqui ↓ **
        *********************************/
        private static final int PONTOS_PARA_VENCER = 5;

        private Raquete jogador;
        private Raquete computador;
        private Bola bola;

        private int placarJogador = 0;
        private int placarComputador = 0;
        private boolean emJogo = false;
        private String mensagem = "Pressione ESPAÇO para começar";
        



        

        private boolean space = false;
        private boolean up = false;
        private boolean down = false;
        private boolean right = false;
        private boolean left = false;

        public ScreenSaverPanel() {
            this.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
            this.setBackground(Color.BLACK);


            this.setFocusable(true);

   
            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    
                    if (e.getKeyCode() == KeyEvent.VK_SPACE)
                        space = true;
                    if (e.getKeyCode() == KeyEvent.VK_UP)
                        up = true;
                    if (e.getKeyCode() == KeyEvent.VK_DOWN)
                        down = true;
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                        right = true;
                    if (e.getKeyCode() == KeyEvent.VK_LEFT)
                        left = true;
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    
                    if (e.getKeyCode() == KeyEvent.VK_SPACE)
                        space = false;
                    if (e.getKeyCode() == KeyEvent.VK_UP)
                        up = false;
                    if (e.getKeyCode() == KeyEvent.VK_DOWN)
                        down = false;
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                        right = false;
                    if (e.getKeyCode() == KeyEvent.VK_LEFT)
                        left = false;
                }
            });

            /***********************************
            ** ↓ Instancie suas formas aqui ↓ **
            ***********************************/
            jogador = new Raquete(30, (CANVAS_HEIGHT - 100) / 2, 15, 100, 7, CANVAS_HEIGHT, Color.WHITE);
            computador = new Raquete(CANVAS_WIDTH - 45, (CANVAS_HEIGHT - 100) / 2, 15, 100, 4, CANVAS_HEIGHT, Color.WHITE);
            bola = new Bola(16, CANVAS_WIDTH, CANVAS_HEIGHT, Color.YELLOW);
            




            this.timer = new Timer(DELAY_MS, this);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
        }

        @Override
        public Dimension getMinimumSize() {
            return new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
        }

        public void startAnimation() {
            this.timer.start();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            /******************************
            ** ↓ Mova suas formas aqui ↓ **
            ******************************/
            // O jogador sempre pode mexer a raquete (setas ↑ e ↓)
            if (up)
                jogador.moverCima();
            if (down)
                jogador.moverBaixo();

            if (!emJogo) {
                if (space) {
                    if (placarJogador >= PONTOS_PARA_VENCER || placarComputador >= PONTOS_PARA_VENCER) {
                        placarJogador = 0;
                        placarComputador = 0;
                    }
                    bola.resetar(1);
                    mensagem = "";
                    emJogo = true;
                }
            } else {
                bola.mover();
                computador.seguir(bola, bola.getCentroY());

               
                if (bola.colideCom(jogador) && bola.getVelocidadeX() < 0)
                    bola.rebater(jogador, 1);
                if (bola.colideCom(computador) && bola.getVelocidadeX() > 0)
                    bola.rebater(computador, -1);

                
                if (bola.saiuPelaEsquerda()) {
                    placarComputador++;
                    fimDeRodada(1);
                } else if (bola.saiuPelaDireita()) {
                    placarJogador++;
                    fimDeRodada(-1);
                }
            }
            




            this.repaint();
        }

        private void fimDeRodada(int direcaoProximoSaque) {
            emJogo = false;
            bola.resetar(direcaoProximoSaque);
            if (placarJogador >= PONTOS_PARA_VENCER) {
                mensagem = "Você venceu! ESPAÇO para jogar de novo";
            } else if (placarComputador >= PONTOS_PARA_VENCER) {
                mensagem = "O computador venceu! ESPAÇO para jogar de novo";
            } else {
                mensagem = "Pressione ESPAÇO para continuar";
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            /*********************************
            ** ↓ Desenhe suas formas aqui ↓ **
            *********************************/
            
            g2d.setColor(Color.DARK_GRAY);
            for (int y = 0; y < CANVAS_HEIGHT; y += 30)
                g2d.fillRect(CANVAS_WIDTH / 2 - 2, y, 4, 15);

            
            jogador.desenhar(g2d);
            computador.desenhar(g2d);
            bola.desenhar(g2d);

           
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Monospaced", Font.BOLD, 48));
            g2d.drawString(String.valueOf(placarJogador), CANVAS_WIDTH / 2 - 80, 60);
            g2d.drawString(String.valueOf(placarComputador), CANVAS_WIDTH / 2 + 45, 60);

         
            if (!mensagem.isEmpty()) {
                g2d.setFont(new Font("SansSerif", Font.BOLD, 24));
                int w = g2d.getFontMetrics().stringWidth(mensagem);
                g2d.drawString(mensagem, (CANVAS_WIDTH - w) / 2, CANVAS_HEIGHT / 2 + 100);
            }





        }
    }
}