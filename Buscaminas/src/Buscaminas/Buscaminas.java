/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Buscaminas;

import java.util.Scanner;

/**
 *
 * @author Zygmut
 */
public class Buscaminas {

    boolean won = false;
    String response = "";
    Scanner sc = new Scanner(System.in);
    prison prisonHustle;

    public void start() {

        introduction();

        do {
            System.out.println("\n" + prisonHustle.toString());
            System.out.println("-------------------------");
            System.out.print("What inmate to check: ");
            response = sc.nextLine();

            try {
                readCommand(response);
            } catch (Exception e) {

            }
        } while (!win());
        System.out.println("\nEnoguh for now! Your shift is over, see you tomorrow");

    }

    public void readCommand(String z) {

        try {
            String[] s = z.split(" ");
            int x = Integer.parseInt(s[1].split(",")[0]), y = Integer.parseInt(s[1].split(",")[1]);
            switch (s.length) {
                case 2:
                    switch (s[0].toLowerCase()) {
                        case "mark":
                            prisonHustle.prisoncell[x][y].marked = !prisonHustle.prisoncell[x][y].marked;
                            break;

                        case "bomb":
                            if (!prisonHustle.prisoncell[x][y].prisoner.equals("bomb")) {
                                prisonHustle.prisoncell[x][y].prisoner = "bomb";
                                prisonHustle.markbomb(x, y);
                                prisonHustle.bombCounter++;
                            } else {
                                System.out.println("There's already a bomb there");
                            }
                            break;

                    }
                    break;
                default:
                    System.out.println("You seem to have lost all memory, because I just told you how to WRITE ya dingus. Try again!");
                    break;
            }
        } catch (Exception e) {
            switch (response.toLowerCase()) {
                case "reveal":
                    for (int i = 0; i < prisonHustle.prisoncell.length; i++) {
                        for (int j = 0; j < prisonHustle.prisoncell[1].length; j++) {
                            prisonHustle.prisoncell[i][j].locked = !prisonHustle.prisoncell[i][j].locked;
                        }
                    }
                    break;

                case "exit":
                    System.exit(0);
                    break;

                default:
                    if (response.contains(",")) {
                        try {
                            prisonHustle.open(Integer.parseInt(response.split(",")[0]), Integer.parseInt(response.split(",")[1]));
                        } catch (Exception ex) {
                            System.out.println("You seem to have lost all memory, because I just told you how to WRITE ya dingus. Try again!");
                        }
                    } else {
                        System.out.println("You seem to have lost all memory, because I just told you how to WRITE ya dingus. Try again!");
                    }
                    break;
            }
        }

    }

    public boolean win() {
        int win = 0;
        for (int i = 0; i < prisonHustle.prisoncell.length && win <= prisonHustle.bombCounter; i++) {
            for (int j = 0; j < prisonHustle.prisoncell[1].length && win <= prisonHustle.bombCounter; j++) {
                if (prisonHustle.prisoncell[i][j].locked) {
                    win++;
                }
            }
        }
        return prisonHustle.bombCounter == win;
    }

    public void introduction() {

        System.out.println("This is your job now. We have new prisoners today and we'd like to check if some of these bastards have any weapons or something");
        System.out.println("We aren't in the possition to lose you, so just tell some other officer to check the prisoner cell by saying the floor and cell");
        System.out.println("For example: 4,6 to check cell 4 of the 6th floor \n");
        System.out.println("You're in luck today, you can choose the dimension of your shift. As we need to brag of this functionality and creating a logical explanation is tu much of a hustle \n");

        boolean goodwriter = false;
        while (!goodwriter) {
            try {
                System.out.print("Dimension of your hustle: ");
                response = sc.nextLine();
                prisonHustle = new prison(Integer.parseInt(response.split(",")[0]), Integer.parseInt(response.split(",")[1]));
                goodwriter = true;
            } catch (Exception e) {
                System.out.println("You seem to have lost all memory, because I just told you how to WRITE ya dingus. Try again! \n");
            }
        }
        System.out.println("\nExcelent! Now get back to work if you want to eat tonight!");
    }

    public static void main(String[] args) {
        new Buscaminas().start();
    }

}
