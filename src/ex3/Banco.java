package ex3;

import java.util.concurrent.Semaphore;

public class Banco extends Thread {

	private String tipo;

	private int codigo;
	private static double[] saldo;
	private double valor;

	private Semaphore semaforo;

	public Banco(String tipo, int codigo, double[] saldo, double valor, Semaphore semaforo) {
		super();
		this.tipo = tipo;
		this.codigo = codigo;
		Banco.saldo = saldo;
		this.valor = valor;
		this.semaforo = semaforo;
	}

	private void transacao() {
		if (tipo == "saque") {
			try {
				semaforo.acquire();
				saque();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		} else if(tipo == "deposito") {
			try {
				semaforo.acquire();
				deposito();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semaforo.release();
			}
		}
	}

	private void saque() {
		saldo[codigo - 1] -= valor;
		
		System.out.println(
				"Conta: " + codigo + " | Valor: " + valor + " | Saldo restante: " + saldo[codigo - 1] + " | Operacao: Saque");		
		
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void deposito() {
		saldo[codigo - 1] += valor;
		
		System.out.println(
				"Conta: " + codigo + " | Valor: " + valor + " | Saldo restante: " + saldo[codigo - 1] + " | Operacao: Deposito");
		
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		transacao();
	}
}
