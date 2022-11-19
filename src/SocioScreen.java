import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

public class SocioScreen extends JFrame {
	private JPanel panel_Princ;
	private JPanel contentPane;
	private String user, linea, nombre, direccion, apellidos, telefono;

	public SocioScreen(String usuario, String nombre, String direccion, String apellidos, String Telefono) {
		this.user = usuario;
		this.direccion = direccion;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = Telefono;
		cargarPanel();
		cargarPaneles();
		cargarLabels();
	}

	public void cargarPanel() {
		setMinimumSize(new Dimension(1080, 720));
        setPreferredSize(new Dimension(1080, 720));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1080, 720));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(168, 201, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

	public void cargarPaneles() {
		contentPane.setLayout(null);
		panel_Princ = new JPanel();
		panel_Princ.setMinimumSize(new Dimension(720, 480));
		panel_Princ.setBounds(10, 11, 1044, 659);
		panel_Princ.setBackground(new Color(249, 249, 249));
		contentPane.add(panel_Princ);
		panel_Princ.setLayout(null);
	}

	public void cargarLabels() {
		JLabel lbl_Textito = new JLabel("Eres un socio");
		lbl_Textito.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_Textito.setBounds(442, 259, 149, 71);
		panel_Princ.add(lbl_Textito);
		JLabel lbl_user = new JLabel(user);
		lbl_user.setBounds(473, 391, 106, 66);
		panel_Princ.add(lbl_user);

	}
}
