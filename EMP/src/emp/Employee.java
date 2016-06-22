/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emp;

/**
 *
 * @author 464547
 */
public abstract class Employee {
        
        protected int empNumber;
        protected String firstName;
        protected String lastName;
        protected float rate;
        
        
        public Employee(int empNum, String fName, String lName, float dedRate){
            empNumber = empNum;
            firstName = fName;
            lastName = lName;
            rate = dedRate;
        }
        public Employee() {
           
        }
        
        public String getFirstName() {
                return firstName;
        }
        
        public String getLastName() {
                return lastName;
        }
        
        public int getEmpNumber() {
                return empNumber;
        }
               
        public void setEmpNumber(int empNo) {
            empNumber = empNo;
        }
                
        public float getDedRate() {
            return rate;
        }
                
        public String empInstanceOf (Employee emp){
            if (emp instanceof FullTimeEmployee){
                return "Full Time Employee";
            } else {
                return "Part Time Employee";
            }
        }
        public abstract float calcWeekPay ();
   
}

