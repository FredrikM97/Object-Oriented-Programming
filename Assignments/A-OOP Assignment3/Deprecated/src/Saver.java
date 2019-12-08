import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Saver {
	private String s;

	public Saver() {
		s = "";
	}

	public String save(Object o)  {
		Class<?> c = o.getClass();
		Element e = c.getAnnotation(Element.class);
		try {
			for (Method m : c.getDeclaredMethods()) {
				Element t = c.getAnnotation(Element.class);
				if(m != null) {
					s += "<"+t.name()+" value=\"" + m.invoke(o)+">";
				}
			}
		} catch (IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// s += ""+c.getAnnotations()[0] + "\n ";
		return s;

	}
}
