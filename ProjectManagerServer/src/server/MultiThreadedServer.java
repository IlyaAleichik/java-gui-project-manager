package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer implements Runnable {

    protected int DEFAULT_PORT = 3030;
    protected ServerSocket server = null;
    protected boolean isStopped = false;

    public MultiThreadedServer(int PORT){
        this.DEFAULT_PORT = PORT;
    }

    @Override
    public void run(){
        openServerSocket();
        while(!isStopped()){
            Socket socket = null;
            try {
                socket  = this.server.accept();
                System.out.println("connection established....");
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            new Thread(new MultiThreadedListener(socket)).start();
        }
        System.out.println("Server Stopped.") ;
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.server.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        System.out.println("Server started...");
        try {
            this.server = new ServerSocket(this.DEFAULT_PORT);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.DEFAULT_PORT, e);
        }
    }

}
