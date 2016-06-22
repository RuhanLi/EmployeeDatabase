package emp;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;

public class HashTable {
    final private int k;
    private final ArrayList<Employee>[] Buckets;
    private int numEmployees;
    // Constructor
    
    public HashTable(int numBuckets) {
        this.k = 5;
        Buckets = new ArrayList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            Buckets[i] = new ArrayList(); 
        }
        numEmployees = 0;
    }

    public int calcBucket(int key) {
        return (key % Buckets.length);
    }

    public boolean addEmployee(Employee employee) {

        int targetBucket = calcBucket(employee.getEmpNumber());
        boolean ifAdding = Buckets[targetBucket].add(employee);
        if (ifAdding) {
            numEmployees++; 
        }
        return (ifAdding);
    }

    public int searchEmployee(int empNum) {

        int targetBucket = calcBucket(empNum);

        for (int i = 0; i < Buckets[targetBucket].size(); i++) {
            if (empNum == Buckets[targetBucket].get(i).getEmpNumber()) {
                return (i);  
            }
        }
        return (-999); 		
    }

    public Employee removeEmployee(int empNum) {

        // Returns reference to removed employee else returns null if employee not found.

        if (searchEmployee(empNum) >= 0) {
            int searchBucket = calcBucket(empNum);
            numEmployees--;
            return (Buckets[searchBucket].remove(searchEmployee(empNum)));

        }
        return (null);		
    }

    public Employee getEmployee(int empNum) {
        if (searchEmployee(empNum) >= 0) {
            int targetBucket = calcBucket(empNum);
            return (Buckets[targetBucket].get(searchEmployee(empNum)));
        }
        return (null);  
    }

    public Employee[] getEmp() {
        Employee[] tempEmp = new Employee[numEmployees];
        int count = 0;
        for (int i = 0; i < Buckets.length; i++) {
            for (int k = 0; k < Buckets[i].size(); k++) {
            	tempEmp[k + count] = Buckets[i].get(k);
            }
            count += Buckets[i].size();
        }
        return tempEmp;
    }
    
    public boolean searching(int keyFind) {

        for (int i = 0; i < Buckets[keyFind % k].size(); i++) {
            // If a match is found, will return true
            if (Buckets[keyFind % k].get(i).getEmpNumber() == keyFind) {
                return (true);
            }
        }
        // Otherwise, return false
        return (false);
    }
    
     public void displayContents() {
                                
                for (int bucketNum = 0; bucketNum < Buckets.length; bucketNum++){
                        System.out.println("\nBucket number: " + bucketNum);
                        int tempNum = Buckets[bucketNum].size();
                        if (tempNum < 1) {
                                System.out.println(" This is an empty bucket.");
                        }
                        for (int i = 0; i < tempNum; i++) {
                                System.out.println("  Employee " + Buckets[bucketNum].get(i).getEmpNumber() + " with a deduction rate of: " + Buckets[bucketNum].get(i).getDedRate());
                        }
                }
        }
     
     public void display() {
        String tempEmp = "";
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < Buckets[i].size(); j++) {
                if (Buckets[i].get(j) instanceof FullTimeEmployee) {
                    tempEmp += ("F " + Buckets[i].get(j).getEmpNumber()
                            + " "
                            + Buckets[i].get(j).getFirstName()
                            + " "
                            + Buckets[i].get(j).getLastName()
                            + " "
                            + Buckets[i].get(j).getDedRate()
                            + " "
                            + ((FullTimeEmployee) Buckets[i].get(j))
                            .getSalary() + "\n");
                } else {
                    tempEmp += ("P " + Buckets[i].get(j).getEmpNumber()
                            + " "
                            + Buckets[i].get(j).getFirstName()
                            + " "
                            + Buckets[i].get(j).getLastName()
                            + " "
                            + Buckets[i].get(j).getDedRate()
                            + " "
                            + ((PartTimeEmployee) Buckets[i].get(j)).getHWage()
                            + " "
                            + ((PartTimeEmployee) Buckets[i].get(j)).getHPWeek() + "\n");
                }
            }
        }
        JOptionPane.showMessageDialog(null, tempEmp);
    }
     
     
     public void save() {
        try {
            //Declares and instantiates a writer using the Java character streams
            BufferedWriter makeDatabase = new BufferedWriter(new FileWriter("Database.txt"));
            for (int i = 0; i < k; i++) {
                //Writes headings to organize the database 
                makeDatabase.write("Sec. " + i);
                makeDatabase.newLine();
                //In each bucket, writes the employees using instanceof to store
                //F or P with it
                for (int j = 0; j < Buckets[i].size(); j++) {
                    if (Buckets[i].get(j) instanceof FullTimeEmployee) {
                        makeDatabase.write("FullTime " + Buckets[i].get(j).getEmpNumber() 
                                + " " 
                                + Buckets[i].get(j).getFirstName() 
                                + " "
                                + Buckets[i].get(j).getLastName() 
                                + " "
                                + Buckets[i].get(j).getDedRate() 
                                + " " 
                                + ((FullTimeEmployee) Buckets[i].get(j)).getSalary());
                        makeDatabase.newLine();
                    } else {
                        makeDatabase.write("PartTime " + Buckets[i].get(j).getEmpNumber()
                                + " "
                                + Buckets[i].get(j).getFirstName()
                                + " "
                                + Buckets[i].get(j).getLastName()
                                + " "
                                + Buckets[i].get(j).getDedRate()
                                + " "
                                + ((PartTimeEmployee) Buckets[i].get(j)).getHWage()
                                + " "
                                + ((PartTimeEmployee) Buckets[i].get(j)).getHPWeek());
                        makeDatabase.newLine();
                    }
                }
                //Continues the writing after each line has been processed
                makeDatabase.newLine();
            }
            //Closes the writer stream 
            makeDatabase.close();
        } catch (IOException a) {
            JOptionPane.showMessageDialog(null, "There was an error in writing to the database. Please review the user tutorial.");
        }
    }
     public void read(HashTable load) {
        Employee tempEmp;
        int number;
        String firstName = "";
        String lastName = "";
        float deductions;
        float salaries;
        float hours;
        //Declares a scanner, which will act as the file reader 
        Scanner newScanner = null;
        try {
            //Instantiates the scanner to the correct file by using the Java stream objects
            newScanner = new Scanner(new BufferedReader(new FileReader("Database.txt")));
            //Continues reading lines until the next line does not exist
            while (newScanner.hasNextLine() != false) {
                if (newScanner.hasNext("FullTime") == true) {
                    //Skips the information not in the employee object and continues reading
                    //since the information would have been stored in a set style, it is very easy to predict
                    //the attribute type 
                    newScanner.skip("FullTime");
                    number = newScanner.nextInt();
                    //Reads all parts of the name (normally FirstName LastName)
                        firstName += newScanner.next();
                        firstName += " ";
                        lastName += newScanner.next();
                        lastName += " ";
                      
                    deductions = newScanner.nextFloat();
                    salaries = newScanner.nextFloat();
                    //Creates the employee and adds it
                    tempEmp = new FullTimeEmployee(number, firstName, lastName, deductions, salaries);
                    load.addEmployee(tempEmp);
                    firstName = "";
                    lastName = "";
                } else if (newScanner.hasNext("PartTime") == true) {
                    //Performs same processes with Part Time employees
                    newScanner.skip("PartTime");
                    number = newScanner.nextInt();
                    while (newScanner.hasNext("[a-zA-Z]+") == true) {
                        firstName += newScanner.next();
                        if (newScanner.hasNext("[a-zA-Z]+") == true) {
                            firstName += " ";
                        }
                    }
                    deductions = newScanner.nextFloat();
                    salaries = newScanner.nextFloat();
                    hours = newScanner.nextFloat();
                    tempEmp = new PartTimeEmployee(number, firstName, lastName, deductions, salaries, hours);
                    load.addEmployee(tempEmp);
                    firstName = "";
                }
                //Advances scanner to the next employee, or next line
                newScanner.nextLine();
            }
            //Closes the scanner after entire file has been read
            newScanner.close();
            //Catches exceptions, including FileNotFoundException 
        } catch (IOException a) {
            JOptionPane.showMessageDialog(null, "There was an error in reading the database. Please review the user tutorial.");
        } catch (InputMismatchException b) {
            JOptionPane.showMessageDialog(null, "There was an error in reading the database. Please review the user tutorial.");
        }
    }
}
