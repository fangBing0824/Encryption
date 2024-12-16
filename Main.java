import java.util.Random;

class Main {
  public static void main(String[] args) {
    (new Main()).init();
  }
  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}

  void init(){

    // This example we are substituting all upper case 
    // emojis to another upper case letter.
    
	char []ABCs = {'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'};
	
	char [] emojis = {'\u2687','\u26C4','\u26F8','\u26E4','\u2606','\u2607','\u2613','\u2618','\u2622','\u2623','\u262E','\u262F','\u2630','\u263A','\u263C','\u263D','\u26F8','\u264C','\u264F','\u2661','\u2613','\u2662','\u2664','\u2672','\u2685','\u268A','\u2690','\u26BE'};
	
  // https://www.compart.com/en/unicode/block/U+2600

    
    // Encoding message
    String file = Input.readFile("Original.txt");

    //Atbash Cipher
    String encodedMsg1 = encode1(file);
    Input.writeFile("Encode1.txt",encodedMsg1);

    // Skip
    String encodedMsg2 = skipEvery(encodedMsg1,3,"+");
    Input.writeFile("Encode2.txt",encodedMsg2);

    // substitution
    String encodedMsg3 = encode3(encodedMsg2,ABCs,emojis);
    Input.writeFile("Encode3.txt",encodedMsg3);

    
    // decoding message
    String file2 = Input.readFile("Encode3.txt");
    
    String decodedMsg1 = decode1(file2,emojis,ABCs);
    Input.writeFile("Decode1.txt", decodedMsg1);
    
    String decodedMsg2 = skipEvery(decodedMsg1,3,"-");
    Input.writeFile("Decode2.txt", decodedMsg2);
	
	String decodedMsg3 = decode3(decodedMsg2);
    Input.writeFile("Decode3.txt", decodedMsg3);
	
  }
 
  
  
 
  
  
  int indexOf(char ch, char[] arry){
    for(int x=0; x<=arry.length-1; x++){
      if(arry[x]==ch){
        return x;
      }
    }
    return -1;
  }
  int randInt(int lower, int upper){
    int range = upper - lower;
    return (int)(Math.random()*range+lower);
  }
  
  String encode1(String text){

	String bld = "";
	char ch;
	for (int x = 0;x < text.length();x++){
		ch = text.charAt(x);
		if (Character.isUpperCase(ch)){
			ch = (char)(155-(int)ch); // 155 = ASCII('A')+ASCII('Z')//
		}
		else if (Character.isLowerCase(ch)){
			ch = (char) (219 - (int)ch); // 219 = ASCII ('a') +ASCII ('z'))//
		}
		bld += ch;
	}
	return bld;
  }

   String encode2(String txt, int skipNum){
    String bld = "";
	int length = txt.length();
	int groups = (length + skipNum-1)/skipNum;
        for(int i=0; i<skipNum; i++){
            for(int j=0; j<groups; j++){
				int index = j*skipNum+i;
               if (index < length){
				   bld = bld +txt.charAt(index);
			   }
            }
        }
    
   
    return bld;
  }
  
  String encode3(String text, char[] changeFrom, char[] changeTo){
    String bld = "";
    for(int i=0; i<text.length(); i++){
      int indexOf = indexOf(text.charAt(i), changeFrom);
      if (indexOf != -1)
        bld+=changeTo[indexOf];
      else bld += text.charAt(i);
    }
    return bld;
  }

  String decode1(String text, char[] changeFrom,char [] changeTo){
	  String bld = "";
	  for (int i=0;i<text.length();i++){
		  int indexOf = indexOf(text.charAt(i),changeFrom);
		  if (indexOf != -1)
			  bld+=changeTo[indexOf];
		 else bld+=text.charAt(i);
		 
	  }
	  return bld;
  }	
   String skipEvery(String txt, int skipNum, String plusOrMinus){
    String bld = "";
    if(plusOrMinus.equals("+")){
        for(int i=0; i<skipNum; i+=1){
            for(int j=i; j<txt.length(); j+=skipNum){
                bld = bld + txt.charAt(j);
            }
        }
    } else {
        char[] result = new char[txt.length()];
        int currentPos = 0;
        
        for(int i=0; i<skipNum; i+=1){
            for(int j=i; j<txt.length(); j+=skipNum){
                result[j] = txt.charAt(currentPos);
                currentPos++;
            }
        }
        
        bld = new String(result);
    }
    return bld;
  }

  String decode3(String text){
	String bld = "";
	char ch;
	for (int x = 0;x < text.length();x++){
		ch = text.charAt(x);
		if (Character.isUpperCase(ch)){
			ch = (char)(((int)ch-155)*-1); // 155 = ASCII('A')+ASCII('Z')//
		}
		else if (Character.isLowerCase(ch)){
			ch = (char) (((int)ch-219)*-1); // 219 = ASCII ('a') +ASCII ('z'))//
		}
		bld += ch;
	}
	return bld;
  }
  
}