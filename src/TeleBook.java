import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;

import java.util.*;

public class TeleBook implements Iterable<Contact>{

    private Map<String, Contact> contact = new TreeMap<>();

    public TeleBook() { }

    public TeleBook(Map<String, Contact> contact) {
        this.contact = contact;
    }

    public boolean add(String name, String telephone) {
        // StringUtil.isNotEmpty(name) // Apache
        // StringUtil.isNotBlank(name)
        // Strings.isNullOrEmpty    // Guava (Google)
        // https://stackoverflow.com/questions/14721397/checking-if-a-string-is-empty-or-null-in-java
        //ImmutableList.of(name, telephone).stream().filter(Strings::isNullOrEmpty).findAny().isPresent();
        if (name == null || telephone == null || name.isEmpty() || telephone.isEmpty()) {
            throw new IllegalArgumentException("Name and telephone cannot be null and empty");
        }
//        if (name.isEmpty() || telephone.isEmpty()) {
//            throw new IllegalArgumentException("name and telephone cannot be empty");
//        }
        if (!contact.containsKey(name)) {
            contact.put(name, new Contact(name, telephone));
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(String name) {
        return contact.remove(name) != null;
    }

    public List<Contact> findByName(String name) {
        List<Contact> result = new ArrayList<>();
        for (var entry : contact.entrySet()) {
            if(entry.getKey().contains(name)) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

    public List<Contact> findByTelephone(String telephone) {
        List<Contact> result = new ArrayList<>();
        for (Contact contact : contact.values()) {
            if (contact.getNumber().contains(telephone)) {
                result.add(contact);
            }
        }
        return result;
    }

    @Override
    public Iterator<Contact> iterator() {
        return contact.values().iterator();
    }


}
