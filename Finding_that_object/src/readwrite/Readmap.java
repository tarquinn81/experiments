import java.util.*;
import java.io.*;
package readwrite;
class Readmap //Reads the map file.
{
    public static void Readmap ( String mapsave )throws IOExeption
    {
	int num1;
	int xmapsize = 10;
	int row1 = 0;
	int column1 = 0;
	int nummap[row1][column1];
	File map = new File( mapsave );
	Scanner mapreader = new Scanner( map );
	while ( mapreader.hasNextInt() )
	{
	    num1 = mapreader.nextInt();
	    row1 +=;
	    nummap[row1][column1] = num1;
	    if ( row1 == xmapsize )
	    {
		column1 +=;
	    }
	}
    }
}