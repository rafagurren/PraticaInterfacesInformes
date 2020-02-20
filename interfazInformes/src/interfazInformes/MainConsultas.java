package interfazInformes;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainConsultas extends JFrame {

	private JPanel card;
	private JMenuItem mntmGenerarFactura;
	private JMenuItem mntmFacturasDelCliente;
	private JMenuItem mntmClientes;
	private JMenu mnConsulta;
	private JMenuBar menuBar;
	private JPanel panelPorDefecto;
	private JTextPane txtDefault;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainConsultas frame = new MainConsultas();
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
	public MainConsultas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 777, 428);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        mnConsulta = new JMenu("Consulta");
        menuBar.add(mnConsulta);
        
        card = new JPanel();
        card.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(card);
        card.setLayout(new CardLayout(0, 0));

        panelPorDefecto = new JPanel();
        card.add(panelPorDefecto, "porDefecto");
        panelPorDefecto.setLayout(new BorderLayout(0, 0));
        
        txtDefault = new JTextPane();
        txtDefault.setEditable(false);
        txtDefault.setText("Buenos dias, bienvenid@ a mi aplicacion.\n Accede al menu para las diferentes opciones.");
        panelPorDefecto.add(txtDefault, BorderLayout.CENTER);
        
        
        

        mntmClientes = new JMenuItem("Clientes");
        mntmClientes.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	PanelClientes clientes = new PanelClientes();
                	card.add(clientes,"clientes");
                	itemStateChanged("clientes");
                }
        });
        mnConsulta.add(mntmClientes);

        mntmFacturasDelCliente = new JMenuItem("Facturas del cliente");
        mntmFacturasDelCliente.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	PanelFactura facturas = new PanelFactura();
                	card.add(facturas,"facturas");
                	itemStateChanged("facturas");
                }
        });
        mnConsulta.add(mntmFacturasDelCliente);

        mntmGenerarFactura = new JMenuItem("Generar factura");
        mntmGenerarFactura.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	PanelInformes info = new PanelInformes();
                	card.add(info,"final");
                	itemStateChanged("final");
                }
        });
        mnConsulta.add(mntmGenerarFactura);
       

	}
	public void itemStateChanged(String evt) {
		CardLayout cl = (CardLayout)(card.getLayout());
		cl.show(card, evt);
	}

}
