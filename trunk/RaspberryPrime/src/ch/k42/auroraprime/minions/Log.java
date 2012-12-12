package ch.k42.auroraprime.minions;

public class Log {
	/**
	 * Log error
	 * @param msg
	 */
	public static void e(Object msg){
		System.err.println(msg);
	}
	/**
	 * Log verbose
	 * @param msg
	 */
	public static void v(Object msg){
		System.out.println(msg);
	}
	/**
	 * Log debug
	 * @param msg
	 */
	public static void d(Object msg){
		System.out.println(msg);
	}
	/**
	 * Log to file?
	 * @param msg
	 */
	public static void l(Object msg){
		//Log into file?
		System.out.println(msg);
	}
}
