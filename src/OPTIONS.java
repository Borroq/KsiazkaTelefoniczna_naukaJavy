public enum OPTIONS {
    ADD_CONTACT(0, "Dodaj kontakt"),
    SEARCH_BY_NAME(1, "Szukaj po nazwie"),
    SEARCH_BY_TELE(2, "Szukaj po telefonie"),
    REMOVE_CONTACT(3, "Usuń"),
    END_PROGRAM(4, "Koniec");

    private final int shortcut;
    private final String description;
    OPTIONS(int shortcut, String description) {
        this.shortcut = shortcut;
        this.description = description;
    }

    public int getShortcut() {
        return shortcut;
    }

    static OPTIONS convertToOption(int option) throws NoSuchFieldException {
        if (option >= OPTIONS.values().length || option < 0) {
            throw new NoSuchFieldException("Brak takiej opcji w menu");
        }
        return OPTIONS.values()[option];
    }

    @Override
    public String toString() {
        return shortcut + " - " + description;
    }
}
