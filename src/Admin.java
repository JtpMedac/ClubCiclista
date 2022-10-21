import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin extends JFrame {

    private JPanel contentPane;
    private String user;
    private JLabel lbl_user;

    public Admin(String user) {
        this.user=user;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        lbl_user = new JLabel(user);
        lbl_user.setBounds(216, -12, 106, 66);
        contentPane.add(lbl_user);
        
        JButton btn_socios = new JButton("Socios");
        btn_socios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminScreen admin = new AdminScreen();
                admin.setVisible(true);
            }
        });
        btn_socios.setBounds(25, 82, 103, 94);
        contentPane.add(btn_socios);
        
        JButton btn_eventos = new JButton("Eventos");
        btn_eventos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdminEvents eventos = new AdminEvents();
                eventos.setVisible(true);
            }
        });
        btn_eventos.setBounds(158, 82, 103, 94);
        contentPane.add(btn_eventos);
        
        JButton btn_ventas = new JButton("Ventas");
        btn_ventas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btn_ventas.setBounds(297, 82, 103, 94);
        contentPane.add(btn_ventas);
    }
}
