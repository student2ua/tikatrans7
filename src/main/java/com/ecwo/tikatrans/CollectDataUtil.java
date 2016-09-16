package com.ecwo.tikatrans;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * User: Администратор
 * Date: 06.09.16
 * Time: 20:22
 */
public class CollectDataUtil {
    static final List<Integer> years = Arrays.asList(2014, 2015, 2016);

    public static String validateSDW(StudentDiplomaWorkItem item) {
        StringBuffer err = new StringBuffer();
        if (!years.contains(item.year)) err.append("wrong year " + item.year);

        return err.toString();
    }

    /**
     * D:\dip\Дипломы архив\2014\Спеціалісти\2014 ЕІ\2014 Технології ЕМВ\Пронько І.А\Демонстрационный материал\Демонстрационній мат. Пронько.docx
     * D:\dip\Дипломы архив\2014\Спеціалісти\2014 ЕІ\2014 Технології ЕМВ\Пронько І.А\Доклад\Доклад_Пронько.docx
     */
    public static StudentDiplomaWorkItem parseFile(File file) throws IOException {

        StudentDiplomaWorkItem rez = new StudentDiplomaWorkItem();
        if (file != null) {
            String toParse = file.getCanonicalPath();
            System.out.println("toParse = " + toParse);

            rez.year = extractYear(toParse);
        }
        return rez;
    }

    public static Integer extractYear(String toParse) {
        String[] str = toParse.split("\\\\"); //^(.+)/([^/]+)$
        int rez = 0;
        for (String s : str) {
            if (s.matches("-?\\d+"))
                try {
                    rez = Integer.parseInt(s);
                } catch (NumberFormatException ignored) {
                }
        }

        return rez;
    }

    protected static class StudentDiplomaWorkItem {

        Integer year;
        String fio;
        String faculty, speciality;

    }
}
