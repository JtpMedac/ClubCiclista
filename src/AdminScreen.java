import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class AdminScreen extends JFrame {

    private JPanel contentPane;
    private JPanel panel_Princ;
    private JLabel lblNewLabel;
    private JLabel lbl_user;
    private String user;
    private JButton btn_anadirSocio, btn_cerrarSesion;
    private JScrollPane scrollPane;
    private JTable table;

    public AdminScreen(String usuario) {
        this.user = usuario;
        cargarPanel();
        cargarPaneles();
        cargarLabel();
        cargarTabla();
        botones();
        iniciarAcciones();

    }

    public void cargarPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        panel_Princ.setBounds(10, 11, 414, 239);
        contentPane.add(panel_Princ);
        panel_Princ.setLayout(null);
    }

    public void cargarLabel() {
        lblNewLabel = new JLabel("Sos admin capo :D");
        lblNewLabel.setBounds(10, -12, 106, 66);
        panel_Princ.add(lblNewLabel);
        lbl_user = new JLabel(user);
        lbl_user.setBounds(216, -12, 106, 66);
        panel_Princ.add(lbl_user);
    }

    public void botones() {
        btn_anadirSocio = new JButton("Anadir socio");
        btn_anadirSocio.setBounds(305, 35, 85, 21);
        panel_Princ.add(btn_anadirSocio);
        btn_cerrarSesion = new JButton("Cerrar Sesion");
        btn_cerrarSesion.setBounds(284, 10, 130, 23);
        panel_Princ.add(btn_cerrarSesion);

        // Famadita
        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 64, 370, 142);
        panel_Princ.add(scrollPane);

        DefaultTableModel modelo = new DefaultTableModel();
        JTable table = new JTable(modelo);
        table.setDefaultRenderer(Object.class, new Render());
        JButton btn_borrar = new JButton("X");
        JButton btn_editar = new JButton("Edit");
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "DNI", "Nombre", "Apellidos", "Estado", "Modificar", "Borrar"
            }
        )
        {
            public boolean isCellEditable(int row, int column){
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
            }
        });

        btn_cerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ConfirmExit Confirmar = new ConfirmExit();
                Confirmar.setVisible(true);
            }
        });
    }

    public void cargarTabla() {
    }
}
