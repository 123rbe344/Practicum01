import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
public class PersonGenerator
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> people = new ArrayList<>();
        boolean addAnother = true;
        do
        {
            String id = SafeInput.getNonZeroLenString(sc, "Enter the person's ID");
            String firstName = SafeInput.getNonZeroLenString(sc, "Enter the person's first name");
            String lastName = SafeInput.getNonZeroLenString(sc, "Enter the person's last name");
            String title = SafeInput.getNonZeroLenString(sc, "Enter the person's title (Mr., Mrs., Ms., Dr., Esq., etc.)");
            int yearOfBirth = SafeInput.getRangedInt(sc, "Enter the person's year of birth", 1, 2026);
            String record = id + ", " + firstName + ", " + lastName + ", " + title + ", " + yearOfBirth;
            people.add(record);
            System.out.println("\nRecord added: " + record);
            addAnother = SafeInput.getYNConfirm(sc, "\nAdd another person");
        } while (addAnother);
        if (!people.isEmpty())
        {
            String fileName = SafeInput.getNonZeroLenString(sc, "\nEnter the file name to save the data to (e.g. PersonTestData.txt)");
            savePeopleToFile(people, fileName);
        }
        else
        {
            System.out.println("\nNo records were entered. No file was saved.");
        }
        System.out.println("\nDone. Goodbye!");
    }
    public static void savePeopleToFile(ArrayList<String> people, String fileName)
    {
        Path outputPath = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8))
        {
            for (String record : people)
            {
                writer.write(record);
                writer.newLine();
            }
            System.out.println("\nSaved " + people.size() + " record(s) to " + outputPath.toAbsolutePath());
        }
        catch (IOException e)
        {
            System.out.println("\nError writing to file " + fileName + ": " + e.getMessage());
        }
    }
}
