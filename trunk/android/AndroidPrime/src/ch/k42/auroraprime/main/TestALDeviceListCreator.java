package ch.k42.auroraprime.main;
//import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import ch.k42.auroraprime.net.*;

public class TestALDeviceListCreator {
	
	private List<ALDevice> list;
	InetAddress inet = intToInetAddress(ipStringToInt( "192.168.0.102"));
	
	ALDevice device = new ALDevice(inet,"horsts light", "1337", "1.0");


public static int ipStringToInt(String str) {
     int result = 0;
     String[] array = str.split("\\.");
     if (array.length != 4) return 0;
     try {
         result = Integer.parseInt(array[3]);
         result = (result << 8) + Integer.parseInt(array[2]);
         result = (result << 8) + Integer.parseInt(array[1]);
         result = (result << 8) + Integer.parseInt(array[0]);
     } catch (NumberFormatException e) {
         return 0;
     }
     return result;
 }

public static InetAddress intToInetAddress(int hostAddress) {
    InetAddress inetAddress;
    byte[] addressBytes = { (byte)(0xff & hostAddress),
                            (byte)(0xff & (hostAddress >> 8)),
                            (byte)(0xff & (hostAddress >> 16)),
                            (byte)(0xff & (hostAddress >> 24)) };

    try {
       inetAddress = InetAddress.getByAddress(addressBytes);
    } catch(UnknownHostException e) {
       return null;
    }
    return inetAddress;
}

}