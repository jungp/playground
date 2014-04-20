package de.hpi.pjung.playground;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReaderThread implements Runnable {
	private String filename = null;
	private int offset = 0;
	private int length = 0;
	private int[] result = null;
	private int id = -1;
	
	private int countLines() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
		String line = in.readLine();
		int overallPosition = 0;
		int lineCount = 0;
		
		while (line != null) {
			line = in.readLine();
			
			if (overallPosition >= offset && length > 0){
				// do actual work here
				lineCount++;
				
				this.length--;
			}
			overallPosition++;	
		}

		in.close();
		return lineCount;
	}
	
	ReaderThread(String filename, int offset, int length, int[] result, int id){
		this.filename = filename;
		this.offset = offset;
		this.length = length;
		this.result = result;
		this.id = id;
	}
	
	
	@Override
	public void run() {
		System.out.println("Starting thread: " + this.id);
		
		try {	
			result[id] = countLines();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Terminating thread: " + this.id);
	}

}
