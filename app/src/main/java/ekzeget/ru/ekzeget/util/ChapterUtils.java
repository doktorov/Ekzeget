package ekzeget.ru.ekzeget.util;

import java.util.ArrayList;
import java.util.List;

public class ChapterUtils {
    public static List<String> getChapters(String key, int parts, boolean full) {
        List<String> chapters = new ArrayList<>();

        chapters.add(full ? "Глава" : "Гл.");

        if (!key.equals("null")) {
            chapters.add("О книге");

            for (int i = 0; i < parts; i++) {
                chapters.add(String.valueOf(i + 1));
            }
        }

        return chapters;
    }
}
