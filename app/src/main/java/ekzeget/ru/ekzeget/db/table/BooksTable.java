package ekzeget.ru.ekzeget.db.table;

import android.provider.BaseColumns;

public class BooksTable implements BaseColumns {
    public static final String TABLE_NAME = "books";

    private static final String IDENTIFIER = "identifier";
    private static final String ZAV = "zav";
    public static final String KN = "kn";
    public static final String TITLE = "title";
    private static final String SOKR = "sokr";
    public static final String MENU = "menu";
    public static final String PARTS = "parts";

    public static final String WHERE_ZAV = ZAV + "=?";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String CREATE_TABLE
            = "CREATE TABLE " + TABLE_NAME + " ("
            + _ID + " INTEGER PRIMARY KEY,"
            + IDENTIFIER + " INTEGER,"
            + ZAV + " TEXT,"
            + KN + " TEXT,"
            + TITLE + " TEXT,"
            + SOKR + " TEXT,"
            + MENU + " TEXT,"
            + PARTS + " INTEGER"
            + ");";

    public static final StringBuilder DEFAULT_VALUES = new StringBuilder()
            .append("INSERT INTO " + TABLE_NAME +" (")
            .append("'" + IDENTIFIER + "', '" + ZAV + "', '" + KN + "',")
            .append("'" + TITLE + "', '" + SOKR + "', '" + MENU + "', '" + PARTS + "'")
            .append(") VALUES")
            .append("(1, 'vz', 'byt', 'Бытие', 'Быт', 'Бытие', '50'),")
            .append("(2, 'vz', 'ish', 'Исход', 'Исх', 'Исход', '40'),")
            .append("(3, 'vz', 'lev', 'Левит', 'Лев', 'Левит', '27'),")
            .append("(4, 'vz', 'chis', 'Числа', 'Чис', 'Числа', '36'),")
            .append("(5, 'vz', 'vtor', 'Второзаконие', 'Втор', 'Второзаконие', '34'),")
            .append("(6, 'vz', 'nav', 'Книга Иисуса Навина', 'Нав', 'Иисуса Навина', '24'),")
            .append("(7, 'vz', 'sud', 'Книга судей Израилевых', 'Суд', 'Судей', '21'),")
            .append("(8, 'vz', 'ruf', 'Книга Руфь', 'Руф', 'Руфь', '4'),")
            .append("(9, 'vz', '1car', '1-я книга Царств', '1 Цар', '1 Царств', '31'),")
            .append("(10, 'vz', '2car', '2-я книга Царств', '2 Цар', '2 Царств', '24'),")
            .append("(11, 'vz', '3car', '3-я книга Царств', '3 Цар', '3 Царств', '22'),")
            .append("(12, 'vz', '4car', '4-я книга Царств', '4 Цар', '4 Царств', '25'),")
            .append("(13, 'vz', '1par', '1-я книга Паралипоменон', '1 Пар', '1 Паралипом.', '29'),")
            .append("(14, 'vz', '2par', '2-я книга Паралипоменон', '2 Пар', '2 Паралипом.', '36'),")
            .append("(15, 'vz', '1ezd', '1-я книга Ездры', '1 Езд', '1 Ездры', '10'),")
            .append("(16, 'vz', 'neem', 'Книга Неемии', 'Неем', 'Неемия', '13'),")
            .append("(17, 'vz', '2ezd', '2-я книга Ездры', '2 Езд', '2 Ездры *', '9'),")
            .append("(18, 'vz', 'tov', 'Книга Товита', 'Тов', 'Товит *', '14'),")
            .append("(19, 'vz', 'iudif', 'Книга Иудифь', 'Иудиф', 'Иудифь *', '16'),")
            .append("(20, 'vz', 'esf', 'Книга Есфири', 'Есф', 'Есфирь', '10'),")
            .append("(21, 'vz', 'iov', 'Книга Иова', 'Иов', 'Иов', '42'),")
            .append("(22, 'vz', 'ps', 'Псалтирь', 'Пс', 'Псалтирь', '151'),")
            .append("(23, 'vz', 'pritch', 'Притчи Соломона', 'Притч', 'Притчи', '31'),")
            .append("(24, 'vz', 'ekkl', 'Книга Екклеcиаста', 'Еккл', 'Екклесиаст', '12'),")
            .append("(25, 'vz', 'pesn', 'Песнь песней Соломона', 'Песн', 'Песнь Песней', '8'),")
            .append("(26, 'vz', 'prem', 'Книга премудрости Соломона', 'Прем', 'Прем. Соломона *', '19'),")
            .append("(27, 'vz', 'sir', 'Книга премудрости Иисуса, сына Сирахова', 'Сир', 'Сирах *', '51'),")
            .append("(28, 'vz', 'is', 'Книга пророка Исаии', 'Ис', 'Исаия', '66'),")
            .append("(29, 'vz', 'ier', 'Книга пророка Иеремии', 'Иер', 'Иеремия', '52'),")
            .append("(30, 'vz', 'plach', 'Плач Иеремии', 'Плач', 'Плач Иеремии', '5'),")
            .append("(31, 'vz', 'posl', 'Послание Иеремии', 'Посл', 'Посл. Иеремии *', '1'),")
            .append("(32, 'vz', 'var', 'Книга пророка Варуха', 'Вар', 'Варух *', '5'),")
            .append("(33, 'vz', 'iez', 'Книга пророка Иезекииля', 'Иез', 'Иезекииль', '48'),")
            .append("(34, 'vz', 'dan', 'Книга пророка Даниила', 'Дан', 'Даниил', '14'),")
            .append("(35, 'vz', 'os', 'Книга пророка Осии', 'Ос', 'Осия', '14'),")
            .append("(36, 'vz', 'ioil', 'Книга пророка Иоиля', 'Иоил', 'Иоиль', '3'),")
            .append("(37, 'vz', 'am', 'Книга пророка Амоса', 'Ам', 'Амос', '9'),")
            .append("(38, 'vz', 'avd', 'Книга пророка Авдия', 'Авд', 'Авдий', '1'),")
            .append("(39, 'vz', 'ion', 'Книга пророка Ионы', 'Ион', 'Иона', '4'),")
            .append("(40, 'vz', 'mih', 'Книга пророка Михея', 'Мих', 'Михей', '7'),")
            .append("(41, 'vz', 'naum', 'Книга пророка Наума', 'Наум', 'Наум', '3'),")
            .append("(42, 'vz', 'avv', 'Книга пророка Аввакума', 'Авв', 'Аввакум', '3'),")
            .append("(43, 'vz', 'sof', 'Книга пророка Софонии', 'Соф', 'Софония', '3'),")
            .append("(44, 'vz', 'agg', 'Книга пророка Аггея', 'Агг', 'Аггей', '2'),")
            .append("(45, 'vz', 'zah', 'Книга пророка Захарии', 'Зах', 'Захария', '14'),")
            .append("(46, 'vz', 'mal', 'Книга пророка Малахии', 'Мал', 'Малахия', '4'),")
            .append("(47, 'vz', '1mak', '1-я книга Маккавейская', '1 Мак', '1 Маккавейская *', '16'),")
            .append("(48, 'vz', '2mak', '2-я книга Маккавейская', '2 Мак', '2 Маккавейская *', '15'),")
            .append("(49, 'vz', '3mak', '3-я книга Маккавейская', '3 Мак', '3 Маккавейская *', '7'),")
            .append("(50, 'vz', '3ezd', '3-я книга Ездры', '3 Езд', '3 Ездры *', '16'),")
            .append("(51, 'nz', 'mf', 'Евангелие от Матфея', 'Мф', 'От Матфея', '28'),")
            .append("(52, 'nz', 'mk', 'Евангелие от Марка', 'Мк', 'От Марка', '16'),")
            .append("(53, 'nz', 'lk', 'Евангелие от Луки', 'Лк', 'От Луки', '24'),")
            .append("(54, 'nz', 'in', 'Евангелие от Иоанна', 'Ин', 'От Иоанна', '21'),")
            .append("(55, 'nz', 'deyan', 'Деяния апостолов', 'Деян', 'Деяния', '28'),")
            .append("(56, 'nz', 'iak', 'Послание ап.Иакова', 'Иак', 'Иакова', '5'),")
            .append("(57, 'nz', '1pet', '1-ое послание ап.Петра', '1 Пет', '1 Петра', '5'),")
            .append("(58, 'nz', '2pet', '2-ое послание ап.Петра', '2 Пет', '2 Петра', '3'),")
            .append("(59, 'nz', '1in', '1-ое послание ап.Иоанна', '1 Ин', '1 Иоанна', '5'),")
            .append("(60, 'nz', '2in', '2-ое послание ап.Иоанна', '2 Ин', '2 Иоанна', '1'),")
            .append("(61, 'nz', '3in', '3-ое послание ап.Иоанна', '3 Ин', '3 Иоанна', '1'),")
            .append("(62, 'nz', 'iud', 'Послание ап.Иуды', 'Иуд', 'Иуды', '1'),")
            .append("(63, 'nz', 'rim', 'К римлянам послание ап.Павла', 'Рим', 'Римлянам', '16'),")
            .append("(64, 'nz', '1kor', '1-ое послание к коринфянам ап.Павла', '1 Кор', '1 Коринфянам', '16'),")
            .append("(65, 'nz', '2kor', '2-ое послание к коринфянам ап.Павла', '2 Кор', '2 Коринфянам', '13'),")
            .append("(66, 'nz', 'gal', 'К галатам послание ап.Павла', 'Гал', 'Галатам', '6'),")
            .append("(67, 'nz', 'ef', 'К ефесянам послание ап.Павла', 'Еф', 'Ефесянам', '6'),")
            .append("(68, 'nz', 'flp', 'К филиппийцам послание ап.Павла', 'Флп', 'Филиппийцам', '4'),")
            .append("(69, 'nz', 'kol', 'К колоссянам послание ап.Павла', 'Кол', 'Колоссянам', '4'),")
            .append("(70, 'nz', '1fes', '1-ое послание к фессалоникийцам ап.Павла', '1 Фес', '1 Фессалоник.', '5'),")
            .append("(71, 'nz', '2fes', '2-ое послание к фессалоникийцам ап.Павла', '2 Фес', '2 Фессалоник.', '3'),")
            .append("(72, 'nz', '1tim', '1-ое послание к Тимофею ап.Павла', '1 Тим', '1 Тимофею', '6'),")
            .append("(73, 'nz', '2tim', '2-ое послание к Тимофею ап.Павла', '2 Тим', '2 Тимофею', '4'),")
            .append("(74, 'nz', 'tit', 'К Титу послание ап.Павла', 'Тит', 'Титу', '3'),")
            .append("(75, 'nz', 'flm', 'К Филимону послание ап.Павла', 'Флм', 'Филимону', '1'),")
            .append("(76, 'nz', 'evr', 'К евреям послание ап.Павла', 'Евр', 'Евреям', '13'),")
            .append("(77, 'nz', 'otkr', 'Откровение ап.Иоанна Богослова', 'Откр', 'Апокалипсис', '22');");
}