//package ch.k42.ironhide.NetClientTMP;/**
// * Created with IntelliJ IDEA.
// * User: thomas
// * Date: 6/8/13
// * Time: 11:16 PM
// *
// *
// *
// * To change this template use File | Settings | File Templates.
// */
//
//import ch.k42.auroraprime.core.IMatrix;
//import ch.k42.auroraprime.core.MatrixManager;
//import ch.k42.auroraprime.minions.Log;
//import ch.k42.auroraprime.multicast.ALDevice;
//import ch.k42.auroraprime.multicast.IDeviceDiscovery;
//import ch.k42.auroraprime.multicast.IDeviceDiscoveryFactory;
//import ch.k42.auroraprime.net.AthmosClient;
//import ch.k42.auroraprime.net.DTO.Request;
//import ch.k42.auroraprime.net.IClient;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.Serializable;
//import java.util.List;
//import java.util.Map;
//
///**
// * Class NetClient
// *
// * @author Thomas Richner
// */
//public class NetClient {
//
//    private static final String TAG = "NetClient";
//    private static String[] quorgs;
//
//    private void listM(){
//        Map<Integer,IMatrix> matrices = MatrixManager.getInstance().getSender().getMatrices();
//        for(IMatrix m : matrices.values()){
//            p("Address: " + m.getAddress() + " : ");
//            l(m.toString());
//        }
//    }
//
//    private static void p(String s){
//        System.out.print(s);
//    }
//    private static void l(String s){
//        System.out.println(s);
//    }
//
//    public static void main(String[] args){
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        // Discover Clients
//        IDeviceDiscovery marcopolo = IDeviceDiscoveryFactory.getInstance();
//        List<ALDevice> discoveredDevices;
//        l("Looking for Devices...");
//        do{
//            discoveredDevices  = marcopolo.getDiscoveredDevices();
//        }while (discoveredDevices.size()<1);
//        l("Found at least one device...");
//        ALDevice device = discoveredDevices.get(0);
//
//        if(discoveredDevices.size()>1){ // multiple clients, select from list
//            l("Found multiple devices, please select one.");
//            for(int i=0;i<discoveredDevices.size();i++){
//                p(i + " : ");
//                l(discoveredDevices.get(i).toString());
//            }
//            l("Select device by number:");   // Select ALDevice by typing in number
//            int dev = -1;
//            do {
//                try {
//                    dev = Integer.parseInt(in.readLine());
//                    device = discoveredDevices.get(dev);
//                } catch (Exception e) {
//                    l("Select device by number");
//                    dev = -1;
//                }
//            } while (dev<0);
//        }
//
//        quorgs = device.getQuorgs();
//        // Connect to device
//        IClient client = new AthmosClient();
//        try {
//            client.connect(device.getAddress());
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            Log.e("MulticastTestClient", "Unable to open socket on " + device.getAddress());
//            System.exit(1);
//        }
//
//        // ==== We should now be connected to one client, handle Client interaction
//        l("Connected. Ready for user.");
//        String line;
//
//        do{
//            try {
//                line = in.readLine();
//            } catch (IOException e) {
//                Log.e(TAG,"unable to read from console input: " + e.getMessage());
//                break;
//            }
//            switch (line){
//                case "put":
//
//                    l("Choose Quorg:");
//                    listQ();
//                    try {
//                        int i = Integer.parseInt(in.readLine());
//                        String[] settings = new String[0];
//                        String classname = quorgs[i];
////                        0 quorgs.add(Matrix.class);
////                        1 quorgs.add(MailQuorg.class);
////                        2 quorgs.add(ColorQuorg.class);
////                        3 quorgs.add(BinaryClock.class);
////                        4 quorgs.add(Nexus.class);
////                        5 quorgs.add(RainbowEffect.class);
////                        6 quorgs.add(RandomQuorg.class);
////                        7 quorgs.add(TextEffect.class);
////                        8 quorgs.add(Wave.class);
//
//                        switch (classname){
//                            case "MailQuorg": //mail quorg
//                                l("Username:");
//                                String user = in.readLine();
//                                l("Password:");
//                                String password = in.readLine();
//                                settings = new String[2];
//                                settings[0] = user;
//                                settings[1] = password;
//                                break;
//                            case "ColorQuorg": //COLOR quorg
//                                settings = new String[1];
//                                settings[0] = "oxFFFFFF";
//                                break;
//                            case "RainbowEffect":
//                                l("Raining? y/n");
//                                settings = new String[1];
//                                if(in.readLine().equals("y")){
//                                    settings[0] = "true";
//                                }else {
//                                    settings[0] = "false";
//                                }
//                                break;
//                            case "TextEffect": //Text
//                                l("Text?");
//                                settings = new String[4];
//                                settings[0] = in.readLine(); // text
//                                settings[1] = "0x00FF00"; //foreground color //TODO
//                                settings[2] = "0x000000"; //background color
//                                settings[3] = "1"; //speed
//                                break;
//                        }
//                        l("Choose Matrix:");
//                        int matrix = -1;
//                        do {
//                            try {
//                                matrix = Integer.parseInt(in.readLine());
//                            } catch (Exception e) {
//                                matrix = -1;
//                            }
//                        } while (matrix<1);
//                        Serializable[] qargs = new Serializable[3];
//                        qargs[0] = new Integer(matrix);
//                        qargs[1] = classname;
//                        qargs[2] = settings;
//                        Log.d(TAG,"Sending Request");
//                        Request response = (Request) client.sendRequest(new Request(Request.Command.SETQUORG,qargs));
//                        l("Request " + (response.wasHandled() ? "successful :)" : "failed :("));
//
//                    } catch (Exception e) {
//                        Log.e(TAG,"Illegal input: " + e.getMessage());
//                    }
//                    break;
//                case "show":
//
//                    Request response = null;
//                    try {
//                        response = (Request) client.sendRequest(new Request(Request.Command.GETUPDATE,null));
//                    } catch (Exception e) {
//                        Log.e(TAG,e.getMessage());
//                    }
//
//                    l("Request " + (response.wasHandled() ? "successful :)" : "failed :("));
//
//                    Map<Integer,Class> qmap = (Map<Integer,Class>) response.getArgs()[0];
//                    List<Integer> matrices = (List<Integer>) response.getArgs()[1];
//
//                    l("Running quorgs:");
//                    for(Map.Entry e : qmap.entrySet()){
//                        int matrix = (Integer) e.getKey();
//                        Class c = (Class) e.getValue();
//                        p(matrix + " : " + c.getName());
//                        l("    Has matrix ? " + (matrices.contains(matrix) ? "Yes" : "No"));
//                    }
//                    l("Available matrices:");
//                    for(Integer i : matrices){
//                        l("Address: " + i + "    Has quorg? " + (qmap.containsKey(i) ? "Yes" : "No"));
//                    }
//                    break;
//                case "help":
//                    l("put    Set a new quorg");
//                    l("show   show running Quorgs and available matrices");
//                    break;
//                default:
//                    l("use 'help' to display available commands");
//                    break;
//            }
//        }while(!line.equals("exit"));
//
//    }
//
//    private static void listQ(){
//        for(int i=0;i<quorgs.length;i++){
//            p(i + " : ");
//            l(quorgs[i]);
//        }
//    }
//}
