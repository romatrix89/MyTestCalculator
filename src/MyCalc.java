import java.util.Scanner;

class MyCalc {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Вас приветствует приложение - Калькулятор!");
        System.out.println("Обратите внимание! ");
        System.out.println("1. Калькулятор умеет работать только с целыми числами");
        System.out.println("2. Калькулятор умеет работать только с арабскими или римскими цифрами одновременно");
        System.out.println("3. Калькулятор работает с числами от 1 до 10 включительно, не более.");
        System.out.println("Введите выражение для вычисления:");
        String newTerm = scan.nextLine();
        Main string=new Main();
        string.setTerm(newTerm);
    }
}

class Main {
    static String ans;
    public static String calc(String input) {
        int sign = 0;
        String result="";
        /*Определяем массив арабских чисел для дальнейшего сравнения на корректность вводимого выражения*/
        String [] arabNumber={"1","2","3","4","5","6","7","8","9","10"};
        /*Выделяем два числа до знака и после знака, также определяем как арифметическую операцию необходимо выполнить*/
        String num1 = "", num2 = "";
        if (input.contains("+")) {
            sign = 1;
            num1 = input.substring(0, input.indexOf("+"));
            num2 = input.substring(input.indexOf("+") + 1);
        }
        if (input.contains("-")) {
            sign = 2;
            num1 = input.substring(0, input.indexOf("-"));
            num2 = input.substring(input.indexOf("-") + 1);
        }
        if (input.contains("*")) {
            sign = 3;
            num1 = input.substring(0, input.indexOf("*"));
            num2 = input.substring(input.indexOf("*") + 1);
        }
        if (input.contains("/")) {
            sign = 4;
            num1 = input.substring(0, input.indexOf("/"));
            num2 = input.substring(input.indexOf("/") + 1);
        }
        /*Определяем являются ли полученые числа римскими, путем сравнения полученых значений с перечислением RomNum */
        int n1 = 0, n2 = 0;
        for (RomNum c : RomNum.values()) {
            if (c.name().equals(num1)) {
                n1 = 1;
            }
            if (c.name().equals(num2)) {
                n2 = 1;
            }
        }
        /*Определяем являются ли полученые числа арабскими, путем сравнения полученых значений с массивом arabNumber */
        for (int i=0; i<10; i++){
            if (num1.equals(arabNumber[i])) {
            n1 = 2;
        }
            if (num2.equals(arabNumber[i])) {
            n2 = 2;
            }}
        /*Обработка исключений*/
        if (sign==0){
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. строка не является математической операцией");
            }
        }
        if ((n1==1&&n2==2)||(n1==2&&n2==1)){
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
            }
        }
        /*Если оба римских числа удовлетворяю условию, то вызываем метод Operation, передавая ему информацию об
        * арифметической операции и два числа предварительно преобразованные в арабские через метод RomToArabic.
        * Если числа арабские, то передаем необходимые значение в метод Operation.*/
        if (n1==1&&n2==1) {
            if ((RomToArabic(num1)-RomToArabic(num2)<0)&&sign==2) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                }
            } else {result=ArabicToRom(Operation(sign, RomToArabic(num1), RomToArabic(num2)));}}
        if (n1==2&&n2==2) {result=Integer.toString(Operation(sign, Integer.parseInt(num1), Integer.parseInt(num2)));}
        if ((n2==0||n1==0)&&sign!=0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
    }
        return result;
    }

    public void setTerm(String term){
        ans=calc(term);
        System.out.println("Ответ:"+ans);
    }

    /*Метод выполняет необходимую арифметическую операцию между двумя числами*/
    public static int Operation(int sign, int num1, int num2){
        int result=0;
        switch (sign) {
            case 1 -> result = num1 + num2;
            case 2 -> result = num1 - num2;
            case 3 -> result = num1 * num2;
            case 4 -> result = num1 / num2;
        }
        return result;
    }
    /*Метод преобразует римское число в арабское*/
    public static int RomToArabic(String romanNumber){
        int arab;
        RomNum ar = RomNum.valueOf(romanNumber);
        if(ar.ordinal() < 10){
            arab = ar.ordinal() + 1;
        } else
        {
            arab = ar.ordinal() - 9;
        }
        return arab;
    }
    /*Метод преобразует арабское число в римское*/
    public static String ArabicToRom(int arab){
        String romanNumber;
        String [][] c={{"","I","II","III","IV","V","VI","VII","VIII","IX"},
                {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
                {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
                {"","M","MM","MMM"}};
        romanNumber=c[3][(int)Math.floor(arab/1000.0%10)]+c[2][(int)Math.floor(arab/100.0%10)]+c[1][(int)Math.floor(arab/10.0%10)]+c[0][(int)Math.floor(arab%10)];
        return romanNumber;
    }
}


