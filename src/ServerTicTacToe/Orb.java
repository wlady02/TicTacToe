package ServerTicTacToe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;

public class Orb {// servidor ORB
	ServerSocket ss;
	Socket sc;
	DataInputStream canalEntrada;
	DataOutputStream canalSalida;
	Hashtable<Integer, ISkeleton> skeletonHash = new Hashtable<Integer, ISkeleton>();
	int iid = 0;
	int serverPort;
	ISkeleton esqueleto;

	class ServerTask implements Runnable {
		Socket socket;

		ServerTask(Socket sc) {
			this.socket = sc;
		}

		public void run() {
			try {
				System.out.println("traza entra al void run");
				InputStream is = sc.getInputStream();
				OutputStream os = sc.getOutputStream();
				canalEntrada = new DataInputStream(is);
				canalSalida = new DataOutputStream(os);
				iid = canalEntrada.readInt();
				esqueleto = getSkeleton(iid);
				esqueleto.process(canalEntrada,canalSalida);
			} catch (Exception e) {
			}
		}
	}

	Orb(int serverPort) {
		this.serverPort = serverPort;
	}

	public void addSkeleton(ISkeleton sk) {
		skeletonHash.put(sk.getIid(), sk);
	}

	public ISkeleton getSkeleton(int iid) {
		return skeletonHash.get(iid);
	}

	public void start() {
		try {
			ss = new ServerSocket(serverPort);
			while (true) {
				sc = ss.accept();
				System.out.println("traza acepta la conexion");
				ServerTask s = new ServerTask(sc);
				Thread th = new Thread(s);
				th.start();
				System.out.println("start thread");
			}
		} catch (Exception e) {
		}		
	}

	public static void main(String[] args) {
		Orb myORB = new Orb(9999);
		// Se crea el esqueto para tratar peticiones de Cliente
		ISkeleton skRaya = new SkeletonGame();		
		// Se añade al servidor ORB
		myORB.addSkeleton(skRaya);
		// Se arranca el servidor ORB
		myORB.start();

	}
}
