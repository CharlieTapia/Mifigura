/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mifigura;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.Stroke;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Triangulo extends Figurabidimensional {

	public Triangulo (int x, int y, boolean isFilled, Paint paint, Stroke stroke) {
		super (x, y, isFilled, paint, stroke);
	}
	
	public Triangulo (int x1, int y1, int x2, int y2, boolean isFilled, Paint paint, Stroke stroke) {
		super (min (x1, x2), min (y1, y2), max (x1, x2), max (y1, y2), isFilled, paint, stroke);
	}
	
	public void draw (Graphics2D g2d) {
		super.draw (g2d);
		int[] pointsX = {(Puntoinicial.x + Puntofinal.x) / 2, Puntofinal.x, Puntoinicial.x};
		int[] pointsY = {Puntoinicial.y, Puntofinal.y, Puntofinal.y};
		Polygon polygon = new Polygon (pointsX, pointsY, 3);
		
		if (isFilled ())
			g2d.fill (polygon);
		else
			g2d.draw (polygon);
	}
}