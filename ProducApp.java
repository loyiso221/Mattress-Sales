package mattresssales;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductApp extends JFrame {

    private JTextField txtCustomer;
    private JTextField txtPrice;
    private JComboBox<String> cmbProduct;
    private JButton btnProcess;

    public ProductApp() {

        setTitle("Product App");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");

        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");

        processItem.addActionListener(e -> processSale());
        clearItem.addActionListener(e -> clearForm());

        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);

        setJMenuBar(menuBar);

       
        txtCustomer = new JTextField(20);
        txtPrice = new JTextField(20);

        cmbProduct = new JComboBox<>(new String[] {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
        });

        btnProcess = new JButton("PROCESS");

        btnProcess.addActionListener(e -> processSale());

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.setBorder(BorderFactory.createEmptyBorder(
                20, 20, 20, 20));

        panel.add(new JLabel("Customer Name:"));
        panel.add(txtCustomer);

        panel.add(new JLabel("Select Product:"));
        panel.add(cmbProduct);

        panel.add(new JLabel("Enter Price:"));
        panel.add(txtPrice);

        panel.add(new JLabel(""));
        panel.add(btnProcess);

        add(panel);

        setVisible(true);
    }

   
    private void processSale() {

        try {

            String customer = txtCustomer.getText().trim();
            String product = cmbProduct.getSelectedItem().toString();
            double price = Double.parseDouble(txtPrice.getText());

            if (customer.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please enter customer name.");
                return;
            }

            if (price <= 0) {

                JOptionPane.showMessageDialog(this,
                        "Price must be greater than zero.");
                return;
            }

            ProductCalculations calc =
                    new ProductCalculations(price);

            double vat = calc.GetVat();
            double commission = calc.GetCommission();
            double total = calc.GetTotal();

          
            String message =
                    "Customer Name: " + customer
                    + "\nProduct: " + product
                    + "\nVAT: R " + vat
                    + "\nCOMMISSION: R " + commission
                    + "\nTOTAL: R " + total;

            JOptionPane.showMessageDialog(this,
                    message,
                    "Sales Information",
                    JOptionPane.INFORMATION_MESSAGE);

            
            saveToFile(customer, product, vat, commission, total);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "Please enter a valid numeric price.");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage());
        }
    }

    
    private void saveToFile(String customer, String product,
            double vat, double commission, double total) {

        try (PrintWriter writer = new PrintWriter(
                new FileWriter("data.txt", true))) {

            writer.println("DATA LOG");
            writer.println("********************");
            writer.println("Customer Name: " + customer);
            writer.println("Product: " + product);
            writer.println("VAT: R " + vat);
            writer.println("COMMISSION: R " + commission);
            writer.println("TOTAL: R " + total);
            writer.println("********************");
            writer.println();

        } catch (IOException e) {

            JOptionPane.showMessageDialog(this,
                    "Unable to save data to file.");
        }
    }

    
    private void clearForm() {

        txtCustomer.setText("");
        txtPrice.setText("");
        cmbProduct.setSelectedIndex(0);
        txtCustomer.requestFocus();
    }

   
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new ProductApp());
    }
}
