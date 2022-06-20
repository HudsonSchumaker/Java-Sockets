package br.com.schumaker.example1.client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Hudson Schumaker
 */
public class Client {

    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", PORT);
        System.out.println("Conexão Estabelecida");
        System.out.println(socket);

        PrintStream saida = new PrintStream(socket.getOutputStream());
        Scanner teclado = new Scanner(System.in);
        Thread sendMessages = new Thread(() -> {
            while (teclado.hasNextLine()) {
                String linha = teclado.nextLine();
                if (linha.trim().equals("")) { // se for vazio não vamos enviar nada para o servidor 
                    break;
                }
                saida.println(linha); // enviando para servidor
            }
            saida.close();
            teclado.close();
        });
        
        Scanner respostaServidor = new Scanner(socket.getInputStream());
        Thread receiveMessages = new Thread(() -> {
            while (respostaServidor.hasNextLine()) { //imprimindo resposta do servidor no console
                String linha = respostaServidor.nextLine();
                System.out.println(linha);
            }
        });
        sendMessages.start();
        sendMessages.join();
        receiveMessages.start();
        respostaServidor.close();
        socket.close();
    }
}
