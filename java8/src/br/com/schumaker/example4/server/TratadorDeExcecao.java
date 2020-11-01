package br.com.schumaker.example4.server;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 *
 * @author hudson schumaker
 */
public class TratadorDeExcecao implements UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        System.out.println("Exceção na thread " + thread.getName() + ": " + ex.getMessage());
    }
}
