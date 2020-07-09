package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class PaintPanel extends JPanel implements MouseListener{

    private final List<Point> polygonCoordinates;
    private boolean pointsToPolygon = false;

    public PaintPanel(int edges){
        this.setBackground(new Color(196,196,196));
        this.polygonCoordinates = new ArrayList<>();
        this.addMouseListener(this);
    }

    public void drawPolygon(){
        pointsToPolygon = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        if(pointsToPolygon){
            int xPoly[] = new int[this.polygonCoordinates.size()];
            int yPoly[] = new int[this.polygonCoordinates.size()];
            for(int i = 0 ; i < this.polygonCoordinates.size() ; i++)
            {
                xPoly[i] = (int)this.polygonCoordinates.get(i).getX();
                yPoly[i] = (int)this.polygonCoordinates.get(i).getY();
            }
            g2.drawPolygon(xPoly, yPoly, xPoly.length);
            pointsToPolygon = false;
            this.polygonCoordinates.clear();

        }else {
            for (Point p : polygonCoordinates) {
                g2.drawOval((int) p.getX(), (int) p.getY(), 6, 6);
                g2.fillOval((int) p.getX(), (int) p.getY(), 6, 6);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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
