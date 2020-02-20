package interfazInformes;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class PanelClientes extends JPanel {
	private JTable table;
	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	
	/**
	 * Create the panel.
	 */
	public PanelClientes() {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblClientes = new JLabel("CLIENTES");
		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblClientes, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		try {
			con =  Conexion.getConnection();
			if(con == null)System.out.println("ERROOOOOOOOOOOOR!!!");
				
			
			
			stmt = con.createStatement();
			
			String sql = "SELECT * FROM CUSTOMER";
			
			rs = stmt.executeQuery(sql);
			
			// TableModel definition
			String[] tableColumnsName = {"ID","FIRSTNAME","LASTNAME","STREET","CITY"}; 
			DefaultTableModel aModel = (DefaultTableModel) table.getModel();
			aModel.setColumnIdentifiers(tableColumnsName);
			
			// Loop through the ResultSet and transfer in the Model
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			int colNo = rsmd.getColumnCount();
			while(rs.next()){
				Object[] objects = new Object[colNo];
				for(int i=0;i<colNo;i++){
					objects[i]=rs.getObject(i+1);
				}
				aModel.addRow(objects);
			}
			table.setModel(aModel);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
		
		
	}

}
