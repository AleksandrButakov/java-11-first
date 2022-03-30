package ru.anbn;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class FirstClass {

    // поле класса или class feet
    int var = 1_000_000;

    byte aByte;     // 8 bit    -128 ... 127
    short aShort;   // 16 bit   -32768 ... 32767
    int aInt;       // 32 bit   -2 ^ 31 ... (2 ^ 31) - 1
    long aLong;     // 64 bit   -2 ^ 63 ... (2 ^ 63) - 1

    float aFloat = 4.6f;   // 32 bit

    // саый используемый если просто хранить, но не для арифметики
    double aDouble = 456.54; // 64 bit

    char aChar;
    boolean aBoolear;
    // литерал - представление типа данных int i = 10, 10 это литерал
    String aString = "QAGuru";

    public static void main(String[] args) {
        // + -- сложение
        // - -- вычитание
        // * -- умножение
        // / -- целочисленное деление 5 / 2 = 2
        // % -- остаток от деления
        // инкремент ++
        // декремент --

        System.out.println(5 + 10);
        // >
        // <
        // >=
        // <=
        // ==
        // !=

        System.out.println(2 < 3);

        // =
        // +=
        // -=

        int a = 10;
        int b = 10;
        a = a + b;
        a += b;  // то же самое

        System.out.println(a++); // вывели и потом инкремент
        System.out.println(++a); // результат отображен до вывода значения

        // && и, логическое умножение
        // || илиб логическое сложение
        // ! не

        boolean result1 = (3 > 2) && (3 > 1);   //
        boolean result2 = (3 > 2) & (3 > 1);    // вычисляем все части

        Book war = new Book("Лев Толстой", "Анна Каренина");
        Book stupid = new Book("Федор Достоевский", "Хдиот");
        Book master = new Book("Михаил Булгаков", "Мастер и Маргарита");
        Book remark = new Book("Эрих Мария Ремарк", "Ночь в Лиссабоне");
        // выводим данные на экран
        war.doReadBook();
        stupid.doReadBook();
        master.doReadBook();
        remark.doReadBook();

        /* кладем элементы в массив
           Book[] library = new Book[4];
           library[0] = war;
           library[1] = stupid;
           library[2] = master;
           library[3] = remark;
         */

        // List, Set and Map - это интерфейсы и у них есть своя реализация

        // или можно так положить
        Book[] library = new Book[]{war, stupid, master, remark};
        /* самая популярная коллекция (интерфейс) данных называется <List> . В отличие от массива она не имеет
           фиксированной длины и легко дополняется новыми данными. Строку выше можно заменить строкой ниже, немного
           изменив последующий код. Лист позволяет хранить множество однотипных объектов.
           Разница с массивом это то, что нет ограничения на размер.
         */
        List<Book> libList = List.of(war, stupid, master, remark);

        /* также есть коллекция Set в которой проверка на однотипные данные происходит на этапе инициализации и
           в случае их наличия получаем исключение.
         */
        Set<Book> libSet = Set.of(war, stupid, master, remark);

        /* Есть коллекция Map. Она удобна тем, что можно работать с коллекцией используя ключи. С ключами доставать
           объекты в определенных условиях легче. Например, конструкция описывает человека, найти его по номеру
           паспорта легче, а затем уже работать с другими его данными. Важен hashMap. Принцип работы как в Json,
           ключ : значение. Map имеет небольшой размер.
         */
        Map<Integer, Book> booksMap = Map.of(
                1, war,
                2, stupid,
                3, master,
                4, remark
        );

        System.out.println("Array length: " + library.length);
        System.out.println("Equal objects? " + (war.equals(stupid)));

        System.out.println();
        System.out.println("Cycles while");
        // cycles
        int index = 0;
        while (index < library.length) {
            System.out.println("Book " + library[index].doReadBook());
            index++;
        }

        System.out.println();
        System.out.println("Cycles do while");
        index = 0;
        do {
            System.out.println("Book " + library[index].doReadBook());
            index++;
        } while (index < library.length);

        System.out.println();
        System.out.println("Cycles for");
        for (int i = 0; i < library.length; i++) {
            System.out.println("Book " + library[i].doReadBook());
        }

        System.out.println();
        System.out.println("for the reverse cycle");
        for (int i = (library.length - 1); i >= 0; i--) {
            System.out.println("Book " + library[i].doReadBook());
        }

        System.out.println();
        System.out.println("for each cycle");
        /* for (что : откуда) Справа источник данных, массив или коллекция, слева это элемент который мы будем
           получать из этой коллекции или массива на каждой итерации и работать с ним в теле цикла.
           Этот цикл будет работать до конца массива
         */
        for (Book book : library) {
            // если книга не содержит Достоевского, то её выводить не будем
            if (!book.author.contains("Достоевский")) {
                System.out.println("Book " + book.doReadBook());
            }
        }

        System.out.println();
        System.out.println("for each cycle with continue");
        // тот же цикл, что и выше, только с использованием continue;
        for (Book book : library) {
            // если книга содержит Достоевского, на continue завершаем текущую итерацию и переходим к следующей
            if (book.author.contains("Достоевский"))
                continue;   // ключевое слово завершает итерацию цикла и переходит к следующей, если она есть
            System.out.println("Book " + book.doReadBook());
        }

        System.out.println();
        System.out.println("for each cycle with break");
        // заведем переменную
        Book desiredBook = null;
        for (Book book : library) {
            /* ищем в авторах достоевского и если находим то присваиваем переменной desiredBook значение book
               и прерываем выполнение цикла командой break. Затем выводим найденную книгу с Достоевским
             */
            if (book.author.contains("Достоевский")) {
                desiredBook = book;
                /* break; завершает цикл. Можем еще использовать return; но return - это конструкция по выходу
                из метода, т.е. работа метода будет прервана. Можно еще System.exit(0) и завершить выполнение
                всей программы. В общем break and continue это два основных способа
                 */
                break;
            }
        }
        System.out.println("Book " + desiredBook.doReadBook());

        System.out.println();
        System.out.println("for each cycle with List<>");
        // работа с List<>
        for (Book book : libList) {
            if (book.author.contains("Достоевский")) {
                desiredBook = book;
                break;
            }
        }
        System.out.println("Book " + desiredBook.doReadBook());

        System.out.println();
        System.out.println("for cycle with List<>");
        // работа с List<>
        for (int i = (libList.size() - 1); i >= 0; i--) {
            System.out.println("Book " + libList.get(i).doReadBook());
            if (libList.contains("Дост")) {
                System.out.println("Data found in the list collections");
            }
        }

        // у Map есть метод entrySet который возвращает пары (ключ : значение),
        for (Map.Entry<Integer, Book> bookEntry : booksMap.entrySet()) {
            // из bookEntry можем достать key
            Integer key = bookEntry.getKey();
            // и из bookEntry можем достать value
            Book value = bookEntry.getValue();
            // и можем что-то с ними сделать. Этот вариант удобен когда необходимо перебрать все данные в Map

        }

        for (Book value : booksMap.values()) {
            /* с помощью booksMap.values() можем пройтись по всем значениям Map в отрыве от ключа
               т.е. итерирование по Map идет с помощью трех ключей:
               entrySet()
               values()
               keySet();
             */
        }
    }

}
