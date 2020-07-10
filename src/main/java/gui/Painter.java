package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

class Painter extends JPanel implements MouseListener {

    private final JSlider scaleSlider;
    private final JSlider rotateSlider;
    private final JSlider translateSlider;
    private final List<Point> polygonCoordinates;
    private boolean pointsToPolygon = false;
    private Shape actualShape;
    private Shape modifyShape;
    boolean scale = false;
    boolean rotate = false;
    boolean translate = false;

    public Painter(JSlider scaleSlider, JSlider rotateSlider, JSlider translateSlider) {
        this.scaleSlider = scaleSlider;
        this.rotateSlider = rotateSlider;
        this.translateSlider = translateSlider;
        this.setBackground(new Color(196, 196, 196));
        this.polygonCoordinates = new ArrayList<>();
        this.addMouseListener(this);
    }

    public void drawPolygon() {
        if (this.polygonCoordinates.size() > 2) {
            pointsToPolygon = true;
            repaint();
        }
    }

    public void changeScale(int value) {
        if (pointsToPolygon) {
            this.modifyShape = AffineTransform.getScaleInstance(1 + 0.1 * value, 1 + 0.1 * value).createTransformedShape(this.actualShape);
            this.scale = true;
            rotateSlider.setValue(0);
            translateSlider.setValue(0);
            repaint();
        }
    }

    public void changeRotate(int value) {
        if (pointsToPolygon) {
            this.modifyShape = AffineTransform.getRotateInstance(Math.toRadians(36 * value)).createTransformedShape(this.actualShape);
            this.rotate = true;
            scaleSlider.setValue(0);
            translateSlider.setValue(0);
            repaint();
        }
    }

    public void changeTranslate(int value) {
        if (pointsToPolygon) {
            this.modifyShape = AffineTransform.getTranslateInstance(10 * value, -10 * value).createTransformedShape(this.actualShape);
            this.translate = true;
            scaleSlider.setValue(0);
            rotateSlider.setValue(0);
            repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        if (pointsToPolygon) {
            if (scale || rotate || translate) {
                g2.setTransform(AffineTransform.getTranslateInstance(10, 10));
                g2.draw(this.modifyShape);

            } else {
                int[] xPoly = new int[this.polygonCoordinates.size()];
                int[] yPoly = new int[this.polygonCoordinates.size()];
                for (int i = 0; i < this.polygonCoordinates.size(); i++) {
                    xPoly[i] = (int) this.polygonCoordinates.get(i).getX();
                    yPoly[i] = (int) this.polygonCoordinates.get(i).getY();
                }
                g2.drawPolygon(xPoly, yPoly, xPoly.length);
                this.actualShape = new Polygon(xPoly, yPoly, xPoly.length);
            }
        } else {
            for (Point p : polygonCoordinates) {
                g2.drawOval((int) p.getX(), (int) p.getY(), 6, 6);
                g2.fillOval((int) p.getX(), (int) p.getY(), 6, 6);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (pointsToPolygon) {
            pointsToPolygon = false;
            scale = false;
            rotate = false;
            translate = false;
            this.modifyShape = null;
            this.polygonCoordinates.clear();
        }
        polygonCoordinates.add(new Point(e.getX(), e.getY()));
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}