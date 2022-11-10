import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdminEconomia extends JFrame {

    private JPanel contentPane;
    private JPanel panel_Princ;
    private JScrollPane scrollPane;
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
        crearTablas();
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
        scrollPane.setBounds(10, 75, 329, 520);
        panel_Princ.add(scrollPane);

        DefaultTableModel modelo = new DefaultTableModel();
        JTable proveedores = new JTable(modelo);
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
            BufferedReader br = new BufferedReader(new FileReader("./src/BBDD.txt"));

            while ((linea = br.readLine()) != null) {

                String[] parte = linea.split(":");
                fila[0] = parte[0];
                fila[1] = parte[3];
                fila[2] = parte[4];
                fila[3] = parte[7];
                fila[4] = btn_editar;
                fila[5] = btn_borrar;
                ((DefaultTableModel) proveedores.getModel()).addRow(fila);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
