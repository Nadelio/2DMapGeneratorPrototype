package src;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main{

    public static String mapName;

    public static int generateSeed(){
        return (int) (Math.random() * 1000);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the name of the map: ");
        mapName = sc.nextLine();
        File map = new File("./map_"+ mapName +".md");
        int seed = generateSeed();
        int[] bounds = {500, 500};
        try{
            FileWriter fw = new FileWriter(map);
            fw.write("Game Map:\n```\n");
            fw.write(Generation.generateTerrainMap(seed, bounds));
            fw.write("```\nKey:\n```\nW - Wall\nw - Water\nR - Deep Water\nG - Grass\nT - Tree\nI - Interactable\n```\n\nSeed: " + seed);
            fw.close();
        } catch(IOException e){e.printStackTrace();}
    }
}