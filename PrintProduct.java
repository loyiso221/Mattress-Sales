Package mattressales;

public class PrintProduct{
	private Products products;
}

public PrintProduct(Products products) {
	this.products = products;
}	

pubic void printTotalProductSales(){
		System.out.println("Total Products Sold: " + products.GetTotalProductsSold());

}

public void printAverageProductSales(){
		System.out.println("Average Products Sold: " + products.GetAverageProductSold());
}

public void printMostPopularProduct(){
		System.out.println("Most Popular Product: " + products.GetMostPopularProduct());
}
}