/*
--------------------------------------------------------------------
- Author Rahul Nagaraju
- Assignment: Lab2
- FileName: BankRecordsTest.java
- Course: ITMD-510 Object-Oriented App Development
- Instructor: James Papademas
----------------------------------------------------------------------
*/
public class BankRecordsTest {
    public static void main(String[] args) {
        // Create an instance of the BankRecords class
        BankRecords bankRecords = new BankRecords();

        // Call the readData() method to read data from a CSV file and process it
        bankRecords.readData();
    }
}