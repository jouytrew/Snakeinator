/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author kevinlawrence
 */
public class GraphicsPalette {

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

    public static void drawHappyFace(Graphics graphics, Point position, Point size, Color color) {
        graphics.setColor(color);
        graphics.fillOval(position.x, position.y, size.x, size.y);

        graphics.setColor(Color.BLACK);
        graphics.fillOval(position.x + (size.x / 5), position.y + (int) (size.y / 3.5), size.x / 4, size.y / 4);
        graphics.fillOval(position.x + (size.x / 2), position.y + (int) (size.y / 3.5), size.x / 4, size.y / 4);

        graphics.drawArc(position.x + (size.x / 4), position.y + (size.y / 4), size.x / 2, size.y / 2, 210, 120);
    }

    public static void drawSmileyFace(Graphics graphics, Point position, Point size, Color color) {

        // Smiley Face

//       Face Structure
        graphics.setColor(Color.YELLOW);
        graphics.fillOval(position.y + (size.y * 6) + 2,
                position.x + (size.x * 1) + 2,
                size.x - 4, size.y - 4);


//       Lenses
        graphics.setColor(Color.BLACK);
        graphics.fillOval(position.y + (size.y * 6) + 3,
                position.x + (size.x * 1) + 5,
                size.x - 13, size.y - 13);

        graphics.setColor(Color.BLACK);
        graphics.fillOval(position.y + (size.y * 6) + 10,
                position.x + (size.x * 1) + 5,
                size.x - 13, size.y - 13);

//        Eyebrows
//        graphics.setColor(Color.BLACK);
//        graphics.drawArc(position.x + 125, position.y + 20, 8, 8, 180, 180);


//        frame
        graphics.setColor(Color.BLACK);
        graphics.fillRect(position.y + (size.y * 6) + 1,
                position.x + (size.x * 1) + 8,
                size.x - 2, size.y - 18);

//      big smile
        graphics.setColor(Color.RED);
        graphics.fillArc(position.x + 126, position.y + 29, 8, 8, 180, 180);

        //     thin smile
        graphics.setColor(Color.BLACK);
        graphics.drawArc(position.x + 126, position.y + 29, 8, 8, 175, 185);


        //        graphics.setColor(color);
//        graphics.fillOval(position.x, position.y, size.x, size.y);
//
//        graphics.setColor(Color.BLACK);
//        graphics.fillOval(position.x + (size.x / 5), position.y + (int) (size.y / 3.5), 5, 5);
//        graphics.fillOval(position.x + (size.x / 2), position.y + (int) (size.y / 3.5), 5, 5);
//
//        graphics.drawArc(position.x + (size.x / 4), position.y + (size.y / 4), size.x / 2, size.y / 2, 210, 120);
    }

    public static void drawHappyFaceSunGlasses(Graphics graphics, Point position, Point size, Color color) {
        //draw a normal happy face...
        drawHappyFace(graphics, position, size, color);
        //then add a line for the sunglasses...
        graphics.drawLine(position.x, position.y + (int) (size.y / 2.4), position.x + size.x, position.y + (int) (size.y / 2.4));
    }

    public static void drawSixPointStar(Graphics graphics, Point position, Point size, Color color, Direction direction) {
        //Point position = this.grid.getCellPosition(new Point(5, 6));
        //position.y -= 5;
        //GraphicsPalette.drawTriangle(graphics, position, this.grid.getCellSize(), Color.YELLOW, Direction.NORTH, 9/2);
        //Point positionTopTriangle = this.grid.getCellPosition(new Point(5, 6));
        //positionTopTriangle.y += 5;
        //GraphicsPalette.drawTriangle(graphics, positionTopTriangle, this.grid.getCellSize(), Color.YELLOW, Direction.SOUTH, 9/2);     
    }

    public static void drawTriforce(Graphics graphics, Point position, Point size, Color color, Color background) {
        //Triforce
        drawTriangle(graphics, position, size, color, Direction.NORTH, 1);

        position.y += 5;
        graphics.setColor(background);
        drawTriangle(graphics, position, size, background, Direction.SOUTH, 5);
    }

