import java.io.*;
public class PizzaFactory
{
	public Pizza createPizza(String type)
	{
		if(type.equals("CheesePizza"))
		{
			return new Pizza(type);
		}
		
		return new Pizza("Plain Pizza");
	}
}
