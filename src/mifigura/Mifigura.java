/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mifigura;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GradientPaint;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Mifigura implements ActionListener, ItemListener {
	private PanelDibujo Paneldibujo;
	private JButton BotonLimpiar;
	private JButton BotonDeshacer;
	private JCheckBox filled;
	private JComboBox Listafiguras;
        private JComboBox Colores;
	private JFrame ventana;
	private Color color1;
	
	public Mifigura () {
		JLabel stateLabel = new JLabel ();
		JPanel controlPanel = new JPanel ();
		controlPanel.setLayout (new GridLayout (2, 1, 5, 5));
		JPanel top = new JPanel ();
		JPanel bottom = new JPanel ();
		BotonLimpiar = new JButton ("Limpiar");
		BotonLimpiar.addActionListener (this);
		BotonDeshacer = new JButton ("Deshacer");
		BotonDeshacer.addActionListener (this);
                ventana = new JFrame ("Aplicacion Figuras");
                String[] Figuras = {"Circulo", "Rectangulo", "Triangulo", "Linea"};
                String[] colores = {"Negro", "Verde", "Rojo","Amarillo","Naranja","Rosa","Azul"};
		Listafiguras = new JComboBox (Figuras);
		Listafiguras.setMaximumRowCount (4);
		Listafiguras.addItemListener (this);
                Colores = new JComboBox (colores);
		Colores.setMaximumRowCount (7);
		Colores.addItemListener (this);
		color1 = Color.BLACK;
		Paneldibujo = new PanelDibujo (stateLabel);
		
		filled = new JCheckBox ("Rellenar");
		filled.addItemListener (this);

                top.add (BotonDeshacer);
                top.add (BotonLimpiar);
                top.add (Listafiguras);
                top.add (Colores);
                top.add (filled);
		
		
		controlPanel.add (top);
		controlPanel.add (bottom);
		ventana.add (controlPanel, BorderLayout.NORTH);
		ventana.add (Paneldibujo);
		ventana.add (stateLabel, BorderLayout.SOUTH);
		ventana.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		ventana.setSize (800, 500);
		ventana.setVisible (true);
	}

	public static void main (String args[]) {
		new Mifigura ();
	}
	
	public void actionPerformed (ActionEvent ae) {
		if (ae.getSource () == BotonDeshacer)
			Paneldibujo.rehacer ();
		else if (ae.getSource () == BotonLimpiar){
			Paneldibujo.limpiar ();	
                }
	}
	
	public void itemStateChanged (ItemEvent ie) {
		if (ie.getSource () == filled)
			Paneldibujo.setFillable (filled.isSelected ());
                if (ie.getStateChange () == ItemEvent.SELECTED)
			Paneldibujo.setTypeShape ((byte) Listafiguras.getSelectedIndex ());  
                    if(Colores.getSelectedItem().toString().equals("Negro")){
                           color1 = Color.BLACK;
                        }else if(Colores.getSelectedItem().toString().equals("Verde")){
                           color1 = Color.green;
                        }else if(Colores.getSelectedItem().toString().equals("Rojo")){
                            color1 = Color.RED;
                        }else if(Colores.getSelectedItem().toString().equals("Amarillo")){
                            color1 = Color.YELLOW;
                        }else if(Colores.getSelectedItem().toString().equals("Naranja")){
                            color1 = Color.ORANGE;
                        }else if(Colores.getSelectedItem().toString().equals("Rosa")){
                             color1 = Color.PINK;
                        }else if(Colores.getSelectedItem().toString().equals("Azul")){
                             color1 = Color.BLUE;
                        }
                        Paneldibujo.setCurrentPaint(color1);
                        System.out.print(Colores.getSelectedItem().toString());
	}
}
