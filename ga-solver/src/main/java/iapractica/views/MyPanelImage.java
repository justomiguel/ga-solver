/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iapractica.views;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Justo Vargas
 */
public class MyPanelImage  extends JPanel {

    private BufferedImage image;
    
    public MyPanelImage(String path) {
        super();
        this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        this.setAutoscrolls(true);
        this.setName("myImagesPanel"); // NOI18N
        this.setLayout(new java.awt.GridLayout());
        
        try {                
          image = ImageIO.read(new File(path));
       } catch (IOException ex) {
            // handle exception...
       }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }
    
    
}
