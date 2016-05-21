package y86;

public class Write {

	static String stat;
	static int icode;
	static int valE, valM;
	static int dstE, dstM;
	static int PC;
	static int st;
	
	static public void init() {
		stat = "AOK";
		icode = 0;
		valE = valM = 0;
		dstE = dstM = Register.non;
		PC = -1;
		st = 0;
	}
	
	static public void write() {
        if (dstM != Register.non) {
        	Pipeline.register[dstM] = valM;
        	if (dstM == Register.esp)
        		Pipeline.stack.add(valM);
        }
        if (dstE != Register.non) {
        	Pipeline.register[dstE] = valE;
        	if (dstE == Register.esp)
        		Pipeline.stack.add(valE);
        }
	}
	
}
