import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminPrincipal extends JFrame {

    private JPanel contentPane;
    private JPanel panel_Princ;
    private JLabel lbl_bienvenida;
    private JButton btn_socios, btn_eventos, btn_ventas;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminPrincipal frame = new AdminPrincipal();
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
    public AdminPrincipal() {
        cargarPanel();
        cargarPaneles();
        cargarLabel();
        cargarBotones();
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
    public void cargarLabel() {
        lbl_bienvenida = new JLabel("Bienvenido administrador, que deseas hacer?");
        lbl_bienvenida.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_bienvenida.setBounds(318, 166, 406, 40);
        panel_Princ.add(lbl_bienvenida);
    }
    public void cargarBotones() {
        btn_socios = new JButton("Ir a la ventana Socios");  
        btn_socios.setBounds(149, 282, 158, 207);
        panel_Princ.add(btn_socios);
        
        btn_eventos = new JButton("Ir a la ventana Eventos");
        btn_eventos.setBounds(453, 282, 158, 207);
        panel_Princ.add(btn_eventos);
        
        btn_ventas = new JButton("Ir a la ventana Economica");
        
        btn_ventas.setBounds(760, 282, 158, 207);
        panel_Princ.add(btn_ventas);
    }
    public void iniciarAcciones() {
        btn_socios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AnadirSocios socios = new AnadirSocios();
                socios.setVisible(true);
                dispose();
            }
        });
        btn_eventos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AnadirEvento eventos = new AnadirEvento();
                eventos.setVisible(true);
                dispose();
            }
        });
        btn_ventas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Hay que crear esta ventana
                AdminEconomia economia = new AdminEconomia();
                economia.setVisible(true);
                dispose();
            }
        });
    }
}