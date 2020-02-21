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
/**
 * 
 * @author Rafael Peral Jimenez
 *
 */
public class MainConsultas extends JFrame {
	/**
	 * El parametro card guarda el JPanel con el CardLayout 
	 */
	private JPanel card;
	/**
	 * El parametro mntmGenerarFactura es donde guardo el menu item para generar el informe de la factura.
	 */
	private JMenuItem mntmGenerarFactura;
	/**
	 * El parametro mntmFacturasDelCliente es donde guardo el menu item para mostrar las facturas de un determinado cliente
	 */
	private JMenuItem mntmFacturasDelCliente;
	/**
	 * El parametro mntmClientes es donde guardo el menu item para mostrar los clientes
	 */
	private JMenuItem mntmClientes;
	/**
	 * El parametro mnConsulta es donde guardo el menu al que añado los menu items
	 */
	private JMenu mnConsulta;
	/**
	 * En este parametro guardo la barra de menu
	 */
	private JMenuBar menuBar;
	/**
	 * En este parametro se guarda el panel que se muestra al principio del programa por defecto
	 */
	private JPanel panelPorDefecto;
	/**
	 * En este parametro guardo el texto que se muestra en el panel por defecto
	 */
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
	/**
	 * Este metodo se usa para cambiar en el CardLayout el panel visible
	 * @param es el string del parametro al cual se quiere acceder del CardLayout
	 */
	public void itemStateChanged(String panel) {
		CardLayout cl = (CardLayout)(card.getLayout());
		cl.show(card, panel);
	}

}
