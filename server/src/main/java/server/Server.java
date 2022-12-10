package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import beans.Book;

public class Server {
	
	private static final int PORT=2000;
	private static final String IP_SERVER="localhost";

	public static void main(String[] args) {
		
		ArrayList<Book> books = new ArrayList<Book>();
		
		books.add(new Book(12345, "Como desarrollar una mente prodijiosa", "Ramon Campayo", 16));
		books.add(new Book(23456, "Como desarrollar una mente prodijiosa", "Ramon Campayo", 16));
		books.add(new Book(34567, "Como desarrollar una mente prodijiosa", "Ramon Campayo", 16));
		books.add(new Book(45678, "Como desarrollar una mente prodijiosa", "Ramon Campayo", 16));

		// [192, 168, 1, 123, 2000] (ip + port = key ) : (hostname = value)
		//private Map<int[], String> proxyInetMap = new HashMap<int[], String>();

		
		try (ServerSocket serverSocket = new ServerSocket()) {

			// lisener ServerSocket bind
			InetSocketAddress listenning = new InetSocketAddress(IP_SERVER,PORT); 
			serverSocket.bind(listenning);
			
			System.out.println(Thread.currentThread());
			System.out.println("java.net.ServerSocket: binded");
			System.out.println("\tINET: " + listenning.getHostName());
			System.out.println("\tPORT: " + listenning.getPort());
			System.out.println("\tIP: " + listenning.getAddress());

			// run until excplicty quiet
			while (true) {	
				Socket socket = serverSocket.accept();
				System.out.println("\nserver socket address "+socket.getLocalSocketAddress());
				System.out.println("client socket address "+socket.getRemoteSocketAddress());

				ServerThread thread = new ServerThread(socket);
				System.out.println(thread);
				thread.start();
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}	
}