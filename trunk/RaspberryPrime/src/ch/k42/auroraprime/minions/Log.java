package ch.k42.auroraprime.minions;

public class Log {
	/**
	 * Log error
	 * @param msg
	 */
	public static void e(String msg){
		System.err.println(msg);
	}
	/**
	 * Log verbose
	 * @param msg
	 */
	public static void v(String msg){
		System.out.println(msg);
	}
	/**
	 * Log debug
	 * @param msg
	 */
	public static void d(String msg){
		System.out.println(msg);
	}
	/**
	 * Log to file?
	 * @param msg
	 */
	public static void l(String msg){
		//Log into file?
		System.out.println(msg);
	}
}
