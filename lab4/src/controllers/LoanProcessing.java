/* 
-----------------------------------------------------
- Author: Rahul Nagaraju
- Assignment: Lab4
- File: LoanProcessing.java
- Course: ITMD-510 Object-Oriented App Development
- Instructor: James Papademas
-----------------------------------------------------
*/

package controllers;

import java.sql.ResultSet;

import models.DaoModel;
import records.BankRecords;
import views.LoanView;

public class LoanProcessing extends BankRecords {
    public static void main(String args[]) {
        ResultSet rs;
        
        // Instantiate a new BankRecords object
        BankRecords bankRecords = new BankRecords();
        bankRecords.readData(); // Load and process data from a CSV file

        // Initialize a Data Access Object (DAO) model
        DaoModel dataAccessModel = new DaoModel();

        // Create a database table for storing bank records
        //dataAccessModel.createTable();

        // Retrieve the records from the database
        rs = dataAccessModel.retrieveRecords();
        
        // Instantiate a LoanView to display the retrieved records
        new LoanView().runView(rs);

        // Insert bank records into the database
        dataAccessModel.insertRecords(BankRecords.recordObjects);
        rs = dataAccessModel.retrieveRecords();
        new LoanView().runView(rs);

        // Delete all bank records from the database
        dataAccessModel.deleteRecords();
		//dataAccessModel.dropTable();
        rs = dataAccessModel.retrieveRecords();
        new LoanView().runView(rs);
    }
}
