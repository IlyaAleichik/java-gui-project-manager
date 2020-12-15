import server.MultiThreadedServer;

import java.io.IOException;


public class ProjectManagerServer {
    public static final int PORT_WORK = 3030;

    public static void main(String[] argv) throws IOException {
        MultiThreadedServer server = new MultiThreadedServer(PORT_WORK);
        new Thread(server).start();
    }
}
