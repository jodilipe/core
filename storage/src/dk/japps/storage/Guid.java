package dk.japps.storage;

import java.util.Random;

public class Guid {
	private static Random random = new Random(System.currentTimeMillis());
	private String id;
	
	public String getId() {
		if (id == null) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < 9; i++) {
				sb.append(random.nextInt(10));
			}
			id = sb.toString();
		}
		return id;
	}
}
