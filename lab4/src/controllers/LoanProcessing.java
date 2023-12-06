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

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import models.DaoModel;
import records.BankRecords;
import records.Records;
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
        dataAccessModel.createTable();

        // Insert bank records into the database
        dataAccessModel.insertRecords(BankRecords.recordObjects);
        rs = dataAccessModel.retrieveRecords();
        new LoanView().runView(rs);

        // Delete all bank records from the database
        //dataAccessModel.deleteRecords();
		//dataAccessModel.dropTable();
        //rs = dataAccessModel.retrieveRecords();
        //new LoanView().runView(rs);
        
        
        Records records = new Records();

        // Read data from BankRecords
        records.readData();

        // Analyze income data
        records.analyzeIncome();

        // Find the count of females with specific accounts
        records.findFemalesCount();

        // Find the count of males with specific attributes
        records.findMalesCount2();

        try {
            // Write name and date/time information to the output file
            records.fw.write("\n");
            records.fw.write("Name: Rahul Nagaraju");

            // Format date and time
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String str = "Current Date and Time: " + currentDateTime.format(formatter) + " " +
                    currentDateTime.getDayOfWeek() + " " + TimeZone.getDefault().getID();

            // Write date and time information to the output file
            records.fw.write("\n");
            records.fw.write(str);
            records.fw.write("\n");
            
            System.out.println("\nProgrammed by Rahul Nagaraju");
            System.out.println(str);
            
            // Close the FileWriter
            records.fw.close();
        }catch (FileNotFoundException e) {
            // Handle file not found errors
            e.printStackTrace();
        } catch (SecurityException e) {
            // Handle security-related errors
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // Handle unsupported encoding errors
            e.printStackTrace();
        } catch (NullPointerException e) {
            // Handle null reference errors
            e.printStackTrace();
        } catch (DateTimeException e) {
            // Handle date and time formatting errors
            e.printStackTrace();
        } catch (IOException e) {
            // Handle IO-related errors
            e.printStackTrace();
        }
    }
}
