package anvilrenamefix;

import java.util.HashMap;
import java.util.HashSet;

public class AnvilNameDecoder {

	public AnvilNameDecoder()
	{
		fillHashMap();
	}
	
	//не стреляйте в пианиста, он играет как умеет (да нихрена, просто из пианино выдрали всё и заставляют играть на пустом ящике)
	private HashMap<Byte, Character> bytesymborpair =  new HashMap<Byte, Character>();
	private void fillHashMap()
	{
		//put lowercase chars
		bytesymborpair.put((byte) -32,'а');
		bytesymborpair.put((byte) -31,'б');
		bytesymborpair.put((byte) -30,'в');
		bytesymborpair.put((byte) -29,'г');
		bytesymborpair.put((byte) -28,'д');
		bytesymborpair.put((byte) -27,'е');
		bytesymborpair.put((byte) -26,'ж');
		bytesymborpair.put((byte) -25,'з');
		bytesymborpair.put((byte) -24,'и');
		bytesymborpair.put((byte) -23,'й');
		bytesymborpair.put((byte) -22,'к');
		bytesymborpair.put((byte) -21,'л');
		bytesymborpair.put((byte) -20,'м');
		bytesymborpair.put((byte) -19,'н');
		bytesymborpair.put((byte) -18,'о');
		bytesymborpair.put((byte) -17,'п');
		bytesymborpair.put((byte) -16,'р');
		bytesymborpair.put((byte) -15,'с');
		bytesymborpair.put((byte) -14,'т');
		bytesymborpair.put((byte) -13,'у');
		bytesymborpair.put((byte) -12,'ф');
		bytesymborpair.put((byte) -11,'х');
		bytesymborpair.put((byte) -10,'ц');
		bytesymborpair.put((byte) -9,'ч');
		bytesymborpair.put((byte) -8,'ш');
		bytesymborpair.put((byte) -7,'щ');
		bytesymborpair.put((byte) -6,'ъ');
		bytesymborpair.put((byte) -5,'ы');
		bytesymborpair.put((byte) -4,'ь');
		bytesymborpair.put((byte) -3,'э');
		bytesymborpair.put((byte) -2,'ю');
		bytesymborpair.put((byte) -1,'я');
		//put uppercase chars
		for (byte number : new HashSet<Byte>(bytesymborpair.keySet()))
		{
			bytesymborpair.put((byte) (number-32), Character.toUpperCase(bytesymborpair.get(number).charValue()));
		}
		//put special chars now
		bytesymborpair.put((byte) -72,'ё');
		bytesymborpair.put((byte) -88,'Ё');
	}
	
	
	public String decodeName(byte[] barray)
	{
		StringBuilder sb = new StringBuilder(50);
		for (byte b : barray)
		{
			if (bytesymborpair.containsKey(b))
			{
				sb.append(bytesymborpair.get(b));
			} else
			{
				sb.append((char) b);
			}
		}
		return  sb.toString();
	}
	
	
}
