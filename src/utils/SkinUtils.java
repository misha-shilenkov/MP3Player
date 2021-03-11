

package utils;

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// класс смены скинов
public class SkinUtils {
    
    //конструктор для передачи скина в виде объекта
    public static void changeSkin(Component comp, LookAndFeel laf){
        try {
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SkinUtils.class.getName()).log(Level.SEVERE, null, ex);
        }   
        //updateComponentTreeUI - метод по смене скина на лету
        SwingUtilities.updateComponentTreeUI(comp);
    }
    //конструктор для передачи скина в виде объекта
    public static void changeSkin(Component comp, String laf){
        try {
            UIManager.setLookAndFeel(laf);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkinUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(SkinUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SkinUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SkinUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        //updateComponentTreeUI - метод по смене скина на лету
    SwingUtilities.updateComponentTreeUI(comp);
    }

    
    
    
    
}
