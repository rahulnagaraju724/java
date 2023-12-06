/*
--------------------------------------------------------------------
- Author Rahul Nagaraju
- Assignment: Lab4
- FileName: Records.java
- Course: ITMD-510 Object-Oriented App Development
- Instructor: James Papademas
----------------------------------------------------------------------
*/
package records;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.TimeZone;

// The "Records" class extends the "BankRecords" class to inherit and possibly extend its functionality.

public class Records extends BankRecords {
    public FileWriter fw;
    // Constructor for Records class
    public Records() {
        try {
            // Initialize the FileWriter for writing to "bankrecords.txt"
            fw = new FileWriter("bankrecords.txt");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            // Handle file not found errors
        } catch (SecurityException e) {
            // Handle security-related errors
            System.err.println("Security Exception: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            // Handle unsupported encoding errors
            System.err.println("Unsupported Encoding: " + e.getMessage());
        } catch (IOException e) {
            // Handle IO-related errors
            System.err.println("IO Exception: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
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
    // Method to analyze income of females and males separately
    public void analyzeIncome() {
        // Sort records by 'Sex' using the 'SexComparator'
        Arrays.sort(recordObjects, new SexComparator());
        int maleCount = 0, femaleCount = 0;
        double maleIncome = 0, femaleIncome = 0;

        for (int i = 0; i < recordObjects.length; i++) {
            if (recordObjects[i].getSex().equals("FEMALE")) {
                femaleCount++;
                femaleIncome += recordObjects[i].getIncome();
            } else {
                maleCount++;
                maleIncome += recordObjects[i].getIncome();
            }
        }

        // Calculate average income for females and males
        double femaleIncomeAverage = femaleIncome / femaleCount;
        double maleIncomeAverage = maleIncome / maleCount;

        System.out.println("\nLoan Analysis Report:");
        System.out.println("Bank record generation report:");
        System.out.println("\nData Analytic Result:");
        System.out.println("Average Income of Females: $" + String.format("%.2f", femaleIncomeAverage));
        System.out.println("Average Income of Males: $" + String.format("%.2f", maleIncomeAverage));

        try {
            // Write data analytic results to the output file
            fw.write("Data Analytic Result:\n");
            fw.write("\n");
            fw.write("Average Income of Females: $" + String.format("%.2f", femaleIncomeAverage));
            fw.write("\n");
            fw.write("Average Income of Males: $" + String.format("%.2f", maleIncomeAverage));
            fw.write("\n");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            // Handle file not found errors
        } catch (SecurityException e) {
            // Handle security-related errors
            System.err.println("Security Exception: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            // Handle unsupported encoding errors
            System.err.println("Unsupported Encoding: " + e.getMessage());
        } catch (IOException e) {
            // Handle IO-related errors
            System.err.println("IO Exception: " + e.getMessage());
        }
    }

    // Method to find Females Count with Mortgage and Savings Account
    public void findFemalesCount() {
        // Sort records by 'Sex' using the 'SexComparator'
        Arrays.sort(recordObjects, new SexComparator());
        int femaleCount = 0;

        for (int i = 0; i < recordObjects.length; i++) {
            if (recordObjects[i].getSex().equals("FEMALE")) {
                if (recordObjects[i].getMortgage().equals("YES") && recordObjects[i].getSave_act().equals("YES")) {
                    femaleCount++;
                }
            }
        }

        System.out.println("Number of Female with both mortgage and savings account: " + femaleCount);

        try {
            // Write the count of females with specific accounts to the output file
            fw.write("\n");
            fw.write("Number of Female with both mortgage and savings account: " + femaleCount);
            fw.write("\n");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            // Handle file not found errors
        } catch (SecurityException e) {
            // Handle security-related errors
            System.err.println("Security Exception: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            // Handle unsupported encoding errors
            System.err.println("Unsupported Encoding: " + e.getMessage());
        } catch (IOException e) {
            // Handle IO-related errors
            System.err.println("IO Exception: " + e.getMessage());
        }
    }

    // Method to find Males Count with car and 1 child for each location
    public void findMalesCount2() {
        // Sort records by 'Location' using the 'LocationComparator'
        Arrays.sort(recordObjects, new LocationComparator());
        String currentLocation = recordObjects[0].getRegion();
        int maleCountForLocation = 0;

        try {
            fw.write("\n");

            for (int i = 0; i < recordObjects.length; i++) {
                if (recordObjects[i].getSex().equals("MALE")) {
                    if (recordObjects[i].getCar().equals("YES") && recordObjects[i].getChildren() == 1) {
                        if (recordObjects[i].getRegion().equals(currentLocation)) {
                            maleCountForLocation++;
                        } else {
                            StringBuilder str = new StringBuilder("No. of male with car and 1 child in Location ");
                            str.append(currentLocation);
                            str.append(": ").append(maleCountForLocation);
                            System.out.println(str.toString());
                            fw.write(str.toString());
                            fw.write("\n");
                            currentLocation = recordObjects[i].getRegion();
                            maleCountForLocation = 1;
                        }
                    }
                }
            }
            // String builder object to create the desired string
            StringBuilder str = new StringBuilder("No. of male with car and 1 children in Location ");
            str.append(currentLocation);
            str.append(": ").append(maleCountForLocation);
            System.out.println(str.toString());
            fw.write(str.toString());
            fw.write("\n");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            // Handle file not found errors
        } catch (SecurityException e) {
            // Handle security-related errors
            System.err.println("Security Exception: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            // Handle unsupported encoding errors
            System.err.println("Unsupported Encoding: " + e.getMessage());
        } catch (IOException e) {
            // Handle IO-related errors
            System.err.println("IO Exception: " + e.getMessage());
        }
    }

    // End of class
}
