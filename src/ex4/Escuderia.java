package ex4;

import java.util.concurrent.Semaphore;

public class Escuderia extends Thread {
	private Semaphore semaforo;
	private int id;
	private Podio[] podio;

	public Escuderia(Semaphore semaforo, int id, Podio[] podio) {
		super();
		this.semaforo = semaforo;
		this.id = id;
		this.podio = podio;
	}

	@Override
	public void run() {
		try {
			semaforo.acquire();
			enviarCarro();		
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		
	}

	private void enviarCarro() {
		Semaphore semaforoCarros = new Semaphore(1);
		
		for (int i = 0; i < 2; i++) {
			Carro carro = new Carro(i + 1, id, semaforoCarros, podio);
			carro.start();
			
		}
		
	}
}
