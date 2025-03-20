package ex4;

import java.util.concurrent.Semaphore;

public class Carro extends Thread {
	private int id;
	private int escuderia;
	private Semaphore semaforo;
	private Podio[] podio;
	private static int ordemChegada = 0;

	public Carro(int id, int escuderia, Semaphore semaforo, Podio[] podio) {
		super();
		this.id = id;
		this.escuderia = escuderia;
		this.semaforo = semaforo;
		this.podio = podio;
	}

	private void voltas() {
		double tempoVolta;
		double menorTempo = 0;

		for (int i = 0; i < 3; i++) {
			tempoVolta =(int) (Math.random() * 3000) + 3000;
			
			try {
				sleep((int) tempoVolta);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Carro #"+id+" | Escuderia: "+escuderia+" | "+(i+1)+"º volta: "+(tempoVolta/1000)+"s.");

			if (menorTempo == 0) {
				menorTempo = tempoVolta;
			} else if (tempoVolta < menorTempo) {
				menorTempo = tempoVolta;
			}
		}
		
		//System.out.println("Carro #"+id+" | Escuderia: "+escuderia+" | Menor volta: "+(menorTempo / 1000)+"s.");
		
		//System.out.println(ordemChegada);
		armazenar(menorTempo, ordemChegada);
		ordemChegada++;
		
	}
	
	
	private void armazenar(double menorTempo, int ordem) {
		podio[ordem].carro = this.id;
		podio[ordem].escuderia = this.escuderia;
		podio[ordem].volta = menorTempo / 1000;
		
		if(ordem == 13) {
			Podio aux = new Podio();
			
			for (int i = 0; i < podio.length; i++) {
				for (int j = i; j < podio.length; j++) {
					if(podio[j].volta < podio[i].volta) {
						aux = podio[i];
						
						podio[i] = podio[j];
						podio[j] = aux;
					}
				}
			}
			
			System.out.println("\n--------------------------------------------------------------");
			for (int i = 0; i < podio.length; i++) {
				System.out.println((i+1)+"º --> Carro #"+podio[i].carro+" | Escuderia: "+podio[i].escuderia+" | Menor volta: "+podio[i].volta+"s.");
			}
		}
	}

	@Override
	public void run() {

		try {
			semaforo.acquire();
			voltas();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}

	}
}
