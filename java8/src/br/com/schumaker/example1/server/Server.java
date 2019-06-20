package br.com.schumaker.example1.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author hudsonschumaker
 */
public class Server {

    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {

        System.out.println("---- Iniciando Servidor ----");
        ServerSocket servidor = new ServerSocket(PORT);

        Runtime runtime = Runtime.getRuntime();
        System.out.println("Qtd de processadores: " + runtime.availableProcessors());
        //ExecutorService pool = Executors.newFixedThreadPool(runtime.availableProcessors());
        ExecutorService pool = Executors.newCachedThreadPool();

        for (;;) {
            Socket socket = servidor.accept();
            System.out.println("Aceitando novo cliente na porta " + socket.getPort());
            Core core = new Core(socket);
            pool.execute(core);
        }
    }
}
