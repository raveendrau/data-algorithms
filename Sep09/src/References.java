
public class References {

	public static void boo() {
		String str1 = "Despicable";
		String str2 = "Me";
		str2 = str1;
//		str1 = str1.concat(" 2");
		System.out.println(str1+" "+str2);
	}
	
	public static void main(String[] args) {
		boo();
	}
}
