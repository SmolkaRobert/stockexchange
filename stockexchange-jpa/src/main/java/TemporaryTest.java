import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TemporaryTest {

	
	//TODO RSmolka delete before commit
	public static void main(String[] args) {
		Date issueDate = new Date();
		LocalDate data = issueDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println(issueDate);
		System.out.println(data);
	}

}
