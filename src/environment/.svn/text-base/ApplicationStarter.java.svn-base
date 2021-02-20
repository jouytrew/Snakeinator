/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author kevinlawrence
 */
public class ApplicationStarter {

    public static void Run(String[] args, String appName, Dimension appSize, Environment environment) {
        JFrame frame = new JFrame(appName);

        frame.add(environment);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(appSize);
        frame.setVisible(true);
    }
}
