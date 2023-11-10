/*
--------------------------------------------------------------------
- Author: Rahul Nagaraju
- Assignment: Lab4
- File: LoanView.java
- Course: ITMD-510 Object-Oriented App Development
- Instructor: James Papademas
--------------------------------------------------------------------
*/

package views;

import java.sql.*;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LoanView {
    public void runView(ResultSet rs) {
        // Create vector objects to store column and row data for JTable
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        Vector<String> column = new Vector<String>();
        
        try {
            // Get metadata of the ResultSet to determine column count
            ResultSetMetaData metaData = rs.getMetaData();
            int columns = metaData.getColumnCount();

            // Get column names from the table and add them to the 'column' vector
            for (int i = 1; i <= columns; i++) {
                column.add(metaData.getColumnName(i));
            }

            // Get row data from the table and populate the 'data' vector
            while (rs.next()) {
                Vector<Object> row = new Vector<Object>(columns);

                for (int i = 1; i <= columns; i++) {
                    row.addElement(rs.getObject(i));
                }
                data.addElement(row);
            }

            // Create a DefaultTableModel with the data and column vectors
            DefaultTableModel model = new DefaultTableModel(data, column);

            // Create a JTable and set its model
            JTable table = new JTable(model);

            // Create a JFrame to display the JTable
            JFrame frame = new JFrame("Loan Details");
            frame.setSize(700, 200);
            frame.add(new JScrollPane(table));
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation
            frame.pack();
            frame.setVisible(true);

            rs.close(); // Close the ResultSet instance
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
