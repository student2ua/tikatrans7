package com.ecwo.tikatrans;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Admin on 12.08.2016.
 */
public class TransferFolderToTXT {
    public static final String[] EXTENSIONS = new String[]{"doc", "docx", "odt", "pdf"};
    private static final Logger log = Logger.getLogger(TransferFolderToTXT.class);
    private static File dirIN;

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView());
        chooser.setDialogTitle("Select dir");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        int i = chooser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            if (selectedFile.isDirectory()) {
                dirIN = selectedFile;
            } else JOptionPane.showMessageDialog(null, "Cancelled");
            log.debug("select dir:" + dirIN);
            log.debug("EXTENSIONS: " + Arrays.toString(EXTENSIONS));
            goTrans(dirIN, dirIN);
        }
    }

    private static void goTrans(File dirIN, File dirOut) {
 /*       dirIN.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.isDirectory()||
                        pathname.getName().endsWith(".doc")||
                        pathname.getName().endsWith(".docx")||
                        pathname.getName().endsWith(".odt")||
                        pathname.getName().endsWith(".pdf");
            }
        });*/
        Iterator<File> iterateFiles = FileUtils.iterateFiles(dirIN, EXTENSIONS, true);
        while (iterateFiles.hasNext()) {
            File file = iterateFiles.next();
            log.info("file:" + file.getAbsolutePath());
            String fileName = file.getName();
            try {
                final String noExtFileName = fileName.substring(0, fileName.lastIndexOf("."));
                File f = new File(file.getParent(), noExtFileName + ".txt");
                if(f.exists()&&f.length()>100) continue;
             /*   if (f.exists()) {
                    f = new File(file.getParent(), noExtFileName + "2.txt");
                }*/
                final String data = parseToPlainText(file.getAbsolutePath());
                log.debug("data.length() = " + data.length());
//                FileUtils.writeStringToFile(f, data, "UTF-8");
                writeString2File(data, f);
            } catch (IOException e) {
                log.error(e.toString(), e);
            } catch (SAXException e) {
                log.error(e.toString(), e);
            } catch (TikaException e) {
                log.warn(e.toString(), e);
            } catch (java.lang.NoClassDefFoundError e) {
                log.warn(e.toString(), e);
            }

        }
    }

    private static String parseToPlainText(String sFile) throws IOException, SAXException, TikaException {
        BodyContentHandler handler = new BodyContentHandler(-1);

        final AutoDetectParser parser = new AutoDetectParser();
        final Metadata metadata = new Metadata();
        InputStream stream = null;
        try {
            stream = new BufferedInputStream(new FileInputStream(sFile));
            parser.parse(stream, handler, metadata);
            return handler.toString();
        } finally {
            if (stream != null) stream.close();
        }
    }

    private static void writeString2File(String s, File file) throws IOException {
        Writer writer = new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8"));
        writer.write(s);
        writer.flush();
        writer.close();
    }

}
