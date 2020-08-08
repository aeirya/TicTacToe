package xoxo.test;

import java.io.IOException;

import xoxo.client.GameClient;
import xoxo.client.net.ServerAPI;
import xoxo.client.ui.CliGraphics;
import xoxo.client.ui.GameMenu;
import xoxo.net.request.game.GameState;
import xoxo.server.ServerMain;

public class Tessst {
    private static final String IP = "localhost";
    private static final int PORT = 8000;

    public static int read() {
        try {
            int a = System.in.read() - 48;
            System.in.read();
            return a;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) {
        ServerMain.main(args);
        final GameClient c = new GameClient(IP, PORT);
        final GameClient c2 = new GameClient(IP, PORT);
        c.run();
        c2.run();
        c.get().login("arya", "1234");
        c.get().findMatch();

        // c2.get().login("ali", "a");
        // c2.get().findMatch();
        // read();
        // c.get().play(0, 1);
        // c2.get().play(2,2);
        // System.out.println(c.get().getUpdate().toString());

        // read();
        


        new Thread(() -> {
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("arrrrrr");
            ServerAPI api = c.get();
            api.play(1, 5);
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("arrrrrr");
            api.play(1,6);
        }
        ).start();
        


        new CliGraphics(c2.get());



        // System.out.println(c.get().getUpdate());




        // c2.get().login("ali", "a");
        // c2.get().findMatch();

        // boolean isAlive = true;
        // while (isAlive) {
        //     int a = read();
        //     System.out.println("it is: " + a);
        //     System.out.println("looping");
        //     switch(a) {
        //         case 0:
        //         isAlive = false;
        //         break;
        //         case 1:
        //         c.get().play(read(), read());
        //         break;
        //         case 3:
        //         c2.get().play(read(), read());
        //         break;
        //         case 2:
        //         final GameState state = c.get().getUpdate();
        //         if (state == null) break;
        //         final String table = new GameMenu.TableCreator().makeTable(state.board);
        //         System.out.println(table);
        //         break;
        //         default:
        //         continue;
        //     }
        // }
    }
}