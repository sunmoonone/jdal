package smn.utils;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.lang.Object; 

public class Functions {
	public static final String EMPTY = "";

	public static <T> Set<T> set(T... obj) {
		Set<T> ret = new HashSet<T>();
		
		if (obj == null || obj.length == 0)
			return ret;
		
		for (T o : obj) {
			ret.add(o);
		}
		
		return ret;
	}

	public static <T> Set<T> union(Set<T> setA, Set<T> setB) {
		Set<T> tmp = new TreeSet<T>(setA);
		tmp.addAll(setB);
		return tmp;
	}

	public static <T> Set<T> intersection(Set<T> setA, Set<T> setB) {
		if (setA.size() <= setB.size()) {
			Set<T> tmp = new TreeSet<T>(setA);
			tmp.retainAll(setB);
			return tmp;
		} else {
			Set<T> tmp = new TreeSet<T>(setB);
			tmp.retainAll(setA);
			return tmp;
		}
	}

	public static <T> boolean hasIntersection(Set<T> setA, Set<T> setB) {
		for (T x : setA)
			if (setB.contains(x))
				return true;
		return false;
	}

	public static String md5(String s) {
		if (s == null || s.equals("")) {
			return null;
		}

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param kvs
	 * @return call: map("success",true,"message","hello") get :
	 *         Map{"success"=>true,"message"=>"hello"}
	 */
	@SuppressWarnings("unchecked")
	public static <K,V> Map<K, V> map(Object... kvs) {
		Map<K, V> m = new HashMap<K, V>();
		if (kvs == null || kvs.length == 0)
			return m;

		for (int i = 0, len = kvs.length; (i + 1) < len; i += 2) {
			if (isEmpty(kvs[i]))
				continue;
			m.put((K)kvs[i], (V)kvs[i + 1]);
		}
		return m;
	}

	/**
	 * 
	 * @param kvs
	 * @return call: dict("success",true,1,"hello") get :
	 *         Map{"success"=>true,"1"=>"hello"}
	 */
	public static Map<String, Object> dict(Object... kvs) {
		Map<String, Object> m = new HashMap<String, Object>();
		if (kvs == null || kvs.length == 0)
			return m;

		for (int i = 0, len = kvs.length; (i + 1) < len; i += 2) {
			if (isEmpty(kvs[i]))
				continue;

			m.put((String) kvs[i], kvs[i + 1]);
		}
		return m;
	}

	public static <T> T[] array(T... vals) {
		return vals;
	}
	
	public static <T> List<T> list(T... vals) {
		List<T> list=new ArrayList<T>();
		if(vals==null || vals.length==0)return list;
		for(T v:vals){
			list.add(v);
		}
		return list;
	}

	/**
	 * 可以用于判断 Map,Collection,String,Array是否为空
	 * 
	 * @param o
	 * @return
	 */
	@SuppressWarnings("all")
	public static boolean isEmpty(Object o) {
		if (o == null)
			return true;

		if (o instanceof String) {
			if (((String) o).length() == 0) {
				return true;
			}
		} else if (o instanceof Collection) {
			if (((Collection) o).isEmpty()) {
				return true;
			}
		} else if (o.getClass().isArray()) {
			if (Array.getLength(o) == 0) {
				return true;
			}
		} else if (o instanceof Map) {
			if (((Map) o).isEmpty()) {
				return true;
			}
		}

		return false;
	}

	public static Boolean asBool(String value) {
		if (value == null || value.equals(""))
			return false;
		if (value.equalsIgnoreCase("true") || value.equals("1")
				|| value.equalsIgnoreCase("yes"))
			return true;
		return false;
	}

	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.equals(""))
			return true;
		return false;
	}

	public static boolean IsNullOrWhiteSpace(String str) {
		if (str == null || str.equals("") || str.matches("^\\s+$"))
			return true;
		return false;
	}

	public static String join(Collection<?> collection, String separator) {
		return join(collection.toArray(), separator);
	}

	public static String join(Object[] array, char separator) {
		if (array == null) {
			return null;
		}

		return join(array, String.valueOf(separator), 0, array.length);
	}

	public static String join(String separator, Object... args) {
		return join(args, separator);
	}

	public static String join(Object[] array, String separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	private static String join(Object[] array, String separator,
			int startIndex, int endIndex) {
		if (array == null) {
			return null;
		}
		if (separator == null) {
			separator = EMPTY;
		}

		// endIndex - startIndex > 0: Len = NofStrings *(len(firstString) +
		// len(separator))
		// (Assuming that all Strings are roughly equally long)
		int bufSize = (endIndex - startIndex);
		if (bufSize <= 0) {
			return EMPTY;
		}

		bufSize *= ((array[startIndex] == null ? 16 : array[startIndex]
				.toString().length()) + separator.length());

		StringBuffer buf = new StringBuffer(bufSize);

		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	public static Set<String> splitToSet(String str, String subStr) {
		Set<String> st = new HashSet<String>();
		if (str == null || subStr == null)
			return st;
		st.addAll(split(str, subStr));
		return st;
	}

	public static List<String> split(String str, String subStr) {
		if (str == null || subStr == null)
			return null;

		List<String> ret = new LinkedList<String>();
		if (str.indexOf(subStr) == -1) {
			ret.add(str);
			return ret;
		}

		int index = str.indexOf(subStr), start = 0, len = subStr.length(), strlen = str
				.length();
		while (true) {
			ret.add(str.substring(start, index));
			start = index + len;

			if (start == strlen) {
				break;
			}
			index = str.indexOf(subStr, start);
			if (index == -1) {
				ret.add(str.substring(start));
				break;
			}
		}
		return ret;
	}

	public static String rtrim(String str, String charlist) {
		if (str == null || str.equals(""))
			return str;

		if (charlist == null || charlist.equals("")) {
			return str.replaceFirst("\\s+$", "");
		}

		int len = str.length();
		int end = len;
		for (int i = len - 1; i >= 0; i--) {
			if (!containsChar(charlist, str.charAt(i))) {
				break;
			} else {
				end = i;
			}
		}
		return str.substring(0, end);
	}

	public static boolean containsChar(String str, char ch) {
		if (str == null)
			return false;

		for (int i = 0, len = str.length(); i < len; i++) {
			if (str.charAt(i) == ch)
				return true;
		}
		return false;
	}

	public static String lstrip(String str, String head) {
		if (str == null || head == null)
			return str;
		if (!str.startsWith(head))
			return str;

		return str.substring(head.length());
	}

	public static String rstrip(String str, String tail) {
		if (str == null || tail == null)
			return str;
		if (!str.endsWith(tail))
			return str;

		return str.substring(0, str.lastIndexOf(tail));
	}

	public static String ucfirst(String str) {
		if (str == null || str.equals(""))
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}
