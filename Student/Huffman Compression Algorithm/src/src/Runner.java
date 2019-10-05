package src;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

public class Runner {
	
	static String file = "dream.txt";

	public static void main(String... args) throws Exception {

		long p = System.currentTimeMillis();

		HashMap<Integer, Integer> map = new HashMap<>();

		BitInputStream bis = new BitInputStream(file);
		int bits;
		// all bits/chars will be counted in the map, in order
		while (true) {
			if ((bits = bis.read()) == -1) {
				break;
			} else {
				if (!map.containsKey(bits)) {
					map.put(bits, 1);
				} else {
					map.put(bits, map.get(bits) + 1);
				}
			}
		}

		PriorityQueue<Node> list = new PriorityQueue<>();

		for (Entry<Integer, Integer> entry : map.entrySet()) {
			list.add(new Node((char) entry.getKey().intValue(), entry.getValue(), null, null));
		}

		// add newly made nodes to list to be considered again for tree-building
		while (list.size() > 1) {
			Node l = list.poll();
			Node r = list.poll();
			Node newNode = new Node('\u0000', l.weight + r.weight, l, r);
			list.add(newNode);
		}

		System.out.println("Total Time: " + (System.currentTimeMillis() - p) + "ms");
		System.out.println(Tree.height(list.peek()));
		bis.close();

		HashMap<Character, String> m = new HashMap<>();

		Tree.genTable(m, list.peek(), "");
		System.out.println();

		String outputFileName = "ser.data";
		FileWriter charToBytesWriter = null;
		try {
			charToBytesWriter = new FileWriter(outputFileName);
		} catch (IOException ioe) {
			System.out.println("Could not create the new file: " + ioe);
		}
		PrintWriter diskFile = new PrintWriter(charToBytesWriter);
		
		serialize(list.peek(), diskFile);

		diskFile.close();
		
		Scanner scan = new Scanner(new File("ser.data"));
		while(scan.hasNext()){
			System.out.println(scan.next());
		}
		scan.close();
		
//		Scanner scan = new Scanner(new File(file));
//		FileOutputStream os = new FileOutputStream("ser.data");
//		BitOutputStream bos = new BitOutputStream(os);
//
//		while(scan.hasNext()){
//			
//			String seq = scan.next();
//			
//			for(char c : seq.toCharArray()){
//				bos.write(Integer.parseInt(c+""));
//			}
//			
//		}
//		
//		bos.close();
//		scan.close();

	}

	public static void serialize(Node root, PrintWriter pw) {
		helper(root, pw);

	}

	public static void helper(Node node, PrintWriter pw) {

		if (node == null) {
			pw.println("-");
			return;
		}

		pw.println(node.toString());
		helper(node.left, pw);
		helper(node.right, pw);

	}

}
