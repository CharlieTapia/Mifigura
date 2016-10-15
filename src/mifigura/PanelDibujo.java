/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mifigura;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.EOFException;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;
public class PanelDibujo extends JPanel {
    public static final byte CIRCULO = 0;
	public static final byte CUADRADO = 1;
	public static final byte TRIANGULO = 2;
	public static final byte LINEA = 3;

	private Figura[] shapes;
	private Figura currentShape;
	private Stroke currentStroke;
	private short numberOfShapes;
	private byte typeOfShape;
	private Paint currentPaint;
	private boolean filled = false;
	private JLabel Labelestado;
        
        public PanelDibujo(JLabel state){
            Labelestado = state;
            shapes = new Figura[100];
            numberOfShapes = 0;
            typeOfShape = CIRCULO;
            currentPaint = Color.BLACK;
            setBackground (Color.WHITE);
            MouseHandler mh = new MouseHandler ();
            addMouseListener (mh);
            addMouseMotionListener (mh);
        }
        @Override
	public void paintComponent (Graphics g) {
		super.paintComponent (g);
		Graphics2D g2d = (Graphics2D) g;

		if (currentShape != null)
			currentShape.draw (g2d);

		for (byte j = 0; shapes[j] != null && j < shapes.length; ++j)
			shapes[j].draw (g2d);
	}
	
	public void setTypeShape (byte newTypeShape) {
		typeOfShape = newTypeShape >= 0 && newTypeShape < 4 ? newTypeShape : 0;
	}
	
	public void setCurrentPaint (Paint newPaint) {
		currentPaint = newPaint;
	}
	
	public void setCurrentStroke (Stroke newStroke) {
		currentStroke = newStroke;
	}
	
	public void setFillable (boolean filledValue) {
		filled = filledValue;
	}
	
	public void rehacer () {
		if (--numberOfShapes < 0)
			numberOfShapes = 0;
		
		shapes[numberOfShapes] = null;
		
		repaint ();
	}
	
	public void limpiar () {
		numberOfShapes = 0;
		currentShape = null;
		
		for (short j = 0; shapes[j] != null && j < shapes.length; ++j)
			shapes[j] = null;
		
		repaint ();
	}
	
	private class MouseHandler extends MouseAdapter implements MouseMotionListener {

		public void mousePressed (MouseEvent me) {
			switch (typeOfShape) {
				case CIRCULO:
					currentShape = new Circulo (me.getX (), me.getY (), filled, currentPaint, currentStroke);
					break;
					
				case CUADRADO:
					currentShape = new Rectangulo (me.getX (), me.getY (), filled, currentPaint, currentStroke);
					break;
					
				case TRIANGULO:
					currentShape = new Triangulo (me.getX(), me.getY(), filled, currentPaint, currentStroke);
					break;
				
				case LINEA:
					currentShape = new Linea (me.getX(), me.getY(), currentPaint, currentStroke);
			}
		}
		
		public void mouseReleased (MouseEvent me) {
			refreshCurrentShape (me.getX (), me.getY ());
			shapes[numberOfShapes++] = currentShape;
			currentShape = null;
			repaint ();
		}
		
		public void mouseMoved (MouseEvent me) {
			refreshStateLabel (me.getX (), me.getY ());
		}
		
		public void mouseDragged (MouseEvent me) {
			refreshStateLabel (me.getX (), me.getY ());
			refreshCurrentShape (me.getX (), me.getY ());
			repaint ();
		}
		
		private void refreshStateLabel (int x, int y) {
			Labelestado.setText (String.format ("(%d, %d)", x, y));
		}
		
		private void refreshCurrentShape (int x, int y) {
			currentShape.setFinalPoint (x, y);
		}
	}
}
