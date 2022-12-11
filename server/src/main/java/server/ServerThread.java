package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.plugins.tiff.TIFFTag;

import beans.Book;
import daos.BookDaoImpl;

public class ServerThread extends Thread {

	private Socket socket;

	// to post data at client
	// private PrintStream output;
	// input from server
	InputStreamReader input;
	BufferedReader buffer;

	// output to server
	PrintStream output;

	// to get data from client
	// private ScreenStream input;
	//private ArrayList<String> data;

	public ServerThread(Socket socket) throws IOException {

		this.socket = socket;
		this.input = new InputStreamReader(socket.getInputStream());
		this.buffer = new BufferedReader(input);
		this.output = new PrintStream(socket.getOutputStream());

		try {
			//this.input = new ScreenStream(this.socket.getInputStream());
			//this.output = new PrintStream(this.socket.getOutputStream());

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	@Override
	public void run() {

		try {

			while (socket.isConnected()) {

				String string = null;
				while (string == null) {
					string = buffer.readLine();
					sleep(100);
				}

				System.out.println(string);	

				output.write(string.getBytes());

			}

			/*
			 * do {
			 * // waitting the client data input
			 * String string = input.read();
			 * System.out.println(string);
			 * 
			 * 
			 * 
			 * 
			 * if (data.get(0).equalsIgnoreCase("1")) {
			 * // consultar por isbn
			 * try {
			 * //output.println("" + book.findByIsbn(Integer.parseInt(data.get(1))));
			 * 
			 * } catch (NumberFormatException e) {
			 * output.println("number format error");
			 * }
			 * }
			 * 
			 * if (data.get(0).equalsIgnoreCase("2")) {
			 * 
			 * try {
			 * //output.println(book.findByTitle(data.get(1)));
			 * 
			 * } catch (Exception e) {
			 * output.println("Exception ->" + e.getMessage());
			 * }
			 * }
			 * 
			 * if (data.get(0).equalsIgnoreCase("3")) {
			 * // consultar por autor
			 * 
			 * try {
			 * //output.println(book.findByAuthor(data.get(1)));
			 * 
			 * } catch (Exception e) {
			 * output.println("Exception ->" + e.getMessage());
			 * }
			 * }
			 * 
			 * if (data.get(0).equalsIgnoreCase("4")) {
			 * // a�adir libro
			 * addOneBookOnTime();
			 * 
			 * }
			 * 
			 * 
			 * 
			 * } while (true);
			 */

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// System.out.println(thread.getName() + " communication closed .");

		}
	}

	/**
	 * Agrega un libro a la vez para evitar problemas.
	 */
	public synchronized void addOneBookOnTime() {
		try {

			// int a = book.addBook(
			// new Book(Integer.parseInt(data.get(1)), data.get(2), data.get(3),
			// Integer.parseInt(data.get(1))));

			// if (a==0)
			output.println("Book created. Thank you so much, nextime gonna to cry in stackoverflow");

		} catch (Exception e) {
			output.println("Exception -> " + e.getMessage());

		}
	}
}
