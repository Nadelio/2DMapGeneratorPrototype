package src;
public class Structures {
    private char[][] structure;
    private String name;

    private static final Structures smallHouse = new Structures(new char[][]{
        {'G', 'G', 'G', 'G', 'G', 'G', 'G'},
        {'G', 'W', 'W', 'W', 'W', 'W', 'G'},
        {'G', 'W', 'I', 'F', 'F', 'W', 'G'},
        {'G', 'W', 'F', 'F', 'F', 'D', 'G'},
        {'G', 'W', 'I', 'F', 'F', 'W', 'G'},
        {'G', 'W', 'W', 'D', 'W', 'W', 'G'},
        {'G', 'G', 'G', 'G', 'G', 'G', 'G'}
    }, "smallHouse");

    private static final Structures mediumHouse = new Structures(new char[][]{
        {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
        {'G', 'W', 'W', 'W', 'W', 'W', 'W', 'G'},
        {'G', 'W', 'I', 'F', 'F', 'F', 'W', 'G'},
        {'G', 'W', 'W', 'W', 'W', 'F', 'D', 'G'},
        {'G', 'W', 'F', 'F', 'D', 'F', 'D', 'G'},
        {'G', 'W', 'I', 'I', 'W', 'I', 'W', 'G'},
        {'G', 'W', 'W', 'W', 'W', 'W', 'W', 'G'},
        {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'}
    }, "mediumHouse");

    private static final Structures bigHouse = new Structures(new char[][]{
        {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
        {'G', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'G'},
        {'G', 'W', 'I', 'F', 'W', 'F', 'I', 'W', 'G'},
        {'G', 'W', 'F', 'F', 'D', 'F', 'F', 'D', 'G'},
        {'G', 'W', 'F', 'I', 'W', 'I', 'F', 'D', 'G'},
        {'G', 'W', 'D', 'W', 'W', 'W', 'W', 'W', 'G'},
        {'G', 'W', 'F', 'I', 'W', 'G', 'G', 'G', 'G'},
        {'G', 'W', 'F', 'I', 'W', 'G', 'G', 'G', 'G'},
        {'G', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'G'},
        {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'}
    }, "bigHouse");

    private static final Structures city = new Structures(new char[][]{
        {'s','G','s','G','m'},
        {'G','G','G','G','G'},
        {'b','G','s','s','m'},
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
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'W', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'W', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'W', 'W', 'W', 'W', 'W', 'W', 'G'},
            {'G', 'W', 'I', 'F', 'F', 'W', 'G', 'G', 'G', 'W', 'I', 'F', 'F', 'W', 'G', 'G', 'G', 'W', 'I', 'F', 'F', 'F', 'W', 'G'},
            {'G', 'W', 'F', 'F', 'F', 'D', 'G', 'G', 'G', 'W', 'F', 'F', 'F', 'D', 'G', 'G', 'G', 'W', 'W', 'W', 'W', 'F', 'D', 'G'},
            {'G', 'W', 'I', 'F', 'F', 'W', 'G', 'G', 'G', 'W', 'I', 'F', 'F', 'W', 'G', 'G', 'G', 'W', 'F', 'F', 'D', 'F', 'D', 'G'},
            {'G', 'W', 'W', 'D', 'W', 'W', 'G', 'G', 'G', 'W', 'W', 'D', 'W', 'W', 'G', 'G', 'G', 'W', 'I', 'I', 'W', 'I', 'W', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'W', 'W', 'W', 'W', 'W', 'W', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'W', 'W', 'W', 'W', 'W', 'G', 'W', 'W', 'W', 'W', 'W', 'G', 'G'},
            {'G', 'W', 'I', 'F', 'W', 'F', 'I', 'W', 'G', 'G', 'G', 'W', 'I', 'F', 'F', 'W', 'G', 'W', 'I', 'F', 'F', 'W', 'G', 'G'},
            {'G', 'W', 'F', 'F', 'D', 'F', 'F', 'D', 'G', 'G', 'G', 'W', 'F', 'F', 'F', 'D', 'G', 'W', 'F', 'F', 'F', 'D', 'G', 'G'},
            {'G', 'W', 'F', 'I', 'W', 'I', 'F', 'D', 'G', 'G', 'G', 'W', 'I', 'F', 'F', 'W', 'G', 'W', 'I', 'F', 'F', 'W', 'G', 'G'},
            {'G', 'W', 'D', 'W', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'W', 'W', 'D', 'W', 'W', 'G', 'W', 'W', 'D', 'W', 'W', 'G', 'G'},
            {'G', 'W', 'F', 'I', 'W', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'W', 'F', 'I', 'W', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'W', 'W', 'W', 'W', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'},
            {'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G', 'G'}
            };
    }

    public char[][] getStructure(){
        return structure;
    }

    public String getName(){
        return name;
    }
}