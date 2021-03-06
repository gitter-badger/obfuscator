package me.superblaubeere27.jobf.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringJoiner;

import static org.objectweb.asm.Opcodes.*;

public class Util {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String chooseDirectory(final File currFolder, final Component parent) {
        final JFileChooser chooser = new JFileChooser(currFolder);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile().getAbsolutePath();
        return null;
    }

    public static String chooseFile(final File currFolder, final Component parent) {
        final JFileChooser chooser = new JFileChooser(currFolder);
        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile().getAbsolutePath();
        return null;
    }

    public static String chooseFile(final File currFolder, final Component parent, FileFilter filter) {
        final JFileChooser chooser = new JFileChooser(currFolder);
        chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile().getAbsolutePath();
        return null;
    }

    public static String chooseFileToSave(final File currFolder, final Component parent, FileFilter filter) {
        final JFileChooser chooser = new JFileChooser(currFolder);
        chooser.setFileFilter(filter);
        if (chooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile().getAbsolutePath();
        return null;
    }

    public static long copy(final InputStream from, final OutputStream to) throws IOException {
        byte[] buf = new byte[1024];
        long total = 0L;
        while (true) {
            int r = from.read(buf);
            if (r == -1) {
                break;
            }
            to.write(buf, 0, r);
            total += r;
        }
        return total;
    }

    public static String prettyGson(final JsonObject newObj) {
        return gson.toJson(newObj);
    }

    public static String modifierToString(int mod) {
        StringJoiner sj = new StringJoiner(" ");

        if ((mod & ACC_BRIDGE) != 0) sj.add("[bridge]");
        if ((mod & ACC_SYNTHETIC) != 0) sj.add("[syntetic]");

        if ((mod & ACC_PUBLIC) != 0) sj.add("public");
        if ((mod & ACC_PROTECTED) != 0) sj.add("protected");
        if ((mod & ACC_PRIVATE) != 0) sj.add("private");

        /* Canonical order */
        if ((mod & ACC_ABSTRACT) != 0) sj.add("abstract");
        if ((mod & ACC_STATIC) != 0) sj.add("static");
        if ((mod & ACC_FINAL) != 0) sj.add("final");
        if ((mod & ACC_TRANSIENT) != 0) sj.add("transient");
        if ((mod & ACC_VOLATILE) != 0) sj.add("volatile");
        if ((mod & ACC_SYNCHRONIZED) != 0) sj.add("synchronized");
        if ((mod & ACC_NATIVE) != 0) sj.add("native");
        if ((mod & ACC_STRICT) != 0) sj.add("strictfp");
        if ((mod & ACC_INTERFACE) != 0) sj.add("interface");

        return sj.toString();
    }

    public static String toIl(int i) {
        return Integer.toBinaryString(i).replace('0', 'I').replace('1', 'l');
    }

    public static String replaceMainClass(String s, String main) {
        StringBuilder sb = new StringBuilder();

        for (String s1 : s.split("\n")) {
            System.out.println(s1);
            if (s1.startsWith("Main-Class")) {
                sb.append("Main-Class: ").append(main);
            } else {
                sb.append(s1).append("\n");
            }
        }

        return sb.toString();
    }
}
