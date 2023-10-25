import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileUtils {
    private static final String FILE_NAME = "telebook.csv";

    public void createFile() {
        File telegook = new File(FILE_NAME);
        if (!telegook.exists()){
            try {
                telegook.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void save (TeleBook teleBook) throws IOException {   //jestli metoda była by statyczna, to wówczas Klasa ta była by narzędziowa ???. Konieczne inne wywołanie metody. Nie ma konieczności tworzenia osobnej instancji obiektu.
        var write = new BufferedWriter(new FileWriter(FILE_NAME));
        for (Contact contact : teleBook) {          //dlaczego tak i dlaczego musiałem zaimplementować interfejs Iterable w klasie TeleBook. Ponieważ obiekt Cobtact contact jest definiowany w klasie TeleBook
            write.write(contact.toCSV());
            write.newLine();
        }
        write.close();
    }

    public static TeleBook read() {    //jestli metoda była by statyczna, to wówczas Klasa ta była by narzędziowa ???. Konieczne inne wywołanie metody. Nie ma konieczności tworzenia osobnej instancji obiektu.
        TeleBook book = null;
        try {
            var reader = new BufferedReader(new FileReader(FILE_NAME));
            Map<String, Contact> contacts = reader.lines()
                    .map(line -> line.split(";"))
                    .map(split -> new Contact(split[0], split[1]))
                    .collect(Collectors.toMap(Contact::getName, Function.identity()));
            book = new TeleBook(new TreeMap<>(contacts));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return book != null? book : new TeleBook();
    }
}
