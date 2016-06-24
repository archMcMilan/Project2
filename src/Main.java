import controller.Controller;

import java.io.IOException;

/**
 * Created by Artem on 18.06.16.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Controller controller=new Controller();
            controller.processUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
