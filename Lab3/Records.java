import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Records extends BankRecords{
    private FileWriter fw;
    public Records(){
        try {
            fw =new FileWriter("bankrecords.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

public static void main(String[] args){
    Records records= new Records();
    records.readData();
    records.analyzeIncome();
    records.findFemalesCount();
    records.findMalesCount();
    try {
        records.fw.close();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

public void analyzeIncome(){
    int maleCount=0,femaleCount=0;
    double maleIncome=0,femaleIncome=0;
    for(int i=0;i<recordObjects.length;i++){
        if(recordObjects[i].getSex().equals("FEMALE")){
            femaleCount++;
            femaleIncome+=recordObjects[i].getIncome();
        }
        else{
            maleCount++;
            maleIncome+=recordObjects[i].getIncome();
        }
    }
    double femaleIncomeAverage=femaleIncome/femaleCount;
    double maleIncomeAverage=maleIncome/maleCount;

    System.out.println("Data Analytics Result:");
    System.out.println("Average Income of Females is: "+femaleIncomeAverage);
    System.out.println("Average Income of Males is: "+maleIncomeAverage);

    try {
        fw.write("Data Analytics Result:\n");
        fw.write("Average Income of Females is: "+femaleIncomeAverage);
        fw.write("\n");
        fw.write("Average Income of Males is: "+maleIncomeAverage);
        fw.write("\n");      
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }


}

public void findFemalesCount(){
    int femaleCount=0;
    for(int i=0;i<recordObjects.length;i++){
        if(recordObjects[i].getSex().equals("FEMALE")){
            if(recordObjects[i].getMortgage().equals("YES") && recordObjects[i].getSave_act().equals("YES")){
                femaleCount++;
            }
        }
    
    }
    System.out.println("Number of Female with both mortgage and savings account are: "+femaleCount);

    try {
        fw.write("Number of Female with both mortgage and savings account are: "+femaleCount);
        fw.write("\n");
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}

public void findMalesCount(){
    int maleCount=0;
    for(int i=0;i<recordObjects.length;i++){
        if(recordObjects[i].getSex().equals("MALE")){
            if(recordObjects[i].getCar().equals("YES") && recordObjects[i].getChildren()==1 ){
                maleCount++;
            }
        }
    }
    System.out.println("Number of Male with both a car and 1 child per location are: "+ maleCount);

    try{
        fw.write("Number of Male with both a car and 1 child per location are: "+ maleCount);
        fw.write("\n");
    } catch(IOException e){
        e.getStackTrace();
    }
}

// End of class
}
