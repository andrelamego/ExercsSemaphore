package ex2;

import java.util.concurrent.Semaphore;

public class Controle {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		
		for (int i = 0; i < 4; i++) {
			Pessoa pessoa = new Pessoa(i, semaforo);
			pessoa.start();
		}
	}

}
