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
        float[][] map = new float[bounds[0]][bounds[1]];
        float scale = 0.2f;
        float amp = 0.6f;
        float freq = 0.3f;
        
        for(int  x = 0; x < map.length; x++){
            for(int y = 0; y < map[x].length; y++){
                
                float xPos = (float) (x * scale);
                float yPos = (float) (y * scale);

                float normalize = 0.0f;
                map[x][y] += amp * png.evaluateNoise(xPos * freq + seed, yPos * freq + seed);
                normalize += amp;

                map[x][y] /= normalize;
            }
        }

        char[][] finalMapAsChar = new char[bounds[0]][bounds[1]];

        for(int x = 0; x < map.length; x++){
            for(int y = 0; y < map[x].length; y++){
                if(map[x][y] < 0.1f){
                    finalMapAsChar[x][y] = '~';
                } else if(map[x][y] < 0.5f && map[x][y] > 0.1f){
                    finalMapAsChar[x][y] = 'G';
                } else if(map[x][y] <= 1.0f){
                    finalMapAsChar[x][y] = 'W';
                } 
            }
        }

        WhiteNoiseGenerator wng = WhiteNoiseGenerator.newBuilder().setSeed(seed).build();

        float[][] secondProcessMap = new float[bounds[0]][bounds[1]];

        for(int x = 0; x < secondProcessMap.length; x++){
            for(int y = 0; y < secondProcessMap[x].length; y++){
                float xPos = (float) (x * scale);
                float yPos = (float) (y * scale);

                float normalize = 0.0f;
                secondProcessMap[x][y] += amp * wng.evaluateNoise(xPos * freq + seed, yPos * freq + seed);
                normalize += amp;

                secondProcessMap[x][y] /= normalize;

                if(secondProcessMap[x][y] <= 0.5f && secondProcessMap[x][y] >= 0.3f && finalMapAsChar[x][y] == 'G' && notSurrounded(finalMapAsChar, x, y)){
                    finalMapAsChar[x][y] = 'T';
                }
            }
        }

        return generateStructures(finalMapAsChar);
    }

    private static String generateStructures(char[][] terrainMap){
        Structures[] structures = Structures.getStructures();
        for(int x = 0; x < terrainMap.length; x++){
            for(int y = 0; y < terrainMap[x].length; y++){
                for(Structures s : structures){
                    if(terrainMap[x][y] == 'G'){
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
                }
            }
        }

        return processCharMap(terrainMap);
    }

    private static char[][] processCity(char[][] map, int x, int y){
        char[][] fullCityStrucutre = Structures.getFullCity();
        if(x + fullCityStrucutre.length < map.length && y + fullCityStrucutre[0].length < map[x].length){
            boolean canPlace = true;
            for(int i = 0; i < fullCityStrucutre.length; i++){
                for(int j = 0; j < fullCityStrucutre[i].length; j++){
                    if(map[x + i][y + j] != 'G'){
                        canPlace = false;
                    }
                }
            }
            if(canPlace){
                for(int i = 0; i < fullCityStrucutre.length; i++){
                    for(int j = 0; j < fullCityStrucutre[i].length; j++){
                        map[x + i][y + j] = fullCityStrucutre[i][j];
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
            top = map[x][y - 1] == 'G';
            topL = map[x - 1][y - 1] == 'G';
            topR = map[x + 1][y - 1] == 'G';
            bottom = map[x][y + 1] == 'G';
            bottomL = map[x - 1][y + 1] == 'G';
            bottomR = map[x + 1][y + 1] == 'G';
            left = map[x - 1][y] == 'G';
            right = map[x + 1][y] == 'G';
        } catch(ArrayIndexOutOfBoundsException e){
            return true;
        }

        if((top && bottom && left && right) || (topL && topR && bottomL && bottomR)){
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
                        color = Color.GREEN;
                        break;
                    case 'W':
                        color = Color.GRAY;
                        break;
                    case 'T':
                        color = new Color(165, 42, 42);
                        break;
                    case '~':
                        color = Color.BLUE;
                        break;
                    case 'I':
                        color = Color.RED;
                        break;
                    default:
                        color = Color.BLACK;
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
