/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author kevinlawrence
 */
public class GraphicsPalette {

    /**
     * Draws a polygon
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param color the color to draw the object
     * @param xPoints array of x (horizontal) values for vertices
     * @param yPoints array of y (vertical) values for vertices
     */
    public static void drawPolygon(Graphics graphics, Point position, Color color, int[] xPoints, int[] yPoints) {
        //Adjust polyon points for position offset
        for (int point = 0; point < xPoints.length; point++) {
            xPoints[point] += position.x;
        }

        for (int point = 0; point < yPoints.length; point++) {
            yPoints[point] += position.y;
        }
        
        graphics.setColor(color);
        graphics.fillPolygon(xPoints, yPoints, xPoints.length);
    }

    /**
     * Draws a happy face
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     */
    public static void drawHappyFace(Graphics graphics, Point position, Dimension size, Color color) {
        graphics.setColor(color);
        graphics.fillOval(position.x, position.y, size.width, size.height);

        graphics.setColor(Color.BLACK);
        graphics.fillOval(position.x + (size.width / 5), position.y + (int) (size.height / 3.5), size.width / 4, size.height / 4);
        graphics.fillOval(position.x + (size.width / 2), position.y + (int) (size.height / 3.5), size.width / 4, size.height / 4);

        graphics.drawArc(position.x + (size.width / 4), position.y + (size.height / 4), size.width / 2, size.height / 2, 210, 120);
    }

    /**
     * Draws a smiley face
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     */
    public static void drawSmileyFace(Graphics graphics, Point position, Dimension size, Color color) {
//      Smiley Face
//      Face Structure
        graphics.setColor(Color.YELLOW);
        graphics.fillOval(position.y + (size.height * 6) + 2,
                position.x + (size.width * 1) + 2,
                size.width - 4, size.height - 4);


//      Lenses
        graphics.setColor(Color.BLACK);
        graphics.fillOval(position.y + (size.height * 6) + 3,
                position.x + (size.width * 1) + 5,
                size.width - 13, size.height - 13);

        graphics.setColor(Color.BLACK);
        graphics.fillOval(position.y + (size.height * 6) + 10,
                position.x + (size.width * 1) + 5,
                size.width - 13, size.height - 13);

//      Frame
        graphics.setColor(Color.BLACK);
        graphics.fillRect(position.y + (size.height * 6) + 1,
                position.x + (size.width * 1) + 8,
                size.width - 2, size.height - 18);

//      Big Smile
        graphics.setColor(Color.RED);
        graphics.fillArc(position.x + 126, position.y + 29, 8, 8, 180, 180);

//      Thin Smile
        graphics.setColor(Color.BLACK);
        graphics.drawArc(position.x + 126, position.y + 29, 8, 8, 175, 185);
    }

    /**
     * Draws a happy face with sunglasses
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     */
    public static void drawHappyFaceSunGlasses(Graphics graphics, Point position, Dimension size, Color color) {
//        draw a normal happy face...
        drawHappyFace(graphics, position, size, color);
//        then add a line for the sunglasses...
        graphics.drawLine(position.x, position.y + (int) (size.height / 2.4), position.x + size.width, position.y + (int) (size.height / 2.4));
    }

    /**
     * Draws a happy face with sunglasses
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     * @param direction 
     */
    public static void drawSixPointStar(Graphics graphics, Point position, Point size, Color color, Direction direction) {
        //Point position = this.grid.getCellPosition(new Point(5, 6));
        //position.y -= 5;
        //GraphicsPalette.drawTriangle(graphics, position, this.grid.getCellSize(), Color.YELLOW, Direction.UP, 9/2);
        //Point positionTopTriangle = this.grid.getCellPosition(new Point(5, 6));
        //positionTopTriangle.y += 5;
        //GraphicsPalette.drawTriangle(graphics, positionTopTriangle, this.grid.getCellSize(), Color.YELLOW, Direction.DOWN, 9/2);     
    }

