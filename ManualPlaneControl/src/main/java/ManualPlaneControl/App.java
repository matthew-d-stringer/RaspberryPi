/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ManualPlaneControl;

import UDP.Client;
import UDP.Server;

public class App {
    public static void main(String[] args) {
        Server s = new Server();
        s.start();
        Client c = new Client("localhost", 4445);
        System.out.println("Recieved: "+c.send("hello"));
        System.out.println("Recieved: "+c.send("how r u"));
        System.out.println("Recieved: "+c.send("no u"));
        System.out.println("Recieved: "+c.send("goodbye"));
        c.close();
    }
}
