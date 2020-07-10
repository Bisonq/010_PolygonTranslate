package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

class Painter extends JPanel implements MouseListener {

    private final List<Point> polygonCoordinates;
    private final List<Point> polygonManipulationCoordinates;
    private boolean pointsToPolygon = false;
    private boolean beenScale = false;

    public Painter() {
        this.setBackground(new Color(196, 196, 196));
        this.polygonCoordinates = new ArrayList<>();
        this.polygonManipulationCoordinates = new ArrayList<>();
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
            for(int i = 0 ; i < this.polygonCoordinates.size() ; i++) {
                FrameCorner corner = getNearestVertex(this.polygonCoordinates.get(i));
                double x = this.polygonCoordinates.get(i).getX();
                double y = this.polygonCoordinates.get(i).getY();
                if(corner.toString().equals(FrameCorner.UPPER_LEFT.toString())){
                    polygonManipulationCoordinates.add(new Point((int)x - 10 * value, (int)y - 10 * value));
                    beenScale = true;
                    repaint();
                }else if(corner.toString().equals(FrameCorner.UPPER_RIGHT.toString())){
                    polygonManipulationCoordinates.add(new Point((int)x + 10 * value, (int)y - 10 * value));
                    beenScale = true;
                    repaint();
                }else if(corner.toString().equals(FrameCorner.BOTTOM_LEFT.toString())){
                    polygonManipulationCoordinates.add(new Point((int)x - 10 * value, (int)y + 10 * value));
                    beenScale = true;
                    repaint();
                }else if(corner.toString().equals(FrameCorner.BOTTOM_RIGHT.toString())){
                    polygonManipulationCoordinates.add(new Point((int)x + 10 * value, (int)y + 10 * value));
                    beenScale = true;
                    repaint();
                }
            }
        }
    }

    public void changeRotate(int value) {
        if (pointsToPolygon)
            System.out.println(value);
    }

    public FrameCorner getNearestVertex(Point p){
        double lane = laneLength(p, FrameCorner.UPPER_LEFT);
        FrameCorner min = FrameCorner.UPPER_LEFT;

        if(lane > laneLength(p, FrameCorner.UPPER_RIGHT)) {
            lane = laneLength(p, FrameCorner.UPPER_RIGHT);
            min = FrameCorner.UPPER_RIGHT;
        }
         if(lane > laneLength(p, FrameCorner.BOTTOM_LEFT)) {
             lane = laneLength(p, FrameCorner.BOTTOM_LEFT);
             min = FrameCorner.BOTTOM_LEFT;
         }
         if(lane > laneLength(p, FrameCorner.BOTTOM_RIGHT)){
            min = FrameCorner.BOTTOM_RIGHT;
        }

        return min;
    }

    private double laneLength(Point p, FrameCorner fc) {
        return Math.sqrt(Math.pow(p.getX() - fc.getX(), 2) + Math.pow(p.getY() - fc.getY(), 2));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        if (pointsToPolygon) {
            int[] xPoly;
            int[] yPoly;
            if(beenScale){
                xPoly = new int[this.polygonManipulationCoordinates.size()];
                yPoly = new int[this.polygonManipulationCoordinates.size()];
                for (int i = 0; i < this.polygonManipulationCoordinates.size(); i++) {
                    xPoly[i] = (int) this.polygonManipulationCoordinates.get(i).getX();
                    yPoly[i] = (int) this.polygonManipulationCoordinates.get(i).getY();
                }
                beenScale = false;
                this.polygonManipulationCoordinates.clear();
            }else {
                xPoly = new int[this.polygonCoordinates.size()];
                yPoly = new int[this.polygonCoordinates.size()];
                for (int i = 0; i < this.polygonCoordinates.size(); i++) {
                    xPoly[i] = (int) this.polygonCoordinates.get(i).getX();
                    yPoly[i] = (int) this.polygonCoordinates.get(i).getY();
                }
            }
            g2.drawPolygon(xPoly, yPoly, xPoly.length);
        }else {
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
