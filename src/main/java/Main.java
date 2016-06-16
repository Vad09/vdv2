import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Наталья on 16.06.2016.
 */
public class Main {

    public static void main (String[] args){

        JFrame frame = new JFrame();
        frame.setSize(230,150);
        JButton run = new JButton();
        JButton stop = new JButton();
        run.setText("Старт");
        stop.setText("Стоп");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(run);
        panel.add(stop);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        final MouseTranslate main =   new MouseTranslate();
        run.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    GlobalScreen.registerNativeHook();
                }
                catch (NativeHookException ex) {
                    System.err.println("There was a problem registering the native hook.");
                    System.err.println(ex.getMessage());

                    System.exit(1);
                }
                GlobalScreen.addNativeMouseListener(main);
            }
        });
        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    GlobalScreen.unregisterNativeHook();
                } catch (NativeHookException e1) {
                    e1.printStackTrace();
                }
                GlobalScreen.removeNativeMouseListener(main);
            }
        });





    }
}
