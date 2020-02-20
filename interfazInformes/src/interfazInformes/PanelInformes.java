package interfazInformes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class PanelInformes extends JPanel {
	private JTextField textField;
	private JFileChooser fileChooser;
	private Connection con;
	private Component panel_desktop;
	private String ruta;
	private JFileChooser jF1;

	/**
	 * Create the panel.
	 */
	public PanelInformes() {
		setLayout(new BorderLayout(0, 0));

		JLabel lblInformes = new JLabel("INFORMES");
		lblInformes.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblInformes, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 3, 10, 100));

		JLabel label = new JLabel("");
		panel.add(label);

		JLabel label_4 = new JLabel("");
		panel.add(label_4);

		JLabel label_3 = new JLabel("");
		panel.add(label_3);

		JLabel lblNewLabel = new JLabel("Introduce el numero factura:");
		panel.add(lblNewLabel);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Generar Informe");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {

				crearInforme();
			}

		});
		panel.add(btnNewButton);

		JLabel label_1 = new JLabel("");
		panel.add(label_1);

	}

	@SuppressWarnings("deprecation")
	private void crearInforme() {
		jF1 = new JFileChooser();
		
		if (jF1.showSaveDialog(this) == jF1.APPROVE_OPTION) {
			ruta = jF1.getSelectedFile().getAbsolutePath();
			
			try {
				con = Conexion.getConnection();
				Map<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("idJava", textField.getText());
				JasperPrint print = JasperFillManager.fillReport("informes/InformeFactura.jasper", parametros, con);
				JasperViewer.viewReport(print);
				
				JasperExportManager.exportReportToPdfFile(print,  ruta);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

}
