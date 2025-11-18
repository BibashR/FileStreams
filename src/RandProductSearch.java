import javax.swing.*;
import java.awt.*;
import java.io.RandomAccessFile;

public class RandProductSearch extends JFrame {

    private JTextField searchField;
    private JTextArea resultArea;

    public RandProductSearch() {
        setTitle("Product Search");
        setSize(500, 400);
        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.add(new JLabel("Enter Product Name:"));
        searchField = new JTextField(15);
        top.add(searchField);

        JButton searchButton = new JButton("Search");
        top.add(searchButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        searchButton.addActionListener(e -> search());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void search() {
        resultArea.setText("");
        try (RandomAccessFile raf = new RandomAccessFile("products.dat", "r")) {

            while (raf.getFilePointer() < raf.length()) {
                String record = raf.readUTF();
                if (record.toLowerCase().contains(searchField.getText().toLowerCase())) {
                    resultArea.append(record + "\n");
                }
            }

        } catch (Exception ignored) {}
    }

    public static void main(String[] args) {
        new RandProductSearch();
    }
}
