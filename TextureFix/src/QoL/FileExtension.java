package QoL;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileExtension {

    public static String getFileNameOnly(String filePath)
    {
        //return name of file without extension
        int lastIndexOfDot = filePath.indexOf('.');
        return filePath.substring(0, lastIndexOfDot);
    }


    //for .atlas file check
    public static boolean checkInclude (String fileName, String include){
        if (fileName.contains(include)) return true;
        else return false;
    }


    public void readContent(File filePath){
        try {
            Scanner sc = new Scanner(filePath);
            while (sc.hasNext()){
                System.out.println(sc.next());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String delete2(String filePath){
        //some spine include texture with this format Ism.png Ism2.png.
        //only need one to create subfolder so delete the unneccessary 2 letter
        if (filePath.charAt(filePath.length() - 1) == '2')
            filePath = filePath.substring(0, filePath.length() - 1);
        return filePath;
    }
    public static String getMainNameLimbus(String filePath){
        //find index of second occurence of "_", if not exists (-1) return the whole file name
        //else return substring from start to the second "_"
        if (filePath.indexOf("_", filePath.indexOf("_") + 1) == -1) return delete2(getFileNameOnly(filePath));
        else return delete2(filePath.substring(0, filePath.indexOf("_", filePath.indexOf("_") + 1)));
    }

    public static String changePath(String args){
        return args.replace('/', '\\');
    }
}
