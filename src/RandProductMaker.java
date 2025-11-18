import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandProductMaker extends JFrame {

    private JTextField nameField, descField, idField, costField, countField;
    private int recordCount = 0;
    private RandomAccessFile file;

    public RandProductMaker() {
        try {
            file = new RandomAccessFile("products.dat", "rw");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "File Error!");
        }

        setTitle("Random Product Creator");
        setSize(400, 300);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Name:")); nameField = new JTextField(); add(nameField);
        add(new JLabel("Description:")); descField = new JTextField(); add(descField);
        add(new JLabel("ID:")); idField = new JTextField(); add(idField);
        add(new JLabel("Cost:")); costField = new JTextField(); add(costField);

        JButton addBtn = new JButton("Add Product");
        add(addBtn);

        countField = new JTextField("0");
        countField.setEditable(false);
        add(new JLabel("Record Count:"));
        add(countField);

        addBtn.addActionListener(this::saveProduct);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void saveProduct(ActionEvent e) {
        try {
            Product p = new Product(
                    nameField.getText(),
                    descField.getText(),
                    idField.getText(),
                    Double.parseDouble(costField.getText())
            );

            file.seek(file.length());
            file.writeUTF(p.getFixedRecord());

            recordCount++;
            countField.setText(String.valueOf(recordCount));

            nameField.setText("");
            descField.setText("");
            idField.setText("");
            costField.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }

    public static void main(String[] args) {
        new RandProductMaker();
    }
}
