import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

/**
 * Created by Наталья on 16.06.2016.
 */
public interface CustomUser32 extends StdCallLibrary {
    CustomUser32 INSTANCE = (CustomUser32) Native.loadLibrary("user32", CustomUser32.class);
    void keybd_event(byte bVk, byte bScan, int dwFlags, int dwExtraInfo);//слушатель клавиш
}
