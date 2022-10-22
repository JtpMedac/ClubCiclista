import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class AdminScreen extends JFrame {

    private JPanel contentPane;
    private JPanel panel_Princ;
    private JLabel lblNewLabel;
    private JLabel lbl_user;
    private JButton btn_anadirSocio, btn_cerrarSesion;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel modelo;
    JButton btnNewButton;
    private String contrasena;
    String dni;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminScreen frame = new AdminScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdminScreen() {
        cargarPanel();
        cargarPaneles();
        cargarLabel();
        cargarTabla();
        botones();
        iniciarAcciones();
    }

    public void cargarPanel() {
        setMinimumSize(new Dimension(1080, 720));
        setPreferredSize(new Dimension(1080, 720));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1080, 720));
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(168, 201, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    public void cargarPaneles() {
        panel_Princ = new JPanel();
        panel_Princ.setBackground(new Color(249, 249, 249));
        panel_Princ.setBounds(10, 11, 1044, 659);
        contentPane.add(panel_Princ);
        panel_Princ.setLayout(null);
    }

    public void cargarLabel() {
        lblNewLabel = new JLabel("Sos admin capo :D");
        lblNewLabel.setBounds(58, 0, 106, 66);
        panel_Princ.add(lblNewLabel);
    }

    public void botones() {
        btn_anadirSocio = new JButton("Anadir socio");
        btn_anadirSocio.setBounds(748, 45, 157, 21);
        panel_Princ.add(btn_anadirSocio);
        btn_cerrarSesion = new JButton("Cerrar Sesion");
        btn_cerrarSesion.setBounds(904, 22, 130, 23);
        panel_Princ.add(btn_cerrarSesion);

        btnNewButton = new JButton("Hola");
        panel_Princ.add(btnNewButton);
    }

    public void cargarTabla() {
        // Fumadita
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 75, 1024, 573);
        panel_Princ.add(scrollPane);

        modelo = new DefaultTableModel();
        table = new JTable(modelo);

        table.setDefaultRenderer(Object.class, new Render());
        JButton btn_borrar = new JButton("X");
        JButton btn_editar = new JButton("Edit");
        btn_borrar.setName("delt");
        btn_editar.setName("edit");
        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "DNI", "Nombre", "Apellidos", "Estado", "Modificar", "Borrar"
                }) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        scrollPane.setViewportView(table);
        int numCols = table.getModel().getColumnCount();
        try {
            Object[] fila = new Object[numCols];

            String linea;
            BufferedReader br = new BufferedReader(new FileReader("./src/BBDD.txt"));

            while ((linea = br.readLine()) != null) {

                String[] parte = linea.split(":");
                fila[0] = parte[0];
                fila[1] = parte[3];
                fila[2] = parte[4];
                fila[3] = parte[7];
                fila[4] = btn_editar;
                fila[5] = btn_borrar;
                ((DefaultTableModel) table.getModel()).addRow(fila);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void iniciarAcciones() {
        btn_anadirSocio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AnadirSocios NuevoSocio = new AnadirSocios();
                NuevoSocio.setVisible(true);
                dispose();
            }
        });

        btn_cerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConfirmExit Confirmar = new ConfirmExit();
                Confirmar.setVisible(true);
            }
        });
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = table.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / table.getRowHeight();

                if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                    Object value = table.getValueAt(row, column);
                    if (value instanceof JButton) {
                        ((JButton) value).doClick();
                        JButton boton = (JButton) value;

                        if (boton.getName().equals("edit")) {
                            System.out.println("Click en el boton modificar");
                            // EVENTOS MODIFICAR
                            int columna = 0;
                            int fila = e.getY() / table.getRowHeight();

                            if (fila < table.getRowCount() && fila >= 0 && columna < table.getColumnCount()
                                    && columna >= 0) {
                                Object objeto = table.getValueAt(fila, columna);

                                ModificarSocios modificar = new ModificarSocios(objeto.toString());
                                modificar.setVisible(true);

                            }
                        }
                        if (boton.getName().equals("delt")) {
                            if (JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Confirmar",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                                if (table.getSelectedRow() >= 0) {
                                    ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                                    // Borrar del fichero

                                    int columna = 0;
                                    int fila = e.getY() / table.getRowHeight();

                                    if (fila < table.getRowCount() && fila >= 0 && columna < table.getColumnCount()
                                            && columna >= 0) {
                                        Object objeto = table.getValueAt(fila, columna);
                                        dni = objeto.toString();
                                    }

                                    try {
                                        File fichero = new File("./src/BBDD.txt");
                                        File ficherotmp = new File("./src/BBDDtmp.txt");
                                        BufferedReader reader = new BufferedReader(new FileReader(fichero));
                                        BufferedWriter writer = new BufferedWriter(new FileWriter(ficherotmp));
                                        String linea;

                                        while ((linea = reader.readLine()) != null) {
                                            String[] parte = linea.split(":");
                                            if (dni.equals(parte[0])) continue;
                                                writer.write(linea + System.getProperty("line.separator"));
                                        }
                                        writer.close();
                                        reader.close();

                                    } catch (IOException exception) {
                                        System.out.println(exception);
                                    }

                                }
                                System.out.println("Click en el boton eliminar");
                                // EVENTOS ELIMINAR
                            }
                        }
                    }
                }
            }
        });

    }
}
