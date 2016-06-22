/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emp;

/**
 *
 * @author 464547
 */
 public class FullTimeEmployee extends Employee {
    protected float yearlySalary;
 
     public FullTimeEmployee(int empNum, String fName,String lName,
			float rate, float salary) {
		super(empNum, fName, lName, rate);
		this.yearlySalary = salary;
	}
     public float getSalary (){
     return yearlySalary;
 }
    public float calcWeekPay() {
		return ((float)Math.round((yearlySalary * (1 - rate) / 52) * 100) / 100);
	}
   
}
