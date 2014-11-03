package LZW;

import java.util.*;


public class lzw {

	public static ArrayList<Integer> compress(String uncompressed) {

		int dictSize = 256;
		Map<String, Integer> dictionary = new HashMap<String, Integer>();
		for (int i = 0; i < 256; i++) {
			dictionary.put("" + (char) i, i);
			// System.out.println( (char)i);
		}

		String element = "";
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (char c : uncompressed.toCharArray()) {
			String newelement = element + c;
			if (dictionary.containsKey(newelement))
				element = newelement;
			else {
				result.add(dictionary.get(element));
				// ndeef wc f el dictionary .
				dictionary.put(newelement, dictSize++);
				element = "" + c;
			}
		}

		// System.out.println(dictionary.keySet());
		// ndeef w
		if (!element.equals(""))
			result.add(dictionary.get(element));
		return result;
	}

	public static String decompress(ArrayList<Integer> compressed) {
		// Build the dictionary.
		int dictSize = 256;
		Map<Integer, String> dictionary = new HashMap<Integer, String>();
		for (int i = 0; i < 256; i++)
			dictionary.put(i, "" + (char) i);

		String w = "" + (char) (int) compressed.remove(0);
		StringBuffer result = new StringBuffer(w);
		for (int k : compressed) {
			String entry = null;
			if (dictionary.containsKey(k))
				entry = dictionary.get(k);
			else if (k == dictSize)
				entry = w + w.charAt(0);
			// else
			// throw new IllegalArgumentException("Bad compressed k: " + k);
			//
			result.append(entry);

			// Add w+entry[0] to the dictionary.
			dictionary.put(dictSize++, w + entry.charAt(0));

			w = entry;
		}
		// System.out.println("---->"+" "+result+" "+w+" ");
		return result.toString();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String x = "abaabca";
		// System .out.println(compress(x));
		// String decompressed = decompress(compress(x));
		// System.out.println(decompressed);
		new App();

	}

}
