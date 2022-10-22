import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmExit extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JLabel lbl_pregunta;
    private JPanel buttonPane;
    private JButton btn_conf, btn_cancel;

    public static void main(String[] args) {
        try {
            ConfirmExit dialog = new ConfirmExit();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConfirmExit() {
        crearPanel();
        crearLabel();
        botones();
        acciones();
    }

    public void crearPanel() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
    }

    public void crearLabel() {
        lbl_pregunta = new JLabel("Estas seguro de que quieres cerrar sesion?");
        lbl_pregunta.setBounds(96, 51, 292, 106);
        contentPanel.add(lbl_pregunta);
    }

    public void botones() {
        {
            buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                btn_conf = new JButton("SI");

                btn_conf.setActionCommand("OK");
                buttonPane.add(btn_conf);
                getRootPane().setDefaultButton(btn_conf);

            }
            {
                btn_cancel = new JButton("No");

                btn_cancel.setActionCommand("Cancel");
                buttonPane.add(btn_cancel);
            }
        }
    }

    public void acciones() {
        btn_conf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
        btn_cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void salir() {
        LogIn logIn = new LogIn();
        logIn.setVisible(true);
        dispose();
        // falta por terminar
    }

}
