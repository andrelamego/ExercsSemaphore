package ex1;

import java.util.concurrent.Semaphore;

public class Carro extends Thread{
	
	private int id;
	private String sentido;
	
	private Semaphore semaforo;
	
	public Carro(int id, Semaphore semaforo) {
		super();
		this.id = id;
		this.semaforo = semaforo;
	}
	

	@Override
	public void run() {
		carroAndando();
		//-----------SEÇAO CRITICA------------
		try {
			semaforo.acquire();
			carroAtravessando();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
		//------------------------------------
	}
	
	
	private void carroAndando() {
		int distanciaTotal = 500;
		int deslocamento = 100;
		int distanciaPercorrida = 0;
		
		switch (id) {
		case 0:
			sentido = "direita";
			break;
		case 1:
			sentido = "baixo";
			break;
		case 2:
			sentido = "cima";
			break;
		case 3:
			sentido = "esquerda";
			break;
		}
		
		while(distanciaPercorrida < distanciaTotal) {
			distanciaPercorrida += deslocamento;
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Carro #"+id+" chegou no cruzamento.");
		
	}

	private void carroAtravessando() {
		System.out.println("Carro #"+id+" está indo para "+sentido);
		
		int distanciaTotal = 500;
		int deslocamento = 20;
		int distanciaPercorrida = 0;
		
		while(distanciaPercorrida < distanciaTotal) {
			distanciaPercorrida += deslocamento;
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Carro #"+id+" atravessou");
	}
}
