package ch.k42.auroraprime.minions;

public final class Log {
    private static final String PFIX_W = "[WRN]";
    private static final String PFIX_E = "[ERR]";
    private static final String PFIX_L = "[LOG]";
    private static final String PFIX_D = "[DBG]";
    private static final String PFIX_V = "[VBS]";
	/**
	 * Log error messages
	 * @param msg message to log
	 */
	public final static void e(Object msg){
		System.err.println(msg);
	}
    /**
     * Log error messages
     * @param tag a Tag specifying the logging class
     * @param msg message to log
     */
    public final static void e(String tag,Object msg){
        System.err.println(composeMessage(PFIX_E,tag,msg));
    }
	/**
	 * Log verbose messages
	 * @param msg message to log
	 */
	public final static void v(Object msg){
		System.out.println(PFIX_V + " " + msg);
	}
    /**
     * Log warning messages
     * @param msg message to log
     */
    public final static void w(Object msg){
        System.out.println(PFIX_W + " " +msg);
    }
	/**
	 * Log debug messages
	 * @param tag a Tag specifying the logging class
     * @param msg message to log
	 */
	public final static void d(String tag,Object msg){
		System.out.println(composeMessage(PFIX_D,tag,msg));
	}
	/**
	 * Log to file?
	 * @param msg message to log
	 */
	public final static void l(String tag,Object msg){
		//Log into file?
		System.out.println(PFIX_L + " " + tag +msg);
	}

    private static final String composeMessage(String prefix,String tag,Object message){
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        sb.append(" ");
        sb.append(String.format("%-8s",tag));
        sb.append(" ");
        sb.append(String.format("%-20s",message));

        return sb.toString();
    }
}
