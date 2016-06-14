package effective_java;

// javac -d class_path Item30_strategy_enum.java
// java -cp class_path effective_java.Item30_strategy_enum


//The strategy enum pattern: get rid of switch statement 
enum PayrollDay {
	MONDAY(PayType.WEEKDAY), TUESDAY(PayType.WEEKDAY), 
	WEDNESDAY(PayType.WEEKDAY), THURSDAY(PayType.WEEKDAY), 
	FRIDAY(PayType.WEEKDAY), 
	SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);

	private final PayType payType;
	PayrollDay(PayType payType) {
		this.payType = payType;
	}

	double pay(double hoursWorked, double payRate) {
		return payType.pay(hoursWorked, payRate);
	}

	//The strategy enum type
	private enum PayType {
		WEEKDAY {
			double overtimePay(double hours, double payRate) {
				return hours<=HOURS_PER_SHIFT ? 0 : 
								(hours-HOURS_PER_SHIFT) * payRate/2;
			}
		},
		WEEKEND {
			double overtimePay(double hours, double payRate) {
				return hours * payRate/2;
			}
		};
		private static final int HOURS_PER_SHIFT = 8;

		//the 'abstact' guarenteeds overtimePay is defined in each enum 
		abstract double overtimePay(double hours, double payRate);

		double pay(double hoursWorked, double payRate) {
			double basePay = hoursWorked*payRate;
			return basePay+overtimePay(hoursWorked, payRate);
		}
	}
}


public class Item30_strategy_enum {
	public static void main(String[] args) {
		double hoursWorked = 8;
		double payRate = 10;
		for (PayrollDay payday : PayrollDay.values()) {
			System.out.printf ("%s pays %f%n", payday.toString(), 
				   payday.pay(hoursWorked, payRate));
		}
	}
}