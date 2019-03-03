import exception.MyArrayDataException;
import exception.MyArraySizeException;

public class Runner {
    private static byte sum(byte... a) {
        byte sum = 0;
        if (a.length == 0) {
            return sum;
        }
        for (byte i1 : a) {
            sum += i1;
        }
        return sum;
    }

    private static short sum(short... a) {
        short sum = 0;
        if (a.length == 0) {
            return sum;
        }
        for (short i1 : a) {
            sum += i1;
        }
        return sum;
    }
    /**
     * Метод вычисляет сумму целых чисел переданных в качестве аргументов
     * @param a
     * @return возвращает сумму чисел
     */
    private static int sum(int... a) {
        int sum = 0;
        if (a.length == 0) {
            return sum;
        }
        for (int i1 : a) {
            sum += i1;
        }
        return sum;
    }

    private static long sum(long... a) {
        long sum = 0;
        if (a.length == 0) {
            return sum;
        }
        for (long i1 : a) {
            sum += i1;
        }
        return sum;
    }

    private static float sum(float... a) {
        float sum = 0;
        if (a.length == 0) {
            return sum;
        }
        for (float i1 : a) {
            sum += i1;
        }
        return sum;
    }

    private static double sum(double... a) {
        double sum = 0;
        if (a.length == 0) {
            return sum;
        }
        for (double i1 : a) {
            sum += i1;
        }
        return sum;
    }

    /**
     * Метод вычисляет сумму n-мерного массива
     * @throws MyArrayDataException - возникает, если массив хранит значения не соответствующие типам Integer и int[]
     * (не определяет точный элемент вызвавший исклбчение, так как не используеться рекурсия)
     * @param a
     * @return  возвращает сумму елементов массива
     */
    private static int deepSum(Object[] a) {
        int sum = 0;
        if (isNull(a) || isEmpty(a)) {
            return sum;
        }
        for (Object e : a) {
            if (!isNull(e)) {
                if (isObjects(e)) {
                    sum += deepSum((Object[]) e);
                } else if (isString(e)) {
                    String strE = (String) e;
                    try {
                        sum += Integer.valueOf(strE);
                    } catch (NumberFormatException ex) {
                        throw new MyArrayDataException("Element is String type, but it isn't Integer");
                    }
                } else if (isObjectInteger(e)) {
                    sum += (int) e;
                } else if (isPrimitiveInts(e)) {
                    sum += sum((double[]) e);
                } else {
                    throw new MyArrayDataException("Element is " + e.getClass().getSimpleName() +
                            " type, but need Integer or int[] type");
                }
            }
        }
        return sum;
    }

