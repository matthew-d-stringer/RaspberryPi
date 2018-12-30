package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server extends Thread{

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public Server(){
        try{
            socket = new DatagramSocket(4445);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        running = true;

        while(running){
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try{
                socket.receive(packet);
            }catch(IOException e){
                e.printStackTrace();
            }

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            String received = new String(packet.getData(), 0, packet.getLength());

            if(received.contains("end")){
                running = false;
                continue;
            }
            try{
                socket.send(packet);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        System.out.println("Closing");
        socket.close();
    }

    public static void main(String[] args) {
        System.out.println("Running server");
        Server s = new Server();
        s.start();
    }
}