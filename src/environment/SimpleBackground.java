/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author kevinlawrence
 */
public class SimpleBackground extends JPanel {

    private ImageIcon background;
    private PaintableIntf painter;

//        Background(URL backgroundURL){
    public SimpleBackground(PaintableIntf painter) {
        this.painter = painter;

//            this.background = new ImageIcon(backgroundURL);
        this.setBackground(Color.WHITE);
//            System.out.println("Help");

        this.setVisible(true);
        this.setSize(500, 500);
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
//            System.out.println("Painting");
//            System.out.println("Icon Width = " + background.getIconWidth());
//            System.out.println("Painted");
        super.paintComponent(graphics);

//            background.paintIcon(this, graphics, 0, 0);

//            System.out.println("Painting");
//            graphics.drawImage(background.getImage(), 100, 100, this);

        if (painter != null) {
            painter.paintComponent(graphics);
        }
    }

    public void setPainter(PaintableIntf painter) {
        this.painter = painter;
    }

    private void setSize() {
//            this.setSize(this.background.getWidth(this), this.background.getHeight(this));
    }

    @Override
    public void repaint() {
        this.setBackground(Color.BLACK);
    }
}
