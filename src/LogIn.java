import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.Font;

public class LogIn extends JFrame {

	private JPanel contentPane, panel_Princ,panel_Sec;
	private JTextField txt_user,txt_psw;
	private JLabel lbl_restablecerContra, lbl_Logo,lbl_LogIn;
	private JButton btn_logIn;
	private String UsuarioTXT, NombreTXT, ApellidosTXT, NumeroTXT, DireccionTXT ;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
	    setMinimumSize(new Dimension(720, 480));
        setPreferredSize(new Dimension(720, 480));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(720, 480));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(168, 201, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	public void llamarText() {
		txt_user = new JTextField();
		txt_user.setToolTipText("");
		txt_user.setBounds(246, 234, 178, 42);
		panel_Princ.add(txt_user);
		txt_user.setColumns(10);
		TextPrompt user = new TextPrompt("Usuario", txt_user);

		txt_psw = new JPasswordField();
		txt_psw.setText("");
		txt_psw.setBounds(246, 286, 178, 42);
		panel_Princ.add(txt_psw);
		txt_psw.setColumns(10);
		TextPrompt psw = new TextPrompt("Contraseña", txt_psw);
	}

	public void boton() {
		btn_logIn = new JButton("Iniciar sesión");
		btn_logIn.setBounds(232, 374, 204, 21);
		panel_Princ.add(btn_logIn);
	}

	public void llamarLbl() {
		lbl_restablecerContra = new JLabel("Restablecer contraseña");
		lbl_restablecerContra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_restablecerContra.setBounds(262, 327, 242, 42);
		panel_Princ.add(lbl_restablecerContra);

		lbl_LogIn = new JLabel("Log in");
		lbl_LogIn.setFont(new Font("Arial", Font.PLAIN, 20));
		lbl_LogIn.setBounds(293, 151, 211, 141);
		panel_Princ.add(lbl_LogIn);
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
				lbl_restablecerContra.setForeground(Color.black);
			}
		});

		btn_logIn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// comprobar login
			    checkLogin();
			}
		});

	}

	public void cargarPaneles() {
		panel_Princ = new JPanel();
		panel_Princ.setBackground(new Color(249, 249, 249));
		panel_Princ.setBounds(10, 11, 669, 406);
		contentPane.add(panel_Princ);
		panel_Princ.setLayout(null);

		panel_Sec = new JPanel();
		panel_Sec.setBackground(new Color(142, 224, 157));
		panel_Sec.setBounds(10, 11, 684, 419);
		contentPane.add(panel_Sec);
	}

	public void cargarImg() {
		lbl_Logo = new JLabel("");
		lbl_Logo.setIcon(new ImageIcon("./src/resources/duke.png"));
		lbl_Logo.setBounds(244, 18, 192, 205);
		panel_Princ.add(lbl_Logo);
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
				ApellidosTXT= parte[4];
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
					SocioScreen ventanaSocio = new SocioScreen(UsuarioTXT,NombreTXT, ApellidosTXT, NumeroTXT, DireccionTXT );
					ventanaSocio.setVisible(true);
				} else if (user_type.equals("Admin")) {
					// Abrir frame admin
				    AdminPrincipal admin = new AdminPrincipal();
					admin.setVisible(true);
					dispose();
				} else if (user_type.equals("Gestor")) {
					// Abrir frame Gestor
					GestorScreen gestor = new GestorScreen(UsuarioTXT);
					gestor.setVisible(true);
					dispose();
				} 
			} else {
				System.out.println("No Funciona");
				JFrame jFrame = new JFrame();
                JOptionPane.showMessageDialog(jFrame,"Usuario/Contraseña incorrecta","Error" , JOptionPane.WARNING_MESSAGE);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return user_type;
	}
}