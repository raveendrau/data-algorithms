
public class CalcStack {
	private StringStack myCharStack;
	private StringStack myNumStack;

	public CalcStack(){
		myCharStack = new StringStack();
		myNumStack = new StringStack();
	}
	
	String special = "+-*/";
	public void compute(String data){
		int i = special.indexOf(data);
		if(special.indexOf(data) > -1){
			computeOp(data, special.indexOf(data));
		}else
			myNumStack.push(data);
	}
	
	public void computeOp(String data, int priority){
		if(myCharStack.isEmpty()){
			myCharStack.push(data);
			return;
		}
		
		String prev = myCharStack.pop();
		int prevPriority = special.indexOf(prev);
		if(prevPriority/2 >= (priority/2)){
			performOp(prev.charAt(0));
			myCharStack.push(data);
		}else{
			myCharStack.push(prev);
			myCharStack.push(data);
		}
	}
	
	public void performOp(char op){
		int op2 = Integer.parseInt(myNumStack.pop());
		int op1 = Integer.parseInt(myNumStack.pop());
		int ans = 0;
		switch(op){
			case('+'): ans = op1 + op2; break; 
			case('-'): ans = op1 - op2; break;
			case('*'): ans = op1 * op2; break;
			case('/'): ans = op1 / op2; break;
		}
		
		myNumStack.push(ans+"");
	}
	
	public void finishCompute(){
		while(!myCharStack.isEmpty()){
			performOp(myCharStack.pop().charAt(0));
		}
	}
		
	public static void main(String[] args){
		CalcStack stack = new CalcStack();
		String equation = "5+7*3-2";

		for(int i=0; i < equation.length(); i++){
			stack.compute(equation.substring(i,i+1));
		}
		stack.finishCompute();
		System.out.println(stack.myNumStack.pop());
	}

}
