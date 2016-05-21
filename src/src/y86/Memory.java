package y86;


public class Memory {
	
	static String stat, m_stat;
	static int icode, m_icode;
	static int valA, valE;
	static int m_valA, m_valE, m_valM;
	static int dstE, dstM;
	static int m_dstE, m_dstM;
	static boolean Bch;
	static int PC;
	static int st;
	
	static public void init() {
		stat = m_stat = "AOK";
		icode = m_icode = 0;
		valA = valE = m_valA = m_valE = m_valM = 0;
		dstE = dstM = m_dstE = m_dstM = Register.non;
		Bch = false;
		PC = -1;
		st = 0;
	}
	
    static public int mem_addr() {
        if (icode == Icode.rmmovl || icode == Icode.pushl || icode == Icode.call || icode == Icode.mrmovl)
            return valE;
        if (icode == Icode.popl || icode == Icode.ret)
            return valA;
        return 0;
    }
    
    static public boolean mem_read() {
        if (icode == Icode.mrmovl || icode == Icode.popl || icode == Icode.ret)
            return true;
        return false;
    }
    
    static public boolean mem_write() {
        if (icode == Icode.rmmovl || icode == Icode.pushl || icode == Icode.call)
            return true;
        return false;
    }
    
    static public void memory() {
        m_icode = icode;
        m_valE = valE;
        m_dstE = dstE;
        m_dstM = dstM;
        m_stat = stat;
        if (mem_read()) {
            if (Pipeline.mem.containsKey(mem_addr())) 
            	m_valM = Pipeline.mem.get(mem_addr());
            else
                m_stat = "ADR";
        }
        if (mem_write()) {
            if (mem_addr() < Pipeline.maxMem){
                Pipeline.mem.put(mem_addr(), valA);
                Pipeline.memory.put(mem_addr(), valA & 0xFF);
                Pipeline.memory.put(mem_addr()+1, (valA >> 8) & 0xFF);
                Pipeline.memory.put(mem_addr()+2, (valA >> 16) & 0xFF);
                Pipeline.memory.put(mem_addr()+3, (valA >> 24) & 0xFF);
            }
            else
                m_stat = "ADR";
        }
    }
	
    public static boolean stall = false;
    public static boolean bubble = false;
}
