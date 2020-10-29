package br.com.schumaker.example6.server;

import java.io.PrintStream;

/**
 *
 * @author hudson schumaker
 */
public class ComandoC1 implements Runnable {

    private final PrintStream saida; //representa a saida do cliente

    public ComandoC1(PrintStream saida) {
        this.saida = saida;
    }

    @Override
    public void run() {
        //será impresso no console do servidor
        System.out.println("Executando comando c1");

        try {
            Thread.sleep(20000);//simulando algo demorado
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //essa mensagem será enviada para o cliente
        this.saida.println("Comando c1 executado com sucesso!");
    }
}
