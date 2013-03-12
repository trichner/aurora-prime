package ch.k42.auroraprime.minions;

public final class Log {
	/**
	 * Log error messages
	 * @param msg message to log
	 */
	public final static void e(Object msg){
		System.err.println(msg);
	}
	/**
	 * Log verbose messages
	 * @param msg message to log
	 */
	public final static void v(Object msg){
		System.out.println(msg);
	}
    /**
     * Log warning messages
     * @param msg message to log
     */
    public final static void w(Object msg){
        System.out.println(msg);
    }
	/**
	 * Log debug messages
	 * @param tag a Tag specifying the logging class
     * @param msg message to log
	 */
	public final static void d(String tag,Object msg){
		System.out.println(tag + " : " + msg);
	}
	/**
	 * Log to file?
	 * @param msg message to log
	 */
	public final static void l(Object msg){
		//Log into file?
		System.out.println(msg);
	}
}