    public static void drawUnicorn(Graphics graphics, Point position, Point size, Color color, Direction direction) {
        //body
        graphics.setColor(Color.MAGENTA);
        graphics.fillOval(position.x, position.y + size.y * 9 / 24, size.x * 2 / 3, size.y / 4);

        //back leg
        graphics.fillOval(position.x, position.y + size.x * 5 / 12, size.x / 6, size.y * 5 / 8);
        graphics.fillOval(position.x, position.y + size.y * 9 / 24, size.x / 4, size.y / 3);

        //front leg
        graphics.fillOval(position.x + size.x / 2, position.y + size.y * 5 / 12, size.x / 6, size.y * 5 / 8);
        graphics.fillOval(position.x + size.x / 2, position.y + size.y / 2, size.x / 6, size.y / 5);

        //head
        graphics.fillRoundRect(position.x + size.x / 2, position.y + size.y / 6, size.x * 5 / 12, size.y / 4, 15, 15);
        graphics.fillOval(position.x + size.x / 2, position.y + size.y / 6, size.x / 5, size.y / 2);

        // graphics.setColor(Color.red);
        graphics.fillOval(position.x + size.x / 2, position.y + size.y / 8, size.x / 5, size.y / 4);

        //eye
        graphics.setColor(Color.GREEN);
        graphics.fillOval(position.x + size.x * 9 / 16, position.y + size.y / 6, size.x / 8, size.y / 7);

        //hooves; back then front
        graphics.setColor(Color.WHITE);
        graphics.fillRect(position.x, position.y + size.y * 11 / 12, size.x / 6, size.y / 9);
        graphics.fillRect(position.x + size.x / 2, position.y + size.y * 11 / 12, size.x / 6, size.y / 9);

        //unicorn horn
        graphics.setColor(Color.WHITE);
        int[] iCharlie = {position.x + (size.x * 13 / 24), position.x + (size.x * 16 / 24), position.x + (size.x * 9 / 12)};
        int[] lCharlie = {position.y + (size.y * 1 / 6), position.y + (size.y * 1 / 6), position.y + (size.y * 1 / 30)};
        graphics.fillPolygon(iCharlie, lCharlie, 3);
    }

    public static void drawPoisonBottle(Graphics graphics, Point position, Point size, Color color) {
        //draw poison bottle
        graphics.setColor(Color.WHITE);
        graphics.fillOval(position.x, position.y + (size.y / 5), size.x * 3 / 4, size.y * 3 / 4);

        //top
        graphics.setColor(Color.WHITE);
        graphics.fillRect(position.x + (size.x / 4), position.y + (size.y / 7), size.x / 4, size.y / 5);

        //draw green liquid
        graphics.setColor(Color.GREEN);
        graphics.fillArc(position.x, position.y + (size.y / 3) - 8, size.x * 3 / 4, size.y - 9, 180, 180);

        // draw top 
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillRect(position.x + size.x / 4, position.y, size.x / 4, size.y / 6);
    }

    public static void drawBomb(Graphics graphics, Point position, Point size, Color color) {
        //draw black circle
        graphics.setColor(Color.BLACK);
        graphics.fillOval(position.x, position.y + (size.y / 5), size.x * 3 / 4, size.y * 3 / 4);

        //top
        graphics.setColor(Color.BLACK);
        graphics.fillRect(position.x + (size.x / 4), position.y + (size.y / 7), size.x / 4, size.y / 5);

        //draw smaller gray circle
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillOval(position.x + 2, position.y + 2 + (size.y / 5), size.x * 5 / 8, size.y * 5 / 8);

        //shiny bit
        graphics.setColor(Color.WHITE);
        graphics.fillOval(position.x + (size.x * 5 / 12), position.y + (size.y / 3), size.x * 1 / 6, size.y * 1 / 6);

        //rafay's fuse
        graphics.setColor(Color.BLACK);
        int arcStart = 0;
        int arcLength = 180 - arcStart;
        graphics.drawArc(position.x + size.x / 3, position.y, size.x / 4, size.y / 4, arcStart, arcLength);
        graphics.drawArc(position.x + size.x / 3 + 1, position.y, size.x / 4, size.y / 4, arcStart, arcLength);
        graphics.drawArc(position.x + size.x / 3 + 2, position.y, size.x / 4, size.y / 4, arcStart, arcLength);

        //fizzy bit
        if (Math.random() > .5) {
            graphics.setColor(Color.RED);
            graphics.fillRect(position.x + size.x * 2 / 3, position.y, size.x / 10, size.y / 10);
        } else {
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(position.x + size.x * 2 / 3, position.y, size.x / 8, size.y / 8);
        }

        //fizzy bit
        if (Math.random() > .5) {
            graphics.setColor(Color.RED);
            graphics.fillRect(position.x + size.x * 2 / 3, position.y, size.x / 10, size.y / 10);
        } else {
            graphics.setColor(Color.YELLOW);
            graphics.fillRect(position.x + size.x * 2 / 3, position.y, size.x / 8, size.y / 8);
        }
    }

    public static void enterPortal(Graphics graphics, Point position, Point size, Color color) {
        if (Math.random() > .5) {
            graphics.setColor(Color.BLUE);
        } else {
            graphics.setColor(Color.GREEN);
        }
        graphics.fillOval(position.x, position.y, size.x, size.y);


        if (Math.random() > .5) {
            graphics.setColor(Color.ORANGE);
        } else {
            graphics.setColor(Color.RED);
        }
        graphics.fillOval(position.x + size.x / 10, position.y + size.y / 10, (size.x * 4 / 5), (size.y * 4 / 5));
    }

