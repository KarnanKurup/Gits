/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mca.bookbank;

import java.awt.event.ActionEvent;
import javax.swing.*;

import javax.swing.plaf.metal.MetalTheme;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import mca.bookbank.theams.*;
/**
 *
 * @author Hare Krishna
 */
public class Theams {
    private BookBank obj;
    private  int numSSs = 0;
    private  static final String mac      =
            "com.sun.java.swing.plaf.mac.MacLookAndFeel";
    private  static final String nimbus   =
            "javax.swing.plaf.nimbus.NimbusLookAndFeel";
    private static final String metal    =
            "javax.swing.plaf.metal.MetalLookAndFeel";
    private static final String motif    =
            "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    private static final String windows  =
            "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    private static final String gtk  =
            "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
    private  ButtonGroup lafMenuGroup = new ButtonGroup();
    private  ButtonGroup themesMenuGroup = new ButtonGroup();

    private static String currentLookAndFeel = nimbus;
    JMenu lafMenu;
        
    JMenu themesMenu;
        
    public Theams(BookBank obj,JMenu menuBar){
        this.obj = obj;
        lafMenu = (JMenu) menuBar.add(new JMenu("Appearance"));
        JMenuItem mi;
        if (numSSs == 0) {
            // ***** create laf switcher menu
            
            mi = createLafMenuItem(lafMenu, "Nimbus Look & Feel",  nimbus);
            mi.setSelected(true); // this is the default l&f

            createLafMenuItem(lafMenu, "Java Look & Feel", metal);

            UIManager.LookAndFeelInfo[] lafInfo = UIManager.getInstalledLookAndFeels();

            for (int counter = 0; counter < lafInfo.length; counter++) {
                String className = lafInfo[counter].getClassName();
                if (className.equals(motif)) {
                    createLafMenuItem(lafMenu, "Motif Look & Feel", motif);
                } else if (className.equals(windows)) {
                    createLafMenuItem(lafMenu, "Windows Style", windows);
                } else if (className.equals(gtk)) {
                    createLafMenuItem(lafMenu, "GTK Style", gtk);
                }
            }

            // ***** create themes menu
            themesMenu = (JMenu) lafMenu.add(new JMenu("Themes"));

            // *** now back to adding color/font themes to the theme menu
            mi = createThemesMenuItem(themesMenu, "Ocean",new OceanTheme());
            mi.setSelected(true); // This is the default theme

            createThemesMenuItem(themesMenu, "Steel",new DefaultMetalTheme());
            
            createThemesMenuItem(themesMenu, "Aqua",new AquaTheme());
            
            createThemesMenuItem(themesMenu, "Charcoal",new CharcoalTheme());
           
            createThemesMenuItem(themesMenu, "Emerald",new EmeraldTheme());
            
            createThemesMenuItem(themesMenu, "Ruby",new RubyTheme());
            themesMenu.setEnabled(false);
        }
    }
    private JMenuItem createThemesMenuItem(JMenu menu, String label, MetalTheme theme) {
        JRadioButtonMenuItem mi = (JRadioButtonMenuItem) menu.add(new JRadioButtonMenuItem(label));
        themesMenuGroup.add(mi);
        mi.addActionListener(new ChangeThemeAction(obj, theme));

        return mi;
    }

    protected boolean isAvailableLookAndFeel(String laf) {
         try {
             Class lnfClass = Class.forName(laf);
             LookAndFeel newLAF = (LookAndFeel)(lnfClass.newInstance());
             return newLAF.isSupportedLookAndFeel();
         } catch(Exception e) { // If ANYTHING weird happens, return false
             return false;
         }
     }
    /**
     * Creates a JRadioButtonMenuItem for the Look and Feel menu
     */
    private JMenuItem createLafMenuItem(JMenu menu, String label, String laf) {
        JMenuItem mi = (JRadioButtonMenuItem) menu.add(new JRadioButtonMenuItem(label));
        lafMenuGroup.add(mi);
        mi.addActionListener(new ChangeLookAndFeelAction(obj, laf));

        mi.setEnabled(isAvailableLookAndFeel(laf));

        return mi;
    }
    class ChangeThemeAction extends AbstractAction {
        BookBank swingset;
        MetalTheme theme;
        protected ChangeThemeAction(BookBank swingset, MetalTheme theme) {
            super("ChangeTheme");
            this.swingset = swingset;
            this.theme = theme;
        }
         @Override
        public void actionPerformed(ActionEvent e) {
            MetalLookAndFeel.setCurrentTheme(theme);
            swingset.updateLookAndFeel(metal);
        }
    }
    class ChangeLookAndFeelAction extends AbstractAction {
        BookBank swingset;
        String laf;
        protected ChangeLookAndFeelAction(BookBank swingset, String laf) {
            super("ChangeTheme");
            this.swingset = swingset;
            this.laf = laf;
        }

        public void actionPerformed(ActionEvent e) {
            setLookAndFeel(laf);
        }
    }
    public void setLookAndFeel(String laf) {
        if(!currentLookAndFeel.equals(laf)) {
            currentLookAndFeel = laf;
           
            String lafName = null;
            if(laf.equals(mac)) lafName = "Macintosh Look & Feel";
            if(laf.equals(metal)) lafName = "Java Look & Feel";
            if(laf.equals(nimbus)) lafName = "Nimbus Look & Feel";
            if(laf.equals(motif)) lafName = "Motif Look & Feel";
            if(laf.equals(windows)) lafName = "Windows Style";
            if(laf.equals(gtk)) lafName = "GTK Style";
            themesMenu.setEnabled(laf.equals(metal));
            obj.updateLookAndFeel(laf);
            for(int i=0;i<lafMenu.getItemCount();i++) {
                JMenuItem item = lafMenu.getItem(i);
                if(item.getText().equals(lafName)) {
                    item.setSelected(true);
                } else {
                    item.setSelected(false);
                }
            }
        }
    }
        
}
