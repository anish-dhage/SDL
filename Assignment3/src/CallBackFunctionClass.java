import java.util.*;

public class CallBackFunctionClass implements CallBackFunctionInterface{

	@Override
	public void callBackList(ArrayList<jdbc> al, jdbc student) {
		al.add(student);
	}

	@Override
	public void callBackPriorityQueue(PriorityQueue<jdbc> pq, jdbc student) {
		pq.add(student);
	}

	@Override
	public void callBackStack(Stack<jdbc> s, jdbc student) {
		if(s.empty()==false) {
			if( s.peek().marks < student.marks )
				s.add(student);
		}
		else
			s.add(student);
		
	}

	@Override
	public void callBackMap(TreeMap<Integer, jdbc> tm, jdbc student) {
		tm.put(student.roll_no, student);
	}

	

}
