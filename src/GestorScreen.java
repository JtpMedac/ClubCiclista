import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class GestorScreen extends JFrame {
    private JPanel panel_Princ;
    private JPanel contentPane;
    private JLabel lbl_user;
    private String user;
    

    public GestorScreen(String usuario) {
        this.user=usuario;
        cargarPanel();
        cargarPaneles();
        cargarLabels();
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
    public void cargarLabels() {
        JLabel lbl_Textito = new JLabel("Eres un gestor");
        lbl_Textito.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lbl_Textito.setBounds(359, 152, 371, 242);
        panel_Princ.add(lbl_Textito);
        lbl_user = new JLabel(user);
		lbl_user.setBounds(416, 360, 106, 66);
		panel_Princ.add(lbl_user);
    }
}
