import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField txt_user;
	private JTextField txt_psw;

	JLabel lbl_restablecerContra;

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
		cargarPanel();
		llamarText();
		boton();
		llamarLbl();
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

	public void llamarText() {
		txt_user = new JTextField();
		txt_user.setText("Usuario");
		txt_user.setBounds(169, 83, 96, 19);
		contentPane.add(txt_user);
		txt_user.setColumns(10);

		txt_psw = new JTextField();
		txt_psw.setText("Contrase\u00F1a");
		txt_psw.setBounds(169, 112, 96, 19);
		contentPane.add(txt_psw);
		txt_psw.setColumns(10);
	}

	public void boton() {
		JButton btn_logIn = new JButton("Iniciar sesion");
		btn_logIn.setBounds(169, 168, 96, 21);
		contentPane.add(btn_logIn);
	}

	public void llamarLbl() {
		lbl_restablecerContra = new JLabel("Restablecer contrase\u00F1a");
		lbl_restablecerContra.setBounds(158, 145, 153, 13);
		contentPane.add(lbl_restablecerContra);
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

	}
}
