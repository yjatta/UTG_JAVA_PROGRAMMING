
public class PlinkedDemo {
	public static void main(String[] args) {
		//System.out.println("adding student to a course!");
		Contianer <String> stu = new pLinkedDesk<String>();
		//System.out.println(stu.isEmpty());
		stu.addFirst("modou");
		stu.addFirst("lamin");
		stu.addFirst("pa");
		stu.addFirst("saidou");
		stu.addFirst("sazz");
		stu.addFirst("lamin");
		stu.addFirst("adama");
		//stu.clear();
		stu.toArray();
		//System.out.println("================================");
		((pLinkedDesk<String>) stu).addLast("yoromang");
		((pLinkedDesk<String>) stu).remove("yoromang");
		//System.out.println(stu.isEmpty());
		System.out.println("================================");
		//System.out.println(" result is :"+stu.getCurrentSize());
		stu.toArray();
		
	}
}
