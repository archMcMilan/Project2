package test;

import controller.Controller;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Artem on 23.06.16.
 */
public class ControllerTest {

    @Test
    public void findWordsInInterrogativeSentenceTest() throws Exception {
        Controller controller= null;
        try {
            controller = new Controller();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Assert.assertEquals(controller.findWordsInInterrogativeSentence(5).size(),4);

    }

}