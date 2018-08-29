# ABCFile
library AS3 decompiler

### Dependencies for this example :
[https://github.com/jguyet/transform-swf](https://github.com/jguyet/transform-swf)  
[https://github.com/jguyet/ABCFile](https://github.com/jguyet/ABCFile)  

#### Example :
```
package com.flasm;

import java.io.File;

import com.flagstone.transform.DoABC;
import com.flagstone.transform.DoAction;
import com.flagstone.transform.Movie;
import com.flagstone.transform.MovieTag;
import com.flagstone.transform.as3.abcfile.AbcFile;
import com.flagstone.transform.as3.abcfile.utils.ByteBufferFlash;

public class Main {

	public static void main(String[] args) throws Exception {
		Movie m = new Movie();//create blank movie file
		
		//select external swf file
		m.decodeFromFile(new File("example.swf"));
		
		//m.getObjects() contains all objets check on 
		//com.flagstone.transform.MovieDecoder.java
		for (MovieTag mt : m.getObjects()) {
            if (mt instanceof DoAction) {//in AS2 contains frame1 script
            	DoAction d = ((DoAction)mt);
            	
            } else if (mt instanceof DoABC) {//in AS3 contains frame1 scripts and classes
            	DoABC tmp = ((DoABC)mt);
				
				//ABCFile Library -->
            	ByteBufferFlash bbuf = ByteBufferFlash.wrap(tmp.getData());
            	AbcFile abcFile = new AbcFile(0, bbuf);
			}
        }
	}
}
```
