package ekzeget.ru.ekzeget;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.Streams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    String[] data = {"Кн.", "Мф.", "Мк.", "Лк.", "Ин.", "Деян.", "Иак.", "1 Пет.", "2 Пет.", "1 Ин.", "2 Ин.", "3 Ин.",
            "Иуд.", "Рим.", "1 Кор.", "2 Кор.", "Гал.", "Еф.", "Флп.", "Кол.", "1 Фес.", "2 Фес.", "1 Тим.",
            "2 Тим.", "Тит.", "Флм.", "Евр.", "Откр.", "Быт.", "Исх.", "Лев.", "Чис.", "Втор.", "Нав.", "Суд.",
            "Руф.", "1 Цар.", "2 Цар.", "3 Цар.", "4 Цар.", "1 Пар.", "2 Пар.", "1 Езд.", "Неем.", "2 Езд.",
            "Тов.", "Иудиф.", "Есф.", "Иов.", "Пс.", "Притч.", "Еккл.", "Песн.", "Прем.", "Сир.", "Ис.",
            "Иер.", "Плач.", "Посл.", "Вар.", "Иез.", "Дан.", "Ос.", "Иоил.", "Ам.", "Авд.", "Ион.", "Мих.",
            "Наум.", "Авв.", "Соф.", "Агг.", "Зах.", "Мал.", "1 Мак.", "2 Мак.", "3 Мак.", "3 Езд."};

    String[] data2 = {"Гл."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        // заголовок
        spinner.setPrompt("Title");
        // выделяем элемент
        spinner.setSelection(0);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data2);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(adapter2);
        spinner2.setPrompt("Title");
        spinner2.setSelection(0);

        InputStream XmlFileInputStream = getResources().openRawResource(R.raw.books);
        String sxml = readTextFile(XmlFileInputStream);

        Gson gson = new Gson();
        GsonBooks response = gson.fromJson(sxml, GsonBooks.class);

        String s = "";
    }

    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
}

