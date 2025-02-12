/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTTT.dal;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import UTTT.bll.bot.IBot;
/**
 *
 * @author PC
 */
public class DynamicBotClassHandler {
    private static String getFilenameNoExtension(Path path) {
        String fileName = path.getFileName().toFile().getName();
        if (fileName.indexOf(".") > 0) {
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        }
        return fileName;
    }
    
    public static void writeBotsToTextFile() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException {
        File file = new File("bots.txt");
        List<String> bots = new ArrayList<>();

        Path dir = FileSystems.getDefault().getPath("./src/UTTT/bll/bot");
        try (DirectoryStream<Path> stream = 
                Files.newDirectoryStream(dir, "*.java")) {
            for (Path path : stream){
                String className = getFilenameNoExtension(path);
                String classPathAndName = "UTTT.bll.bot." + className;
                URL[] urls = {path.toFile().toURI().toURL()};
                ClassLoader cl = new URLClassLoader(urls);
                Class clazz = cl.loadClass(classPathAndName);
                if (!clazz.isInterface()) {
                    IBot bot = (IBot) clazz.newInstance();
                    bots.add(bot.getBotName());
                }
            }
        }
        
        Files.write(
            Paths.get(file.toURI()), bots, 
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
    }
    
    public static ObservableList<IBot> loadBotList() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        ObservableList<IBot> bots = FXCollections.observableArrayList();

        Path dir = FileSystems.getDefault().getPath("./src/UTTT/bll/bot");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.java")) {
            for (Path path : stream) {
                String classPathAndName = "UTTT.bll.bot." + getFilenameNoExtension(path);
                URL[] urls = {path.toFile().toURI().toURL()};
                ClassLoader cl = new URLClassLoader(urls);
                Class clazz = cl.loadClass(classPathAndName);
                if (!clazz.isInterface()) {
                    IBot bot = (IBot) clazz.newInstance();
                    bots.add(bot);
                }
            }
        }
        return bots;
    }
}