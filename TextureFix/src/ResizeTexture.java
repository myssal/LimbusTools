import QoL.FileExtension;
import QoL.ImageResize;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ResizeTexture {

    /*class to resize wrong res textures caused by Studio export
    //idea: get main name (string on the left of 2nd "_"
    //read through atlas file, get the correct resolution (width height format)
    //use bufferedImage to resize corresponding texture
    */
    String sourceName;
    File sourcePath;
    ArrayList<File> atlasList = new ArrayList<File>(); //atlas file list
    ArrayList<String> spineName = new ArrayList<String>();
    //get list of atlas files
    String[] sinnerAbrrev = {"ys", "yisang", "fau", "don", "ryoshu", "meur", "maur", "honglu", "honhlu",
                             "heath", "ism", "ishmael", "rodya", "sinc", "outis", "ot", "tantis", "greg"};
    String[] sinnerName = {"YiSang", "YiSang", "Faust", "DonQuixote", "Ryoshu", "Mersault", "Mersault", "HongLu", "HongLu",
                           "Heathcliff", "Ishmael", "Ishmael", "Rodion", "Sinclair", "Outis", "Outis", "Outis", "Gregor"};
    public ResizeTexture(String sourceName){
        this.sourceName = sourceName;
        this.sourcePath = new File(sourceName);
    }
    public void getAtlasList(){
        File[] tempList = sourcePath.listFiles();
        int counter = 0;
        for (File check: tempList) {
            if (FileExtension.checkInclude(check.getName(), "atlas")){
                atlasList.add(check);
                spineName.add(FileExtension.getMainNameLimbus(check.getName()));
                counter++;
            }
        }
    }

    public void reziseTexture(){
        String textureName, sizeString;
        int width, height; //get width and height
        for (File file: atlasList){

            try {
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNext()){
                    textureName = fileReader.next();
                    if (textureName.contains(".png")){
                        sizeString = fileReader.next();
                        System.out.println("Resize " + textureName);
                        width = Integer.parseInt(sizeString.substring(sizeString.indexOf(":") + 1,sizeString.indexOf(",")));
                        height = Integer.parseInt(sizeString.substring(sizeString.indexOf(",") + 1));
                        System.out.println("Resized width:" + width + ", height:" + height);

                        //resize texture
                        File resizeTexture = new File(sourcePath + "\\" + textureName);
                        ImageResize.resizeImage(resizeTexture, width, height, "png");

                    }
                }
                fileReader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void moveSpine(){
        for (File file: sourcePath.listFiles()){
            String fileName = file.getName();
            for (int i = 0; i < sinnerAbrrev.length; i++){
                if (fileName.contains(sinnerAbrrev[i])){

                    try {
                        //move to corresponding folders
                        System.out.println("Move " + file.getName());
                        String subFolder = sourcePath + "\\" + sinnerName[i];
                        if (Files.notExists(Path.of(subFolder))) Files.createDirectory(Path.of(subFolder));
                        Files.move(file.toPath(), new File(subFolder + "\\" + fileName).toPath(), REPLACE_EXISTING);
                        break;
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ResizeTexture test = new ResizeTexture(args[0]);
        test.getAtlasList();
        test.reziseTexture();
        test.moveSpine();


    }
}
