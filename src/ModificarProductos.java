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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ModificarProductos extends JFrame {

    private final JPanel contentPanel = new JPanel();
    private JTextField txt_Nombre,txt_Precio,txt_Cantidad;
    private JPanel panel_datos;
    private JLabel lbl_datos, lbl_Nombre, lbl_Precio, lbl_Cantidad, lbl_Descripcion, lblAviso, lbl_ID, lbl_Foto, lbl_Ruta;
    private JTextField txt_ID;
    private JTextArea textArea_Descripcion;
    private String productos;
    /**
     * Launch the application.
     */
    

    /**
     * Create the frame.
     */
    public ModificarProductos(String Producto) {
        this.productos = Producto;
        cargarPanelPrin();
        cargarPanelSec();
        cargarJLabels();
        cargarTextFields();
        botonesConf();
        leerProducto();

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
        lbl_datos = new JLabel("Modificar producto");
        lbl_datos.setFont(new Font("Tahoma", Font.PLAIN, 36));
        lbl_datos.setBounds(34, 26, 420, 44);
        contentPanel.add(lbl_datos);

        panel_datos = new JPanel();
        panel_datos.setBackground(new Color(158, 232, 134));
        panel_datos.setBounds(10, 81, 884, 354);
        contentPanel.add(panel_datos);
        panel_datos.setLayout(null);
    }

    public void cargarJLabels() {
        lbl_Nombre = new JLabel("Nombre");
        lbl_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Nombre.setBounds(42, 115, 193, 29);
        panel_datos.add(lbl_Nombre);

        lbl_Precio = new JLabel("Precio (euros)");
        lbl_Precio.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Precio.setBounds(42, 187, 193, 29);
        panel_datos.add(lbl_Precio);

        lbl_Cantidad = new JLabel("Cantidad");
        lbl_Cantidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Cantidad.setBounds(42, 246, 193, 29);
        panel_datos.add(lbl_Cantidad);
        
        lbl_Descripcion = new JLabel("Descripcion del producto");
        lbl_Descripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Descripcion.setBounds(582, 35, 193, 29);
        panel_datos.add(lbl_Descripcion);
        
        lbl_ID = new JLabel("ID");
        lbl_ID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_ID.setBounds(42, 60, 45, 13);
        panel_datos.add(lbl_ID);
        
        lblAviso = new JLabel("");
        lblAviso.setBounds(315, 402, 556, 20);
        panel_datos.add(lblAviso);
        
        lbl_Foto = new JLabel("Foto");
        lbl_Foto.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Foto.setBounds(42, 299, 193, 29);
        panel_datos.add(lbl_Foto);
        
        lbl_Ruta = new JLabel("");
        lbl_Ruta.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Ruta.setBounds(183, 299, 165, 29);
        panel_datos.add(lbl_Ruta);
    }

    public void cargarTextFields() {
        txt_Nombre = new JTextField();
        txt_Nombre.setEditable(false);
        txt_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Nombre.setBounds(179, 119, 238, 20);
        panel_datos.add(txt_Nombre);
        txt_Nombre.setColumns(10);

        txt_Precio = new JTextField();
        txt_Precio.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Precio.setColumns(10);
        txt_Precio.setBounds(179, 191, 238, 20);
        panel_datos.add(txt_Precio);

        txt_Cantidad = new JTextField();
        txt_Cantidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Cantidad.setColumns(10);
        txt_Cantidad.setBounds(179, 250, 238, 20);
        panel_datos.add(txt_Cantidad);

        txt_ID = new JTextField();
        txt_ID.setEditable(false);
        txt_ID.setBounds(179, 59, 238, 19);
        panel_datos.add(txt_ID);
        txt_ID.setColumns(10);
        
        textArea_Descripcion = new JTextArea();
        textArea_Descripcion.setLineWrap(true);
        textArea_Descripcion.setEditable(false);
        textArea_Descripcion.setBounds(582, 75, 262, 238);
        panel_datos.add(textArea_Descripcion);
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
                        modificarProducto();
                        dispose();
                        AdminScreen admin = new AdminScreen();
                        admin.setVisible(true);
                        admin.setLocationRelativeTo(null);
                        dispose();
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
                        admin.setLocationRelativeTo(null);
                        dispose();
                    }

                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

    public void leerProducto() {
        String linea;
        try {

            BufferedReader br = new BufferedReader(
                    new FileReader("./src/Economia.txt"));
            while ((linea = br.readLine()) != null) {
                String[] parte = linea.split(":");
                if (productos.equals(parte[0])) {
                    txt_Nombre.setText(parte[1]);
                    textArea_Descripcion.setText(parte[2]);
                    txt_Precio.setText(parte[3]);
                    txt_Cantidad.setText(parte[4]);
                    lbl_Ruta.setText(parte[5]);
                }
            }
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    public void modificarProducto() {
        try {
            File fichero = new File("./src/Economia.txt");
            File ficherotmp = new File("./src/Economiatmp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(fichero));
            BufferedWriter writer = new BufferedWriter(new FileWriter(ficherotmp));
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] parte = linea.split(":");
                if (productos.equals(parte[0])) {
                    if (!(txt_Precio.getText().equals(""))) {

                        writer.write(parte[0] + ":" + parte[1] + ":" + txt_Precio.getText() + ":" + parte[3] + ":"
                                + parte[4] + ":" + parte[5] + "\n");

                    }

                }
                if (productos.equals(parte[0]))
                    continue;
                writer.write(linea + System.getProperty("line.separator"));

            }
            writer.close();
            reader.close();

        } catch (IOException exception) {
            System.out.println(exception);
        }

        try {
            File fichero = new File("./src/Economia.txt");
            File ficherotmp = new File("./src/Economiatmp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(ficherotmp));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fichero));
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] parte = linea.split(":");
                writer.write(linea + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
            ficherotmp.delete();
        } catch (IOException exception) {
            System.out.println(exception);
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
}
