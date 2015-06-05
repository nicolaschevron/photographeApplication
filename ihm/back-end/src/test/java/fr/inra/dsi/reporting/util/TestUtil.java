package fr.inra.dsi.reporting.util;

public class TestUtil {
    
    private static int NEXT_INT = 1;

    public static int getNextCodeInt(){
        int result;
        synchronized(TestUtil.class){
            result = NEXT_INT;
            NEXT_INT = NEXT_INT+1;
        }
        return result;
    }
    
    public static String getNextCodeString(){
        return intToCode(getNextCodeInt());
    }

    private static String intToCode(int theInt){
        String result = "000"+theInt;
        return result.substring(result.length()-4, result.length());
    }
    
    private static int NEXT_CODE_ACTIVITE = 1;
    
    /**
     * @see #getNextCodeInt()
     * @see #getNextCodeString()
     * @return
     */
    @Deprecated
    public static String getNextCodeActivite(){
        String result;
        synchronized(TestUtil.class){
            result = intToCode(NEXT_CODE_ACTIVITE);
            NEXT_CODE_ACTIVITE = NEXT_CODE_ACTIVITE+1;
        }
        return result;
    }
    
    private static int NEXT_CODE_UNITE = 1;

    /**
     * @see #getNextCodeInt()
     * @see #getNextCodeString()
     * @return
     */
    @Deprecated
    public static String getNextCodeUnite(){
        String result;
        synchronized(TestUtil.class){
            result = intToCode(NEXT_CODE_UNITE);
            NEXT_CODE_UNITE = NEXT_CODE_UNITE+1;
        }
        return result;
    }

    private static int NEXT_CODE_DEPARTEMENT = 1;

    /**
     * @see #getNextCodeInt()
     * @see #getNextCodeString()
     * @return
     */
    @Deprecated
    public static String getNextCodeDepartement(){
        String result;
        synchronized(TestUtil.class){
            result = intToCode(NEXT_CODE_DEPARTEMENT);
            NEXT_CODE_DEPARTEMENT = NEXT_CODE_DEPARTEMENT+1;
        }
        return result;
    }

    private static int NEXT_IDENTIFIANT_STRUCTURE = 1;

    /**
     * @see #getNextCodeInt()
     * @see #getNextCodeString()
     * @return
     */
    @Deprecated
    public static int getNextIdentifiantStructure(){
        int result;
        synchronized(TestUtil.class){
            result = NEXT_IDENTIFIANT_STRUCTURE;
            NEXT_IDENTIFIANT_STRUCTURE = NEXT_IDENTIFIANT_STRUCTURE+1;
        }
        return result;
    }
}
