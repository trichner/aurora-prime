package ch.k42.auroraprime.minions;

public class Log {
	/**
	 * Log error
	 * @param msg
	 */
	public final static void e(Object msg){
		System.err.println(msg);
	}
	/**
	 * Log verbose
	 * @param msg
	 */
	public final static void v(Object msg){
		System.out.println(msg);
	}
	/**
	 * Log debug
	 * @param msg
	 */
	public final static void d(String tag,Object msg){
		System.out.println(tag + " : " + msg);
	}
	/**
	 * Log to file?
	 * @param msg
	 */
	public final static void l(Object msg){
		//Log into file?
		System.out.println(msg);
	}
}
