package server;

import java.io.*;
import java.net.Socket;

public class MultiThreadedListener implements Runnable {
    protected Socket socket = null;

    public MultiThreadedListener(Socket SOCKET) {
        this.socket = SOCKET;
    }

    @Override
    public void run() {
        try( ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream()); ObjectOutputStream toClient = new ObjectOutputStream(socket.getOutputStream());){
            while (true){
                String choice = fromClient.readObject().toString();
                if (choice.equals("ok")){
                    toClient.writeObject("Вы успешно вошли в систему");
                    toClient.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("Client disconnected.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
