import java.io.*;

public class PizzaStore
{
	PizzaFactory simpleFactory;

	PizzaStore()
	{
		simpleFactory = new PizzaFactory();	
	}

	public Pizza orderPizza(String type)
	{
		
		Pizza pizza = simpleFactory.createPizza(type);
		bakePizza();
	        return pizza;
	}
	
	public void createPizza(){}
	public void bakePizza(){}

	public static void main(String[] args)
	{
		PizzaStore pizzaStore = new PizzaStore();
		Pizza pizza = pizzaStore.orderPizza("CheesePizza");	

		System.out.println("Pizza name = "+pizza.name);
	}
}
