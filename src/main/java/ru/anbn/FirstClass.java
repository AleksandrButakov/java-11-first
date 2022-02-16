package ru.anbn;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Long.toBinaryString;

public class FirstClass {
    /* Программа расчета подсети по введенному IP адресу и маски в формате CIDR. В выходных данных
     * присутствует маска, адрес подсети с учетом маски, минимальный и максимальный адреса в подсети
     * и количество возможных хостов в диапазоне
     */

    static String sIPAddress;   // IP address
    static String sb3 = "", sb2 = "", sb1 = "", sb0 = "";   // переменные для хранения значений байт IP адреса
    static String sIPAddressSubnet;   // IP адрес подсети
    static String sIPBinByte;   // бинарный вид последнего байта IP адреса в формате String
    static String sIPBinByteSubnet = "";   // бинарный вид последнего байта IP c учетом маски подсети в формате String
    static int sLength;   // длина IP адреса

    static String sCIDR;   // маска в формате CIDR
    static int iCIDR;   // маска в формате CIDR
    static String sMaskBinByte;   // бинарный вид байта маски в формате String

    static String sIPMask; // IP mask

    static String sTempVar;      // временная переменная для выполнения промежуточных расчетов
    static String sByteVar = "";
    static int iTempVar;

    static String hostsMin, hostsMax;   // начальный и конечные IP адреса в подсети
    static int numberHosts;   // количество хостов в подсети

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
            checkingCorrectnessSymbol(sTempVar, i);
        }

        // проверим что значение CIDR в допустимом диапазоне
        if (iCIDR >= 24 && iCIDR <= 30) {
            // correct data
        } else {
            // incorrect data
            incorrectData("03");
        }

        // присвоим значеня sBinByte и sIPMask в соответствии с выбранной маской CIDR
        switch (iCIDR) {
            case (24):
                sMaskBinByte = "00000000";
                sIPMask = "255.255.255.0";
                numberHosts = 254;
                break;
            case (25):
                sMaskBinByte = "10000000";
                sIPMask = "255.255.255.128";
                numberHosts = 126;
                break;
            case (26):
                sMaskBinByte = "11000000";
                sIPMask = "255.255.255.192";
                numberHosts = 62;
                break;
            case (27):
                sMaskBinByte = "11100000";
                sIPMask = "255.255.255.224";
                numberHosts = 30;
                break;
            case (28):
                sMaskBinByte = "11110000";
                sIPMask = "255.255.255.240";
                numberHosts = 14;
                break;
            case (29):
                sMaskBinByte = "11111000";
                sIPMask = "255.255.255.248";
                numberHosts = 6;
                break;
            case (30):
                sMaskBinByte = "11111100";
                sIPMask = "255.255.255.252";
                numberHosts = 2;
                break;
        }

        System.out.println();
        System.out.println("Subnet calculation:");
        System.out.println("Subnet mask byte: " + sMaskBinByte);
        System.out.println("Subnet mask: " + sIPMask);

        // переведем младший байт IP адреса в двоичный вид типа String
        sIPBinByte = toBinaryString(Integer.parseInt(sb0));
        iTempVar = sIPBinByte.length();
        if (iTempVar < 8) {
            for (int i = 0; i <= 7 - iTempVar; i++) {
                sIPBinByte = "0" + sIPBinByte;
            }
        }
        System.out.println("IP address byte: " + sIPBinByte);

        // расчитаем IP адрес с учетом маски подсети, данные запишем в переменную sIPBinByteSubnet
        for (int i = 0; i <= 7; i++) {
            if (sMaskBinByte.substring(i, i + 1).equals("1")) {
                sIPBinByteSubnet = sIPBinByteSubnet + sIPBinByte.substring(i, i + 1);
            } else {
                sIPBinByteSubnet = sIPBinByteSubnet + "0";
            }
        }
        System.out.println("The lowest byte of the IP address, taking into account the mask: " + sIPBinByteSubnet);

        // найдем IP адрес подсети
        sIPAddressSubnet = sb3 + "." + sb2 + "." + sb1 + "." + (Integer.parseInt(sIPBinByteSubnet, 2));
        System.out.println("Subnet IP address: " + sIPAddressSubnet);

        // Найдем начальный адрес в подсети, адрес gateway
        hostsMin = sb3 + "." + sb2 + "." + sb1 + "." + (Integer.parseInt(sIPBinByteSubnet, 2) + 1);
        hostsMax = sb3 + "." + sb2 + "." + sb1 + "." + (Integer.parseInt(sIPBinByteSubnet, 2) + numberHosts);

        System.out.println("Minimum address in the subnet: " + hostsMin);
        System.out.println("Maximum address in the subnet: " + hostsMax);
        System.out.println("Number of hosts in the subnet: " + numberHosts);

        // присвоим значения экземпляру класса ipAddress (в данном функционела дальнейшего их использования не происходит)
        ipAddress.sNetwork = sIPMask;
        ipAddress.sGateway = hostsMax;
        ipAddress.hosts = String.valueOf(numberHosts);

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

    static void incorrectData(String step) {
        System.out.println("Incorrectly entered data :-(  Step = " + step);
        System.exit(0);
    }
}