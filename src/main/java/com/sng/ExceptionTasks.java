package com.sng;

import java.io.IOException;

public class ExceptionTasks {

    // ====================== Задание 1
    /**
     * Не изменяйте этот метод.
     */
    private static String doRiskyThings(String param) throws IOException {
        if ("do_throw".equals(param)) {
            throw new IOException("cause of " + param);
        }
        return param;
    }

    /**
     * Реализуйте метод вызова рискованной операции, которая бросает проверяемое исключение.
     * Внутри метода run() вашей реализации
     * должен вызываться метод {@link #doRiskyThings(String) doRiskyThings(param)}
     * и ему необходимо передавать riskyParam на входе
     * @param riskyParam параметр, который необходимо передать в {@link #doRiskyThings}
     */
    public static Runnable doSomething(String riskyParam) {
        return () -> {
            try {
                doRiskyThings(riskyParam);
            } catch (IOException e) {
                throwMyException();
            }
        };
    }

    // ====================== Задание 2
    public static class MyException extends RuntimeException {
        // Переопределите default конструктор класса MyException так,
        // чтобы избежать сбора стек трейса в момент генерации исключения
        public MyException() {
            super(null, null, false, false);
        }
    }

    /**
     * Найдите способ создания исключения MyException без сохранения стека.
     * Знаете ли вы что выбрасывание таких исключений по
     * производительности не отличается от условного ветвления?
     */
    public static void throwMyException() {
        throw new MyException();
    }
}
