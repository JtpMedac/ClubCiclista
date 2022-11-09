import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnadirSocios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txt_Nombre;
	private JTextField txt_Apellidos;
	private JTextField txt_DNI;
	private JTextField txt_Direccion;
	private JTextField txt_Telefono;
	private JTextField txt_contrasena;
	private JPanel panel_datos;
	private JLabel lbl_datos, lbl_Nombre, lbl_Apellidos, lbl_DNI, lbl_Direccion, lbl_Telefono, lbl_Contrasena, lblAviso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AnadirSocios dialog = new AnadirSocios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AnadirSocios() {

		cargarPanelPrin();
		cargarPanelSec();
		cargarJLabels();
		cargarTextFields();
		botonesConf();

	}

	public void cargarPanelPrin() {
		setMinimumSize(new Dimension(920, 518));
		setPreferredSize(new Dimension(920, 518));
		setResizable(false);
		setSize(new Dimension(920, 518));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(168, 201, 240));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	}

	public void cargarPanelSec() {
		lbl_datos = new JLabel("Datos del nuevo socio");
		lbl_datos.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lbl_datos.setBounds(34, 26, 364, 44);
		contentPanel.add(lbl_datos);

		panel_datos = new JPanel();
		panel_datos.setBackground(new Color(217, 217, 217));
		panel_datos.setBounds(10, 81, 884, 354);
		contentPanel.add(panel_datos);
		panel_datos.setLayout(null);
	}

	public void cargarJLabels() {
		lbl_Nombre = new JLabel("Nombre");
		lbl_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Nombre.setBounds(56, 35, 193, 29);
		panel_datos.add(lbl_Nombre);

		lbl_Apellidos = new JLabel("Apellidos");
		lbl_Apellidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Apellidos.setBounds(56, 141, 193, 29);
		panel_datos.add(lbl_Apellidos);

		lbl_DNI = new JLabel("DNI");
		lbl_DNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_DNI.setBounds(56, 265, 193, 29);
		panel_datos.add(lbl_DNI);

		lbl_Direccion = new JLabel("Direccion");
		lbl_Direccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Direccion.setBounds(486, 35, 193, 29);
		panel_datos.add(lbl_Direccion);

		lbl_Telefono = new JLabel("Telefono");
		lbl_Telefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Telefono.setBounds(486, 141, 193, 29);
		panel_datos.add(lbl_Telefono);

		lbl_Contrasena = new JLabel("Contrasena");
		lbl_Contrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Contrasena.setBounds(486, 265, 193, 29);
		panel_datos.add(lbl_Contrasena);
	}

	public void cargarTextFields() {
		txt_Nombre = new JTextField();
		txt_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_Nombre.setBounds(179, 39, 238, 20);
		panel_datos.add(txt_Nombre);
		txt_Nombre.setColumns(10);

		txt_Apellidos = new JTextField();
		txt_Apellidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_Apellidos.setColumns(10);
		txt_Apellidos.setBounds(179, 145, 238, 20);
		panel_datos.add(txt_Apellidos);

		txt_DNI = new JTextField();
		txt_DNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_DNI.setColumns(10);
		txt_DNI.setBounds(179, 274, 238, 20);
		panel_datos.add(txt_DNI);

		txt_Direccion = new JTextField();
		txt_Direccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_Direccion.setColumns(10);
		txt_Direccion.setBounds(633, 39, 238, 20);
		panel_datos.add(txt_Direccion);

		txt_Telefono = new JTextField();
		txt_Telefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_Telefono.setColumns(10);
		txt_Telefono.setBounds(633, 145, 238, 20);
		panel_datos.add(txt_Telefono);

		txt_contrasena = new JPasswordField();
		txt_contrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_contrasena.setColumns(10);
		txt_contrasena.setBounds(633, 269, 238, 20);
		panel_datos.add(txt_contrasena);

		lblAviso = new JLabel("");
		lblAviso.setBounds(315, 402, 556, 20);
		panel_datos.add(lblAviso);
	}

	public void botonesConf() {
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(168, 201, 240));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						aniadirSocio();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						AdminScreen admin = new AdminScreen();
		                admin.setVisible(true);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void aniadirSocio() {
		try {
			// Este if es como los miembros de este grupo son feos pero sirven
			if (!((txt_DNI.getText().equals("")) || (txt_contrasena.getText().equals(""))
					|| (txt_Nombre.getText().equals("")) || (txt_Apellidos.getText().equals(""))
					|| (txt_Telefono.getText().equals("")) || (txt_Direccion.getText().equals("")))) {

				BufferedWriter bw = new BufferedWriter(new FileWriter("./src/BBDD.txt", true));
				bw.newLine();
				bw.write(txt_DNI.getText() + ":" + txt_contrasena.getText() + ":Socio:" + txt_Nombre.getText() + ":"
						+ txt_Apellidos.getText() + ":" + txt_Telefono.getText() + ":" + txt_Direccion.getText() + ":"
						+ "Alta");
				bw.close();
				JOptionPane.showMessageDialog(null, "Socio creado correctamente", "Socio creado",
						JOptionPane.INFORMATION_MESSAGE);
				int opcionJpane = JOptionPane.showConfirmDialog(null, "¿Quieres crear otro usuario?",
						"¿Crear otro usuario?", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				switch (opcionJpane) {
					case 0:
						txt_DNI.setText("");
						txt_contrasena.setText("");
						txt_Nombre.setText("");
						txt_Apellidos.setText("");
						txt_Telefono.setText("");
						txt_Direccion.setText("");
						break;
					case 1:
					    AdminScreen admin = new AdminScreen();
		                admin.setVisible(true);
						dispose();
						break;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Error al crear el socio\nRellene todos los campos",
						"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}
