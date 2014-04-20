package de.hpi.pjung.playground;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SplitDocument {

	
	public static int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    count++;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } finally {
	        is.close();
	    }
	}
	
	public static void main(String[] args) throws IOException {
		String src = "resources/20095yr_acs_topline.tsv";
		String target = "resources/split_partNUMBER.txt";
		int count = 0;
		
		System.out.println(count = countLines(src));
		
		int cores = Runtime.getRuntime().availableProcessors();
		int splitSize = count / cores;
		int potentialRest = count - (cores * splitSize);
		int startLine = 0;
		
		Thread[] threads = new Thread[cores];
		int[] results = new int[cores];
		
		long start = System.currentTimeMillis();
		
		for(int i = 0; i < cores; i++){
			if (i == cores - 1){ // last core might get additional lines from potentialRest
				threads[i] = new Thread(new ReaderThread(src, startLine, splitSize + potentialRest, results, i));
				startLine += splitSize + 1;
				threads[i].start();
			} else {
				threads[i] = new Thread(new ReaderThread(src, startLine, splitSize, results, i));
				startLine += splitSize + 1;
				threads[i].start();
			}
			
		}
		
		for(int i = 0; i < cores; i++){
			try {
				threads[i].join();
				System.out.println("Thread #" + i + " result: " + results[i]);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		int sum = 0;
		for(int val : results) sum += val;
		System.out.println("Overall result: " + sum);
		
		long time = System.currentTimeMillis() - start;
		System.out.println("Running time: " + time + "ms");
		
	}
	


}