    /**
     * <strong>Улучшенный метод deepSum()</strong>
     * Метод вычисляет сумму n-мерного массива
     * @throws MyArrayDataException - возникает, если массив хранит не числовое значение или если элемент массива я
     * вляеться строкой, но строка не содержит числа (не определяет точный элемент вызвавший исклбчение,
     * так как не используеться рекурсия)
     * @param a
     * @return сумму элементов массива
     */
    private static double deepSum2(Object[] a) {
        double sum = 0;
        if (isNull(a) || isEmpty(a)) {
            return sum;
        }
        for (Object e : a) {
            if (!isNull(e)) {
                if (isObjects(e)) {
                    sum += deepSum2((Object[]) e);
                } else if (isString(e)) {
                    String strE = (String) e;
                    try {
                        sum += Byte.valueOf(strE);
                    } catch (NumberFormatException e1) {
                        try {
                            sum += Short.valueOf(strE);
                        } catch (NumberFormatException e2) {
                            try {
                                sum += Integer.valueOf(strE);
                            } catch (NumberFormatException e3) {
                                try {
                                    sum += Long.valueOf(strE);
                                } catch (NumberFormatException e4) {
                                    try {
                                        sum += Float.valueOf(strE);
                                    } catch (NumberFormatException e5) {
                                        try {
                                            sum += Double.valueOf(strE);
                                        } catch (NumberFormatException e6) {
                                            throw new MyArrayDataException("Element is String type, but it isn't number");
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (isObjectByte(e)) {
                    sum += (Byte) e;
                } else if (isObjectShort(e)) {
                    sum += (Short) e;
                } else if (isObjectInteger(e)) {
                    sum += (Integer) e;
                } else if (isObjectLong(e)) {
                    sum += (Long) e;
                } else if (isObjectFloat(e)) {
                    sum += (Float) e;
                } else if (isObjectDouble(e)) {
                    sum += (Double) e;
                } else if (isPrimitiveBytes(e)) {
                    sum += sum((byte[]) e);
                } else if (isPrimitiveShorts(e)) {
                    sum += sum((short[]) e);
                } else if (isPrimitiveInts(e)) {
                    sum += sum((int[]) e);
                } else if (isPrimitiveLongs(e)) {
                    sum += sum((long[]) e);
                } else if (isPrimitiveFloats(e)) {
                    sum += sum((float[]) e);
                } else if (isPrimitiveDoubles(e)) {
                    sum += sum((double[]) e);
                } else {
                    throw new MyArrayDataException("Element is " + e.getClass().getSimpleName() +
                            " type, but need number type");
                }
            }
        }
        return sum;
    }

    /**
     * Метод вычисляет сумму квадратного 2-мерного массива размером 4
     * @throws MyArrayDataException - возникает, если массив хранит значения не соответствующие типам Integer и int[]
     * (не определяет точный элемент вызвавший исклбчение, так как не используеться рекурсия)
     * @throws MyArraySizeException - возникает, если массив не являеться квадратным 2-мерным массиво размера 4
     * @param a
     * @return  возвращает сумму елементов массива
     */
    private static int sum4x4(Object[] a) {
        int sum = 0;
        if (isNull(a) || isEmpty(a)) {
            return sum;
        }
        if (countDeepLevel(a) == 2 && is4x4((Object[][]) a)) {
            sum = deepSum(a);
        } else {
            throw new MyArraySizeException("The array isn't square two-dimensional with a size of 4");
        }
        return sum;
    }

    /**
     * Определяет являеться ли 2-мерный массив квадратным размером 4
     * @param a
     * @return  true если массив являеться квадратным 2-мерным массивом размера 4, иначе false
     */
    private static boolean is4x4(Object[][] a) {
        if (a.length != 4)
            return false;
        for (int i = 0; i < a.length; i++)
            if (a[i].length != 4) return false;
        return true;
    }

    /**
     * Метод определенет глубину массива (способен вычислять глубину массивов примитивного типв n - 1, где n настоящая глубина массива,
     * но int[] вычисляет коректно)
     * @param a
     * @return Возвращает глубину массива
     */
    private static int countDeepLevel(Object a) {
        int count = 0;
        if (isNull(a)) {
            return count;
        }
        Object e;
        if (isObjects(a)) {
            count = 1;
            e = ((Object[]) a)[0];
            count += countDeepLevel(e);
        } else if (isPrimitiveInts(a)) {
            count += 1;
        }
        return count;
    }

    private static boolean isPrimitiveBytes(Object a) {
        return a instanceof byte[];
    }

    private static boolean isPrimitiveShorts(Object a) {
        return a instanceof short[];
    }

    private static boolean isPrimitiveInts(Object a) {
        return a instanceof int[];
    }

    private static boolean isPrimitiveLongs(Object a) {
        return a instanceof long[];
    }

    private static boolean isPrimitiveFloats(Object a) {
        return a instanceof float[];
    }

    private static boolean isPrimitiveDoubles(Object a) {
        return a instanceof double[];
    }

    private static boolean isObjects(Object a) {
        return a instanceof Object[];
    }

    private static boolean isString(Object a) {
        return a instanceof String;
    }

    private static boolean isObjectByte(Object a) {
        return a instanceof Byte;
    }

    private static boolean isObjectShort(Object a) {
        return a instanceof Short;
    }

    private static boolean isObjectInteger(Object a) {
        return a instanceof Integer;
    }

    private static boolean isObjectLong(Object a) {
        return a instanceof Long;
    }

    private static boolean isObjectFloat(Object a) {
        return a instanceof Float;
    }

    private static boolean isObjectDouble(Object a) {
        return a instanceof Double;
    }

    private static boolean isEmpty(Object[] a) {
        return a.length == 0;
    }

    private static boolean isNull(Object a) {
        return a == null;
    }

    public static void main(String[] args) {
        int size = 4;
/*
        Object[][] array = new Object[size][size];
        array[1][2] = 1;
        array[2][1] = "10";
        //array[2][2] = "";     //Вызовет исключение
        //array[2][3] = new Object();   //Вызовет исключение
*/
/*
        Object[][][] array = new Object[size][size][size];
        array[1][2][3] = 1;
        //array[1][1][1] = 4.5f;
        //array[1][1][2] = 4.5d;
        array[2][2][2] = "10";
        array[2][2][3] = "";    //Вызовет исключение
*/
/*
        int[][] array = null;
*/
/*
        int[][] array = new int[size][size];
        array[3][2] = 6;
*/
/*
        int[][] array = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
*/
/*
        byte[][] array = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
*/
/*
        double[][] array = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
*/
/*
        try {
            //System.out.println(deepSum(array));
            System.out.println(sum4x4(array));
            //System.out.println(deepSum2(array));
        } catch (MyArrayDataException | MyArraySizeException e) {
            System.err.println("Вычисление суммы было прерванно, так как массив не соответствует требованиям");
            e.printStackTrace();
        }
*/
    }
}
