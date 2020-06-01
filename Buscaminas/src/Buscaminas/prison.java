/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and locked the template in the editor.
 */
package Buscaminas;

import java.util.Scanner;

/**
 *
 * @author Zygmut
 */
public class prison {

    public cell[][] prisoncell;
    public int bombCounter;

    public prison(int dim) {
        prisoncell = new cell[dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                prisoncell[i][j] = new cell();
            }
        }
        bombify(numberBombs());
    }

    public prison(int dimx, int dimy) {
        prisoncell = new cell[dimx][dimy];
        for (int i = 0; i < dimx; i++) {
            for (int j = 0; j < dimy; j++) {
                prisoncell[i][j] = new cell();
            }
        }
        bombify(numberBombs());
    }

    public int numberBombs() {
        Scanner sc = new Scanner(System.in);
        boolean goodwriter = false;
        System.out.print("How many bombs would you like: ");
        while (!goodwriter) {
            try {
                bombCounter = Integer.parseInt(sc.nextLine());
                goodwriter = true;
            } catch (Exception e) {
                System.out.println("You seem to have lost all memory, because I just told you how to WRITE ya dingus. Try again! \n");
            }
        }
        return bombCounter;
    }

    public void bombify(int bombs) {
        for (int i = 0; i < bombs; i++) {
            int rx, ry;

            do {
                rx = (int) (Math.random() * (prisoncell.length - 1) + 1);
                ry = (int) (Math.random() * (prisoncell[1].length - 1) + 1);
            } while (prisoncell[rx][ry].prisoner.equals("bomb"));

            prisoncell[rx][ry].prisoner = cell.cont.bomb.toString();
            markbomb(rx, ry);
        }
    }

    public void markbomb(int rx, int ry) {
        boolean put = false;
        for (int i = rx - 1; i < rx + 2; i++) {
            for (int j = ry - 1; j < ry + 2; j++) {
                try {
                    for (int k = 0; k < 8 && !put; k++) {
                        if (cell.cont.values()[k].toString().equals(prisoncell[i][j].prisoner) && !cell.cont.bomb.equals(prisoncell[i][j].prisoner)) {
                            try {
                                prisoncell[i][j].prisoner = cell.cont.values()[k + 1].toString();
                                put = true;
                                break;
                            } catch (Exception e) {
                            }
                        }
                    }
                } catch (Exception e) {
                }
                put = false;
            }
        }

    }

    public void open(int posx, int posy) {
        prisoncell[posx][posy].locked = false;
        switch (prisoncell[posx][posy].prisoner) {
            case "zero":
                spread(posx, posy, "zero");
                break;
            case "bomb":
                System.out.println("WHAT THE FUCK, IS THAT A BLOODY BOMB?!");
                System.out.println("you lose");
                System.exit(0);
                break;
        }

    }

    public void spread(int posx, int posy, String num) {
        for (int i = posx - 1; i < posx + 2; i++) {
            for (int j = posy - 1; j < posy + 2; j++) {
                try {
                    if (prisoncell[i][j].locked) {
                        if (prisoncell[i][j].prisoner.equals(num)) {
                            prisoncell[i][j].locked = false;
                            spread(i, j, num);
                        } else {
                            open(i, j);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    @Override
    public String toString() {
        String r = "   ";
        for (int i = 0; i < prisoncell[1].length; i++) {
            if (i <= 9) {
                r += " 0" + i + " ";
            } else {
                r += " " + i + " ";
            }
        }
        r += "\n";
        for (int i = 0; i < prisoncell.length; i++) {
            if (i <= 9) {
                r += " 0" + i + " ";
            } else {
                r += " " + i + " ";
            }
            for (int j = 0; j < prisoncell[1].length; j++) {
                if (prisoncell[i][j].locked) {
                    if (prisoncell[i][j].marked) {
                        r += "[!] ";
                    } else {
                        r += "[#] ";
                    }
                } else {
                    switch (prisoncell[i][j].prisoner) {
                        case "zero":
                            r += "[ ] ";
                            break;
                        case "one":
                            r += "[1] ";
                            break;
                        case "two":
                            r += "[2] ";
                            break;
                        case "three":
                            r += "[3] ";
                            break;
                        case "four":
                            r += "[4] ";
                            break;
                        case "five":
                            r += "[5] ";
                            break;
                        case "six":
                            r += "[6] ";
                            break;
                        case "seven":
                            r += "[7] ";
                            break;
                        case "eight":
                            r += "[8] ";
                            break;
                        case "bomb":
                            r += "[@] ";
                            break;
                    }
                }
            }
            r += "\n";
        }
        return r;
    }
}
