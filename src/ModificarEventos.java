import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
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

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class ModificarEventos extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txt_Nombre,txt_Fecha,txt_Plazas;
    private JPanel panel_datos;
    private JLabel lbl_datos, lbl_Nombre, lbl_Fecha, lbl_Plazas, lbl_Descripcion, lblAviso, lbl_ID ;
    private JTextField txt_ID;
    private JTextArea textArea_Descripcion;
    private String ID;

    public ModificarEventos(String ID) {
        this.ID = ID;
        cargarPanelPrin();
        cargarPanelSec();
        cargarJLabels();
        cargarTextFields();
        botonesConf();
        limitarCaracteres();
        leerSocio();

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
        lbl_datos = new JLabel("Modificar evento");
        lbl_datos.setFont(new Font("Tahoma", Font.PLAIN, 36));
        lbl_datos.setBounds(34, 26, 415, 44);
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

        lbl_Fecha = new JLabel("Fecha");
        lbl_Fecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Fecha.setBounds(42, 187, 193, 29);
        panel_datos.add(lbl_Fecha);

        lbl_Plazas = new JLabel("Numero de plazas");
        lbl_Plazas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Plazas.setBounds(42, 246, 193, 29);
        panel_datos.add(lbl_Plazas);
        
        lbl_Descripcion = new JLabel("Descripcion del evento");
        lbl_Descripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Descripcion.setBounds(582, 35, 193, 29);
        panel_datos.add(lbl_Descripcion);
        
        lbl_ID = new JLabel("ID");
        lbl_ID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_ID.setBounds(42, 60, 45, 13);
        panel_datos.add(lbl_ID);
    }

    public void cargarTextFields() {
        txt_Nombre = new JTextField();
        txt_Nombre.setEditable(false);
        txt_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Nombre.setBounds(179, 119, 238, 20);
        panel_datos.add(txt_Nombre);
        txt_Nombre.setColumns(10);

        txt_Fecha = new JTextField();
        txt_Fecha.setEditable(false);
        txt_Fecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Fecha.setColumns(10);
        txt_Fecha.setBounds(179, 191, 238, 20);
        panel_datos.add(txt_Fecha);

        txt_Plazas = new JTextField();
        txt_Plazas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Plazas.setColumns(10);
        txt_Plazas.setBounds(179, 250, 238, 20);
        panel_datos.add(txt_Plazas);

        txt_ID = new JTextField();
        txt_ID.setEditable(false);
        txt_ID.setBounds(179, 59, 238, 19);
        panel_datos.add(txt_ID);
        txt_ID.setColumns(10);
        
        textArea_Descripcion = new JTextArea();
        textArea_Descripcion.setLineWrap(true);
        textArea_Descripcion.setBounds(582, 70, 263, 258);
        panel_datos.add(textArea_Descripcion);

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
                        modificarSocio();
                        dispose();
                        AdminEvents admin = new AdminEvents();
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
                        AdminEvents admin = new AdminEvents();
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

    public void leerSocio() {
        String linea;
        try {

            BufferedReader br = new BufferedReader(new FileReader("./src/Eventos.txt"));
            while ((linea = br.readLine()) != null) {
                String[] parte = linea.split(":");
                if (ID.equals(parte[0])) {
                    txt_ID.setText(parte[0]);
                    txt_Nombre.setText(parte[1]);
                    textArea_Descripcion.setText(parte[2]);
                    txt_Fecha.setText(parte[3]);
                    txt_Plazas.setText(parte[4]);
                }
            }
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    public void modificarSocio() {
        try {
            File fichero = new File("./src/Eventos.txt");
            File ficherotmp = new File("./src/Eventostmp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(fichero));
            BufferedWriter writer = new BufferedWriter(new FileWriter(ficherotmp));
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] parte = linea.split(":");
                if (ID.equals(parte[0])) {
                    if (!((txt_Nombre.getText().equals("")||(textArea_Descripcion.getText().equals(""))||(txt_Fecha.getText().equals(""))||(txt_Plazas.getText().equals(""))))) {

                        writer.write(parte[0] + ":" + txt_Nombre.getText() + textArea_Descripcion.getText() + ":"
                                + txt_Fecha.getText() + ":" + txt_Plazas.getText() + ":" + parte[5] + "\n");

                    }

                }
                if (ID.equals(parte[0]))continue;
                writer.write(linea + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();

        } catch (IOException exception) {
            System.out.println(exception);
        }

        try {
            File fichero = new File("./src/Eventos.txt");
            File ficherotmp = new File("./src/Eventostmp.txt");
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
        txt_ID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_ID.getText().length() >= 4) {
                    e.consume();    
                }
            }
        });
        txt_Plazas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_Plazas.getText().length() >= 4) {
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
        txt_Fecha.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_Fecha.getText().length() >= 10) {
                    e.consume();    
                }
            }
        });
    }
}
