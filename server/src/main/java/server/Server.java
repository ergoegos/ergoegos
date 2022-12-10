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
			sysoutSocketAddress(listenning);
			
			// run until excplicty quiet
			while (true) {	
				Socket socket = serverSocket.accept();
				new ServerThread(socket);
			}
			

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	private static void sysoutSocketAddress(InetSocketAddress direction) {
		System.out.println("SOCKET ADDRESS INFO");
		System.out.println("  INET: " + direction.getHostName());
		System.out.println("  PORT: " + direction.getPort());
		System.out.println("  IP: " + direction.getAddress());
	}
}