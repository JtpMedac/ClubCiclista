import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class AnadirEvento extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txt_Nombre,txt_Fecha,txt_Plazas;
    private JPanel panel_datos;
    private JLabel lbl_Nombre, lbl_Fecha, lbl_Plazas, lbl_Descripcion, lblAviso, lbl_ID ;
    private JTextField txt_ID;
    private JTextArea textArea_Descripcion;
    private JLabel lblNewLabel;
    private JLabel lblBackground;

    public static void main(String[] args) {
        try {
            AnadirEvento dialog = new AnadirEvento();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            dialog.setLocationRelativeTo(null);
            dialog.setTitle("Anadir evento");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public AnadirEvento() {
        cargarPanelPrin();
        cargarPanelSec();
        cargarJLabels();
        cargarTextFields();
        //botonesConf();
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
        panel_datos.setBounds(0, 0, 916, 491);
        contentPanel.add(panel_datos);
        panel_datos.setLayout(null);
        JButton okButton = new JButton("OK");
        okButton.setLocation(705, 432);
        okButton.setSize(81, 20);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pattern numero = Pattern.compile("^[0-9]*$");
                Matcher sacarNum = numero.matcher(txt_ID.getText());
                boolean comprobar = sacarNum.find();
                if(comprobar) {
                    aniadirEvento();
                }else {
                    JOptionPane.showMessageDialog(null, "Error al crear el evento\nEl identificador debe de ser numerico",
                            "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        okButton.setActionCommand("OK");
        panel_datos.add(okButton);
        getRootPane().setDefaultButton(okButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setLocation(796, 432);
        cancelButton.setSize(75, 20);
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
        panel_datos.add(cancelButton);}

    public void cargarJLabels() {
        lbl_Nombre = new JLabel("Nombre");
        lbl_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Nombre.setBounds(48, 140, 139, 29);
        panel_datos.add(lbl_Nombre);

        lbl_Fecha = new JLabel("Fecha");
        lbl_Fecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Fecha.setBounds(48, 170, 139, 29);
        panel_datos.add(lbl_Fecha);

        lbl_Plazas = new JLabel("Numero de plazas");
        lbl_Plazas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Plazas.setBounds(48, 209, 139, 29);
        panel_datos.add(lbl_Plazas);
        
        lbl_Descripcion = new JLabel("Descripcion del evento");
        lbl_Descripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Descripcion.setBounds(512, 74, 193, 29);
        panel_datos.add(lbl_Descripcion);
        
        lbl_ID = new JLabel("ID");
        lbl_ID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_ID.setBounds(48, 117, 45, 13);
        panel_datos.add(lbl_ID);
        
        lblAviso = new JLabel("");
        lblAviso.setBounds(315, 402, 556, 20);
        panel_datos.add(lblAviso);
    }

    public void cargarTextFields() {
        txt_Nombre = new JTextField();
        txt_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Nombre.setBounds(185, 149, 238, 20);
        panel_datos.add(txt_Nombre);
        txt_Nombre.setColumns(10);

        txt_Fecha = new JTextField();
        txt_Fecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Fecha.setColumns(10);
        txt_Fecha.setBounds(185, 179, 238, 20);
        panel_datos.add(txt_Fecha);

        txt_Plazas = new JTextField();
        txt_Plazas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Plazas.setColumns(10);
        txt_Plazas.setBounds(185, 213, 238, 20);
        panel_datos.add(txt_Plazas);

        txt_ID = new JTextField();
        txt_ID.setBounds(185, 116, 238, 19);
        panel_datos.add(txt_ID);
        txt_ID.setColumns(10);
        
        textArea_Descripcion = new JTextArea();
        textArea_Descripcion.setLineWrap(true);
        textArea_Descripcion.setBounds(512, 113, 263, 258);
        panel_datos.add(textArea_Descripcion);
        
        lblNewLabel = new JLabel("Datos del evento");
        lblNewLabel.setFont(new Font("Roboto", Font.BOLD, 25));
        lblNewLabel.setBounds(318, 10, 263, 27);
        panel_datos.add(lblNewLabel);
        
        lblBackground = new JLabel("");
        lblBackground.setIcon(new ImageIcon(AnadirEvento.class.getResource("/resources/Backgrounds/redimensionado2.png")));
        lblBackground.setBounds(0, 0, 906, 481);
        panel_datos.add(lblBackground);
    }

//    public void botonesConf() {
//        {
//            {
//             
//            }
//            {
//               
//                    }
//              
//            }
//        }
//    }

    public void aniadirEvento() {
        try {
            // Este if es como los miembros de este grupo son feos pero sirven
            if ((!(textArea_Descripcion.getText().equals("") || (txt_Plazas.getText().equals(""))
                    || (txt_Nombre.getText().equals("")) || (txt_Fecha.getText().equals(""))
                    || (txt_ID.getText().equals(""))))) {

                BufferedWriter bw = new BufferedWriter(new FileWriter("./src/Eventos.txt", true));
                bw.newLine();
                bw.write(txt_ID.getText() + ":" + txt_Nombre.getText() + ":" + textArea_Descripcion.getText() + ":"
                        + txt_Fecha.getText() + ":" + txt_Plazas.getText() + ":0");
                bw.close();
                JOptionPane.showMessageDialog(null, "Evento creado correctamente", "Evento creado",
                        JOptionPane.INFORMATION_MESSAGE);
                int opcionJpane = JOptionPane.showConfirmDialog(null, "Quieres crear otro evento?",
                        "Crear otro evento?", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                switch (opcionJpane) {
                    case 0:
                        textArea_Descripcion.setText("");
                        txt_Fecha.setText("");
                        txt_Nombre.setText("");
                        txt_Plazas.setText("");
                        txt_ID.setText("");
                        break;
                    case 1:
                        AdminEvents admin = new AdminEvents();
                        admin.setVisible(true);
                        admin.setLocationRelativeTo(null);
                       dispose();
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al crear el evento\nRellene todos los campos",
                        "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            System.out.println(e);
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