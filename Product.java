package mattresssales;

public class Product implements Products {

    private String[] productNames;
    private int[][] sales;

    public Product(String[] productNames, int[][] sales) {
        this.productNames = productNames;
        this.sales = sales;
    }

    @Override
    public int GetTotalProductsSold() {

        int total = 0;

        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                total += sales[i][j];
            }
        }

        return total;
    }

    @Override
    public double GetAverageProductSold() {

        int total = GetTotalProductsSold();
        int count = sales.length * sales[0].length;

        return (double) total / count;
    }

    @Override
    public String GetMostPopularProduct() {

        int highestTotal = 0;
        String popularProduct = "";

        for (int i = 0; i < sales.length; i++) {

            int productTotal = 0;

            for (int j = 0; j < sales[i].length; j++) {
                productTotal += sales[i][j];
            }

            if (productTotal > highestTotal) {
                highestTotal = productTotal;
                popularProduct = productNames[i];
            }
        }

        return popularProduct + " (" + highestTotal + ")";
    }
}