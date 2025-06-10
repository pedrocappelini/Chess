package com.pedrao.movement;

import com.pedrao.Main;

public class movePieces {

    public static boolean whiteTurn = true;

    public void movePiece(int row, int col, int tRow, int tCol) {
        char piece = Main.board.boardPosition[row][col];

        if (whiteTurn && ableMove(row, col, tRow, tCol)) {
            switch (piece) {
                case 'p' -> {
                    if (tCol == col && tRow == row - 1 && Main.board.boardPosition[tRow][tCol] == 'E') {
                        makeMove(row, col, tRow, tCol, piece);
                    } else if (row == 6 && tRow == 4 && tCol == col
                                && Main.board.boardPosition[5][col] == 'E'
                                && Main.board.boardPosition[4][col] == 'E') {
                        makeMove(row, col, tRow, tCol, piece);
                    }else if(tRow == row-1 || tRow == row+1){
                        makeMove(row, col, tRow, tCol, piece);
                    }
                }

                case 'r' -> {
                    if ((tCol == col || tRow == row)) {
                        makeMove(row, col, tRow, tCol, piece);
                    }
                }

                case 'k' -> {
                    if ((tRow == row + 2 && (tCol == col + 1 || tCol == col - 1)) ||
                        (tRow == row - 2 && (tCol == col + 1 || tCol == col - 1)) ||
                        (tRow == row + 1 && (tCol == col + 2 || tCol == col - 2)) ||
                        (tRow == row - 1 && (tCol == col + 2 || tCol == col - 2))) {
                        makeMove(row, col, tRow, tCol, piece);
                        
                    }
                }

                case 'b' -> {
                    if (Math.abs(row - tRow) == Math.abs(col - tCol)) {
                        makeMove(row, col, tRow, tCol, piece);
                    }
                }

                case 'q' -> {
                    if ((Math.abs(col - tCol) == Math.abs(row - tRow) ||
                         tCol == col || tRow == row)) {
                        makeMove(row, col, tRow, tCol, piece);
                    }
                }

                case 'o' -> {
                    if (Math.abs(row - tRow) <= 1 && Math.abs(col - tCol) <= 1) {
                        makeMove(row, col, tRow, tCol, piece);
                    }
                }
            }
        }else if(!whiteTurn && ableMove(row, col, tRow, tCol)){
            switch (piece) {
                case 'P' -> {
                    if (tCol == col && tRow == row + 1 && Main.board.boardPosition[tRow][tCol] == 'E') {
                        makeMove(row, col, tRow, tCol, piece);
                    } else if (row == 1 && tRow == 3 && tCol == col
                            && Main.board.boardPosition[3][col] == 'E'
                            && Main.board.boardPosition[2][col] == 'E') {
                        makeMove(row, col, tRow, tCol, piece);
                    }else if(tRow == row-1 || tRow == row+1){
                        makeMove(row, col, tRow, tCol, piece);
                    }
                }

                case 'R' -> {
                    if ((tCol == col || tRow == row) && Main.board.boardPosition[tRow][tCol] == 'E') {
                        makeMove(row, col, tRow, tCol, piece);
                    }
                }

                case 'K' -> {
                    if ((tRow == row + 2 && (tCol == col + 1 || tCol == col - 1)) ||
                        (tRow == row - 2 && (tCol == col + 1 || tCol == col - 1)) ||
                        (tRow == row + 1 && (tCol == col + 2 || tCol == col - 2)) ||
                        (tRow == row - 1 && (tCol == col + 2 || tCol == col - 2))) {
                        makeMove(row, col, tRow, tCol, piece);
                    }
                }

                case 'B' -> {
                    if (Math.abs(row - tRow) == Math.abs(col - tCol)) {
                        makeMove(row, col, tRow, tCol, piece);
                    }
                }

                case 'Q' -> {
                    if ((Math.abs(col - tCol) == Math.abs(row - tRow) ||
                         tCol == col || tRow == row)) {
                        makeMove(row, col, tRow, tCol, piece);
                    }
                }

                case 'O' -> {
                    if (Math.abs(row - tRow) <= 1 && Math.abs(col - tCol) <= 1) {
                        makeMove(row, col, tRow, tCol, piece);
                    }
                }
            }
        }
    }

    private void makeMove(int row, int col, int tRow, int tCol, char piece) {
        Main.board.boardPosition[tRow][tCol] = piece;
        Main.board.boardPosition[row][col] = 'E';
        whiteTurn = !whiteTurn;
    }

    public boolean ableMove(int row, int col, int tRow, int tCol){
        char piece = Main.board.boardPosition[row][col];
        char target = Main.board.boardPosition[tRow][tCol];

        if (Character.isLowerCase(piece) && Character.isLowerCase(target) && target != 'E') return false;
        if (Character.isUpperCase(piece) && Character.isUpperCase(target) && target != 'E') return false;

        switch(Character.toLowerCase(piece)) {
            case 'p' -> {
                if(tRow == row && tCol == col) return false;
                int temp = 0;
                for(int x = col; x < tCol; x++){
                    if(Main.board.boardPosition[row][x] == 'E')
                        temp++;
                }
                if((tRow == row-1 || tRow == row+1) && Main.board.boardPosition[tRow][tCol] != 'E'){
                    return true;
                }
            return temp == Math.abs(tCol - col);
            }

            case 'b' -> {
                if(tRow == row && tCol == col) return false;
                int rStep = (tRow > row) ? 1 : -1;
                int cStep = (tCol > col) ? 1 : -1;
                int r = row + rStep;
                int c = col + cStep;
    
                while (r != tRow && c != tCol) {
                    if (Main.board.boardPosition[r][c] != 'E') return false;
                    r += rStep;
                    c += cStep;
                }
                return true;
            }

            case 'r' -> {
                if(tRow == row && tCol == col) return false;
                if (row == tRow) {
                    int step = (tCol > col) ? 1 : -1;
                    for (int c = col + step; c != tCol; c += step) {
                        if (Main.board.boardPosition[row][c] != 'E') return false;
                    }
                } else {
                    int step = (tRow > row) ? 1 : -1;
                    for (int r = row + step; r != tRow; r += step) {
                        if (Main.board.boardPosition[r][col] != 'E') return false;
                    }
                }
            
                return true;
            }
            
            case 'q' -> {
                if(tRow == row && tCol == col) return false;
                if (row == tRow) {
                    int step = (tCol > col) ? 1 : -1;
                    for (int c = col + step; c != tCol; c += step) {
                        if (Main.board.boardPosition[row][c] != 'E') return false;
                    }
                } else if (col == tCol) {
                    int step = (tRow > row) ? 1 : -1;
                    for (int r = row + step; r != tRow; r += step) {
                        if (Main.board.boardPosition[r][col] != 'E') return false;
                    }
                }
                else if (Math.abs(row - tRow) == Math.abs(col - tCol)) {
                    int rStep = (tRow > row) ? 1 : -1;
                    int cStep = (tCol > col) ? 1 : -1;
            
                    int r = row + rStep;
                    int c = col + cStep;
                
                    while (r != tRow && c != tCol) {
                        if (Main.board.boardPosition[r][c] != 'E') return false;
                        r += rStep;
                        c += cStep;
                    }
                } else {
                    return false;
                }
        
                return true;
            }

            case 'k' -> {
                return !(tRow == row && tCol == col);
            }

            case 'o' -> {
                int dRow = Math.abs(tRow - row);
                int dCol = Math.abs(tCol - col);

                return dRow <= 1 && dCol <= 1;
            }

        }
    return false;
    }
}