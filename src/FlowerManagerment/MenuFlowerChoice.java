
package FlowerManagerment;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuFlowerChoice {
    
    public static int getChoice(ArrayList options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + "-" + options.get(i));
        }
        System.out.println("***************************************************************************");

        int response;
        do {
            System.out.print("Choose 1-" + options.size() + ": ");
            Scanner box = new Scanner(System.in);
            response = Integer.parseInt(box.nextLine());
            return response;

        } while (response < 1 || response > options.size());
    }

    
    
}