    /**
     * Draws a triforce
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     * @param background the color of the background of the triforce
     */
    public static void drawTriforce(Graphics graphics, Point position, Dimension size, Color color, Color background) {
        //Triforce
        drawTriangle(graphics, position, size, color, Direction.UP, 1);

        position.y += 5;
        graphics.setColor(background);
        drawTriangle(graphics, position, size, background, Direction.DOWN, 5);
    }

    /**
     * Draws a Unicorn
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     * @param direction
     */
    public static void drawUnicorn(Graphics graphics, Point position, Dimension size, Color color, Direction direction) {
        //body
        graphics.setColor(Color.MAGENTA);
        graphics.fillOval(position.x, position.y + size.height * 9 / 24, size.width * 2 / 3, size.height / 4);

        //back leg
        graphics.fillOval(position.x, position.y + size.width * 5 / 12, size.width / 6, size.height * 5 / 8);
        graphics.fillOval(position.x, position.y + size.height * 9 / 24, size.width / 4, size.height / 3);

        //front leg
        graphics.fillOval(position.x + size.width / 2, position.y + size.height * 5 / 12, size.width / 6, size.height * 5 / 8);
        graphics.fillOval(position.x + size.width / 2, position.y + size.height / 2, size.width / 6, size.height / 5);

        //head
        graphics.fillRoundRect(position.x + size.width / 2, position.y + size.height / 6, size.width * 5 / 12, size.height / 4, 15, 15);
        graphics.fillOval(position.x + size.width / 2, position.y + size.height / 6, size.width / 5, size.height / 2);

        // graphics.setColor(Color.red);
        graphics.fillOval(position.x + size.width / 2, position.y + size.height / 8, size.width / 5, size.height / 4);

        //eye
        graphics.setColor(Color.GREEN);
        graphics.fillOval(position.x + size.width * 9 / 16, position.y + size.height / 6, size.width / 8, size.height / 7);

        //hooves; back then front
        graphics.setColor(Color.WHITE);
        graphics.fillRect(position.x, position.y + size.height * 11 / 12, size.width / 6, size.height / 9);
        graphics.fillRect(position.x + size.width / 2, position.y + size.height * 11 / 12, size.width / 6, size.height / 9);

        //unicorn horn
        graphics.setColor(Color.WHITE);
        int[] xUnicorn = {position.x + (size.width * 13 / 24), position.x + (size.width * 16 / 24), position.x + (size.width * 9 / 12)};
        int[] yUnicorn = {position.y + (size.height / 6), position.y + (size.height / 6), position.y + (size.height / 30)};
        graphics.fillPolygon(xUnicorn, yUnicorn, 3);
    }

    /**
     * Draws a poison bottle
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     */
    public static void drawPoisonBottle(Graphics graphics, Point position, Dimension size, Color color) {
        //draw poison bottle
        graphics.setColor(Color.WHITE);
        graphics.fillOval(position.x, position.y + (size.height / 5), size.width * 3 / 4, size.height * 3 / 4);

        //top
        graphics.setColor(Color.WHITE);
        graphics.fillRect(position.x + (size.width / 4), position.y + (size.height / 7), size.width / 4, size.height / 5);

        //draw green liquid
        graphics.setColor(Color.GREEN);
        graphics.fillArc(position.x, position.y + (size.height / 3) - 8, size.width * 3 / 4, size.height - 9, 180, 180);

        // draw top 
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(position.x + size.width / 4, position.y, size.width / 4, size.height / 6);
    }

