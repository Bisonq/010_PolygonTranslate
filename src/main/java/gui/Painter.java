package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

class Painter extends JPanel implements MouseListener {

    private final List<Point> polygonCoordinates;
    private boolean pointsToPolygon = false;

    public Painter() {
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
        if (pointsToPolygon)
            System.out.println(value);
    }

    public void changeRotate(int value) {
        if (pointsToPolygon)
            System.out.println(value);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        if (pointsToPolygon) {
            int[] xPoly = new int[this.polygonCoordinates.size()];
            int[] yPoly = new int[this.polygonCoordinates.size()];
            for (int i = 0; i < this.polygonCoordinates.size(); i++) {
                xPoly[i] = (int) this.polygonCoordinates.get(i).getX();
                yPoly[i] = (int) this.polygonCoordinates.get(i).getY();
            }
            g2.drawPolygon(xPoly, yPoly, xPoly.length);
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
