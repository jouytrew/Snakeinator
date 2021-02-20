/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

/**
 *
 * @author kevinlawrence
 */
public abstract class Environment extends JPanel {
    private Image background = null;
    private Point backgroundImagePosition = new Point(0, 0);
    private ArrayList<Actor> actors = new ArrayList<Actor>();
    private final long TIMER_DELAY_MS = 0;
    private final long TIMER_PERIOD_MS = 50;
    private Timer environmentTimer = new Timer();

    public Environment() {
        initialize();
    }

    public Environment(Image background) {
        this.background = background;
        initialize();
    }

    public void initialize() {
        // ---- Timer ----
        initializeEnvironment();
        initializeTimer();

        // ---- Mouse Event Handler ----
        addMouseListener(new EnvironmentMouseListener());

        // ---- Key Handler ----
        addKeyListener(new EnvironmentActionListener());
        setFocusable(true);
    }

    public abstract void initializeEnvironment();
    
    public void act() {
        for (Actor actor : actors) {
            actor.move();
        }
        repaint();
    }
    
//   <editor-fold defaultstate="collapsed" desc="Timer">  
    public void initializeTimer() {
        environmentTimer.schedule(new EnvironmentTimerTask(), TIMER_DELAY_MS, TIMER_PERIOD_MS);
    }

    private class EnvironmentTimerTask extends TimerTask {

        @Override
        public void run() {
            timerTaskHandler();
            act();
        }
    }

    public abstract void timerTaskHandler();
//  </editor-fold>

//   <editor-fold defaultstate="collapsed" desc="Key Handler">    
    private class EnvironmentActionListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            keyPressedHandler(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            keyReleasedHandler(e);
        }
    }

    public abstract void keyPressedHandler(KeyEvent e);

    public abstract void keyReleasedHandler(KeyEvent e);
//  </editor-fold>
    
//   <editor-fold defaultstate="collapsed" desc="Mouse Event Handler"> 
    private class EnvironmentMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            for (Actor actor: actors){
                if (actor.contains(e.getPoint())){
                    actor.onMouseClick(e);
                }
            }
            
            environmentMouseClicked(e);
        }
    }

    public abstract void environmentMouseClicked(MouseEvent e);
//  </editor-fold>
    
//   <editor-fold defaultstate="collapsed" desc="Painting"> 
    public void paint(Graphics graphics) {
        super.paint(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        paintBackground(graphics2D);
        paintEnvironment(graphics);
        paintActors(graphics);
    }

    public void paintBackground(Graphics2D graphics2D) {
        graphics2D.drawImage(background, backgroundImagePosition.x, backgroundImagePosition.y, this);
    }

    public abstract void paintEnvironment(Graphics graphics);

    public void paintActors(Graphics graphics) {
        for (Actor actor : getActors()) {
            actor.paint(graphics);
        }
    }
//  </editor-fold>

//   <editor-fold defaultstate="collapsed" desc="Property Getters and Setters"> 
    public void setBackground(String fileSystemPath) {
        this.background = background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public Image getBackgroundImage() {
        return background;
    }

    /**
     * @return the actors
     */
    public ArrayList<Actor> getActors() {
        return actors;
    }
    
    /**
     * @return the backgroundImagePosition
     */
    public Point getBackgroundImagePosition() {
        return backgroundImagePosition;
    }

    /**
     * @param backgroundImagePosition the backgroundImagePosition to set
     */
    public void setBackgroundImagePosition(Point backgroundImagePosition) {
        this.backgroundImagePosition = backgroundImagePosition;
    }


//  </editor-fold>    

}
