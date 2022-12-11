package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

//esto es el socket de la parte cliente
public class ClientMain {

	// indicamos los puertos e ip que usaremos
	public static final int PORT = 2000;
	public static final String IP_SERVER = "localhost";

	public static void main(String[] args) {

		InetSocketAddress server = new InetSocketAddress(IP_SERVER, PORT);
		
		
		
		try (Scanner sc = new Scanner(System.in)) {

			Socket socket = new Socket();
			socket.connect(server);
			System.out.println("Connection socket established.");

			PrintStream output = new PrintStream(socket.getOutputStream());
			InputStreamReader input = new InputStreamReader(socket.getInputStream());
			BufferedReader buffer = new BufferedReader(input);
			System.out.println("Comunication stream established.");

			String string = "";
			while (!string.equalsIgnoreCase("stop")) {

				string = sc.nextLine();
				output.println(string);
		
			}

			
			buffer.close();
			input.close();
			output.close();
			socket.close();

		} catch (UnknownHostException e) {
			System.err.println("Client-> Can't find the server at the address" + IP_SERVER);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Client-> Error input/output");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Client-> Error -> " + e);
			e.printStackTrace();
		}
	}
}
