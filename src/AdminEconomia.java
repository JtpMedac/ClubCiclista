import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdminEconomia extends JFrame {

    private JPanel contentPane;
    private JPanel panel_Princ;
    private JScrollPane scrollPane;
    private JTable proveedores;
    private JButton btn_añadirProducto, btn_salir;
    String producto;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminEconomia frame = new AdminEconomia();
                    frame.setVisible(true);
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
        crearTablas();
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
    }
    
    public void cargarPaneles() {
        contentPane.setLayout(null);
        panel_Princ = new JPanel();
        panel_Princ.setBounds(10, 11, 1044, 659);
        panel_Princ.setBackground(new Color(249, 249, 249));
        contentPane.add(panel_Princ);
        panel_Princ.setLayout(null);
    
    }
    public void crearTablas() {
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 75, 1024, 520);
        panel_Princ.add(scrollPane);

        DefaultTableModel modelo = new DefaultTableModel();
        proveedores = new JTable(modelo);
        proveedores.setDefaultRenderer(Object.class, new Render());
        JButton btn_pedir = new JButton("Pedir");
        JButton btn_borrar = new JButton("X");
        JButton btn_editar = new JButton("Edit");
        proveedores.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Nombre", "Realizar pedido", "Modificar", "Borrar"
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
                fila[0] = parte[0];
                fila[1] = btn_pedir;
                fila[2] = btn_editar;
                fila[3] = btn_borrar;
                ((DefaultTableModel) proveedores.getModel()).addRow(fila);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void iniciarAcciones() {
        btn_añadirProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AñadirProducto NuevoProducto = new AñadirProducto();
                NuevoProducto.setVisible(true);
                dispose();
            }
        });
        btn_salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminPrincipal principal = new AdminPrincipal();
                principal.setVisible(true);
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

                                ModificarProductos modificar = new ModificarProductos(objeto.toString());
                                modificar.setVisible(true);

                            }
                        }
                        if (boton.getName().equals("delt")) {
                            if (JOptionPane.showConfirmDialog(null, "¿Desea eliminar este registro?", "Confirmar",
                                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                                if (proveedores.getSelectedRow() >= 0) {
                                    ((DefaultTableModel) proveedores.getModel()).removeRow(proveedores.getSelectedRow());
                                    // Borrar del fichero

                                    int columna = 0;
                                    int fila = e.getY() / proveedores.getRowHeight();

                                    if (fila < proveedores.getRowCount() && fila >= 0 && columna < proveedores.getColumnCount()
                                            && columna >= 0) {
                                        Object objeto = proveedores.getValueAt(fila, columna);
                                        producto = objeto.toString();
                                    }

                                    try {
                                        File fichero = new File("./src/Economia.txt");
                                        File ficherotmp = new File("./src/Economiatmp.txt");
                                        BufferedReader reader = new BufferedReader(new FileReader(fichero));
                                        BufferedWriter writer = new BufferedWriter(new FileWriter(ficherotmp));
                                        String linea;

                                        while ((linea = reader.readLine()) != null) {
                                            String[] parte = linea.split(":");
                                            if (producto.equals(parte[0])) continue;
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
                        if (boton.getName().equals("Pedir")) {
                            
                        }
                    }
                }
            }
        });

    }

}
