package y86;


public class Decode {

	static String stat, d_stat;
	static int icode, d_icode;
	static int ifun, d_ifun;
	static int rA, rB;
	static int valC;
	static int valP;
	static int d_valA, d_valB, d_valC;
	static int d_srcA, d_srcB;
	static int d_dstE, d_dstM;
	static int PC;
	static int d_rvalA, d_rvalB;
	static int st;
	
	static public void init() {
		stat = d_stat = "AOK";
		icode = d_icode = ifun = d_ifun = 0;
		rA = rB = d_srcA = d_srcB = Register.non;
		valC = valP = d_valA = d_valB = d_valC = 0;
		d_dstE = d_dstM = Register.non;
		PC = -1;
		d_rvalA = d_rvalB = 0;
		st = 0;
	}
	
	static public int new_E_srcA() {
		if (icode == Icode.rrmovl || icode == Icode.rmmovl || icode == Icode. OPl || icode == Icode.pushl) 
			return rA;
		else if (icode == Icode.popl || icode == Icode.ret) 
			return Register.esp;
		else
			return Register.non;
	}
	
	static public int new_E_srcB() {
		if (icode == Icode.OPl || icode == Icode.rmmovl || icode == Icode.mrmovl)
			return rB;
		else if (icode == Icode.pushl || icode == Icode.popl || icode == Icode.call || icode == Icode.ret)
			return Register.esp;
		else
			return Register.non;
	}
	
	static public int new_E_dstE() {
		if (icode == Icode.rrmovl || icode == Icode.irmovl || icode == Icode.OPl)
			return rB;
		else if (icode == Icode.pushl || icode == Icode.popl || icode == Icode.call || icode == Icode.ret)
			return Register.esp;
		else
			return Register.non;
	}
	
	static public int new_E_dstM() {
		if (icode == Icode.mrmovl || icode == Icode.popl)
			return rA;
		else
			return Register.non;
	}
	
	static public int new_E_valA() {
		if (icode == Icode.call || icode == Icode.jXX) 
			return valP;
        if (d_srcA == Execute.dstE && d_srcA != Register.non)
            return Execute.e_valE;
        if (d_srcA == Memory.dstM && d_srcA != Register.non)
            return Memory.m_valM;
        if (d_srcA == Memory.dstE && d_srcA != Register.non)
            return Memory.valE;
        if (d_srcA == Write.dstM && d_srcA != Register.non)
            return Write.valM;
        if (d_srcA == Write.dstE && d_srcA != Register.non)
            return Write.valE;
        return d_rvalA;		
	}
	
	static public int new_E_valB() {
        if (d_srcB == Execute.dstE && d_srcB != Register.non)
            return Execute.e_valE;
        if (d_srcB == Memory.dstM && d_srcB != Register.non)
            return Memory.m_valM;
        if (d_srcB == Memory.dstE && d_srcB != Register.non)
            return Memory.valE;
        if (d_srcB == Write.dstM && d_srcB != Register.non)
            return Write.valM;
        if (d_srcB == Write.dstE && d_srcB != Register.non)
            return Write.valE;
        return d_rvalB;
	}
	
	static public void decode() {
        d_icode = icode;
        d_ifun = ifun;
        d_dstE = new_E_dstE();
        d_dstM = new_E_dstM();
        d_valC = valC;
        d_srcA = new_E_srcA();
        d_srcB = new_E_srcB();
        d_rvalA = Pipeline.register[d_srcA];
        d_rvalB = Pipeline.register[d_srcB];
        d_valA = new_E_valA();
        d_valB = new_E_valB();
        d_stat = stat;
	}
	
    static public boolean stall() {
        boolean temp1 = Execute.icode == Icode.mrmovl || Execute.icode == Icode.popl;
        boolean temp2 = Execute.dstM == d_srcA || Execute.dstM == d_srcB;
        return temp1 && temp2;
    }
    
    static public boolean bubble() {
        boolean temp1 = icode == Icode.ret || Execute.icode == Icode.ret || Memory.icode == Icode.ret;
        return temp1 || (Execute.icode == Icode.jXX && !Execute.e_Bch);
    }
}
