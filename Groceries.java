
//Groceries class implementing comparable interface for array of objects
public class Groceries implements Comparable<Groceries> {
	private String name;
	private double price;
	private int id;
	//constructor
	public Groceries(String name, double price, int id) {
		this.name = name;
		this.price = price;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getId() {
		return id;
	}
	//overridden functions since we are working with array of objects
	@Override
	public String toString() {
		return "Item name: '" + name + '\'' +
				"        Price: $" + price
				+ "       Item id: " + id;
	}

	//compareTo method overridden for sorting array of objects
	@Override
	public int compareTo(Groceries o) {
		if (this.id != o.getId()) {
			return this.id - o.getId();
		}
		return this.name.compareTo(o.getName());
	}
}