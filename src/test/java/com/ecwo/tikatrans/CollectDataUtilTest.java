package com.ecwo.tikatrans;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

/**
 * User: Admin
 * Date: 07.09.16
 * Time: 14:45
 */
public class CollectDataUtilTest {
    String[] args={
            "D:\\dip\\Дипломы архив\\2014\\Спеціалісти\\2014 ЕІ\\2014 Технології ЕМВ\\Пронько І.А\\Демонстрационный материал\\Демонстрационній мат. Пронько.docx",
            "D:\\dip\\Дипломы архив\\2014\\Спеціалісти\\2014 ЕІ\\2014 Технології ЕМВ\\Пронько І.А\\Доклад\\Доклад_Пронько.docx",
            "D:\\dip\\Дипломы архив\\2015\\Магистры 2015 зима\\Економіки і права\\ДУПАРЕ\\ПА\\\\Доклад.docx",
            "D:\\dip\\Дипломы архив\\2015\\Магистры 2015 зима\\Економіки і права\\8.0305401 Економіка підприємства\\ЩЕНДРИГИН.txt",
            "D:\\dip\\Дипломы архив\\2015\\Магистры заочн 2015\\Банк. справа\\ЩЕНДРИГИН.txt",
            "D:\\dip\\2016\\Магистры 2016\\1.КІМБ\\БО\\ДЕК №1\\Аксьонова А.С\\Доклад.docx",
            "D:\\dip\\2016\\Магистры 2016\\1.КІМБ\\Ек анализ\\УФЕБ\\Яровцова Доклад.docx",
            "D:\\dip\\2016\\Магистры 2016\\6.МЕВ\\МЕтаМЗЕД\\МенЗЕД\\КалининаААДоклад.docx",
            "D:\\dip\\2016\\Магистры 2016\\6.МЕВ\\Політекономія\\Голубцова Яна Костянтинівна\\Доклад.docx",
    };
    @Test @Ignore
    public void testValidateSDW() throws Exception {

    }

    @Test
    public void testParseFile() throws Exception {
        final File file = new File("D:/dip/Дипломы архив/2014/Спеціалісти/2014 ЕІ/2014 Технології ЕМВ/Пронько І.А/Демонстрационный материал/Демонстрационній мат. Пронько.docx");
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.isFile());
        CollectDataUtil.parseFile(file);
    }

    @Test
    public void testExtractYear() throws Exception {

      Integer y= CollectDataUtil.extractYear(args[0]);
        Assert.assertNotNull(y);
        Assert.assertTrue(y==2014);
        y= CollectDataUtil.extractYear(args[1]);
        Assert.assertNotNull(y);
        Assert.assertTrue(y == 2014);
    }
}
