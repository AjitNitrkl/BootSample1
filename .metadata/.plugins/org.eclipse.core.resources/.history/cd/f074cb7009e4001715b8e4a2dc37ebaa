import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class JodaTime {
	
	public static void main(String a[]) {
		LocalDate date = LocalDate.now();
		System.out.println(date);
		int dd = date.getDayOfMonth();
		int mm = date.getMonthValue();
		int yy = date.getYear();
		System.out.println(dd+"..."+mm+"..."+yy);
		 System.out.printf("\n%d-%d-%d",dd,mm,yy);
		 
		 LocalDateTime dt=LocalDateTime.of(1995,Month.APRIL,28,12,45);
		 System.out.println(dt);
		 
		 System.out.println("After six months:"+dt.plusMonths(6));
		 System.out.println("Before six months:"+dt.minusMonths(6));
		 
		 ZoneId zone = ZoneId.systemDefault();
		  System.out.println(zone);
		  
		  
		  ZoneId la = ZoneId.of("America/Los_Angeles");
		  ZonedDateTime zt = ZonedDateTime.now(la);
		  System.out.println(zt);
		 
	}

}
