package ex2;

import java.util.concurrent.Semaphore;

public class Pessoa extends Thread {
	private int id;
	private Semaphore semaforo;
	
	public Pessoa(int id, Semaphore semaforo) {
		super();
		this.id = id;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {

		pessoaAndando();
		
		try {
			semaforo.acquire();
			pessoaAtravessando();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
			
	}

	private void pessoaAndando() {
		int distancia = 50;
		int andou = 0;
		
		while(andou < distancia) {
			int desloc = (int)((Math.random() * 3) + 4);
			andou += desloc;
			
			System.out.println("Pessoa #"+id+" andou "+ desloc+"m.  ||  "+andou+"m/"+distancia+"m");
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Pessoa #"+id+" chegou na porta");
	}
	
	private void pessoaAtravessando() {
		System.out.println("Pessoa #"+id+" está atravessando a porta");
		
		int tempo = (int)((Math.random() * 2000) + 1000);
		
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Pessoa #"+id+" atravessou em "+(tempo/1000)+"s.");
		
	}

	
}
