package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nums = scanner.nextLine();
        Calculator calculator = new Calculator(nums);
        System.out.println(nums + " = " + calculator.PlusAndMinus());
    }
    /**
     * Класс описывающий калькулятор
     * @author Fomin Nikita
     * @version 2.0
     * */

    public static class Calculator {
        /** Поле для поиска пробелов в выражении */
        String[] space;
        /** Поле идентификатор */
        int indexspace;
        /**
         * Конструктор для установки значений полей
         * indexspace идентификатор
         * space массив принимающий в себя значения выражения
         * */
        public Calculator(String nums) {
            this.space = nums.split(" ");
            this.indexspace = 0;
        }
        /** Метод calculate
         * Ключевое слово try используется для обработки исключительных операций
         * К переменной first присваивается результат метода DivisionAndMultiplication
         * В методе указываются условия при которых будут выполняться операции сложения и вычитания
         * К переменной second присваивается результат метода DivisionAndMultiplication
         * @return first
         * */
        public double PlusAndMinus() {
            try {
                double first = DivisionAndMultiplication();
                while (indexspace < space.length) {
                    String operator = space[indexspace];
                    if (!operator.equals("+") && !operator.equals("-")) {
                        return 0;
                    } else {
                        indexspace++;
                    }
                    double second = DivisionAndMultiplication();
                    if (operator.equals("+")) {
                        first += second;
                    } else {
                        first -= second;
                    }
                }
                return first;

            } catch (Exception e) {
                System.out.println("Некорректный ввод! incorrect input!");
                System.exit(0);
            }
            return 0;
        }
        /** Метод calculate2
         * К переменной first присваивается результат метода calculate3
         * В методе указываются условия при которых будут выполняться операции умножения и деления
         * К переменной second присваивается результат метода calculate3
         * @return first
         * */
        public double DivisionAndMultiplication() {

            double first = ExpandReamainder();
            while (indexspace < space.length) {
                String operator = space[indexspace];

                if (!operator.equals("*") && !operator.equals("/")) {
                    break;
                } else {
                    indexspace++;
                }

                double second = ExpandReamainder();
                if (operator.equals("*")) {
                    first *= second;
                } else {
                    first /= second;
                }
            }
            return first;
        }
        /** Метод calculate3
         К переменной first присваивается значение первого оператора
         * В методе указываются условия при которых будут выполняться операции выделения остатка и возведения в степень
         К переменной second присваивается значение первого оператора
         * @return first
         * */
        public double ExpandReamainder() {
            double first = Double.parseDouble(space[indexspace++]);
            while (indexspace < space.length) {
                String operator = space[indexspace];
                if (!operator.equals("%") && !operator.equals("^")){
                    break;
                }else {
                    indexspace++;
                }
                double second = Double.parseDouble(space[indexspace++]);
                if (operator.equals("%")) {
                    first %= second;
                } else {
                    first = Math.pow(first,second);
                }
            }
            return first;
        }

    }
}
