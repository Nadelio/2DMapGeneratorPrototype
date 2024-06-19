package src;
public class Structures {
    private char[][] structure;
    private String name;

    private static final Structures smallHouse = new Structures(new char[][]{
        {'W', 'W', 'W', 'W', 'W'},
        {'W', 'I', 'G', 'G', 'W'},
        {'W', 'G', 'G', 'G', 'D', 'G'},
        {'W', 'I', 'G', 'G', 'W'},
        {'W', 'W', 'D', 'W', 'W'}
    }, "smallHouse");

    private static final Structures mediumHouse = new Structures(new char[][]{
        {'W', 'W', 'W', 'W', 'W', 'W'},
        {'W', 'I', 'G', 'G', 'G', 'W'},
        {'W', 'W', 'W', 'W', 'G', 'D', 'G'},
        {'W', 'G', 'G', 'D', 'G', 'D', 'G'},
        {'W', 'I', 'I', 'W', 'I', 'W'},
        {'W', 'W', 'W', 'W', 'W', 'W'}
    }, "mediumHouse");

    private static final Structures bigHouse = new Structures(new char[][]{
        {'W', 'W', 'W', 'W', 'W', 'W', 'W'},
        {'W', 'I', 'G', 'W', 'G', 'I', 'W'},
        {'W', 'G', 'G', 'D', 'G', 'G', 'D', 'G'},
        {'W', 'G', 'I', 'W', 'I', 'G', 'D', 'G'},
        {'W', 'D', 'W', 'W', 'W', 'W', 'W'},
        {'W', 'G', 'I', 'W', 'G', 'G', 'G'},
        {'W', 'G', 'I', 'W', 'G', 'G', 'G'},
        {'W', 'W', 'W', 'W', 'G', 'G', 'G'}
    }, "bigHouse");

    private static final Structures city = new Structures(new char[][]{
        {'s','G','s','G','m'},
        {'G','G','G','G','G'},
        {'b','G','s','s','m'},
        {'G','G','G','G','G'},
    }, "city");

    private static Structures[] structures = {smallHouse, mediumHouse, bigHouse, city};

    public Structures(char[][] structure, String name){
        this.structure = structure;
        this.name = name;
    }

    public static Structures[] getStructures(){
        return structures;
    }

    public static Structures getSHouse(){
        return smallHouse;
    }

    public static Structures getMHouse(){
        return mediumHouse;
    }

    public static Structures getBHouse(){
        return bigHouse;
    }

    public static Structures getCity(){
        return city;
    }

    public static char[][] getFullCity(){
        return new char[][]{
            {'W', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'W', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'W', 'W', 'W', 'W', 'W', 'W'},
            {'W', 'I', 'G', 'G', 'W', 'G', 'G', 'G', 'W', 'I', 'G', 'G', 'W', 'G', 'G', 'G', 'W', 'I', 'G', 'G', 'G', 'W'},
            {'W', 'G', 'G', 'G', 'D', 'G', 'G', 'G', 'W', 'G', 'G', 'G', 'D', 'G', 'G', 'G', 'W', 'W', 'W', 'W', 'G', 'D', 'G'},
            {'W', 'I', 'G', 'G', 'W', 'G', 'G', 'G', 'W', 'I', 'G', 'G', 'W', 'G', 'G', 'G', 'W', 'G', 'G', 'D', 'G', 'D', 'G'},
            {'W', 'W', 'D', 'W', 'W', 'G', 'G', 'G', 'W', 'W', 'D', 'W', 'W', 'G', 'G', 'G', 'W', 'I', 'I', 'W', 'I', 'W'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'W', 'W', 'W', 'W', 'W', 'W'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'W', 'W', 'W', 'W', 'W', 'G', 'W', 'W', 'W', 'W', 'W', 'G'},
            {'W', 'I', 'G', 'W', 'G', 'I', 'W', 'G', 'G', 'G', 'W', 'I', 'G', 'G', 'W', 'G', 'W', 'I', 'G', 'G', 'W', 'G'},
            {'W', 'G', 'G', 'D', 'G', 'G', 'D', 'G', 'G', 'G', 'W', 'G', 'G', 'G', 'D', 'G', 'W', 'G', 'G', 'G', 'D', 'G'},
            {'W', 'G', 'I', 'W', 'I', 'G', 'D', 'G', 'G', 'G', 'W', 'I', 'G', 'G', 'W', 'G', 'W', 'I', 'G', 'G', 'W', 'G'},
            {'W', 'D', 'W', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'W', 'W', 'D', 'W', 'W', 'G', 'W', 'W', 'D', 'W', 'W', 'G'},
            {'W', 'G', 'I', 'W', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'W', 'G', 'I', 'W', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'W', 'W', 'W', 'W', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
        };
    }

    public char[][] getStructure(){
        return structure;
    }

    public String getName(){
        return name;
    }
}