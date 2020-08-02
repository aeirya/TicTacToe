package xoxo.net.connection;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class DataConnectionListener implements IConnectionListener {

    private final Socket socket;
    private final IDataReceiver handler;
    private boolean isRunning;

    public DataConnectionListener(IDataReceiver handler, Socket socket) {
        this.socket = socket;
        this.handler = handler;
    }

    public void listen() {
        new Thread(this::run).start();
    }

    private DataInputStream getInStream() throws IOException {
        return new DataInputStream(socket.getInputStream());
    }

    private void run() {
        isRunning = true;
        try {
            final DataInputStream in = getInStream();
            while (isRunning) {
                    final byte[] data = read(in);
                    if (data.length > 0) {
                        handler.receive(data);
                    }    
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] read(DataInputStream in) throws IOException {
        return in.readAllBytes();
    }

    @Override
    public void terminate() {
        try {
            getInStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}