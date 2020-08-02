package xoxo.server;

public class RequestHandler implements IRequestHandler {

    @Override
    public void handle(String request) {
        // System.out.println("received request: " + request);
        String[] a = request.split("/end/");
        for (String s : a) {
            System.out.println(s);
        }
    }
    
}