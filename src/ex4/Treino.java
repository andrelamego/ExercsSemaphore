package ex4;

import java.util.concurrent.Semaphore;

public class Treino {
	public static void main(String[] args) throws InterruptedException {
		Semaphore semaf = new Semaphore(5);
		Podio[] podio = new Podio[14];
		
		for (int i = 0; i < podio.length; i++) {
			podio[i] = new Podio();
		}
		
		for (int i = 0; i < 7; i++) {
			Escuderia escuderia = new Escuderia(semaf, i, podio);
			escuderia.start();
		}
	}
}
