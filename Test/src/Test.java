import java.util.function.BiConsumer;

public class Test {
	
	public static void main(String a[]) {
		
		Person person = new Person();
		
		BiConsumer< String, String> setNameOnPersonL = 
			( first, last) ->{ 
			person.setName1(first, last);
			};
			setNameOnPersonL.accept("Ajit", "Anil");
			BiConsumer< String, String> setNameOnPersonMR =
			Person::setName;
			setNameOnPersonMR.accept("Ajit","shubhra");
			System.out.println(setNameOnPersonMR);
	}

}
