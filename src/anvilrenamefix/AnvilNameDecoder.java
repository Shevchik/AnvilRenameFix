/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 */

package anvilrenamefix;

import java.util.HashMap;

public class AnvilNameDecoder {

	public AnvilNameDecoder()
	{
		fillHashMap();
	}
	
	private HashMap<Byte, Character> bytesymborpair =  new HashMap<Byte, Character>();
	private void fillHashMap()
	{	
		//put lowercase characters
		String symbols = "абвгдежзийклмнопрстуфхцчшщъыьэюя";
		for (int i = 0; i<symbols.length(); i++)
		{
			bytesymborpair.put((byte) (-32+i), symbols.charAt(i));
		}
		//put uppercase characters
		symbols = symbols.toUpperCase();
		for (int i = 0; i<symbols.length(); i++)
		{
			bytesymborpair.put((byte) (-64+i), symbols.charAt(i));
		}
		//put special characters
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
