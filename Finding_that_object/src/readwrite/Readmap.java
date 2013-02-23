import java.util.*;
import java.io.*;
package readwrite;
class Readmap
{
    public static void Character[][] Readmap ( String mapsave )throws IOExeption
    {
	int num1, row1, column1;
	int nummap[row1][column1];
	File map = new File( mapsave );
	Scanner mapreader = new Scanner( map );
	while ( mapreader.hasNextInt() )
	{
	    num1 = mapreader.nextInt();
	    
	}
    }
}