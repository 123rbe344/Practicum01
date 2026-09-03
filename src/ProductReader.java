import java.io.IOException;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.util.Scanner;
import javax.swing.JFileChooser;
public class ProductReader
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        boolean anotherFile = true;
        System.out.println("=== Product Reader ===");
        do
        {
            File selectedFile = chooseProductFile();
            if (selectedFile != null)
            {
                displayProductFile(selectedFile.toPath());
            }
            else
            {
                System.out.println("\nNo file was selected.");
            }
            anotherFile = SafeInput.getYNConfirm(sc, "\nOpen another Product file");
        } while (anotherFile);
        System.out.println("\nDone. Goodbye!");
    }
    public static File chooseProductFile()
    {
        JFileChooser chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);
        chooser.setDialogTitle("Select a Product data file to open");
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            return chooser.getSelectedFile();
        }
        return null;
    }
    public static void displayProductFile(Path filePath)
    {
        final int FIELDS_LENGTH = 4;
        System.out.printf("%n%-10s%-15s%-25s%-10s%n", "ID", "Name", "Description", "Cost");
        System.out.println("=====================================================");
        try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] fields = line.split(",");
                if (fields.length == FIELDS_LENGTH)
                {
                    String id = fields[0].trim();
                    String name = fields[1].trim();
                    String description = fields[2].trim();
                    double cost = Double.parseDouble(fields[3].trim());
                    System.out.printf("%-10s%-15s%-25s$%,.2f%n", id, name, description, cost);
                }
                else
                {
                    System.out.println("Found a record that may be corrupt: " + line);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("\nError reading file " + filePath + ": " + e.getMessage());
        }
    }
}
