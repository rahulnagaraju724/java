/*
--------------------------------------------------------------------
- Author Rahul Nagaraju
- Assignment: Lab4
- FileName: RecordSerialize
- Course: ITMD-510 Object-Oriented App Development
- Instructor: James Papademas
----------------------------------------------------------------------
*/

package records;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class RecordSerialize extends BankRecords{
    public static void main(String[] args) {
    	// Create a new BankRecords instance
		BankRecords br = new BankRecords();
        // Read and process data from the CSV file
		br.readData();
    	
        // Create a map to store BankRecords objects with ID as the key
        Map<String, BankRecords> recordsMap = new HashMap<>();
        
        int i=0;
        // Iterate through recordObjects and populate the map
        for (BankRecords record : recordObjects) {
            recordsMap.put(record.getId(), record);
            i++;
        }

        // Display the number of records to be serialized
        System.out.println("Serializing "+i+" Records");
        
        // Serialization
        long startTime = System.currentTimeMillis();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("bankrecords.ser"))) {
            out.writeObject(recordsMap);// Serialize and write the recordsMap to a file
        } catch (IOException e) {
        	//System.out.println("Error here 1");
            e.printStackTrace();
        }

        System.out.println("Serialized!!");
        
        // Sleep for 5 seconds
        try {
        	System.out.println("Sleeping 5 secs");
            Thread.sleep(5000);    
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Deserialization
        long endTime = System.currentTimeMillis();
        
        System.out.println("DeSerializing Records...");
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("bankrecords.ser"))) {
            @SuppressWarnings("unchecked")
            Map<String, BankRecords> deserializedRecordsMap = (Map<String, BankRecords>) in.readObject();
            
            System.out.printf("%-10s %-4s %-6s %-10s %-10s %-10s%n",
                    "ID", "Age", "Sex", "Region", "Income", "Mortgage");
        
            // Perform operations with deserialized data if needed
            for (Map.Entry<String, BankRecords> entry : deserializedRecordsMap.entrySet()) {
                //System.out.println("ID: " + record.getKey());
            	//System.out.println("DeSerializing "+i+" Record");
                
                // Performing operations with deserialized data
            	System.out.printf("%-10s %-4d %-6s %-10s %-10.2f %-10.2s%n",
                        entry.getValue().getId(), entry.getValue().getAge(), entry.getValue().getSex(), entry.getValue().getRegion(), entry.getValue().getIncome(),
                        entry.getValue().getMortgage());
            	i--;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        System.out.println("DeSerialized!!");
        
        // Calculate and display the time difference
        long timeDifference = endTime - startTime;
        System.out.println("Time Difference (Serialization to Deserialization with sleep 5 secs): " + timeDifference + " ms");
        timeDifference-=5000;
        System.out.println("Time Difference (Serialization to Deserialization without sleep): " + timeDifference+ " ms");
    }
}
