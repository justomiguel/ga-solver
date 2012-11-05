/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iapractica.views;

import java.awt.Dimension;
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
       
        Dimension size = new Dimension(this.image.getWidth(null), this.image.getHeight(null));
        this.setSize(size);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }
    
    public Dimension getImageSize(){
        Dimension size = new Dimension(this.image.getWidth(null)+50, this.image.getHeight(null)+50);
        return size;
    }
    
    
}
