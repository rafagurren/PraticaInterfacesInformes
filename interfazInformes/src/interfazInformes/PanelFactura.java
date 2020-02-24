package interfazInformes;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
/**
 * 
 * @author Rafael Peral
 *
 */
public class PanelFactura extends JPanel {
	/**
	 * Este parametro guarda el text field donde se introduce el id cliente para generar su factura mas tarde
	 */
	private JTextField tfCliente;
	/**
	 * Este parametro es un label para mostrar infromacion sobre la applicacion
	 */
	private JLabel lblSeleccionaUnCliente;
	/**
	 * Este parametro es el que me trae la conexion desde una clase externa
	 */
	private Connection con;
	/**
	 * Este parametro guarda un preparedstatement para realizar consultas parametrizadas
	 */
	private PreparedStatement pt;
	/**
	 * Este parametro es el jpanel que abarca los elementos de la parte superior
	 */
	private JPanel panel;
	/**
	 * Este parametro guarda el scrollpane que alberga la tabla
	 */
	private JScrollPane scrollPane;
	/**
	 * Este parametro guarda la tabla
	 */
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelFactura() {
		setToolTipText("");
		setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		lblSeleccionaUnCliente = new JLabel("Selecciona un Cliente:");
		panel.add(lblSeleccionaUnCliente);

		tfCliente = new JTextField();
		panel.add(tfCliente);
		tfCliente.setColumns(10);

		JButton btnGenerarFactura = new JButton("Generar Factura");
		panel.add(btnGenerarFactura);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnGenerarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarFacturas();
			}
		});

	}
	/**
	 * Este metodo es el que se encarga de generar las facturas
	 */
	public void generarFacturas() {
		try {
			con = Conexion.getConnection();

			eliminar();
			String sql = "SELECT * FROM invoice WHERE customerid = ?";

			pt = con.prepareStatement(sql);

			pt.setString(1, tfCliente.getText().toString());

			ResultSet rs = pt.executeQuery();

			
			// TableModel definition
			String[] tableColumnsName = { "ID", "CUSTOMERID", "TOTAL" };
			DefaultTableModel aModel = (DefaultTableModel) table.getModel();
			aModel.setColumnIdentifiers(tableColumnsName);

			// Loop through the ResultSet and transfer in the Model
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int colNo = rsmd.getColumnCount();
			while (rs.next()) {
				Object[] objects = new Object[colNo];
				for (int i = 0; i < colNo; i++) {
					objects[i] = rs.getObject(i + 1);
				}
				aModel.addRow(objects);
			}
			table.setModel(aModel);
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * Este metodo se encarga de limpiar los datos de la tabla para mostrar los del siguiente cliente sin que se acumulen
	 */
	 public void eliminar(){
	        DefaultTableModel tb = (DefaultTableModel) table.getModel();
	        int a = table.getRowCount()-1;
	        for (int i = a; i >= 0; i--) {          
	        tb.removeRow(tb.getRowCount()-1);
	        }
	    }


}
