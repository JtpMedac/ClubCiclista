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

public class AñadirEvento extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JTextField txt_Nombre,txt_Fecha,txt_Plazas,txt_Descripcion;
    private JPanel panel_datos;
    private JLabel lbl_datos, lbl_Nombre, lbl_Fecha, lbl_Plazas, lbl_Descripcion, lblAviso, lbl_ID ;
    private JTextField txt_ID;

    public static void main(String[] args) {
        try {
            AñadirEvento dialog = new AñadirEvento();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public AñadirEvento() {
        cargarPanelPrin();
        cargarPanelSec();
        cargarJLabels();
        cargarTextFields();
        botonesConf();
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
        lbl_datos = new JLabel("Datos del nuevo evento");
        lbl_datos.setFont(new Font("Tahoma", Font.PLAIN, 36));
        lbl_datos.setBounds(34, 26, 364, 44);
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

        lbl_Plazas = new JLabel("Número de plazas");
        lbl_Plazas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbl_Plazas.setBounds(42, 246, 193, 29);
        panel_datos.add(lbl_Plazas);
        
        lbl_Descripcion = new JLabel("Descripción del evento");
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
    }

    public void cargarTextFields() {
        txt_Nombre = new JTextField();
        txt_Nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Nombre.setBounds(179, 119, 238, 20);
        panel_datos.add(txt_Nombre);
        txt_Nombre.setColumns(10);

        txt_Fecha = new JTextField();
        txt_Fecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Fecha.setColumns(10);
        txt_Fecha.setBounds(179, 191, 238, 20);
        panel_datos.add(txt_Fecha);

        txt_Plazas = new JTextField();
        txt_Plazas.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Plazas.setColumns(10);
        txt_Plazas.setBounds(179, 250, 238, 20);
        panel_datos.add(txt_Plazas);
        
        txt_Descripcion = new JTextField();
        txt_Descripcion.setFont(new Font("Tahoma", Font.PLAIN, 16));
        txt_Descripcion.setColumns(10);
        txt_Descripcion.setBounds(575, 60, 248, 243);
        panel_datos.add(txt_Descripcion);

        txt_ID = new JTextField();
        txt_ID.setBounds(179, 59, 238, 19);
        panel_datos.add(txt_ID);
        txt_ID.setColumns(10);
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
                        Matcher sacarNum = numero.matcher(txt_ID.getText());
                        boolean comprobar = sacarNum.find();
                        if(comprobar) {
                            aniadirEvento();
                        }else {
                            JOptionPane.showMessageDialog(null, "Error al crear el evento\nEl identificador debe de ser númerico",
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
                        AdminEvents admin = new AdminEvents();
                        admin.setVisible(true);
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }

    public void aniadirEvento() {
        try {
            // Este if es como los miembros de este grupo son feos pero sirven
            if ((!(txt_Descripcion.getText().equals("") || (txt_Plazas.getText().equals(""))
                    || (txt_Nombre.getText().equals("")) || (txt_Fecha.getText().equals(""))
                    || (txt_ID.getText().equals(""))))) {

                BufferedWriter bw = new BufferedWriter(new FileWriter("./src/Eventos.txt", true));
                bw.newLine();
                bw.write(txt_ID.getText() + ":" + txt_Nombre.getText() + ":" + txt_Descripcion.getText() + ":"
                        + txt_Fecha.getText() + ":" + txt_Plazas.getText() + ":0");
                bw.close();
                JOptionPane.showMessageDialog(null, "Evento creado correctamente", "Evento creado",
                        JOptionPane.INFORMATION_MESSAGE);
                int opcionJpane = JOptionPane.showConfirmDialog(null, "¿Quieres crear otro evento?",
                        "¿Crear otro evento?", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                switch (opcionJpane) {
                    case 0:
                        txt_Descripcion.setText("");
                        txt_Fecha.setText("");
                        txt_Nombre.setText("");
                        txt_Plazas.setText("");
                        txt_ID.setText("");
                        break;
                    case 1:
                        AdminEvents admin = new AdminEvents();
                        admin.setVisible(true);
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
        txt_Descripcion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(txt_Descripcion.getText().length() >= 100) {
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
