/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author holls9719
 */
public class Testing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int randNum2 = 0;
        for (int j = 0; j < 5; j++) {

            //randomly genorate monsters from the hole every so often
            //random chance for monsters to spawn if the radom number is greater than 10
            int randNum1 = (int) (Math.random() * (12 - 1 + 1)) + 1;
            System.out.println("Num1 " + randNum1);
            if (randNum1 > 10) {

                //get a random number for the number of monsters to spawn
                randNum2 = (int) (Math.random() * (5 - 1 + 1)) + 1;
                System.out.println("Num2 " + randNum2);
            }
            //reset randNum2
            if (randNum1 < 10) {
                randNum2 = 0;
            }
            //generate that many monsters
            for (int i = 0; i < randNum2; i++) {
                //setting monster color(yes, its pink)
                System.out.println("*");

                //reset randNum2 to zero for the next wave
                if (i == randNum2) {
                    randNum2 = 0;
                }
            }

        }
    }
}
