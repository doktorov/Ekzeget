package ekzeget.ru.ekzeget;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;

import ekzeget.ru.ekzeget.db.DbHelper;
import ekzeget.ru.ekzeget.db.DbQuery;
import ekzeget.ru.ekzeget.preferences.PDefaultValue;
import ekzeget.ru.ekzeget.preferences.Prefs;
import ekzeget.ru.ekzeget.util.AppVersionCode;

/*
Сергей Жидков: осталось в принципе только продумать:
1. как осуществлять переход между стихами когда читаешь стих? Хочется свайпом влево или вправо
2. Как осуществлять аналогичный переход в самом толковании (толкование на следующий или предыдущий стих)?
3. Все-таки хочется видеть текст самого стиха, когда читаешь толкование.
4. По умолчанию андроид не воспринимает html теги и аски коды?

Dmitriy Doktorov: 1. Если читаешь стих там где табы, то никак
2. Можно перенести как было сделано в прошлый раз
3. Хорошо
4. Нет

Сергей Жидков: 1. переход по стихам - жалко. Надо что-то придумать. Это из-за таб нельзя?
Сергей Жидков: 2. Да, видимо так придется сделать
Сергей Жидков: 4. Надо обучить его )))

Dmitriy Doktorov: 1. переход по стихам - жалко. Надо что-то придумать. Это из-за таб нельзя?Я подумаю
Dmitriy Doktorov: 4. Надо обучить его )))попробую
 */

/*
|||слово||| - слово будет жирным шрифтом
///слово/// - слово будет курсивом
        {{{Мф. 1:2-3}}} - ссылка на соответствующий стих Писания
        {{{Мф. 1}}} - ссылка на соответствующую главу Писания
        При наведении курсора мыши на ссылку
        всплывает окно с текстом данной ссылки
        [[[Logoj]]] - слово будет выводиться греческим шрифтом
@1 - ссылка на примечание
        *1 - сноска соответствующего примечания
        На сайте используется принцип постихового размещения толкований. Но бывает, что экзегет составил одно толкование сразу же на несколько стихов, и разбить это толкование по стихам не представляется возможным. В таком случае используются специальные шорт-коды. У толкования на первый стих нужной группы (нескольких стихов) в самом начале толкования стоит SHORTCODE_GROUP-, после указана группа стихов, например SHORTCODE_GROUP-3-10 (без пробелов, с дефисами), далее следует текст толкования. У всех остальных стихов (в нашем примере с 4 по 10) вставлено SHORTCODE_SSIL- и номер стиха с основным толкованием, т.е. в нашем примере SHORTCODE_SSIL-3.
        Чтобы как-то выделить блок сносок, а также указание на источник толкования (где они имеются), необходимо обрамить весь блок ссылок вначале тремя "равно", а в конце двумя "равно". Указание на источник тремя "плюсами" и двумя "плюсами" соответственно. Т.е. должно получиться:
        ===
        *1 Текст первой сноски
        *2 Текст второй сноски
        ==
        Соответственно ссылка на источник толкования:
        +++Начало ересей. Православное обозрение, 1861++
*/
public class App extends Application {

    private static Context mContext;

    private static String mPackageName;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        mPackageName = getPackageName();

        if (Prefs.getApplicationVersionCode(this) < AppVersionCode.getApkVersionCode(this))
            checkApplicationVersionCode();
    }

    public static Resources getAppResources() {
        return mContext.getResources();
    }

    public static Context getContext() {
        return mContext;
    }

    public static String getInfoPackageName() {
        return mPackageName;
    }

    public static SQLiteDatabase getWritableDatabase() {
        return new DbHelper(mContext).getWritableDatabase();
    }

    public static SQLiteDatabase getReadableDatabase() {
        return new DbHelper(mContext).getReadableDatabase();
    }

    void checkApplicationVersionCode() {
        switch(Prefs.getApplicationVersionCode(this)) {
            case PDefaultValue.VERSION_CODE:
                DbQuery db = new DbQuery(this);
                db.createDatabase();
                db.close();
                Prefs.putApplicationVersionCode(this, AppVersionCode.getApkVersionCode(this));
                checkApplicationVersionCode();
                break;
            //**********************************************************//
            default:
                break;
        }
    }
}
