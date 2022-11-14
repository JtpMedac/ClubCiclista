import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdminEvents extends JFrame {

    private JPanel contentPane;
    private JPanel panel_Princ;
    private JLabel lblNewLabel;
    private JLabel lbl_user;
    private JButton btn_anadirEvento, btn_salir, btn_editar, btn_borrar, btn_mostrar;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel modelo;
    JButton btnNewButton;
    private JMenuBar menu_Principal;
    private JMenuItem mntm_Economia;
    private JMenuItem mnt_Socios;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminEvents frame = new AdminEvents();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AdminEvents() {
            cargarPanel();
            cargarPaneles();
            cargarLabel();
            cargarTabla();
            botones();
            iniciarAcciones();
            cargarMenu();
            accionesMenu();
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
            btn_anadirEvento = new JButton("Añadir evento");
            btn_anadirEvento.setBounds(748, 45, 157, 21);
            panel_Princ.add(btn_anadirEvento);
            btn_salir = new JButton("Cerrar Sesion");
            btn_salir.setBounds(904, 22, 130, 23);
            panel_Princ.add(btn_salir);

            btnNewButton = new JButton("Hola");
            panel_Princ.add(btnNewButton);
        }

        public void cargarTabla() {
            // Fumadita
            scrollPane = new JScrollPane();
            scrollPane.setEnabled(false);
            scrollPane.setBounds(10, 75, 1024, 573);
            panel_Princ.add(scrollPane);

            modelo = new DefaultTableModel();
            table = new JTable(modelo);
            table.setEnabled(false);

            table.setDefaultRenderer(Object.class, new Render());
            btn_borrar = new JButton("X");
            btn_editar = new JButton("Edit");
            btn_mostrar = new JButton("Mostrar");
            btn_borrar.setName("delt");
            btn_editar.setName("edit");
            btn_mostrar.setName("most");
            table.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "ID", "Nombre", "Fecha", "Apuntados", "Plazas totales", "Mostrar datos", "Modificar", "Borrar"
                }
            ) {
                boolean[] columnEditables = new boolean[] {
                    false, false, false, false, false, false, false, false
                };
                public boolean isCellEditable(int row, int column) {
                    return columnEditables[column];
                }
            });
            table.getTableHeader().setReorderingAllowed(false);
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(7).setResizable(false);
            scrollPane.setViewportView(table);
            int numCols = table.getModel().getColumnCount();
            try {
                Object[] fila = new Object[numCols];

                String linea;
                BufferedReader br = new BufferedReader(new FileReader("./src/Eventos.txt"));

                while ((linea = br.readLine()) != null) {

                    String[] parte = linea.split(":");
                    fila[0] = parte[0];
                    fila[1] = parte[1];
                    fila[2] = parte[3];
                    fila[3] = parte[4];
                    fila[4] = parte[5];
                    fila[5] = btn_mostrar;
                    fila[6] = btn_editar;
                    fila[7] = btn_borrar;
                    ((DefaultTableModel) table.getModel()).addRow(fila);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        public void iniciarAcciones() {
            btn_anadirEvento.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    AñadirEvento NuevoEvento = new AñadirEvento();
                    NuevoEvento.setVisible(true);
                    NuevoEvento.setLocationRelativeTo(null);
                    dispose();
                }
            });

            btn_salir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    AdminPrincipal principal = new AdminPrincipal();
                    principal.setVisible(true);
                    principal.setLocationRelativeTo(null);
                    dispose();
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

                                    ModificarEventos modificar = new ModificarEventos(objeto.toString());
                                    modificar.setVisible(true);
                                    modificar.setLocationRelativeTo(null);
                                    dispose();

                                }
                            }
                            
                            if (boton.getName().equals("delt")) {
                                if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar este registro?", "Confirmar",
                                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                                    if (table.getSelectedRow() >= 0) {
                                        ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                                    //evento eliminar
                                        
                                        int columna = 0;
                                        int fila = e.getY() / table.getRowHeight();

                                        if (fila < table.getRowCount() && fila >= 0 && columna < table.getColumnCount()
                                                && columna >= 0) {
                                            Object objeto = table.getValueAt(fila, columna);

                                            ModificarEventos modificar = new ModificarEventos(objeto.toString());
                                            modificar.setVisible(true);
                                        try {
                                            File fichero = new File("./src/Eventos.txt");
                                            File ficherotmp = new File("./src/Eventostmp.txt");
                                            BufferedReader reader = new BufferedReader(new FileReader(fichero));
                                            BufferedWriter writer = new BufferedWriter(new FileWriter(ficherotmp));
                                            String linea;

                                            while ((linea = reader.readLine()) != null) {
                                                String[] parte = linea.split(":");
                                                 if(parte[0].equals(objeto.toString()))continue;
                                                 writer.write(linea + System.getProperty("line.separator"));}
                                            writer.close();
                                            reader.close();
                                        
                                        
                                        
                                        } catch (IOException exception) {
                                            System.out.println(exception);
                                        }
                                    
                                    }
                                }
                                System.out.println("Click en el boton eliminar");
                                // EVENTOS ELIMINAR
                            }}
                            else if (boton.getName().equals("most")) {
                                System.out.println("Click en el boton mostrar");
                                // EVENTOS MODIFICAR
                                int columna = 0;
                                int fila = e.getY() / table.getRowHeight();

                                if (fila < table.getRowCount() && fila >= 0 && columna < table.getColumnCount()
                                        && columna >= 0) {
                                    Object objeto = table.getValueAt(fila, columna);

                                    MostrarEventos mostrar = new MostrarEventos(objeto.toString());
                                    mostrar.setVisible(true);
                                    mostrar.setLocationRelativeTo(null);
                                    dispose();

                                }
                            }
                        }
                    }
                }
            });
         
        }
        public void cargarMenu() {
            menu_Principal = new JMenuBar();
            setJMenuBar(menu_Principal);
            mnt_Socios = new JMenuItem("Ir a la ventana socios");
            menu_Principal.add(mnt_Socios);
            mntm_Economia = new JMenuItem("Ir a la ventana economica");     
            menu_Principal.add(mntm_Economia);    
        }
        public void accionesMenu() {
            mnt_Socios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
            mnt_Socios.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    AdminScreen admin = new AdminScreen();
                    admin.setVisible(true);
                    dispose();
                }
            });
            mntm_Economia.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));
            mntm_Economia.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    AdminEconomia economia = new AdminEconomia();
                    economia.setVisible(true);
                    dispose();
                }
            });
        }
    }