import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class A�adirSocios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txt_Nombre,txt_Apellidos,txt_DNI,txt_Direccion,txt_Telefono,txt_contrase�a, txt_Email, txt_contrase�aM;
	private JPanel panel_datos;
	private JLabel lbl_datos, lbl_Nombre, lbl_Apellidos, lbl_DNI, lbl_Direccion, lbl_Telefono, lbl_Contrase�a, lbl_Aviso, lbl_Imagen, lbl_Ruta, lbl_Sexo, lbl_Email;
	private JButton btn_InsertarImagen;
	private ButtonGroup btngrp_sexo = new ButtonGroup();
	private JRadioButton rdbtn_Macho, rdbtn_Hembra;
	private JFileChooser imagenElegida;
	private boolean mostrar = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			A�adirSocios dialog = new A�adirSocios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public A�adirSocios() {
		cargarPanelPrin();
		cargarPanelSec();
		cargarJLabels();
		cargarTextFields();
		cargarBotones();
		botonesConf();
		limitarCaracteres();
		grupoBotones();
		insertarImagen();
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
		lbl_Apellidos.setBounds(56, 102, 193, 29);
		panel_datos.add(lbl_Apellidos);

		lbl_DNI = new JLabel("DNI");
		lbl_DNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_DNI.setBounds(56, 234, 193, 29);
		panel_datos.add(lbl_DNI);

		lbl_Direccion = new JLabel("Direcci�n");
		lbl_Direccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Direccion.setBounds(486, 35, 193, 29);
		panel_datos.add(lbl_Direccion);

		lbl_Telefono = new JLabel("Tel�fono");
		lbl_Telefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Telefono.setBounds(486, 141, 193, 29);
		panel_datos.add(lbl_Telefono);

		lbl_Contrase�a = new JLabel("Contrase�a");
		lbl_Contrase�a.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Contrase�a.setBounds(486, 234, 193, 29);
		panel_datos.add(lbl_Contrase�a);
		
		lbl_Sexo = new JLabel("Sexo");
        lbl_Sexo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Sexo.setBounds(56, 297, 193, 29);
        panel_datos.add(lbl_Sexo);
        
        lbl_Imagen = new JLabel("Foto");
        lbl_Imagen.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Imagen.setBounds(486, 297, 109, 29);
        panel_datos.add(lbl_Imagen);
        
        lbl_Ruta = new JLabel("");
        lbl_Ruta.setBounds(738, 306, 133, 14);
        panel_datos.add(lbl_Ruta);
        
        lbl_Email = new JLabel("Email");
        lbl_Email.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Email.setBounds(56, 175, 193, 29);
        panel_datos.add(lbl_Email);
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
		txt_Apellidos.setBounds(179, 106, 238, 20);
		panel_datos.add(txt_Apellidos);

		txt_DNI = new JTextField();
		txt_DNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_DNI.setColumns(10);
		txt_DNI.setBounds(179, 238, 238, 20);
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

		txt_contrase�a = new JPasswordField();
		txt_contrase�a.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_contrase�a.setColumns(10);
		txt_contrase�a.setBounds(633, 238, 238, 20);
		panel_datos.add(txt_contrase�a);
		
        txt_Email = new JTextField();
        txt_Email.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Email.setColumns(10);
        txt_Email.setBounds(179, 179, 238, 20);
        panel_datos.add(txt_Email);
		txt_contrase�aM = new JTextField();
        txt_contrase�aM.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_contrase�aM.setColumns(10);
        txt_contrase�aM.setBounds(633, 269, 238, 20);
        panel_datos.add(txt_contrase�aM);
        txt_contrase�aM.setVisible(false);

		lbl_Aviso = new JLabel("");
		lbl_Aviso.setBounds(315, 402, 556, 20);
		panel_datos.add(lbl_Aviso);
		
		
	}
	public void cargarBotones() {
	    rdbtn_Macho = new JRadioButton("Hombre");
        rdbtn_Macho.setBounds(179, 302, 109, 23);
        panel_datos.add(rdbtn_Macho);
        
        rdbtn_Hembra = new JRadioButton("Mujer");
        rdbtn_Hembra.setBounds(315, 302, 109, 23);
        panel_datos.add(rdbtn_Hembra);
        
        btn_InsertarImagen = new JButton("Insertar foto");
        btn_InsertarImagen.setBounds(633, 302, 95, 23);
        panel_datos.add(btn_InsertarImagen);

	}
	
	public void grupoBotones() {
	    btngrp_sexo.add(rdbtn_Macho);
	    btngrp_sexo.add(rdbtn_Hembra);
		JButton btn_mostrarContra = new JButton("OJO");
		btn_mostrarContra.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if (!mostrar) {
		            txt_contrase�aM.setText(txt_contrase�a.getText());
		            txt_contrase�a.setVisible(false);
		            txt_contrase�aM.setVisible(true);
		            mostrar=true;
                }else {
                    txt_contrase�a.setText(txt_contrase�aM.getText());
                    txt_contrase�a.setVisible(true);
                    txt_contrase�aM.setVisible(false);
                    mostrar=false;
                }
		    }
		});
		btn_mostrarContra.setBounds(643, 305, 69, 21);
		panel_datos.add(btn_mostrarContra);
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
					    Pattern numero = Pattern.compile("^[0-9]*$");
					    Pattern correo = Pattern.compile("^(.+)@(.+)$");
					    Matcher sacarNum = numero.matcher(txt_Telefono.getText());
					    Matcher sacarCorreo = correo.matcher(txt_Email.getText());
					    boolean comprobar = sacarNum.find();
					    boolean comprobarCorreo = sacarCorreo.find();
					    if(comprobar && comprobarCorreo) {
					        aniadirSocio();
					    }else if(!comprobar) {
					        JOptionPane.showMessageDialog(null, "Error al crear el socio\nIntroduzca un n�mero correcto",
			                        "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
					    }else if(!comprobarCorreo) {
                            JOptionPane.showMessageDialog(null, "Error al crear el socio\nIntroduzca un correo correcto",
                                    "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                        }
						
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
	public void insertarImagen() {
	    btn_InsertarImagen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String path = null;
                imagenElegida = new JFileChooser();
                //Carpeta que se abre por defecto
                imagenElegida.setCurrentDirectory(new File(System.getProperty("user.home")));
                
                FileNameExtensionFilter extensiones = new FileNameExtensionFilter("*Images", "png");
                imagenElegida.addChoosableFileFilter(extensiones);
                
                int estado = imagenElegida.showSaveDialog(null);
                
                if(estado == JFileChooser.APPROVE_OPTION) {
                    File imagenSeleccionada = imagenElegida.getSelectedFile();
                    path = imagenSeleccionada.getAbsolutePath();
                    lbl_Ruta.setText(path);
                }
            }
        });
    }

	public void aniadirSocio() {
		try {
			// Este if es como los miembros de este grupo son feos pero sirven
			if (!((txt_DNI.getText().equals("")) || (txt_contrase�a.getText().equals(""))
					|| (txt_Nombre.getText().equals("")) || (txt_Apellidos.getText().equals(""))
					|| (txt_Telefono.getText().equals("")) || (txt_Direccion.getText().equals("")) || (lbl_Ruta.getText().equals("")))) {
				BufferedWriter bw = new BufferedWriter(new FileWriter("./src/BBDD.txt", true));
				bw.newLine();
				if (mostrar) {
				    bw.write(txt_DNI.getText() + ":" + txt_contrase�aM.getText() + ":Socio:" + txt_Nombre.getText() + ":"
	                        + txt_Apellidos.getText() + ":" + txt_Telefono.getText() + ":" + txt_Direccion.getText() + ":"
	                        + "Alta");
                }else {
                    bw.write(txt_DNI.getText() + ":" + txt_contrase�a.getText() + ":Socio:" + txt_Nombre.getText() + ":"
                            + txt_Apellidos.getText() + ":" + txt_Telefono.getText() + ":" + txt_Direccion.getText() + ":"
                            + "Alta");
                }
				
				bw.close();
				JOptionPane.showMessageDialog(null, "Socio creado correctamente", "Socio creado",
						JOptionPane.INFORMATION_MESSAGE);
				int opcionJpane = JOptionPane.showConfirmDialog(null, "�Quieres crear otro socio?",
						"�Crear otro usuario?", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);
				switch (opcionJpane) {
					case 0:
						txt_DNI.setText("");
						txt_contrase�a.setText("");
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
	public void limitarCaracteres() {
	    txt_Nombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_Nombre.getText().length() >= 20) {
                    e.consume();    
                }
            }
        });
	    txt_Apellidos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_Apellidos.getText().length() >= 20) {
                    e.consume();    
                }
            }
        });
	    txt_contrase�a.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_contrase�a.getText().length() >= 10) {
                    e.consume();    
                }
            }
        });
	    txt_Direccion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_Direccion.getText().length() >= 30) {
                    e.consume();    
                }
            }
        });
	    txt_DNI.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_DNI.getText().length() >= 9) {
                    e.consume();    
                }
            }
        });
	    txt_Telefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_Telefono.getText().length() >= 9) {
                    e.consume();    
                }
            }
        });
	    txt_Email.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_Email.getText().length() >= 40) {
                    e.consume();    
                }
            }
        });
	}
}
