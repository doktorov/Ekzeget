package ekzeget.ru.ekzeget.model.gson;

public class GsonBooks {
    public Book[] nz;
    public Book[] vz;

    public class Book {
        public String key;
        public String name;
        public String short_name;
        public int parts;
    }
}
