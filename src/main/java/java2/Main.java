package java2;

import org.jnativehook.GlobalScreen;

/**
 * Created by Наталья on 16.06.2016.
 */
public class Main {

    public static void main(String args[]){
        Mouse mouse = new Mouse();
        try{
            GlobalScreen.registerNativeHook();

        }
        catch(Exception e){
            System.out.print(e);

        }
        GlobalScreen.addNativeMouseListener(mouse);



    }
}
