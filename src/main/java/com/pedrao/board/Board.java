package com.pedrao.board;

import java.awt.Graphics;

import com.pedrao.Main;

public class Board {

    public int selectedRow = -1;
    public int selectedCol = -1;
    public boolean pieceSelected = false;

    char[][] boardLayout = {
        {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
        {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
        {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
        {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
        {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
        {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
        {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
        {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'}
    };

    public char [][] boardPosition = {
            {'R', 'K', 'B', 'Q', 'O', 'B', 'K', 'R'}, // White back row
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'}, // White pawns
            {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'}, // Empty row
            {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'}, // Black pawns
            {'r', 'k', 'b', 'q', 'o', 'b', 'k', 'r'}  // Black back row
    };



    public void render(Graphics g){
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                int x = col * 96;
                int y = row * 96;

                // Se o quadrado está selecionado, desenha o highlight
                if(pieceSelected && row == selectedRow && col == selectedCol){
                    if(boardLayout[row][col] == 'B'){
                        g.drawImage(Main.selectedBsquare, x, y, 96, 96, null);
                    } else {
                        g.drawImage(Main.selectedWsquare, x, y, 96, 96, null);
                    }
                } else {
                    if(boardLayout[row][col] == 'B'){
                        g.drawImage(Main.blackSquare, x, y, 96, 96, null);
                    }else{
                        g.drawImage(Main.whiteSquare, x, y, 96, 96, null);
                    }
                }

                // Desenhar as peças por cima
                switch (boardPosition[row][col]) {
                    case 'P' -> g.drawImage(Main.blackPawn, x, y, 96, 96, null);
                    case 'Q' -> g.drawImage(Main.blackQueen, x, y, 96, 96, null);
                    case 'R' -> g.drawImage(Main.blackRook, x, y, 96, 96, null);
                    case 'B' -> g.drawImage(Main.blackBishop, x, y, 96, 96, null);
                    case 'K' -> g.drawImage(Main.blackKnight, x, y, 96, 96, null);
                    case 'O' -> g.drawImage(Main.blackKing, x, y, 96, 96, null);

                    case 'p' -> g.drawImage(Main.whitePawn, x, y, 96, 96, null);
                    case 'q' -> g.drawImage(Main.whiteQueen, x, y, 96, 96, null);
                    case 'r' -> g.drawImage(Main.whiteRook, x, y, 96, 96, null);
                    case 'b' -> g.drawImage(Main.whiteBishop, x, y, 96, 96, null);
                    case 'k' -> g.drawImage(Main.whiteKnight, x, y, 96, 96, null);
                    case 'o' -> g.drawImage(Main.whiteKing, x, y, 96, 96, null);
                }
            }
        }
    }
}
