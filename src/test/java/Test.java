import java.time.LocalDateTime;
import java.time.Month;

public class Test {

	public static void main(String[] args) {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime date1 = LocalDateTime.of(2018, 12, 1, 14, 53);
		LocalDateTime date2 = LocalDateTime.of(2018, 12, 1, 14, 55);
		
		System.out.println(today);
		System.out.println(date1);
		System.out.println(date2);
		if(date1.isBefore(today))
			System.out.println("date1 before today");
		if(date2.isAfter(today))
			System.out.println("date2 after today");
	}

}
