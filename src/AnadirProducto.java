import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class AnadirProducto extends JFrame {
    private final JPanel contentPanel = new JPanel();
    private JTextField txt_Nombre,txt_Precio,txt_Cantidad;
    private JPanel panel_datos;
    private JLabel lbl_Nombre, lbl_Precio, lbl_Cantidad, lbl_Descripcion, lblAviso, lbl_ID, lbl_Foto, lbl_Ruta;
    private JTextField txt_ID;
    private JTextArea textArea_Descripcion;
    private JButton btn_Foto;
    private JFileChooser imagenElegida;
    private JLabel lbl_Talla;
    private JTextField txt_Talla;
    private JLabel lblBackground;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AnadirProducto frame = new AnadirProducto();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    frame.setTitle("Anadir producto");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AnadirProducto() {
        cargarPanelPrin();
        cargarPanelSec();
        cargarJLabels();
        cargarTextFields();
        botonImagen();
     //   botonesConf();
        insertarImagen();
        limitarCaracteres();
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

        panel_datos = new JPanel();
        panel_datos.setBackground(new Color(158, 232, 134));
        panel_datos.setBounds(0, 0, 906, 481);
        contentPanel.add(panel_datos);
        panel_datos.setLayout(null);
    }

    public void cargarJLabels() {
        lbl_Nombre = new JLabel("Nombre");
        lbl_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Nombre.setBounds(47, 147, 102, 29);
        panel_datos.add(lbl_Nombre);

        lbl_Precio = new JLabel("Precio (euros)");
        lbl_Precio.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Precio.setBounds(47, 186, 119, 29);
        panel_datos.add(lbl_Precio);

        lbl_Cantidad = new JLabel("Cantidad");
        lbl_Cantidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Cantidad.setBounds(47, 264, 119, 29);
        panel_datos.add(lbl_Cantidad);
        
        lbl_Descripcion = new JLabel("Descripcion del producto");
        lbl_Descripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Descripcion.setBounds(588, 81, 193, 29);
        panel_datos.add(lbl_Descripcion);
        
        lbl_ID = new JLabel("ID");
        lbl_ID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_ID.setBounds(47, 124, 45, 13);
        panel_datos.add(lbl_ID);
        
        lblAviso = new JLabel("");
        lblAviso.setBounds(315, 402, 556, 20);
        panel_datos.add(lblAviso);
        
        lbl_Foto = new JLabel("Foto");
        lbl_Foto.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Foto.setBounds(47, 303, 119, 29);
        panel_datos.add(lbl_Foto);
        
        lbl_Ruta = new JLabel("");
        lbl_Ruta.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Ruta.setBounds(47, 342, 375, 29);
        panel_datos.add(lbl_Ruta);
        
        lbl_Talla = new JLabel("Talla/Tamano");
        lbl_Talla.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Talla.setBounds(47, 225, 119, 29);
        panel_datos.add(lbl_Talla);
    }

    public void cargarTextFields() {
        txt_Nombre = new JTextField();
        txt_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Nombre.setBounds(184, 156, 238, 20);
        panel_datos.add(txt_Nombre);
        txt_Nombre.setColumns(10);

        txt_Precio = new JTextField();
        txt_Precio.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Precio.setColumns(10);
        txt_Precio.setBounds(184, 190, 238, 20);
        panel_datos.add(txt_Precio);

        txt_Cantidad = new JTextField();
        txt_Cantidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Cantidad.setColumns(10);
        txt_Cantidad.setBounds(184, 268, 238, 20);
        panel_datos.add(txt_Cantidad);

        txt_ID = new JTextField();
        txt_ID.setBounds(184, 123, 238, 19);
        panel_datos.add(txt_ID);
        txt_ID.setColumns(10);
        
        textArea_Descripcion = new JTextArea();
        textArea_Descripcion.setLineWrap(true);
        textArea_Descripcion.setBounds(588, 120, 262, 238);
        panel_datos.add(textArea_Descripcion);
        
        txt_Talla = new JTextField();
        txt_Talla.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Talla.setColumns(10);
        txt_Talla.setBounds(184, 229, 238, 20);
        panel_datos.add(txt_Talla);
    }
    
    public void botonImagen() {
        btn_Foto = new JButton("Insertar Imagen");
        btn_Foto.setBounds(187, 308, 131, 23);
        panel_datos.add(btn_Foto); 
        JButton okButton = new JButton("OK");
        okButton.setLocation(689, 432);
        okButton.setSize(72, 20);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pattern numero = Pattern.compile("^[0-9]*$");
                Matcher sacarNum = numero.matcher(txt_ID.getText());
                boolean comprobar = sacarNum.find();
                Matcher sacarCantidad = numero.matcher(txt_Cantidad.getText());
                boolean comprobarCant = sacarCantidad.find();
                Matcher sacarPrecio = numero.matcher(txt_Precio.getText());
                boolean comprobarPre = sacarPrecio.find();
                if(comprobar && comprobarCant && comprobarPre) {
                    aniadirEvento();
                    guardarFoto();
                }else if(!comprobar){
                    JOptionPane.showMessageDialog(null, "Error al crear el producto\nEl identificador debe de ser numerico",
                            "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }else if(!comprobarCant) {
                    JOptionPane.showMessageDialog(null, "Error al crear el producto\nLa cantidad debe de ser numerica",
                            "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }else if(!comprobarPre) {
                    JOptionPane.showMessageDialog(null, "Error al crear el producto\nEl precio debe de ser numerico",
                            "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        okButton.setActionCommand("OK");
        panel_datos.add(okButton);
        getRootPane().setDefaultButton(okButton);
        {
            JButton cancelButton = new JButton("Cancelar");
            cancelButton.setLocation(771, 432);
            cancelButton.setSize(79, 20);
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    AdminEconomia admin = new AdminEconomia();
                    admin.setVisible(true);
                    admin.setLocationRelativeTo(null);
                   dispose();
                }
            });
            cancelButton.setActionCommand("Cancel");
            panel_datos.add(cancelButton);
        }
        
        lblBackground = new JLabel("");
        lblBackground.setIcon(new ImageIcon(AnadirProducto.class.getResource("/resources/Backgrounds/redimensionado2.png")));
        lblBackground.setBounds(0, 0, 906, 481);
        panel_datos.add(lblBackground);}
   
    

//    public void botonesConf() {
//        {
//            JPanel buttonPane = new JPanel();
//            buttonPane.setBackground(new Color(168, 201, 240));
//            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
//            getContentPane().add(buttonPane, BorderLayout.SOUTH);
//            {
//              
//        }
//    }
//    
    public void insertarImagen() {
        btn_Foto.addActionListener(new ActionListener() {
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
    
    public void aniadirEvento() {
        try {
            // Este if es como los miembros de este grupo son feos pero sirven
            if ((!(textArea_Descripcion.getText().equals("") || (txt_Cantidad.getText().equals(""))
                    || (txt_Nombre.getText().equals("")) || (txt_Precio.getText().equals(""))
                    || (txt_ID.getText().equals("")) || (lbl_Ruta.getText().equals(""))))) {

                BufferedWriter bw = new BufferedWriter(new FileWriter("./src/Economia.txt", true));
                bw.newLine();
                bw.write(txt_ID.getText() + ":" + txt_Nombre.getText() + ":" + textArea_Descripcion.getText() + ":"
                        + txt_Precio.getText() + ":" + txt_Cantidad.getText() + ":" + lbl_Ruta.getText());
                bw.close();
                JOptionPane.showMessageDialog(null, "Producto creado correctamente", "Producto creado",
                        JOptionPane.INFORMATION_MESSAGE);
                int opcionJpane = JOptionPane.showConfirmDialog(null, "Quieres crear otro producto?",
                        "Crear otro producto?", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                switch (opcionJpane) {
                    case 0:
                        textArea_Descripcion.setText("");
                        txt_Precio.setText("");
                        txt_Nombre.setText("");
                        txt_Cantidad.setText("");
                        txt_ID.setText("");
                        break;
                    case 1:
                        AdminEconomia admin = new AdminEconomia();
                        admin.setVisible(true);
                        admin.setLocationRelativeTo(null);
                       dispose();
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al crear el producto\nRellene todos los campos",
                        "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }
    public void limitarCaracteres() {
        textArea_Descripcion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(textArea_Descripcion.getText().length() >= 100) {
                    e.consume();    
                }
            }
        });
        txt_ID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_ID.getText().length() >= 4) {
                    e.consume();    
                }
            }
        });
        txt_Cantidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_Cantidad.getText().length() >= 4) {
                    e.consume();    
                }
            }
        });
        txt_Nombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_Nombre.getText().length() >= 30) {
                    e.consume();    
                }
            }
        });
        txt_Precio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_Precio.getText().length() >= 4) {
                    e.consume();    
                }
            }
        });
    }
    
    public void guardarFoto() {
        File origen = new File(lbl_Ruta.getText());
        File destino = new File("./src/resources/fotoProductos");

        try {
                InputStream in = new FileInputStream(origen);
                OutputStream out = new FileOutputStream(destino);

                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                }

                in.close();
                out.close();
        } catch (IOException ioe){
                ioe.printStackTrace();
        }
    }
}
