package br.com.schumaker.example1.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author hudson schumaker
 */
public class Core implements Runnable {

    private final Socket socket;

    public Core(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader inFromClient = null;
        try {
            System.out.println(socket + " " + Thread.currentThread().getName());
            inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());

            String requestMessageLine = inFromClient.readLine();
            System.out.println(requestMessageLine);

            switch (requestMessageLine) {
                case "c1": {
                    outToClient.writeBytes("Confirmação do comando c1");
                    break;
                }
                case "c2": {
                    outToClient.writeBytes("Confirmação do comando c2");
                    break;
                }
                default: {
                    outToClient.writeBytes("Comando não encontrado");
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                if (inFromClient != null) {
                    inFromClient.close();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
