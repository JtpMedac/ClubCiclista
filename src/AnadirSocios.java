import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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

public class AnadirSocios extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txt_Nombre,txt_Apellidos,txt_DNI,txt_Direccion,txt_Telefono,txt_contrasena, txt_Email, txt_contrasenaM;
    private JPanel panel_datos;
    private JLabel lbl_Nombre, lbl_Apellidos, lbl_DNI, lbl_Direccion, lbl_Telefono, lbl_Contrasena, lbl_Aviso, lbl_Imagen, lbl_Ruta, lbl_Sexo, lbl_Email;
    private JButton btn_InsertarImagen;
    private ButtonGroup btngrp_sexo = new ButtonGroup();
    private JRadioButton rdbtn_Macho, rdbtn_Hembra;
    private JFileChooser imagenElegida;
    private boolean mostrar = false;
    private JLabel lbl_datos;
    private JLabel lblBackground;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            AnadirSocios dialog = new AnadirSocios();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            dialog.setLocationRelativeTo(null);
            dialog.setTitle("Nuevo Socio");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public AnadirSocios() {
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
        setMinimumSize(new Dimension(906, 349));
        setPreferredSize(new Dimension(920, 440));
        setResizable(false);
        setSize(new Dimension(906, 341));
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(168, 201, 240));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
    }

    public void cargarPanelSec() {

        panel_datos = new JPanel();
        panel_datos.setBackground(new Color(217, 217, 217));
        panel_datos.setBounds(0, 0, 906, 313);
        contentPanel.add(panel_datos);
        panel_datos.setLayout(null);
        
    }

    public void cargarJLabels() {
        lbl_Nombre = new JLabel("Nombre");
        lbl_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Nombre.setBounds(56, 50, 78, 29);
        panel_datos.add(lbl_Nombre);

        lbl_Apellidos = new JLabel("Apellidos");
        lbl_Apellidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Apellidos.setBounds(56, 89, 78, 29);
        panel_datos.add(lbl_Apellidos);

        lbl_DNI = new JLabel("DNI");
        lbl_DNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_DNI.setBounds(56, 162, 45, 29);
        panel_datos.add(lbl_DNI);

        lbl_Direccion = new JLabel("Direccion");
        lbl_Direccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Direccion.setBounds(486, 55, 100, 29);
        panel_datos.add(lbl_Direccion);

        lbl_Telefono = new JLabel("Telefono");
        lbl_Telefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Telefono.setBounds(486, 89, 109, 29);
        panel_datos.add(lbl_Telefono);

        lbl_Contrasena = new JLabel("Contrasena");
        lbl_Contrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Contrasena.setBounds(486, 128, 115, 29);
        panel_datos.add(lbl_Contrasena);
        
        lbl_Sexo = new JLabel("Sexo");
        lbl_Sexo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Sexo.setBounds(56, 201, 55, 29);
        panel_datos.add(lbl_Sexo);
        
        lbl_Imagen = new JLabel("Foto");
        lbl_Imagen.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Imagen.setBounds(486, 196, 109, 29);
        panel_datos.add(lbl_Imagen);
        
        lbl_Ruta = new JLabel("");
        lbl_Ruta.setBounds(738, 306, 133, 14);
        panel_datos.add(lbl_Ruta);
        
        lbl_Email = new JLabel("Email");
        lbl_Email.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Email.setBounds(56, 123, 78, 29);
        panel_datos.add(lbl_Email);
    }

    public void cargarTextFields() {
        txt_Nombre = new JTextField();
        txt_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Nombre.setBounds(144, 59, 238, 20);
        panel_datos.add(txt_Nombre);
        txt_Nombre.setColumns(10);

        txt_Apellidos = new JTextField();
        txt_Apellidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Apellidos.setColumns(10);
        txt_Apellidos.setBounds(144, 93, 238, 20);
        panel_datos.add(txt_Apellidos);

        txt_DNI = new JTextField();
        txt_DNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_DNI.setColumns(10);
        txt_DNI.setBounds(144, 166, 238, 20);
        panel_datos.add(txt_DNI);

        txt_Direccion = new JTextField();
        txt_Direccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Direccion.setColumns(10);
        txt_Direccion.setBounds(633, 59, 238, 20);
        panel_datos.add(txt_Direccion);

        txt_Telefono = new JTextField();
        txt_Telefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Telefono.setColumns(10);
        txt_Telefono.setBounds(633, 93, 238, 20);
        panel_datos.add(txt_Telefono);

        txt_contrasena = new JPasswordField();
        txt_contrasena.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_contrasena.setColumns(10);
        txt_contrasena.setBounds(633, 137, 238, 20);
        panel_datos.add(txt_contrasena);
        
        txt_Email = new JTextField();
        txt_Email.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Email.setColumns(10);
        txt_Email.setBounds(144, 127, 238, 20);
        panel_datos.add(txt_Email);
        txt_contrasenaM = new JTextField();
        txt_contrasenaM.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_contrasenaM.setColumns(10);
        txt_contrasenaM.setBounds(633, 137, 238, 20);
        panel_datos.add(txt_contrasenaM);
        txt_contrasenaM.setVisible(false);

        lbl_Aviso = new JLabel("");
        lbl_Aviso.setBounds(486, 246, 385, 20);
        panel_datos.add(lbl_Aviso);
        
        
    }
    public void cargarBotones() {
        rdbtn_Macho = new JRadioButton("Hombre");
        rdbtn_Macho.setBounds(144, 201, 109, 23);
        panel_datos.add(rdbtn_Macho);
        
        rdbtn_Hembra = new JRadioButton("Mujer");
        rdbtn_Hembra.setBounds(273, 201, 109, 23);
        panel_datos.add(rdbtn_Hembra);
        
        btn_InsertarImagen = new JButton("Insertar foto");
        btn_InsertarImagen.setBounds(633, 206, 173, 23);
        panel_datos.add(btn_InsertarImagen);

    }
    
    public void grupoBotones() {
        btngrp_sexo.add(rdbtn_Macho);
        btngrp_sexo.add(rdbtn_Hembra);
        JButton btn_mostrarContra = new JButton("Mostar contrase\u00F1a");
        btn_mostrarContra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!mostrar) {
                    txt_contrasenaM.setText(txt_contrasena.getText());
                    txt_contrasena.setVisible(false);
                    txt_contrasenaM.setVisible(true);
                    mostrar=true;
                }else {
                    txt_contrasena.setText(txt_contrasenaM.getText());
                    txt_contrasena.setVisible(true);
                    txt_contrasenaM.setVisible(false);
                    mostrar=false;
                }
            }
        });
        btn_mostrarContra.setBounds(633, 168, 238, 21);
        panel_datos.add(btn_mostrarContra);
        
        lbl_datos = new JLabel("Datos del nuevo socio");
        lbl_datos.setFont(new Font("Roboto", Font.BOLD, 25));
        lbl_datos.setBounds(324, 0, 257, 44);
        panel_datos.add(lbl_datos);
        
        lblBackground = new JLabel("");
        lblBackground.setIcon(new ImageIcon(AnadirSocios.class.getResource("/resources/Backgrounds/redimensionado2.png")));
        lblBackground.setBounds(0, -154, 896, 467);
        panel_datos.add(lblBackground);
    }

    public void botonesConf() {
        JButton okButton = new JButton("OK");
        okButton.setSize(55, 20);
        okButton.setLocation(711, 276);
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
                    JOptionPane.showMessageDialog(null, "Error al crear el socio\nIntroduzca un numero correcto",
                            "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }else if(!comprobarCorreo) {
                    JOptionPane.showMessageDialog(null, "Error al crear el socio\nIntroduzca un correo correcto",
                            "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        okButton.setActionCommand("OK");
        panel_datos.add(okButton);
        getRootPane().setDefaultButton(okButton);
               
            
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setLocation(776, 276);
                cancelButton.setSize(95, 20);
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        AdminScreen admin = new AdminScreen();
                        admin.setVisible(true);
                        admin.setLocationRelativeTo(null);
                        dispose();
                    }
                });
                cancelButton.setActionCommand("Cancel");
                panel_datos.add(cancelButton);
            }}
    
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
            if (!((txt_DNI.getText().equals("")) || (txt_contrasena.getText().equals(""))
                    || (txt_Nombre.getText().equals("")) || (txt_Apellidos.getText().equals(""))
                    || (txt_Telefono.getText().equals("")) || (txt_Direccion.getText().equals("")) || (lbl_Ruta.getText().equals("")))) {
                BufferedWriter bw = new BufferedWriter(new FileWriter("./src/BBDD.txt", true));
                bw.newLine();
                if (mostrar) {
                    bw.write(txt_DNI.getText() + ":" + txt_contrasenaM.getText() + ":Socio:" + txt_Nombre.getText() + ":"
                            + txt_Apellidos.getText() + ":" + txt_Telefono.getText() + ":" + txt_Direccion.getText() + ":"
                            + "Alta");
                }else {
                    bw.write(txt_DNI.getText() + ":" + txt_contrasena.getText() + ":Socio:" + txt_Nombre.getText() + ":"
                            + txt_Apellidos.getText() + ":" + txt_Telefono.getText() + ":" + txt_Direccion.getText() + ":"
                            + "Alta");
                }
                
                bw.close();
                JOptionPane.showMessageDialog(null, "Socio creado correctamente", "Socio creado",
                        JOptionPane.INFORMATION_MESSAGE);
                int opcionJpane = JOptionPane.showConfirmDialog(null, "Quieres crear otro socio?",
                        "Crear otro usuario?", JOptionPane.YES_NO_OPTION,
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
                        admin.setLocationRelativeTo(null);
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
        txt_contrasena.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_contrasena.getText().length() >= 10) {
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