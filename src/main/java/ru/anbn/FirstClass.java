package ru.anbn;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.zip.CheckedInputStream;


public class FirstClass {

    static String sIPAddress;              // IP address
    static int iCIDR;                      // маска в формате CIDR
    static String sCIDR;                   // маска в формате CIDR
    static String sTempVar;
    static String sByteVar = ""; // временная переменная для выполнения промежуточных расчетов
    static int iTempVar;                   // временная переменная для выполнения промежуточных расчетов
    static int b3, b2, b1, b0;             // переменные для хранения значений байт
    static String sb3 = "", sb2 = "", sb1 = "", sb0 = "";      // переменные для хранения значений байт
    static int sLength;                    // длина IP адреса

    public static void main(String[] args) {
        // программа расчета IP адреса подсети, количества хостов в ней, адреса gateway
        // по введенному IP адресу из диапазона подсети и маски введенной в пормате CIDR
        IPAddress ipAddress = new IPAddress();

        Scanner in = new Scanner(System.in);
        System.out.print("Enter IP address: ");
        sIPAddress = in.nextLine();
        ipAddress.sIPAddress = sIPAddress;

        try {
            System.out.print("Enter CIDR: ");
            iCIDR = in.nextInt();
            sCIDR = String.valueOf(iCIDR);
            ipAddress.sCIDR = sCIDR;

        } catch (InputMismatchException e) {
            incorrectData("01");
        }

        // проверка корректности введеных значений включает в себя следующие шаги:
        // 1. Значение первого и четвертого байта лежит в диапазоне 1...254
        // 2. Значение второго и третьего байта лежит в диапазоне 0...255
        //. IP адрес содержит три точки или три зяпятые (значения для нас эквивалентны)
        // 4. Значение CIDR лежит в диапазоне 24...32

        // проверим что длина IP адреса лежит в диапазоне 7...15 символов
        sLength = sIPAddress.length();
        if (sLength < 7 || sLength > 15) {
        incorrectData("02");
        }

        // проверим что введенный символ является числом, точкой или запятой
        for (int i = 0; i < sLength; i++) {
            sTempVar = sIPAddress.substring(i, i + 1);
            System.out.println("sTempVar = " + sTempVar);
            checkingCorrectnessSymbol(sTempVar, i);
        }

        // проверим что значение CIDR в допустимом диапазоне

        if (iCIDR >= 24 && iCIDR <= 32) {
            // correct data
        } else {
            // incorrect data
            incorrectData("03");
        }

        System.out.println(sb3);
        System.out.println(sb2);
        System.out.println(sb1);
        System.out.println(sb0);
    }


    // проверим что введенный символ является числом, точкой или запятой
    static void checkingCorrectnessSymbol(String sTempVar, int i) {
        if (sTempVar.equals("0") || sTempVar.equals("1") || sTempVar.equals("2") || sTempVar.equals("3") || sTempVar.equals("4") ||
                sTempVar.equals("5") || sTempVar.equals("6") || sTempVar.equals("7") || sTempVar.equals("8") || sTempVar.equals("9") ||
                sTempVar.equals(".") || sTempVar.equals(",")) {
            // the correct is data
        } else {
            // the incorrect is data
            incorrectData("04");
        }

        // нашли очередную точку в адресе, заполним значения текущего байта
        if (sTempVar.equals(".") || sTempVar.equals(",") || i + 1 == sLength) {
            if (sb3.equals("")) {
                sb3 = sByteVar;
            } else {
                if (sb2.equals("")) {
                    sb2 = sByteVar;
                } else {
                    if (sb1.equals("")) {
                        sb1 = sByteVar;
                    } else {
                        if (sb0.equals("")) {
                            sByteVar = sByteVar + sTempVar;
                            sb0 = sByteVar;
                        } else {
                            System.out.println("Something went wrong :-(");
                            System.exit(0);
                        }
                    }
                }
            }
            sByteVar = "";
        } else {
            sByteVar = sByteVar + sTempVar;
        }

    }

    static void incorrectData (String step) {
        System.out.println("Incorrectly entered data :-(  Step = " + step);
        System.exit(0);
    }

}


        /*
        // boolean, int
        boolean aBoolaen = true;
        int counter = 1;    // counter for the cycle
        int decimalNumber;  // decimal number to convert to other number systems

        while (aBoolaen) {
            System.out.println("The while loop is executed! Step " + counter);
            counter++;
            if (counter == 5) aBoolaen = false;
        }

        // entering a decimal number 0 ... 255 and converting it to binary and hexadecimal
        Scanner in = new Scanner(System.in);
        try {
            // the number belongs to the range 0 ... 255
            decimalNumber=in.nextInt();
            if (decimalNumber<0 || decimalNumber>255) {
                System.out.println("Incorrect data entered :-(");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid data :-(");
        }



        // int

        //

        //
        //

    /*
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
     */