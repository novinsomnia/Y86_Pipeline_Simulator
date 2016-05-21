package y86;


public class Fetch {

	static String f_stat;
	static int f_icode;
	static int f_ifun;
	static int f_rA, f_rB;
	static int f_valC, f_valP;
	static int predPC, f_predPC;
	static int PC;
	static int st;
	
	static public void init() {
		f_stat = "AOK";
		f_icode = f_ifun = 0;
		f_rA = f_rB = Register.non;
		f_valC = f_valP = 0;
		predPC = f_predPC = 0;
		PC = 0;
		st = 0;
	}
	
    static public int f_pc()
    {
        if (Memory.icode == Icode.jXX && Memory.Bch == false)
            return Memory.valA;
        if (Write.icode == Icode.ret)
            return Write.valM;
        return predPC;
    }
    
    static public boolean need_regids() {
        if (f_icode == Icode.rrmovl || f_icode == Icode.OPl || f_icode == Icode.pushl || f_icode == Icode.popl
        		|| f_icode == Icode.irmovl || f_icode == Icode.rmmovl || f_icode == Icode.mrmovl)
            return true;
        return false;
    }
    
    static public boolean need_valC() {
        if (f_icode == Icode.irmovl || f_icode == Icode.rmmovl || f_icode == Icode.mrmovl 
        		|| f_icode == Icode.jXX || f_icode == Icode.call)
            return true;
        return false;
    }
    
    static public boolean instr_valid()
    {
        if (f_icode == Icode.nop || f_icode == Icode.halt 
        		|| f_icode == Icode.rrmovl || f_icode == Icode.irmovl || f_icode == Icode.rmmovl || f_icode == Icode.mrmovl 
        		|| f_icode == Icode.OPl || f_icode == Icode.jXX || f_icode == Icode.call || f_icode == Icode.ret 
        		|| f_icode == Icode.pushl || f_icode == Icode.popl)
            return true;
        return false;
    }
    
    static public int new_F_predPC() {
    	if (f_icode == Icode.jXX || f_icode == Icode.call)
    		return f_valC;
    	return f_valP;
    }
	
	static public void fetch() {
		String instr;
		if (Pipeline.instr.get(Fetch.f_pc()) == null) {
			Pipeline.instr.put(Fetch.f_pc(), "00");
		}
		instr = Pipeline.instr.get(Fetch.f_pc());
		//get f_icode, f_ifun
		f_icode = Integer.parseInt(instr.substring(0, 1), 16);
		f_ifun = Integer.parseInt(instr.substring(1, 2), 16);
		
		if (f_icode == Icode.halt)
			f_stat = "HLT";
		else
			f_stat = "AOK";
		//get f_rA, f_rB
        if (need_regids()) {
            f_rA = Integer.parseInt(instr.substring(2, 3), 16);
            f_rB = Integer.parseInt(instr.substring(3, 4), 16);
        }
        else {
            f_rA = Register.non;
            f_rB = Register.non;
        }
        //get f_valC
        if (need_valC()) {
            if (need_regids()) {
            	int a = Integer.parseInt(instr.substring(10,11), 16);
                String f_valC_s = instr.substring(10, 12) + instr.substring(8, 10) + instr.substring(6, 8) + instr.substring(4, 6);
                if (a <= 7)
                	f_valC = Integer.parseInt(f_valC_s, 16);
                else {
                	String a_s = String.valueOf(a-8);
                	f_valC = Integer.parseInt(a_s+f_valC_s.substring(1, 8), 16) - 0x7fffffff - 1;
                }
            }
            else {
            	int a = Integer.parseInt(instr.substring(8,9), 16);
            	String f_valC_s = instr.substring(8, 10) + instr.substring(6, 8) + instr.substring(4, 6) + instr.substring(2, 4);
                if (a <= 7)
                	f_valC = Integer.parseInt(f_valC_s, 16);
                else {
                	String a_s = String.valueOf(a-8);
                	f_valC = Integer.parseInt(a_s+f_valC_s.substring(1, 8), 16) - 0x7fffffff - 1;
                }
            }
        }
        else {
            f_valC = 0;
        }
        //get f_valP
        f_valP = f_pc() + 1;
        if (need_regids())
            f_valP += 1;
        if (need_valC())
            f_valP += 4;
	}
    
    static public boolean bubble = false;
    static public boolean stall()
    {
        boolean temp1 = Execute.icode == Icode.mrmovl || Execute.icode == Icode.popl;
        boolean temp2 = Execute.dstM == Decode.d_srcA || Execute.dstM == Decode.d_srcB;
        boolean temp3 = Decode.icode == Icode.ret || Execute.icode == Icode.ret || Memory.icode == Icode.ret;
        return temp1 && temp2 || temp3;
    }
	
}
