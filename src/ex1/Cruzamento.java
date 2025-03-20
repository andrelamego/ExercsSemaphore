package ex1;

import java.util.concurrent.Semaphore;

public class Cruzamento {
	
	public static void main(String[] args) {
		controle();
	}
	
	public static void controle() {
		Semaphore semaforo = new Semaphore(1);
		
		for (int i = 0; i < 4; i++) {
			Carro carro = new Carro(i, semaforo);
			carro.start();
		}
	}
}
