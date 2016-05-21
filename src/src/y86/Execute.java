package y86;


public class Execute {

	static String stat, e_stat;
	static int icode, e_icode;
	static int ifun, e_ifun, e_aluifun;
	static int valA, valB, valC;
	static int e_valA, e_valB, e_valC, e_valE;
	static int dstE, dstM, srcA, srcB;
	static int e_dstE, e_dstM;
	static boolean e_Bch;
	static int PC;
	static boolean OF, ZF, SF;
	static int st;
	
	static public void init() {
		stat = e_stat = "AOK";
		icode = e_icode = ifun = e_ifun = e_aluifun = 0;
		valA = valB = valC = e_valA = e_valB = e_valC = e_valE = 0;
		dstE = dstM = srcA = srcB = e_dstE = e_dstM = Register.non;
		e_Bch = false;
		PC = -1;
		OF = ZF = SF = false;
		st = 0;
	}
	
    static public int aluA() {
        if (icode == Icode.rrmovl || icode == Icode.OPl)
            return valA;
        else if (icode == Icode.irmovl || icode == Icode.rmmovl || icode == Icode.mrmovl)
            return valC;
        else if (icode == Icode.call || icode == Icode.pushl)
            return -4;
        else if (icode == Icode.ret || icode == Icode.popl)
            return 4;
        return 0;
    }
	
    static public int aluB() {
        if (icode == Icode.rmmovl || icode == Icode.mrmovl || icode == Icode.OPl || icode == Icode.call
        		|| icode == Icode.pushl || icode == Icode.ret || icode == Icode.popl)
            return valB;
        else if (icode == Icode.rrmovl || icode == Icode.irmovl)
            return 0;
        return 0;
    }
    
    static public int alufun() {
        if (icode == Icode.OPl)
            return ifun;
        return OPl.addl;
    }
    
    static public boolean set_cc() {
        if (icode == Icode.OPl)
            return true;
        return false;
    }
	
    static public void execute() {
        e_icode = icode;
        e_dstE = dstE;
        e_dstM = dstM;
        e_valA = valA;
        e_stat = stat;
        
        if (alufun() == OPl.addl)
            e_valE = aluA() + aluB();
        else if (alufun() == OPl.andl)
            e_valE = aluA() & aluB();
        else if (alufun() == OPl.subl)
            e_valE = aluB() - aluA();
        else
            e_valE = aluB() ^ aluA();
        
        if (set_cc()) {
            OF = ((aluA() < 0 == aluB() < 0) && (e_valE < 0 != aluA() < 0) && alufun() == 0) || ((aluB() < 0 == (~aluA() + 1) < 0) && (e_valE < 0 != aluB() < 0) && alufun() == 1);
            ZF = (e_valE == 0);
            SF = (e_valE < 0);
        }
        
        if (e_icode == Icode.jXX) {
            switch (ifun)
            {
                case 0: e_Bch = true;
                    	break;
                case 1: if ((ZF || SF) && !OF) e_Bch = true;
                    	else e_Bch = false;
                    	break;
                case 2: if (SF && !ZF && !OF) e_Bch = true;
                    	else e_Bch = false;
                    	break;
                case 3: if (ZF && !SF && !OF) e_Bch = true;
                    	else e_Bch = false;
                    	break;
                case 4: if (!ZF) e_Bch = true;
                    	else e_Bch = false;
                		break;
                case 5: if ((ZF || !SF) && !OF) e_Bch = true;
                    	else e_Bch = false;
                    	break;
                case 6: if (!ZF && !SF && !OF) e_Bch = true;
                    	else e_Bch = false;
                    	break;
            }
        }
        else
            e_Bch = false;
    }
    
    public static boolean stall = false;
    public static boolean bubble() {
        boolean temp1 = icode == Icode.mrmovl || icode == Icode.popl;
        boolean temp2 = dstM == Decode.d_srcA || dstM == Decode.d_srcB;
        return (icode == Icode.jXX && !e_Bch) || temp1 && temp2;
    }
    
}
