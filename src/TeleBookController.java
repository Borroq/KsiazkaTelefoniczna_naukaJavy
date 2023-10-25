import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TeleBookController {


    private TeleBook teleBook = new TeleBook();
    private Scanner input = new Scanner(System.in);

    //FileUtils fileUtils = new FileUtils();

    public TeleBookController() {
        FileUtils.read();
    }


    public void loop() {
        OPTIONS options = null;
        do {
            //fileUtils.createFile();
            showOptions();
            try {
                options = chooseOption();
                executeOption(options);
            } catch (NoSuchFieldException e) {
                System.out.println("Nieprawidłowa opcja");
            }

        } while (options != OPTIONS.END_PROGRAM);

    }

    private void showOptions() {
        System.out.println(">>>Opcje:");
        for (OPTIONS option : OPTIONS.values()) {
            System.out.println(option);
        }
    }

    private OPTIONS chooseOption() throws NoSuchFieldException {
        int choose = input.nextInt();
        input.nextLine();
        return OPTIONS.convertToOption(choose);
    }

    private void executeOption(OPTIONS options) {
        int choseOption = options.getShortcut();
        switch (choseOption) {
            case 0:
                addContact();
                break;
            case 1:
                searchByName();
                break;
            case 2:
                searchByTelephon();
                break;
            case 3:
                delete();
                break;
            case 4:
                close();
                break;
        }
    }

    private void delete() {
        System.out.println("Podaj nazwę kontaktu do usunięcia: ");
        String name = input.nextLine();
        boolean removed = teleBook.remove(name);
        if(removed) {
            System.out.println("Rekord usunięty");
        } else {
            System.out.println("Nie ma rekordu o takiej nazwi");
        }
    }

    private void searchByTelephon() {
        System.out.println("Podaj fragment numeru telefonu");
        String telephone = input.nextLine();
        List<Contact> contacts = teleBook.findByTelephone(telephone);
        if(contacts.isEmpty()) {
            System.out.println("Brak wyniku");
        } else {
            contacts.forEach(System.out::println);
        }
    }

    private void searchByName() {
        System.out.println("Podaj fragment nazwy kontaktu");
        String name = input.nextLine();

        List<Contact> contacts = teleBook.findByName(name);
        if(contacts.isEmpty()) {
            System.out.println("Brak wyniku");
        } else {
            contacts.forEach(System.out::println);
        }
    }

    private void addContact() {
        System.out.println();
        System.out.println("Podaj nazwę kontaktu");
        String name = input.nextLine();
        System.out.println("Podaj nr telefonu");
        String telephone = input.nextLine();
        try {
            boolean add = teleBook.add(name,telephone);
            if (add) {
                System.out.println("Rekord dodany");
            } else {
                System.out.println("Nie można dodać rekordu. Wpis o takiej nazwie już istnieje.");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Nazwa ani numer telefonu nie mogą być puste");
        }
    }

    private void close(){
        input.close();
        try {
            FileUtils.save(teleBook);
            System.out.println("Zapisano zmiany.");
        } catch (IOException e) {
            System.err.println("nie udalo się zapisac danych");
        }
        System.out.println("Bye bye. See you!");
    }
}
