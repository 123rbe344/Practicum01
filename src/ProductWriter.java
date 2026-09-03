import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
public class ProductWriter
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> products = new ArrayList<>();
        boolean addAnother = true;
        do
        {
            String id = SafeInput.getNonZeroLenString(sc, "Enter the product's ID");
            String name = SafeInput.getNonZeroLenString(sc, "Enter the product's name");
            String description = SafeInput.getNonZeroLenString(sc, "Enter the product's description");
            double cost = SafeInput.getDouble(sc, "Enter the product's cost");
            String record = id + ", " + name + ", " + description + ", " + cost;
            products.add(record);
            System.out.println("\nRecord added: " + record);
            addAnother = SafeInput.getYNConfirm(sc, "\nAdd another product");
        } while (addAnother);
        if (!products.isEmpty())
        {
            String fileName = SafeInput.getNonZeroLenString(sc, "\nEnter the file name to save the data to (e.g. ProductTestData.txt)");
            saveProductsToFile(products, fileName);
        }
        else
        {
            System.out.println("\nNo records were entered. No file was saved.");
        }
        System.out.println("\nDone. Goodbye!");
    }
    public static void saveProductsToFile(ArrayList<String> products, String fileName)
    {
        Path outputPath = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8))
        {
            for (String record : products)
            {
                writer.write(record);
                writer.newLine();
            }
            System.out.println("\nSaved " + products.size() + " record(s) to " + outputPath.toAbsolutePath());
        }
        catch (IOException e)
        {
            System.out.println("\nError writing to file " + fileName + ": " + e.getMessage());
        }
    }
}
