package com.pedrao;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.pedrao.board.Board;
import com.pedrao.movement.movePieces;

public class Main extends Canvas implements Runnable, MouseListener{

    public static final int WIDTH = 768;
    public static final int HEIGHT = 768;
    public static final int SCALE = 8;

    public static BufferedImage sprite;
    public static BufferedImage blackSquare;
    public static BufferedImage whiteSquare;
    public static BufferedImage blackPawn;
    public static BufferedImage blackRook;
    public static BufferedImage blackKnight;
    public static BufferedImage blackKing;
    public static BufferedImage blackBishop;
    public static BufferedImage blackQueen;
    public static BufferedImage whitePawn;
    public static BufferedImage whiteRook;
    public static BufferedImage whiteKnight;
    public static BufferedImage whiteKing;
    public static BufferedImage whiteBishop;
    public static BufferedImage whiteQueen;
    public static BufferedImage selectedWsquare;
    public static BufferedImage selectedBsquare;

    public static Board board = new Board();
    public static movePieces movement = new movePieces();
    public static void main(String[] args){
        Main main = new Main();
        main.setupFrame();
        new Thread(main).start();        
    }

    public Main(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);

        addMouseListener(this);

            try {
                sprite = ImageIO.read((Main.class.getResource("/Sprites.png")));
                blackSquare = sprite.getSubimage(0, 0, 16, 16);
                whiteSquare = sprite.getSubimage(16, 0, 16, 16);
                selectedBsquare = sprite.getSubimage(32, 0, 16, 16);
                selectedWsquare = sprite.getSubimage(48, 0, 16, 16);
                blackPawn = sprite.getSubimage(0, 16, 16, 16);
                blackQueen = sprite.getSubimage(16, 16, 16, 16);
                blackRook = sprite.getSubimage(32, 16, 16, 16);
                blackKnight= sprite.getSubimage(48, 16, 16, 16);
                blackBishop = sprite.getSubimage(64, 16, 16, 16);
                blackKing = sprite.getSubimage(80, 16, 16, 16);
                whitePawn = sprite.getSubimage(0, 32, 16, 16);
                whiteQueen = sprite.getSubimage(16, 32, 16, 16);
                whiteRook = sprite.getSubimage(32, 32, 16, 16);
                whiteKnight = sprite.getSubimage(48, 32, 16, 16);
                whiteBishop = sprite.getSubimage(64, 32, 16, 16);
                whiteKing = sprite.getSubimage(80, 32, 16, 16);
            } catch (IOException e) {}
    }

    public void setupFrame(){
        JFrame frame = new JFrame("Chess");
        frame.setSize(getPreferredSize());
        frame.setMinimumSize(getMinimumSize());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(getBufferStrategy() == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g;
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        board.render(g);
        g.dispose();
        bs.show();
    }

    public void cicle(){
        
    }

    @Override
    public void run() {
        while (true) {
            render();
            try {
                Thread.sleep(16); //60 fps
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
    
        int col = mouseX / 96;
        int row = mouseY / 96;
    
        if (!board.pieceSelected) {
            if (board.boardPosition[row][col] != 'E') {
                board.selectedRow = row;
                board.selectedCol = col;
                board.pieceSelected = true;
                System.out.println("Piece selected");
            }
        } else {
            System.out.println("piece move");
            movement.movePiece(board.selectedRow, board.selectedCol, row, col);
            board.pieceSelected = false;
            board.selectedCol = -1;
            board.selectedRow = -1;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
}