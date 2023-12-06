/*
--------------------------------------------------------------------
- Author Rahul Nagaraju
- Assignment: Lab4
- FileName: DaoModel.java
- Course: ITMD-510 Object-Oriented App Development
- Instructor: James Papademas
----------------------------------------------------------------------
*/

package models;

import java.sql.*;

import records.BankRecords;

public class DaoModel {
	
	// Declare DB objects 
	DBConnect conn = null;
	Statement stmt = null;

	// constructor
	public DaoModel() { //create db object instance
		conn= new DBConnect();
		//createTable();
	}

	// CREATE TABLE METHOD
	public void createTable() {
		 try {
	
			 // Open a connection
			 System.out.println("Connecting to database to create Table...");
			 System.out.println("Connected database successfully...");
		
			 // Execute create query
			 System.out.println("Creating table in given database...");
		
			 stmt = conn.connect().createStatement();
		
			 String sql = "CREATE TABLE r_naga_tab " + 
				              "(pid INTEGER not NULL AUTO_INCREMENT, " + 
				  	        " id VARCHAR(10), " +
						  " income numeric(8,2), " + 
						  " pep VARCHAR(3), " + 
					  " PRIMARY KEY ( pid ))";
			 stmt.executeUpdate(sql);
			 System.out.println("Created table in given database...");
			 conn.connect().close(); //close db connection 
		}catch (SQLException e) { // Handle errors for JDBC
		 e.printStackTrace();
		}
	}
	// INSERT INTO METHOD
	public void insertRecords2(BankRecords[] robjs) {
		try {
			// Execute a query
			System.out.println("Inserting records into the table...");
			stmt = conn.connect().createStatement();
			String sql = null;
					
			// Include all object data to the database table
			for (int i = 0; i < robjs.length; ++i) {

			sql =  "INSERT INTO r_naga_tab(id, income, pep) " +
			"VALUES (' "+robjs[i].getId()+ " ', '  "+robjs[i].getIncome()+ " ', '  "+robjs[i].getPep()+"')";
			stmt.executeUpdate(sql); 
		}
		conn.connect().close();
		}catch (SQLException se) { se.printStackTrace();  }
	}// INSERT INTO METHOD

	// INSERT INTO METHOD
	public void insertRecords(BankRecords[] robjs) {
	  try {
		  // Execute a query
		  System.out.println("Inserting records into the table...");
		  stmt = conn.connect().createStatement();
		  String sql =  "INSERT INTO r_naga_tab(id, income, pep) VALUES( ?, ?, ?)";
	      PreparedStatement insertRecordsStatement = conn.connect().prepareStatement(sql);
		  // Include all object data to the database table
		  for (int i = 0; i < robjs.length; ++i) {
			  
			  insertRecordsStatement.setString(1, robjs[i].getId());
	          insertRecordsStatement.setDouble(2, robjs[i].getIncome());
	          insertRecordsStatement.setString(3, robjs[i].getPep());
	          insertRecordsStatement.executeUpdate();
	      
		  }
		  System.out.println("All Records inserted!");
		  conn.connect().close();
	  } catch (SQLException e) {
		  System.out.println("Error Occurred while inserting the data :"+ e.getMessage());
		  e.printStackTrace();
	  }
	}
		
	
	public ResultSet retrieveRecords() {
	    ResultSet rs = null;

	    try {
	        // Attempt to create a statement
	        stmt = conn.connect().createStatement();
	        
	        // Execute the SQL query
	        // String sql = "SELECT * from r_naga_tab";
	        String sql="select pid, id,income, pep from r_naga_tab order by pep desc";
	        rs = stmt.executeQuery(sql);
	        System.out.println("Records retrieved!");
	    } catch (SQLException e) {
	        // Handle SQL exceptions
	        e.printStackTrace();

	    } finally {
	        try {
	            // Close the connection in a finally block to ensure it's always closed
	            conn.connect().close();
	        } catch (SQLException e) {
	            // Handle closing connection exceptions
	        	System.out.println("Error Occurred while retrieving the data :"+ e.getMessage());
	            e.printStackTrace();
	        }
	    }

	    return rs;
	}

		
    public void deleteRecords() {
        try{
            stmt = conn.connect().createStatement();
            String sql = "DELETE from r_naga_tab";
            System.out.println("Deleting Records from the table...");
            stmt.executeUpdate(sql);
            System.out.println("All Records deleted!");
            conn.connect().close();
        }catch(SQLException e){
            System.out.println("Error Occurred while deleting the data :"+ e.getMessage());
            e.printStackTrace();
        }
    }
	
	public void dropTable() {
		try {
			// Execute the drop table query
			System.out.println("Deleting the table...");
			Statement stmt = conn.connect().createStatement();
			String sql = "DROP TABLE r_naga_tab";
			stmt.executeUpdate(sql);
			
			System.out.println("Table 'r_naga_tab' deleted successfully.");
			
			// Close the database connection
			conn.connect().close();
		} catch (SQLException e) { 
			System.out.println("Error Occurred while deleting the table :"+ e.getMessage());
			e.printStackTrace();
		}
   }
}



