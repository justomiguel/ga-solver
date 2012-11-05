/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frre.cemami.utils;

import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author developer
 */
public class Utils {
    
    static DefaultLogguer logguer = DefaultLogguer.getLogger();
    
        public static synchronized void saveToFile(final JFreeChart chart, final String name) {
        
        //java.awt.EventQueue.invokeLater(new Runnable() {
           // @Override
          //  public void run() {
                if (chart != null) {
                    File myDir = new File("images");
                    if (!myDir.exists()) {
                        myDir.mkdirs();
                    }
                    File myImage = new File("images/" + name + ".png");
                    if (!myImage.exists()) {
                        try {
                            myImage.createNewFile();
                        }
                        catch (IOException ex) {
                            logguer.logError(ex.getMessage());
                        }
                    }
                    try {
                        ChartUtilities.saveChartAsPNG(myImage, chart, 800, 600);
                    }
                    catch (IOException ex) {
                        logguer.logError(ex.getMessage());
                    }
                }
          //  }
       // });
        
    }
    
}
