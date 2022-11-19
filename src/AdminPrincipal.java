import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import javax.swing.ImageIcon;

public class AdminPrincipal extends JFrame {

    private JPanel contentPane;
    private JPanel panel_Princ;
    private JLabel lbl_bienvenida;
    private JButton btn_socios, btn_eventos, btn_ventas;
    String user;
    private JMenuBar menu_Principal;
    private JMenuItem mnt_Economia;
    private JMenuItem mnt_Socios;
    private JMenuItem mnt_Eventos;
        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        AdminEvents frame = new AdminEvents();
                        frame.setVisible(true);
                        frame.setTitle("Admin: Principal");
                        frame.setLocationRelativeTo(null);
                       
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    public AdminPrincipal() {
        cargarPanel();
        cargarPaneles();
        cargarLabel();
        cargarBotones();
        iniciarAcciones();
        cargarMenu();
        accionesMenu();
    }
    public void cargarPanel() {
        setMinimumSize(new Dimension(700, 480));
        setPreferredSize(new Dimension(720, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(698, 480));
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(168, 201, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
    }
    public void cargarPaneles() {
        contentPane.setLayout(null);
        panel_Princ = new JPanel();
        panel_Princ.setBounds(0, 0, 697, 668);
        panel_Princ.setBackground(new Color(249, 249, 249));
        contentPane.add(panel_Princ);
        panel_Princ.setLayout(null);
    }
    public void cargarLabel() {
        lbl_bienvenida = new JLabel("Menu administrador: Club ciclista");
        lbl_bienvenida.setFont(new Font("Roboto", Font.BOLD, 20));
        lbl_bienvenida.setBounds(65, 6, 406, 40);
        panel_Princ.add(lbl_bienvenida);
    }
    public void cargarBotones() {
        btn_socios = new JButton("Ventana Socios");  
        btn_socios.setBounds(56, 120, 158, 207);
        panel_Princ.add(btn_socios);
        
        btn_eventos = new JButton("Ventana Eventos");
        btn_eventos.setBounds(481, 120, 158, 207);
        panel_Princ.add(btn_eventos);
        
        btn_ventas = new JButton("Ventana Productos");
        
        btn_ventas.setBounds(270, 120, 158, 207);
        panel_Princ.add(btn_ventas);
        
        JButton btn_CerrarSesion = new JButton("Cerrar sesion");
        btn_CerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                ConfirmExit Confirmar = new ConfirmExit();
                Confirmar.setVisible(true);
                Confirmar.setLocationRelativeTo(null);

            }
        });
        btn_CerrarSesion.setBounds(481, 19, 147, 21);
        panel_Princ.add(btn_CerrarSesion);
        
        JLabel lblBackground = new JLabel("New label");
        lblBackground.setIcon(new ImageIcon(AdminPrincipal.class.getResource("/resources/Backgrounds/bgmprincipal.png")));
        lblBackground.setBounds(0, 0, 685, 425);
        panel_Princ.add(lblBackground);
    }
    public void iniciarAcciones() {
        btn_socios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminScreen admin = new AdminScreen();
                admin.setVisible(true);
                admin.setLocationRelativeTo(null);
                dispose();
            }
        });
        btn_eventos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminEvents eventos = new AdminEvents();
                eventos.setVisible(true);
                eventos.setLocationRelativeTo(null);
                dispose();
            }
        });
        btn_ventas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                AdminEconomia economia = new AdminEconomia();
                economia.setVisible(true);
                economia.setLocationRelativeTo(null);
                dispose();
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
        mnt_Economia = new JMenuItem("Ir a la ventana economia");     
        menu_Principal.add(mnt_Economia);    
    }
    public void accionesMenu() {
        mnt_Socios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK));
        mnt_Socios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminScreen admin = new AdminScreen();
                admin.setVisible(true);
                admin.setLocationRelativeTo(null);
                dispose();
            }
        });
        mnt_Eventos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK));
        mnt_Eventos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminEvents eventos = new AdminEvents();
                eventos.setVisible(true);
                eventos.setLocationRelativeTo(null);
                dispose();
            }
        });
        mnt_Economia.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_DOWN_MASK));
        mnt_Economia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminEconomia economia = new AdminEconomia();
                economia.setVisible(true);
                economia.setLocationRelativeTo(null);
                dispose();
            }
        });
    }
}