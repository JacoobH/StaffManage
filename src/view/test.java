package view;

public class test {

	
	public static void main(String[] args) {
		int pID=0;
		String name="ss";
		String gender="male";
		String department="..";
		double wages=1000;
		String s="UPDATE staffList SET name = '"+name+"', SET gender = '"+gender+"', SET department = '"+department+"',SET wages = "+wages+" WHERE id = "+pID;
		System.out.println(s);;
		System.out.println("ALTER TABLE stafflist AUTO_INCREMENT = SELECT MAX(id) FROM stafflist");

	}

}
