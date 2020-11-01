package br.com.schumaker.example4.server;

import java.util.concurrent.ThreadFactory;

/**
 *
 * @author hudson schumaker
 */
public class FabricaDeThreads implements ThreadFactory {

    private static int numero = 1;

    @Override
    public Thread newThread(Runnable tarefa) {
        Thread thread = new Thread(tarefa, "Thread Servidor Tarefas " + numero);
        //thread.setPriority(Thread.MIN_PRIORITY);
        thread.setUncaughtExceptionHandler(new TratadorDeExcecao());
        numero++;
        return thread;
    }
}
