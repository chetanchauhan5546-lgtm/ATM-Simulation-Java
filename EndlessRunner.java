import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EndlessRunner {

    public static void main(String[] args) {
        new GameFrame();
    }
}

class GameFrame extends JFrame {

    GameFrame() {

        setTitle("Endless Runner Game");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        add(new GamePanel());

        setVisible(true);
    }
}

class GamePanel extends JPanel implements ActionListener, KeyListener {

    int playerX = 100;
    int playerY = 350;

    int obstacleX = 800;

    int score = 0;

    boolean jumping = false;
    boolean gameOver = false;

    int jumpSpeed = 15;

    Timer timer;

    GamePanel() {

        setBackground(Color.WHITE);

        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!gameOver) {

            obstacleX -= 5;

            if (obstacleX < -30) {
                obstacleX = 800;
                score++;
            }

            if (jumping) {

                playerY -= jumpSpeed;
                jumpSpeed--;

                if (playerY >= 350) {
                    playerY = 350;
                    jumping = false;
                    jumpSpeed = 15;
                }
            }

            // Collision Detection
            if (obstacleX < playerX + 40 &&
                obstacleX + 30 > playerX &&
                playerY + 50 > 360) {

                gameOver = true;
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Ground
        g.setColor(Color.BLACK);
        g.drawLine(0, 400, 800, 400);

        // Player
        g.setColor(Color.BLUE);
        g.fillRect(playerX, playerY, 40, 50);

        // Obstacle
        g.setColor(Color.RED);
        g.fillRect(obstacleX, 360, 30, 40);

        // Score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score : " + score, 20, 30);

        if (gameOver) {

            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 250, 180);

            g.setFont(new Font("Arial", Font.PLAIN, 22));
            g.drawString("Press R to Restart", 260, 230);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_SPACE && !jumping && !gameOver) {
            jumping = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {

            playerY = 350;
            obstacleX = 800;
            score = 0;
            jumping = false;
            jumpSpeed = 15;
            gameOver = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}