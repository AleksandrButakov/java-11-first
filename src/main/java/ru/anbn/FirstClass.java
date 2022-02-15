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

        Book murzilka = new Book();
        murzilka.doReadBook();

    }

}