    /**
     * Draws a bomb
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     */
    public static void drawBomb(Graphics graphics, Point position, Dimension size, Color color) {
        //draw black circle
        graphics.setColor(Color.BLACK);
        graphics.fillOval(position.x, position.y + (size.height / 5), size.width * 3 / 4, size.height * 3 / 4);

        //top
        graphics.setColor(Color.BLACK);
        graphics.fillRect(position.x + (size.width / 4), position.y + (size.height / 7), size.width / 4, size.height / 5);

        //draw smaller gray circle
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillOval(position.x + 2, position.y + 2 + (size.height / 5), size.width * 5 / 8, size.height * 5 / 8);

        //shiny bit
        graphics.setColor(Color.WHITE);
        graphics.fillOval(position.x + (size.width * 5 / 12), position.y + (size.height / 3), size.width * 1 / 6, size.height * 1 / 6);

        //rafay's fuse
        graphics.setColor(Color.BLACK);
        int arcStart = 0;
        int arcLength = 180 - arcStart;
        graphics.drawArc(position.x + size.width / 3, position.y, size.width / 4, size.height / 4, arcStart, arcLength);
        graphics.drawArc(position.x + size.width / 3 + 1, position.y, size.width / 4, size.height / 4, arcStart, arcLength);
        graphics.drawArc(position.x + size.width / 3 + 2, position.y, size.width / 4, size.height / 4, arcStart, arcLength);

        //fizzy bit
        if (Math.random() > .5) {
            graphics.setColor(Color.RED);
            graphics.fillRect(position.x + size.width * 2 / 3, position.y, size.width / 10, size.height / 10);
        } else {
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(position.x + size.width * 2 / 3, position.y, size.width / 8, size.height / 8);
        }

        //fizzy bit
        if (Math.random() > .5) {
            graphics.setColor(Color.RED);
            graphics.fillRect(position.x + size.width * 2 / 3, position.y, size.width / 10, size.height / 10);
        } else {
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(position.x + size.width * 2 / 3, position.y, size.width / 8, size.height / 8);
        }
    }

    /**
     * Draws an entry portal
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     */
    public static void drawEntryPortal(Graphics graphics, Point position, Dimension size, Color color) {
        if (Math.random() > .5) {
            graphics.setColor(Color.BLUE);
        } else {
            graphics.setColor(Color.GREEN);
        }
        
        graphics.fillOval(position.x, position.y, size.width, size.height);


        if (Math.random() > .5) {
            graphics.setColor(Color.ORANGE);
        } else {
            graphics.setColor(Color.RED);
        }
        graphics.fillOval(position.x + size.width / 10, position.y + size.height / 10, (size.width * 4 / 5), (size.height * 4 / 5));
    }

    /**
     * Draws an exit portal
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     */
    public static void drawExitPortal(Graphics graphics, Point position, Dimension size, Color color) {
        if (Math.random() > .5) {
            graphics.setColor(Color.RED);
        } else {
            graphics.setColor(Color.GREEN);
        }
        graphics.fillOval(position.x, position.y, size.width, size.height);


        if (Math.random() > .5) {
            graphics.setColor(Color.ORANGE);
        } else {
            graphics.setColor(Color.RED);
        }
        graphics.fillOval(position.x + size.width / 10, position.y + size.height / 10, (size.width * 4 / 5), (size.height * 4 / 5));

        if (Math.random() > .5) {
            graphics.setColor(Color.YELLOW);
        } else {
            graphics.setColor(Color.BLACK);
        }
        graphics.fillOval(position.x + size.width / 5, position.y + size.height / 5, (size.width * 8 / 13), (size.height * 8 / 13));
    }

    /**
     * Draws an exit portal
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     * @param direction the direction the triangle should point
     */
    public static void drawTriangle(Graphics graphics, Point position, Dimension size, Color color, Direction direction) {
        drawTriangle(graphics, position, size, color, direction, 3);
    }

    /**
     * Draws an triangle
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     * @param direction the direction the triangle should point
     * @param offset the offset from the sides
     */
    public static void drawTriangle(Graphics graphics, Point position, Dimension size, Color color, Direction direction, int offset) {
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];

