import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class MostrarEventos extends JDialog {
    
    private final JPanel contentPanel = new JPanel();
    public JTextField txt_ID, txt_Nombre, txt_Descripcion, txt_Fecha, txt_Plazas;  //Separar txtFechas en dos, poner numero apuntados, cambiar descripcion a textarea
    private JPanel panel_datos;
    private JLabel lbl_ID, lbl_Nombre, lbl_Descripcion, lbl_Fecha, lbl_Plazas, lblAviso;
    private String ID;
    private JLabel lblMostrarSocios;
    private JLabel lblBackground;

    public MostrarEventos(String ID) {
        this.ID = ID;
        cargarPanelPrin();
        cargarPanelSec();
        cargarJLabels();
        cargarTextFields();
       // botonesConf();
        leerEvento();

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
        setResizable(false);
        panel_datos = new JPanel();
        panel_datos.setBackground(new Color(217, 217, 217));
        panel_datos.setBounds(0, 0, 906, 481);
        contentPanel.add(panel_datos);
        panel_datos.setLayout(null);
        JButton okButton = new JButton("OK");
        okButton.setLocation(817, 432);
        okButton.setSize(54, 20);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarEvento();
                dispose();
                AdminEvents admin = new AdminEvents();
                admin.setVisible(true);
                admin.setLocationRelativeTo(null);
                dispose();
            }
        });
        okButton.setActionCommand("OK");
        panel_datos.add(okButton);
        getRootPane().setDefaultButton(okButton);
  
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setLocation(736, 432);
        cancelButton.setSize(71, 20);
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
        panel_datos.add(cancelButton);
    }
    

    public void cargarJLabels() {
        lbl_ID = new JLabel("ID");
        lbl_ID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_ID.setBounds(56, 122, 193, 29);
        panel_datos.add(lbl_ID);

        lbl_Nombre = new JLabel("Nombre");
        lbl_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Nombre.setBounds(56, 175, 193, 29);
        panel_datos.add(lbl_Nombre);

        lbl_Descripcion = new JLabel("Descripcion");
        lbl_Descripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Descripcion.setBounds(56, 228, 113, 29);
        panel_datos.add(lbl_Descripcion);

        lbl_Fecha = new JLabel("Fecha");
        lbl_Fecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Fecha.setBounds(486, 175, 193, 29);
        panel_datos.add(lbl_Fecha);

        lbl_Plazas = new JLabel("Plazas");
        lbl_Plazas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Plazas.setBounds(486, 228, 193, 29);
        panel_datos.add(lbl_Plazas);
    }

    public void cargarTextFields() {
        txt_ID = new JTextField();
        txt_ID.setEditable(false);
        txt_ID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_ID.setBounds(179, 126, 238, 20);
        panel_datos.add(txt_ID);
        txt_ID.setColumns(10);

        txt_Nombre = new JTextField();
        txt_Nombre.setEditable(false);
        txt_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Nombre.setColumns(10);
        txt_Nombre.setBounds(179, 179, 238, 20);
        panel_datos.add(txt_Nombre);

        txt_Descripcion = new JTextField();
        txt_Descripcion.setEditable(false);
        txt_Descripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Descripcion.setColumns(10);
        txt_Descripcion.setBounds(179, 237, 238, 20);
        panel_datos.add(txt_Descripcion);

        txt_Fecha = new JTextField();
        txt_Fecha.setEditable(false);
        txt_Fecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Fecha.setColumns(10);
        txt_Fecha.setBounds(633, 179, 238, 20);
        panel_datos.add(txt_Fecha);

        txt_Plazas = new JTextField();
        txt_Plazas.setEditable(false);
        txt_Plazas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Plazas.setColumns(10);
        txt_Plazas.setBounds(633, 232, 238, 20);
        panel_datos.add(txt_Plazas);

        lblAviso = new JLabel("");
        lblAviso.setBounds(315, 402, 556, 20);
        panel_datos.add(lblAviso);
        
        lblMostrarSocios = new JLabel("Evento:");
        lblMostrarSocios.setFont(new Font("Roboto", Font.BOLD, 25));
        lblMostrarSocios.setBounds(407, 10, 92, 38);
        panel_datos.add(lblMostrarSocios);
        
        lblBackground = new JLabel("");
        lblBackground.setIcon(new ImageIcon(MostrarEventos.class.getResource("/resources/Backgrounds/redimensionado2.png")));
        lblBackground.setBounds(0, 0, 906, 481);
        panel_datos.add(lblBackground);
    }
//
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

    public void leerEvento() {
        String linea;
        try {

            BufferedReader br = new BufferedReader(new FileReader("./src/Eventos.txt"));
            while ((linea = br.readLine()) != null) {
                String[] parte = linea.split(":");
                if (ID.equals(parte[0])) {
                    txt_ID.setText(parte[0]);
                    txt_Nombre.setText(parte[1]);
                    txt_Descripcion.setText(parte[2]);
                    txt_Fecha.setText(parte[3]);
                    txt_Plazas.setText(parte[4]);
                }
            }
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    public void modificarEvento() {
        try {
            File fichero = new File("./src/Eventos.txt");
            File ficherotmp = new File("./src/Eventostmp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(fichero));
            BufferedWriter writer = new BufferedWriter(new FileWriter(ficherotmp));
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] parte = linea.split(":");
                if (ID.equals(parte[0])) {
                    if (!((txt_Nombre.getText().equals("")||(txt_Descripcion.getText().equals(""))||(txt_Fecha.getText().equals(""))||(txt_Plazas.getText().equals(""))))) {

                        writer.write(parte[0] + ":" + txt_Nombre.getText() + txt_Descripcion.getText() + ":"
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
}