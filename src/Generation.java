package src;
import de.articdive.jnoise.core.api.functions.Interpolation;
import de.articdive.jnoise.generators.noisegen.perlin.PerlinNoiseGenerator;
import de.articdive.jnoise.generators.noisegen.random.white.WhiteNoiseGenerator;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Generation {
    public static String generateTerrainMap(int seed, int[] bounds){
        PerlinNoiseGenerator png = PerlinNoiseGenerator.newBuilder().setSeed(seed).setInterpolation(Interpolation.COSINE).build();
        PerlinNoiseGenerator png1 = PerlinNoiseGenerator.newBuilder().setSeed(Main.generateSeed()).setInterpolation(Interpolation.COSINE).build();
        PerlinNoiseGenerator[] pngs = {png, png1};
        float[][] map = new float[bounds[0]][bounds[1]];
        float islandSize = 0.2f;
        float amp = 0.7f;
        float freq = 0.2f;
        float groundThreshold = 0.2f;
        float waterThreshold = 0.4f;
        float deepWaterThreshold = 1.0f;


        for(int  x = 0; x < map.length; x++){
            for(int y = 0; y < map[x].length; y++){
                
                float xPos = (float) (x * islandSize);
                float yPos = (float) (y * islandSize);

                float normalize = 0.0f;
                for(PerlinNoiseGenerator pn : pngs){                    
                    map[x][y] += amp * pn.evaluateNoise(xPos * freq + seed, yPos * freq + seed);
                    normalize += amp;
                }

                map[x][y] /= normalize;
            }
        }

        char[][] finalMapAsChar = new char[bounds[0]][bounds[1]];

        for(int x = 0; x < map.length; x++){
            for(int y = 0; y < map[x].length; y++){
                if(map[x][y] <= groundThreshold){
                    finalMapAsChar[x][y] = 'G';
                } else if(map[x][y] < waterThreshold && map[x][y] > groundThreshold){
                    finalMapAsChar[x][y] = '~';
                } else if(map[x][y] <= deepWaterThreshold){
                    finalMapAsChar[x][y] = 'R';
                } 
            }
        }

        WhiteNoiseGenerator wng = WhiteNoiseGenerator.newBuilder().setSeed(seed).build();
        WhiteNoiseGenerator wng2 = WhiteNoiseGenerator.newBuilder().setSeed(Main.generateSeed()).build();

        WhiteNoiseGenerator[] wngs = {wng, wng2};

        float[][] secondProcessMap = new float[bounds[0]][bounds[1]];

        for(int x = 0; x < secondProcessMap.length; x++){
            for(int y = 0; y < secondProcessMap[x].length; y++){
                float xPos = (float) (x * islandSize);
                float yPos = (float) (y * islandSize);

                float normalize = 0.0f;

                for(WhiteNoiseGenerator w : wngs){
                    secondProcessMap[x][y] += amp * w.evaluateNoise(xPos * freq + seed, yPos * freq + seed);
                    normalize += amp;
                }

                secondProcessMap[x][y] += amp * png.evaluateNoise(xPos * freq + seed, yPos * freq + seed);
                normalize += amp;

                secondProcessMap[x][y] /= normalize;

                float treeUpperThreshold = 0.35f;
                float treeLowerThreshold = 0.2f;
                if(secondProcessMap[x][y] <= treeUpperThreshold && secondProcessMap[x][y] >= treeLowerThreshold && finalMapAsChar[x][y] == 'G' && notSurrounded(finalMapAsChar, x, y)){
                    finalMapAsChar[x][y] = 'T';
                } else if(finalMapAsChar[x][y] == 'G' && secondProcessMap[x][y] > treeUpperThreshold){
                    finalMapAsChar[x][y] = 'g';
                }
            }
        }

        return generateStructures(finalMapAsChar);
    }

    private static String generateStructures(char[][] terrainMap){
        Structures[] structures = Structures.getStructures();
        for(int x = 0; x < terrainMap.length; x++){
            for(int y = 0; y < terrainMap[x].length; y++){
                Structures s = structures[(int) (Math.random() * structures.length)];
                processStructures(terrainMap, x, y, s);
            }
        }

        return processCharMap(terrainMap);
    }

    private static char[][] processStructures(char[][] terrainMap, int x, int y, Structures s){
        if((terrainMap[x][y] == 'G' || terrainMap[x][y] == 'g') && Math.random() >= 0.8){
            if(s.getName().equals("city")){
                terrainMap = processCity(terrainMap, x, y);
            } else if(x + s.getStructure().length < terrainMap.length && y + s.getStructure()[0].length < terrainMap[x].length){
                boolean canPlace = true;
                for(int i = 0; i < s.getStructure().length; i++){
                    for(int j = 0; j < s.getStructure()[i].length; j++){
                        if(terrainMap[x + i][y + j] != 'G'){
                            canPlace = false;
                        }
                    }
                }
                if(canPlace){
                    for(int i = 0; i < s.getStructure().length; i++){
                        for(int j = 0; j < s.getStructure()[i].length; j++){
                            terrainMap[x + i][y + j] = s.getStructure()[i][j];
                        }
                    }
                }
            }
        }
        return terrainMap;
    }

    private static char[][] processCity(char[][] map, int x, int y){
        char[][] fullCityStructure = Structures.getFullCity();
        if(x + fullCityStructure.length < map.length && y + fullCityStructure[0].length < map[x].length){
            boolean canPlace = true;
            for(int i = 0; i < fullCityStructure.length; i++){
                for(int j = 0; j < fullCityStructure[i].length; j++){
                    if(map[x + i][y + j] != 'G' || map[x + i][y + j] != 'g'){
                        canPlace = false;
                    }
                }
            }
            if(canPlace){
                System.out.println("Placing city at (" + x + ", " + y + ")");
                for(int i = 0; i < fullCityStructure.length; i++){
                    for(int j = 0; j < fullCityStructure[i].length; j++){
                        map[x + i][y + j] = fullCityStructure[i][j];
                    }
                }
            }
        }
        return map;
    }

    private static String processCharMap(char[][] map){
        String finalMap = "";
        for(int x = 0; x < map.length; x++){
            for(int y = 0; y < map[x].length; y++){
                finalMap += map[x][y];
            }
            finalMap += "\n";
        }
        buildMapImage(map);
        return finalMap;
    }

    private static boolean notSurrounded(char[][] map, int x, int y){
        boolean top = false;
        boolean topL = false;
        boolean topR = false;
        boolean bottom = false;
        boolean bottomL = false;
        boolean bottomR = false;
        boolean left = false;
        boolean right = false;

        try{
            top = map[x][y - 1] == 'G' || map[x][y - 1] == '~';
            topL = map[x - 1][y - 1] == 'G' || map[x][y - 1] == '~';
            topR = map[x + 1][y - 1] == 'G' || map[x][y - 1] == '~';
            bottom = map[x][y + 1] == 'G' || map[x][y - 1] == '~';
            bottomL = map[x - 1][y + 1] == 'G' || map[x][y - 1] == '~';
            bottomR = map[x + 1][y + 1] == 'G' || map[x][y - 1] == '~';
            left = map[x - 1][y] == 'G' || map[x][y - 1] == '~';
            right = map[x + 1][y] == 'G' || map[x][y - 1] == '~';
        } catch(ArrayIndexOutOfBoundsException e){
            return true;
        }

        boolean[] bools = {top, topL, topR, bottom, bottomL, bottomR, left, right};
        int count = 0;

        for(boolean b : bools){
            if(b && (Math.random() > 0.5)){
                count++;
            }
        }

        if(count >= 5){
            return true;
        }
        return false;
    }

    private static void buildMapImage(char[][] map){
        int width = map.length;
        int height = map[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color;
                switch (map[x][y]) {
                    case 'G':
                        Color[] greens = {Color.GREEN, new Color(0, 200, 0), Color.GREEN, Color.GREEN, new Color(0, 220, 0)};
                        color = greens[(int) (Math.random() * greens.length)];
                        break;
                    case 'g':
                        Color[] greens2 = {Color.GREEN, Color.GREEN, new Color(0, 200, 0), new Color(0, 220, 0)};
                        color = greens2[(int) (Math.random() * greens2.length)];
                        break;
                    case 'W':
                        color = Color.DARK_GRAY;
                        break;
                    case 'F':
                        color = new Color(139, 69, 19);
                        break;
                    case 'T':
                        Color[] browns = {new Color(165, 42, 42), new Color(160, 82, 45)};
                        color = browns[(int) (Math.random() * browns.length)];
                        break;
                    case '~':
                        Color[] blues = {Color.BLUE, new Color(0, 0, 225), Color.BLUE, Color.BLUE};
                        color = blues[(int) (Math.random() * blues.length)];
                        break;
                    case 'R':
                        color = new Color(0, 0, 200); // deep water color
                        break;
                    case 'I':
                        color = Color.RED;
                        break;
                    default:
                        color = new Color(85, 21, 21);
                        break;
                }
                image.setRGB(x, y, color.getRGB());
            }
        }

        try {
            File outputfile = new File("map.png");
            ImageIO.write(image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