    public static void leavePortal(Graphics graphics, Point position, Point size, Color color) {
        if (Math.random() > .5) {
            graphics.setColor(Color.RED);
        } else {
            graphics.setColor(Color.GREEN);
        }
        graphics.fillOval(position.x, position.y, size.x, size.y);


        if (Math.random() > .5) {
            graphics.setColor(Color.ORANGE);
        } else {
            graphics.setColor(Color.RED);
        }
        graphics.fillOval(position.x + size.x / 10, position.y + size.y / 10, (size.x * 4 / 5), (size.y * 4 / 5));

        if (Math.random() > .5) {
            graphics.setColor(Color.YELLOW);
        } else {
            graphics.setColor(Color.BLACK);
        }
        graphics.fillOval(position.x + size.x / 5, position.y + size.y / 5, (size.x * 8 / 13), (size.y * 8 / 13));
    }

    public static void drawTriangle(Graphics graphics, Point position, Point size, Color color, Direction direction) {
        drawTriangle(graphics, position, size, color, direction, 3);
    }

    public static void drawTriangle(Graphics graphics, Point position, Point size, Color color, Direction direction, int offset) {
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];

        switch (direction) {
            case NORTH:
                xPoints[0] = size.x / 2;
                xPoints[1] = offset;
                xPoints[2] = size.x - offset;

                yPoints[0] = offset;
                yPoints[1] = size.y - offset;
                yPoints[2] = size.y - offset;
                break;

            case WEST:
                xPoints[0] = size.x - offset;
                xPoints[1] = offset;
                xPoints[2] = size.x - offset;

                yPoints[0] = offset;
                yPoints[1] = size.y / 2;
                yPoints[2] = size.y - offset;
                break;

            case EAST:
                xPoints[0] = offset;
                xPoints[1] = offset;
                xPoints[2] = size.x - offset;

                yPoints[0] = offset;
                yPoints[1] = size.y - offset;
                yPoints[2] = size.y / 2;
                break;

            case SOUTH:
            default:
                xPoints[0] = offset;
                xPoints[1] = size.x / 2;
                xPoints[2] = size.x - offset;

                yPoints[0] = offset;
                yPoints[1] = size.y - offset;
                yPoints[2] = offset;
        }

        drawPolygon(graphics, position, color, xPoints, yPoints);
    }

    public static void drawDiamond(Graphics graphics, Point position, Point size, Color color) {
        drawDiamond(graphics, position, size, color, 3);
    }

    public static void drawDiamond(Graphics graphics, Point position, Point size, Color color, int offset) {
        //define points
        int[] xPoints = {size.x / 2, offset, size.x / 2, size.x - offset};
        int[] yPoints = {offset, size.y / 2, size.y - offset, size.y / 2};

        drawPolygon(graphics, position, color, xPoints, yPoints);
    }

    public static void drawApple(Graphics graphics, Point position, Point size) {
        // draw apple body
        graphics.setColor(Color.RED);
        graphics.fillOval(position.x + (size.x / 5), position.y + (size.y / 5), size.x - (size.x / 4), size.y - (size.x / 4));

        // draw stem
        graphics.setColor(Color.GREEN);
        graphics.drawArc(position.x, position.y + (size.y / 6), (size.x / 2), (size.y / 2), 0, 80);
    }

    public static void drawGrid(Graphics graphics, Point position, Point cellSize, Color color, int rows, int columns) {
        graphics.setColor(color);

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                graphics.drawRect(position.x + (column * cellSize.x),
                        position.y + (row * cellSize.y),
                        cellSize.x, cellSize.y);
            }
        }
    }

    public static void drawLollipop(Graphics graphics, Point position, Point size) {
//        // draw apple body
//        graphics.setColor(Color.RED);
//        graphics.fillOval(position.x + (size.x / 5), position.y + (size.y / 5), size.x - (size.x / 4), size.y - (size.x / 4));
//
//        // draw stem
//        graphics.setColor(Color.GREEN);
//        graphics.drawArc(position.x, position.y + (size.y / 6), (size.x / 2), (size.y / 2), 0, 80);


        //          Lollipop!
        graphics.setColor(Color.MAGENTA);
        graphics.fillOval(position.y * 11 / 1 / 2, position.x, size.x * 15 / 24, size.y * 15 / 24);

        graphics.setColor(Color.WHITE);
        graphics.fillRect(position.y * 72 / 3 / 4, position.x * 27 / 3 / 4, size.x - 18, size.y - 11);

        graphics.setColor(Color.CYAN);
        graphics.fillRect(position.y * 68 / 3 / 4, position.x * 22 / 3 / 4, size.x * 1 / 2, size.y * 1 / 8);

        graphics.setColor(Color.CYAN);
        graphics.fillRect(position.y * 68 / 3 / 4, position.x * 16 / 3 / 4, size.x * 1 / 2, size.y * 1 / 8);

    }

    public static void drawSixPointStar(Graphics graphics, Point position, Point size, Color color) {
        position.y -= 4;
        drawTriangle(graphics, position, size, Color.YELLOW, Direction.NORTH, 9 / 2);

        position.y += 10;
        drawTriangle(graphics, position, size, Color.YELLOW, Direction.SOUTH, 9 / 2);
    }
}
