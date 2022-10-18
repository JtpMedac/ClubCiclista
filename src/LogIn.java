import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField txt_user;
	private JTextField txt_psw;
	private JPanel panel_Princ;
	private JPanel panel_Sec;
	private JLabel lbl_restablecerContra, lbl_Logo;
	private JButton btn_logIn;
	private JLabel lbl_LogIn;

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
		cargarImg();
		llamarText();
		boton();
		llamarLbl();
		iniciarAcciones();
		cargarPaneles();
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

	public void llamarText() {
		txt_user = new JTextField();
		txt_user.setToolTipText("");
		txt_user.setBounds(169, 83, 96, 19);
		contentPane.add(txt_user);
		txt_user.setColumns(10);
		TextPrompt user = new TextPrompt("Usuario", txt_user);

		txt_psw = new JPasswordField();
		txt_psw.setText("");
		txt_psw.setBounds(169, 112, 96, 19);
		contentPane.add(txt_psw);
		txt_psw.setColumns(10);
		TextPrompt psw = new TextPrompt("Contrasena", txt_psw);
	}

	public void boton() {
		btn_logIn = new JButton("Iniciar sesion");
		btn_logIn.setBounds(157, 168, 119, 21);
		contentPane.add(btn_logIn);
	}

	public void llamarLbl() {
		lbl_restablecerContra = new JLabel("Restablecer contrasena");
		lbl_restablecerContra.setBounds(158, 145, 153, 13);
		contentPane.add(lbl_restablecerContra);
		
		lbl_LogIn = new JLabel("Log in");
		lbl_LogIn.setFont(new Font("Arial", Font.PLAIN, 18));
		lbl_LogIn.setBounds(213, 34, 119, 38);
		contentPane.add(lbl_LogIn);
	}

	public void iniciarAcciones() {
		lbl_restablecerContra.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JFrame jFrame = new JFrame();
				JOptionPane.showMessageDialog(jFrame,
						"Pongase en contacto con un administrador para restablecer la contrasena");
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
		panel_Princ.setBounds(60, 23, 298, 206);
		contentPane.add(panel_Princ);
		panel_Princ.setLayout(null);

		panel_Sec = new JPanel();
		panel_Sec.setBackground(new Color(142, 224, 157));
		panel_Sec.setBounds(60, 23, 309, 214);
		contentPane.add(panel_Sec);
	}

	public void cargarImg() {
		lbl_Logo = new JLabel("");
		lbl_Logo.setIcon(new ImageIcon("./src/resources/duke-bicycle.png"));
		lbl_Logo.setBounds(153, 7, 50, 95);
		contentPane.add(lbl_Logo);
	}

	private String checkLogin() {
		String linea;
		String user_type = null;
		boolean isLoginSuccess = false;
		String User_Text = txt_user.getText(); 
		String Contra_Text = txt_psw.getText();
		try {

			BufferedReader br = new BufferedReader(new FileReader("./src/Usuarios.txt"));

			while ((linea = br.readLine()) != null) {

				String[] parte = linea.split(":");
				String UsuarioTXT = parte[0];
				String ContraseñaTXT = parte[1];
				String TipoTXT = parte[2];
				if (User_Text.equals(UsuarioTXT) && Contra_Text.equals(ContraseñaTXT)) {
					user_type = parte[2];
					isLoginSuccess = true;
					break;
				}
			}
			if (isLoginSuccess) {
				System.out.println("Funciona");
			} else {
				System.out.println("No Funciona");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		return user_type;

	}
}
