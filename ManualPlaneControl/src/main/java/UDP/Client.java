package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;

    private int port;

    public Client(String ipAddress, int port) {
        try{
            socket = new DatagramSocket();
            address = InetAddress.getByName(ipAddress);
        }catch(Exception e){
            e.printStackTrace();
        }

        this.port = port;
    }

    public String send(String msg){
        System.out.println("Sending: \""+msg+"\"");
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
        try{
            socket.send(packet);
        }catch(Exception e){
            e.printStackTrace();
        }
        packet = new DatagramPacket(buf, buf.length);
        try{
            socket.receive(packet);
        }catch(Exception e){
            e.printStackTrace();
        }
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }

    public void close(){
        send("end");
        socket.close();
    }

    public static void main(String[] args) {
        Client c = new Client("localhost", 4445);
        System.out.println(c.send("hello"));
        System.out.println(c.send("hi"));
        System.out.println(c.send("good day"));
        c.close();
    }
}