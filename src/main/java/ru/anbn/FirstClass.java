package ru.anbn;

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

        int a=10;
        int b= 10;
        a=a+b;
        a +=b;  // то же самое

        System.out.println(a++); // вывели и потом инкремент
        System.out.println(++a); // результат отображен до вывода значения

        // && и, логическое умножение
        // || илиб логическое сложение
        // ! не

        boolean result1 = (3>2) && (3>1);   //
        boolean result2 = (3>2) & (3>1);    // вычисляем все части

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

        // или можно так положить
        Book[] library = new Book[] {war, stupid, master, remark};

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
        for (Book book: library) {
            // если книга не содержит Достоевского, то её выводить не будем
            if (!book.author.contains("Достоевский")) {
                System.out.println("Book " + book.doReadBook());
            }
        }

        // тот же цикл, что и выше, только с использованием continue;
        for (Book book: library) {
            // если книга содержит Достоевского, на continue завершаем текущую итерацию и переходим к следующей
            if (book.author.contains("Достоевский"))
                continue;   // ключевое слово завершает итерацию цикла и переходит к следующей, если она есть
                System.out.println("Book " + book.doReadBook());
        }

        // заведем переменную
        Book desiredBook = null;
        for (Book book: library) {
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


    }

}
