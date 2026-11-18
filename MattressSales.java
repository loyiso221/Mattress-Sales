package mattresssales;

import java.util.Scanner;

public class MattressSales {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String[] products = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        };

        int[][] sales = new int[3][3];

        // Input sales
        for (int i = 0; i < products.length; i++) {

            for (int j = 0; j < 3; j++) {

                System.out.print("Enter number of sales for month "
                        + (j + 1) + " for " + products[i] + ": ");

                sales[i][j] = input.nextInt();
            }
        }

        Product product = new Product(products, sales);

        PrintProduct report = new PrintProduct(product);

        System.out.println();
        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println("**********************");

        report.printTotalProductSales();
        report.printAverageProductSales();
        report.printMostPopularProduct();

        input.close();
    }
}