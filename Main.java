import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main{

    public static int generateSeed(){
        return (int) (Math.random() * 1000);
    }

    public static void main(String[] args){
        File map = new File("./map.md");
        int seed = generateSeed();
        int[] bounds = {100, 100};
        try{
            FileWriter fw = new FileWriter(map);
            fw.write("Game Map:\n```\n");
            fw.write(Generation.generateTerrainMap(seed, bounds));
            fw.write("```\nKey:\n```\nW - Wall\nw - Water\nG - Grass\nT - Tree\nI - Interactable\n```\n\nSeed: " + seed);
            fw.close();
        } catch(IOException e){e.printStackTrace();}
    }
}