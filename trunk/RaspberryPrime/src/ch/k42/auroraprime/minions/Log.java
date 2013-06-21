package ch.k42.auroraprime.minions;

public final class Log {
    private static final String PFIX_W = "[WRN]";
    private static final String PFIX_E = "[ERR]";
    private static final String PFIX_L = "[LOG]";
    private static final String PFIX_D = "[DBG]";
    private static final String PFIX_V = "[VBS]";
    private static final String PFIX_VV = "[VVS]";


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * defines what actually gets written to the console, the higher, the more, for more
     * information, look at the auroraprime.properties file
     */
    private static final int loglevel= Integer.parseInt(ALSettings.getProperty("loglevel"));
    /**
     * Log error messages
     * @param tag a Tag specifying the logging class
     * @param msg message to log
     */
    public static void e(String tag,Object msg){
        if(loglevel<1) return;
        System.err.println(composeMessage(PFIX_E,tag,msg,ANSI_RED));
    }
    /**
     * Log verbose messages
     * @param tag a Tag specifying the logging class
     * @param msg message to log
     */
    public static void v(String tag,Object msg){
        if(loglevel<3) return;
        System.out.println(composeMessage(PFIX_V,tag,msg,ANSI_GREEN));
    }
    /**
     * Log very verbose messages
     * @param tag a Tag specifying the logging class
     * @param msg message to log
     */
    public static void vv(String tag,Object msg){
        if(loglevel<4) return;
        System.out.println(composeMessage(PFIX_VV,tag,msg,ANSI_GREEN));
    }
    /**
     * Log warning messages
     * @param msg message to log
     */
    public static void w(String tag,Object msg){
        if(loglevel<2) return;
        System.out.println(composeMessage(PFIX_W,tag,msg,ANSI_YELLOW));
    }
	/**
	 * Log debug messages
	 * @param tag a Tag specifying the logging class
     * @param msg message to log
	 */
	public static void d(String tag,Object msg){
        if(loglevel<5) return;
		System.out.println(composeMessage(PFIX_D,tag,msg,ANSI_BLUE));
	}
    /**
     * Log (spamming) debug messages
     * @param tag a Tag specifying the logging class
     * @param msg message to log
     */
    public static void dd(String tag,Object msg){
        if(loglevel<6) return;
        System.out.println(composeMessage(PFIX_D,tag,msg,ANSI_BLUE));
    }
	/**
	 * Log to file?
	 * @param msg message to log
	 */
	public static void l(String tag,Object msg){
		//Log into file?
		System.out.println(PFIX_L + " " + tag +msg);
	}

    private static String composeMessage(String prefix,String tag,Object message){
        return composeMessage(prefix, tag, message,ANSI_RESET);
    }
    private static String composeMessage(String prefix,String tag,Object message,String color){
        StringBuilder sb = new StringBuilder();
        sb.append(color);
        sb.append(prefix);
        sb.append(" ");
        sb.append(String.format("%-20s",tag));
        sb.append(ANSI_RESET);
        sb.append(" ");
        sb.append(String.format("%-50s",message));

        return sb.toString();
    }
}
