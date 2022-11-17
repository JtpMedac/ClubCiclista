import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.jdbc.Driver;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JSeparator;

public class LogIn extends JFrame {

    private JPanel contentPane, panel_Princ, panel;
    private JTextField txt_user, txt_psw;
    private JLabel lbl_restablecerContra, lbl_LogIn,lblBackground;
    private JButton btn_logIn;
    private String UsuarioTXT, NombreTXT, ApellidosTXT, NumeroTXT, DireccionTXT;
    private static Connection conexion; // Conexion DB

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogIn frame = new LogIn();
                    frame.setVisible(true);
                    frame.setTitle("Iniciar Sesión: Club Ciclista");
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Connection pruebaConexion = LogIn.getConnection(); // Conexion DB
        if (pruebaConexion != null) { // Conexion DB
            System.out.println("Conectado"); // Conexion DB
            System.out.println(pruebaConexion); // Conexion DB
        } else { // Conexion DB
            System.out.println("Desconectado"); // Conexion DB
        } // Conexion DB
    } // Conexion DB

    public static Connection getConnection() { // Conexion DB
        try { // Conexion DB
            Class.forName("com.mysql.cj.jdbc.Driver"); // Conexion DB
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/clubCiclistaDB", "root", "1234"); // Conexion
                                                                                                                  // DB
        } catch (Exception e) { // Conexion DB
            conexion = null; // Conexion DB
        } // Conexion DB
        return conexion; // Conexion DB
    } // Conexion DB

    public LogIn() {
        setResizable(false);
        cargarPanel();
        cargarPaneles();
        cargarImg();
        llamarText();
        boton();
        llamarLbl();
        iniciarAcciones();
    }

    public void cargarPanel() {
        setMinimumSize(new Dimension(720, 460));
        setPreferredSize(new Dimension(720, 480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(720, 480));
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
    }

    public void llamarText() {
        txt_user = new JTextField();
        txt_user.setToolTipText("");
        txt_user.setBounds(64, 125, 260, 54);
        panel_Princ.add(txt_user);
        txt_user.setColumns(10);
        TextPrompt user = new TextPrompt("Usuario", txt_user);

        txt_psw = new JPasswordField();
        txt_psw.setText("");
        txt_psw.setBounds(64, 200, 260, 54);
        panel_Princ.add(txt_psw);
        txt_psw.setColumns(10);
        TextPrompt psw = new TextPrompt("Contraseña", txt_psw);
    }

    public void boton() {
        btn_logIn = new JButton("Iniciar sesión");
        btn_logIn.setBounds(110, 295, 178, 21);
        panel_Princ.add(btn_logIn);
    }

    public void llamarLbl() {
        lbl_restablecerContra = new JLabel("Restablecer contraseña");
        lbl_restablecerContra.setForeground(new Color(0, 0, 0));
        lbl_restablecerContra.setFont(new Font("Roboto", Font.PLAIN, 14));
        lbl_restablecerContra.setBounds(121, 329, 151, 42);
        panel_Princ.add(lbl_restablecerContra);

        lbl_LogIn = new JLabel("Log in");
        lbl_LogIn.setForeground(new Color(0, 0, 0));
        lbl_LogIn.setBackground(new Color(255, 255, 255));
        lbl_LogIn.setFont(new Font("Roboto", Font.BOLD, 20));
        lbl_LogIn.setBounds(164, 54, 55, 54);
        panel_Princ.add(lbl_LogIn);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 282, 393, 13);
        panel_Princ.add(separator);
    }

    public void iniciarAcciones() {
        lbl_restablecerContra.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFrame jFrame = new JFrame();
                JOptionPane.showMessageDialog(jFrame,
                        "Pongase en contacto con un administrador para restablecer la contraseña");
            }

            public void mouseEntered(MouseEvent e) {
                lbl_restablecerContra.setForeground(Color.blue);
            }

            public void mouseExited(MouseEvent e) {
                lbl_restablecerContra.setForeground(Color.white);
            }
        });

        btn_logIn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // comprobar login
                checkLogin();
            }
        });
        txt_psw.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkLogin();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (JOptionPane.showConfirmDialog(null, "¿Desea salir del programa?", "Confirmar",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                        dispose();
                    }
                }
            }
        });
        txt_user.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkLogin();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (JOptionPane.showConfirmDialog(null, "¿Desea salir del programa?", "Confirmar",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                        dispose();
                    }
                }
            }
        });
    }

    public void cargarPaneles() {
        panel_Princ = new JPanel();
        panel_Princ.setBorder(new MatteBorder(0, 2, 0, 0, (Color) new Color(0, 0, 0)));
        panel_Princ.setBackground(new Color(255, 255, 255));
        panel_Princ.setBounds(325, 0, 403, 453);
        contentPane.add(panel_Princ);
        panel_Princ.setLayout(null);
        
        panel = new JPanel();
        panel.setBackground(new Color(69, 151, 239));
        panel.setBounds(0, 0, 335, 423);
        contentPane.add(panel);
        
     
    }

    public void cargarImg() {
        
        lblBackground = new JLabel("");
        lblBackground.setBounds(209, 17, 0, 0);
        lblBackground.setIcon(new ImageIcon("./src/resources/bg1.png"));
        panel.add(lblBackground);
       // panel.setLayout(null);
    }

    private String checkLogin() {
        String linea;
        String user_type = "";
        boolean isLoginSuccess = false;
        String User_Text = txt_user.getText();
        String Contra_Text = txt_psw.getText();
        try {

            BufferedReader br = new BufferedReader(new FileReader("./src/BBDD.txt"));

            while ((linea = br.readLine()) != null) {

                String[] parte = linea.split(":");
                UsuarioTXT = parte[0];
                String ContraseñaTXT = parte[1];
                String TipoTXT = parte[2];
                NombreTXT = parte[3];
                ApellidosTXT = parte[4];
                NumeroTXT = parte[5];
                DireccionTXT = parte[6];
                if (User_Text.equals(UsuarioTXT) && Contra_Text.equals(ContraseñaTXT)) {
                    user_type = parte[2];
                    isLoginSuccess = true;
                    break;
                }
            }
            if (isLoginSuccess) {
                System.out.println("Funciona");
                if (user_type.equals("Socio")) {
                    // Abrir frame socio
                    SocioScreen ventanaSocio = new SocioScreen(UsuarioTXT, NombreTXT, ApellidosTXT, NumeroTXT,
                            DireccionTXT);
                    ventanaSocio.setVisible(true);
                    ventanaSocio.setLocationRelativeTo(null);
                    dispose();
                } else if (user_type.equals("Admin")) {
                    // Abrir frame admin
                    AdminPrincipal admin = new AdminPrincipal();
                    admin.setVisible(true);
                    admin.setLocationRelativeTo(null);
                    dispose();
                } else if (user_type.equals("Gestor")) {
                    // Abrir frame Gestor
                    GestorScreen gestor = new GestorScreen(UsuarioTXT);
                    gestor.setVisible(true);
                    gestor.setLocationRelativeTo(null);
                    dispose();
                }
            } else {
                System.out.println("No Funciona");
                JFrame jFrame = new JFrame();
                JOptionPane.showMessageDialog(jFrame, "Usuario/Contraseña incorrecta", "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return user_type;
    }
}