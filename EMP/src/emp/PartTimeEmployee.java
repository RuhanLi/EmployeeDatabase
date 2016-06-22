package emp;
public class PartTimeEmployee extends Employee {
	
	protected float hourlyWage;
	protected float hoursPerWeek;
	
	
	public PartTimeEmployee(int empNum, String fName, String lName, float rate, float hrWage, float hrsPerWeek) {
		super(empNum, fName, lName, rate);
		hourlyWage = hrWage;
		hoursPerWeek = hrsPerWeek;
	}

        public float getHWage(){
            return hourlyWage;
        }
        
        public float getHPWeek () {
            return hoursPerWeek;
        }
        public float calcWeekPay() {
            return ((float)Math.round((hourlyWage * hoursPerWeek) * (1 - rate) * 100) / 100);
        }
        public float calcYearPay() {
            return ((float)Math.round((hourlyWage * hoursPerWeek) * (1 - rate) * 100) *52 / 100);
        }
}
