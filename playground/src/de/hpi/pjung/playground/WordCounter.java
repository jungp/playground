package de.hpi.pjung.playground;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class WordCounter {

	private static void add(String word, Map<String, Integer> map) {
		int wordcount = 0;

		if (!word.isEmpty() && word != "") {
			if (!map.containsKey(word)) {
				map.put(word, 1);
			} else {
				wordcount = map.get(word);
				map.put(word, ++wordcount);
			}
		}
	}

	private static String cleanupWord(String word) {
		word = word.toLowerCase();
		word = word.replaceAll("[^a-zäöü]", "");
		return word;
	}

	public static void main(String[] args) throws IOException {
		String src = "resources/hamlet_de.txt";
		String target = "resources/result_de.csv";

		Map<String, Integer> count = new HashMap<String, Integer>();

		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(src), "UTF-8"));
		String line = in.readLine();
		String[] tokens;

		while (line != null) {
			tokens = line.split("\\s"); // split on any whitespace character

			for (String word : tokens) {
				word = cleanupWord(word);
				add(word, count);
			}
			line = in.readLine();
		}

		in.close();
		FileWriter fw = new FileWriter(new File(target));
		
		Set<Entry<String, Integer>> set = count.entrySet();
		Iterator<Entry<String, Integer>> it = set.iterator();
		Entry<String, Integer> entry = null;

		while (it.hasNext()) {
			entry = it.next();
			System.out.println(entry.getKey() + ", " + entry.getValue());
			fw.append(entry.getKey() + "; " + entry.getValue() + ";\n");
		}
		fw.close();
	}

}
