package br.com.schumaker.example3.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author hudson schumaker
 */
public class ServidorTarefas {
    
    public static void main(String[] args) throws Exception {
        System.out.println("---- Iniciando Servidor ----");
        ServerSocket servidor = new ServerSocket(8080);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (;;) {
            Socket socket = servidor.accept();
            System.out.println("Aceitando novo cliente na porta " + socket.getPort());
            DistribuirTarefas distribuirTarefas = new DistribuirTarefas(socket);
            threadPool.execute(distribuirTarefas);
        }
    }
}
