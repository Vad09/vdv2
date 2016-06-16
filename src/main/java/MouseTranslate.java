import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseListener;

import java.awt.*;
import java.awt.datatransfer.*;

/**
 * Created by Наталья on 16.06.2016.
 */
public class MouseTranslate implements CustomUser32,ClipboardOwner,NativeMouseListener {
    private int xPressed;
    private int yPressed;
    private int xReleased;
    private int yReleased;
    public void lostOwnership(Clipboard clipboard, Transferable contents) {

    }
    public void keybd_event(byte bVk, byte bScan, int dwFlags, int dwExtraInfo) {

    }
    String getClipboardText() throws Exception {
        try{
            return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        }
        catch(UnsupportedFlavorException e){
            System.out.println("UnsupportedFlavorException: error while transferring data");
            return "";
        }
    }

    void setClipboardText(String data) throws Exception {
        try{
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(data), this);
        }
        catch(IllegalStateException e){
        }
    }
    void controlC(CustomUser32 customUser32) {
        customUser32.keybd_event((byte) 0x11 /* VK_CONTROL*/, (byte) 0, 0, 0);
        customUser32.keybd_event((byte) 0x43 /* 'C' */, (byte) 0, 0, 0);
        customUser32.keybd_event((byte) 0x43 /* 'C' */, (byte) 0, 2 /* KEYEVENTF_KEYUP */, 0);
        customUser32.keybd_event((byte) 0x11 /* VK_CONTROL*/, (byte) 0, 2 /* KEYEVENTF_KEYUP */, 0);// 'Left Control Up
    }
    String getSelectedText(CustomUser32 customUser32) throws Exception {
        String before = getClipboardText();
        controlC(customUser32); // emulate Ctrl C
        Thread.sleep(100); // give it some time
        String text = getClipboardText();
        setClipboardText(before);
        return text;
    }




    public void nativeMouseClicked(NativeMouseEvent me) {

    }

    public void nativeMousePressed(NativeMouseEvent me) {
        xPressed=me.getX();
        yPressed=me.getY();
    }

    public void nativeMouseReleased(NativeMouseEvent me) {
        xReleased=me.getX();
        yReleased=me.getY();
        if(Math.abs(xPressed-xReleased)>0||Math.abs(yPressed-yReleased)>0){ //if mouse dragged

            try {
                new YandApi(getSelectedText(CustomUser32.INSTANCE),xPressed,yPressed);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}
