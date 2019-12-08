import java.util.ArrayList;

public interface Filter {
	boolean accept(String x);
}
class filterTest implements Filter {
	@Override
	public boolean accept(String x) {
		return x.length() <= 3;
	}
	
	public static String[] filter(String[] a, Filter f) {
		ArrayList<String> array = new ArrayList<String>();
		for(int i = 0; i < a.length; i++) {
			if(f.accept(a[i])) {
				array.add(a[i]);
			}
		}
		return (String[]) array.toArray(new String[array.size()]);
	}
}
