import java.awt.Color;
import java.awt.Dimension;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdminEconomia extends JFrame {

    private JPanel contentPane;
    private JPanel panel_Princ;
    private JScrollPane scrollPane;
    private JButton btn_anadirProducto, btn_salir, btn_pedir, btn_borrar, btn_editar;
    private JTable proveedores;
    private JMenuBar menu_Principal;
    private JMenuItem mnt_Socios;
    private JMenuItem mnt_Eventos;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminEconomia frame = new AdminEconomia();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AdminEconomia() {
        cargarPanel();
        cargarPaneles();
        crearTabla();
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
    }
    
    public void cargarPaneles() {
        contentPane.setLayout(null);
        panel_Princ = new JPanel();
        panel_Princ.setBounds(10, 11, 1044, 659);
        panel_Princ.setBackground(new Color(249, 249, 249));
        contentPane.add(panel_Princ);
        panel_Princ.setLayout(null);
    
    }
    public void crearTabla() {
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 75, 1024, 520);
        panel_Princ.add(scrollPane);

        DefaultTableModel modelo = new DefaultTableModel();
        proveedores = new JTable(modelo);
        proveedores.setDefaultRenderer(Object.class, new Render());
        btn_pedir = new JButton("Pedir");
        btn_borrar = new JButton("X");
        btn_editar = new JButton("Edit");
        btn_borrar.setName("delt");
        btn_editar.setName("edit");
        btn_pedir.setName("pedir");
        proveedores.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Nombre", "Precio", "Cantidad", "Talla/Tamaño", "Realizar pedido", "Modificar", "Borrar"
            }
        )
        {
            public boolean isCellEditable(int row, int column){
                return false;
            }
        });
        scrollPane.setViewportView(proveedores);
        int numCols = proveedores.getModel().getColumnCount();
        try {
            Object[] fila = new Object[numCols];

            String linea;
            BufferedReader br = new BufferedReader(new FileReader("./src/Economia.txt"));

            while ((linea = br.readLine()) != null) {

                String[] parte = linea.split(":");
                fila[0] = parte[1];
                fila[1] = parte[3];
                fila[2] = parte[4];
                fila[3] = parte[5];
                fila[4] = btn_pedir;
                fila[5] = btn_editar;
                fila[6] = btn_borrar;
                ((DefaultTableModel) proveedores.getModel()).addRow(fila);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void botones() {
        btn_anadirProducto = new JButton("Anadir producto");
        btn_anadirProducto.setBounds(748, 45, 157, 21);
        panel_Princ.add(btn_anadirProducto);
        btn_salir = new JButton("Cerrar Sesion");
        btn_salir.setBounds(904, 22, 130, 23);
        panel_Princ.add(btn_salir);
    }
    public void iniciarAcciones() {
        btn_anadirProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AñadirProducto nuevoProducto = new AñadirProducto();
                nuevoProducto.setVisible(true);
                nuevoProducto.setLocationRelativeTo(null);
                dispose();
            }
        });

        btn_salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminPrincipal principal = new AdminPrincipal();
                principal.setVisible(true);
                principal.setLocationRelativeTo(null);
            }
        });
        proveedores.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = proveedores.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / proveedores.getRowHeight();

                if (row < proveedores.getRowCount() && row >= 0 && column < proveedores.getColumnCount() && column >= 0) {
                    Object value = proveedores.getValueAt(row, column);
                    if (value instanceof JButton) {
                        ((JButton) value).doClick();
                        JButton boton = (JButton) value;

                        if (boton.getName().equals("edit")) {
                            System.out.println("Click en el boton modificar");
                            // EVENTOS MODIFICAR
                            int columna = 0;
                            int fila = e.getY() / proveedores.getRowHeight();

                            if (fila < proveedores.getRowCount() && fila >= 0 && columna < proveedores.getColumnCount()
                                    && columna >= 0) {
                                Object objeto = proveedores.getValueAt(fila, columna);

                                ModificarEventos modificar = new ModificarEventos(objeto.toString());
                                modificar.setVisible(true);

                            }
                        }
                        
                        if (boton.getName().equals("delt")) {
                            if (JOptionPane.showConfirmDialog(null, "Desea eliminar este producto", "Confirmar",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                                if (proveedores.getSelectedRow() >= 0) {
                                    ((DefaultTableModel) proveedores.getModel()).removeRow(proveedores.getSelectedRow());
                                //evento eliminar
                                    
                                    int columna = 0;
                                    int fila = e.getY() / proveedores.getRowHeight();

                                    if (fila < proveedores.getRowCount() && fila >= 0 && columna < proveedores.getColumnCount()
                                            && columna >= 0) {
                                        Object objeto = proveedores.getValueAt(fila, columna);

                                        ModificarEventos modificar = new ModificarEventos(objeto.toString());
                                        modificar.setVisible(true);
                                    try {
                                        File fichero = new File("./src/Economía.txt");
                                        File ficherotmp = new File("./src/Economíatmp.txt");
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
                        if (boton.getName().equals("pedir")) {
                            if (JOptionPane.showInputDialog("¿Cuántos quieres pedir?", 1) != null) {
                                //Aqui se sumará el número a la base de datos
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
        mnt_Eventos = new JMenuItem("Ir a la ventana eventos");
        menu_Principal.add(mnt_Eventos);     
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
        mnt_Eventos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
        mnt_Eventos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminEvents eventos = new AdminEvents();
                eventos.setVisible(true);
                dispose();
            }
        });
    }
}