        switch (direction) {
            case UP:
                xPoints[0] = size.width / 2;
                xPoints[1] = offset;
                xPoints[2] = size.width - offset;

                yPoints[0] = offset;
                yPoints[1] = size.height - offset;
                yPoints[2] = size.height - offset;
                break;

            case LEFT:
                xPoints[0] = size.width - offset;
                xPoints[1] = offset;
                xPoints[2] = size.width - offset;

                yPoints[0] = offset;
                yPoints[1] = size.height / 2;
                yPoints[2] = size.height - offset;
                break;

            case RIGHT:
                xPoints[0] = offset;
                xPoints[1] = offset;
                xPoints[2] = size.width - offset;

                yPoints[0] = offset;
                yPoints[1] = size.height - offset;
                yPoints[2] = size.height / 2;
                break;

            case DOWN:
            default:
                xPoints[0] = offset;
                xPoints[1] = size.width / 2;
                xPoints[2] = size.width - offset;

                yPoints[0] = offset;
                yPoints[1] = size.height - offset;
                yPoints[2] = offset;
        }
        drawPolygon(graphics, position, color, xPoints, yPoints);
    }

    /**
     * Draws an diamond
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     */
    public static void drawDiamond(Graphics graphics, Point position, Dimension size, Color color) {
        drawDiamond(graphics, position, size, color, 3);
    }

    /**
     * Draws an diamond
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     * @param offset the offset from the sides
     */
    public static void drawDiamond(Graphics graphics, Point position, Dimension size, Color color, int offset) {
        //define points
        int[] xPoints = {size.width / 2, offset, size.width / 2, size.width - offset};
        int[] yPoints = {offset, size.height / 2, size.height - offset, size.height / 2};

        drawPolygon(graphics, position, color, xPoints, yPoints);
    }

    /**
     * Draws an apple
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     */
    public static void drawApple(Graphics graphics, Point position, Dimension size) {
        drawApple(graphics, position, size, Color.RED); 
    }

    /**
     * Draws an apple
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color to draw the object
     */
    public static void drawApple(Graphics graphics, Point position, Dimension size, Color color) {
        // draw apple body
        graphics.setColor(color);
        graphics.fillOval(position.x + (size.width / 5), position.y + (size.height / 5), size.width - (size.width / 4), size.height - (size.width / 4));

        // draw stem
        graphics.setColor(Color.GREEN);
        graphics.drawArc(position.x, position.y + (size.height / 6), (size.width / 2), (size.height / 2), 0, 80);
    }

    /**
     * Draws an grid
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param cellSize the size to draw the cells
     * @param color the color to draw the object
     * @param rows the number of rows in the grid
     * @param columns the number of columns in the grid
     */
    public static void drawGrid(Graphics graphics, Point position, Dimension cellSize, Color color, int rows, int columns) {
        graphics.setColor(color);

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                graphics.drawRect(position.x + (column * cellSize.width),
                        position.y + (row * cellSize.height),
                        cellSize.width, cellSize.height);
            }
        }
    }

    /**
     * Draws an lollipop
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     */
    public static void drawLollipop(Graphics graphics, Point position, Dimension size) {
        graphics.setColor(Color.MAGENTA);
        graphics.fillOval(position.y * 11 / 2, position.x, size.width * 15 / 24, size.height * 15 / 24);

        graphics.setColor(Color.WHITE);
        graphics.fillRect(position.y * 72 / 3 / 4, position.x * 27 / 3 / 4, size.width - 18, size.height - 11);

        graphics.setColor(Color.CYAN);
        graphics.fillRect(position.y * 68 / 3 / 4, position.x * 22 / 3 / 4, size.width * 1 / 2, size.height * 1 / 8);

        graphics.setColor(Color.CYAN);
        graphics.fillRect(position.y * 68 / 3 / 4, position.x * 16 / 3 / 4, size.width * 1 / 2, size.height * 1 / 8);
    }

    /**
     * Draws a six pointed star
     * @param graphics graphics context for drawing
     * @param position object position, within screen graphics coordinates context
     * @param size the size to draw the object
     * @param color the color of the object
     */
    public static void drawSixPointStar(Graphics graphics, Point position, Dimension size, Color color) {
        position.y -= 4;
        drawTriangle(graphics, position, size, Color.YELLOW, Direction.UP, 9 / 2);

        position.y += 10;
        drawTriangle(graphics, position, size, Color.YELLOW, Direction.DOWN, 9 / 2);
    }
}
