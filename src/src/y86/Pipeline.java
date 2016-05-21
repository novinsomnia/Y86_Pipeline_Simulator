package y86;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Pipeline {	
	static Map<Integer, Integer> memory = new HashMap<Integer, Integer>();
	static Map<Integer, String> instr = new HashMap<Integer, String>();
	static Map<Integer, Integer> mem = new HashMap<Integer, Integer>();
	static Map<Integer, String> instr_s = new HashMap<Integer, String>();
	static int register[] = new int[9];
	static int maxMem = 2048;
	static int maxInstr = 2048;
	static int maxCycle = 2048;
	static int cycle;
	static int totalcycle;
	static boolean load = false;
	static String[] instructions;
	static String[] instructions_addr;
	static Set<Integer> stack = new TreeSet<Integer>(Collections.reverseOrder());
	
	static int[][] memsave;
	static int[][] memorysave; // by byte
	static int[][] registersave;
	static String[][] stacksave;
	
	static int[] f_icode, f_ifun, f_rA, f_rB, f_valC, f_valP, F_predPC, f_predPC, F_PC, F_st;
	static int[] D_icode, d_icode, D_ifun, d_ifun, D_rA, D_rB, D_valC, D_valP, 
				d_valA, d_valB, d_valC, d_srcA, d_srcB, d_dstE, d_dstM, d_rvalA, d_rvalB, D_PC, D_st;
	static int[] E_icode, e_icode, E_ifun, e_ifun, e_aluifun, E_valA, E_valB, E_valC,
				e_valA, e_valB, e_valC, e_valE, E_dstE, E_dstM, E_srcA, E_srcB,
				e_dstE, e_dstM, E_PC, E_st;
	static int[] M_icode, m_icode, M_valA, M_valE, m_valA, m_valE, m_valM,
		 		M_dstE, M_dstM, m_dstE, m_dstM, M_PC, M_st;
	static int[] W_icode, W_valE, W_valM, W_dstE, W_dstM, W_PC, W_st;
	static boolean[] e_Bch, M_Bch;
	static boolean[] OF, ZF, SF;
	static String[] f_stat, D_stat, d_stat, E_stat, e_stat, M_stat, m_stat, W_stat;
	
	static public void init() {
		Fetch.init();
		Decode.init();
		Execute.init();
		Memory.init();
		Write.init();
		
		cycle = 0;
		totalcycle = 0;
		load = false;
		for (int i = 0; i < 9; i++)
			register[i] = 0;
		
		instructions = new String[maxInstr];
		instructions_addr = new String[maxInstr];
		
		mem.clear();
		memory.clear();
		instr_s.clear();
		instr.clear();
		stack.clear();
		
		memsave = new int[maxMem][maxCycle];
		memorysave = new int[maxMem][maxCycle]; // by byte
		registersave = new int[9][maxCycle];
		stacksave = new String[maxMem][maxCycle];
		
		f_icode = new int[maxCycle];
		f_ifun = new int[maxCycle];
		f_rA = new int[maxCycle];
		f_rB = new int[maxCycle];
		f_valC = new int[maxCycle];
		f_valP = new int[maxCycle];
		F_predPC = new int[maxCycle];
		f_predPC = new int[maxCycle];
		F_PC = new int[maxCycle];
		F_st = new int[maxCycle];
		
		D_icode = new int[maxCycle];
		d_icode = new int[maxCycle];
		D_ifun = new int[maxCycle];
		d_ifun = new int[maxCycle];
		D_rA = new int[maxCycle];
		D_rB = new int[maxCycle];
		D_valC = new int[maxCycle];
		D_valP = new int[maxCycle];
		d_valA = new int[maxCycle];
		d_valB = new int[maxCycle];
		d_valC = new int[maxCycle];
		d_srcA = new int[maxCycle];
		d_srcB = new int[maxCycle];
		d_dstE = new int[maxCycle];
		d_dstM = new int[maxCycle];
		d_rvalA = new int[maxCycle];
		d_rvalB = new int[maxCycle];
		D_PC = new int[maxCycle];
		D_st = new int[maxCycle];
		
		E_icode = new int[maxCycle];
		e_icode = new int[maxCycle];
		E_ifun = new int[maxCycle];
		e_ifun = new int[maxCycle];
		e_aluifun = new int[maxCycle];
		E_valA = new int[maxCycle];
		E_valB = new int[maxCycle];
		E_valC = new int[maxCycle];
		e_valA = new int[maxCycle];
		e_valB = new int[maxCycle];
		e_valC = new int[maxCycle];
		e_valE = new int[maxCycle];
		E_dstE = new int[maxCycle];
		E_dstM = new int[maxCycle];
		E_srcA = new int[maxCycle];
		E_srcB = new int[maxCycle];
		e_dstE = new int[maxCycle];
		e_dstM = new int[maxCycle];
		e_Bch = new boolean[maxCycle];
		OF = new boolean[maxCycle];
		ZF = new boolean[maxCycle];
		SF = new boolean[maxCycle];
		E_PC = new int[maxCycle];
		E_st = new int[maxCycle];
		
		M_icode = new int[maxCycle];
		m_icode = new int[maxCycle];
		M_valA = new int[maxCycle];
		M_valE = new int[maxCycle];
		m_valA = new int[maxCycle];
		m_valE = new int[maxCycle];
		m_valM = new int[maxCycle];
		M_dstE = new int[maxCycle];
		M_dstM = new int[maxCycle];
		m_dstE = new int[maxCycle];
		m_dstM = new int[maxCycle];
		M_Bch = new boolean[maxCycle];
		M_PC = new int[maxCycle];
		M_st = new int[maxCycle];
		
		W_icode = new int[maxCycle];
		W_valE = new int[maxCycle];
		W_valM = new int[maxCycle];
		W_dstE = new int[maxCycle];
		W_dstM = new int[maxCycle];
		W_PC = new int[maxCycle];
		W_st = new int[maxCycle];
		
		f_stat = new String[maxCycle];
		D_stat = new String[maxCycle];
		d_stat = new String[maxCycle];
		E_stat = new String[maxCycle];
		e_stat = new String[maxCycle];
		M_stat = new String[maxCycle];
		m_stat = new String[maxCycle];
		W_stat = new String[maxCycle];
	}
	
	static public void readFile(String path) throws IOException {
		File file=new File(path);
        if(!file.exists()||file.isDirectory())
            throw new FileNotFoundException();
		FileReader fr = new FileReader(file);
        
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        line = br.readLine();
        int ind = 0;
        
        while(line != null) {
        	int i = line.indexOf(":");
        	int j = line.indexOf("|");
        	int k = line.indexOf("x");
        	int h = line.indexOf("#");
        	int l = line.indexOf(":", j);
        	if (i == -1) {
        		line = br.readLine();
        		continue;
        	}
        	
        	String addr_s = line.substring(k+1, i).trim();
        	String val_s = line.substring(i+1, j).trim();
        	int addr = Integer.parseInt(addr_s, 16);
        	
        	if (val_s.length() == 0) {
        		line = br.readLine();
        		continue;
        	}
        	if (val_s.length() == 8) {
        		int val;
            	int a = Integer.parseInt(val_s.substring(6,7), 16);
        		String mem_s = val_s.substring(6, 8) + val_s.substring(4, 6) + val_s.substring(2, 4) + val_s.substring(0, 2);
                if (a <= 7)
                	val = Integer.parseInt(mem_s, 16);
                else {
                	String a_s = String.valueOf(a-8);
                	val = Integer.parseInt(a_s+mem_s.substring(1, 8), 16) - 0x7fffffff - 1;
                }
        		mem.put(addr, val);
        		memory.put(addr, Integer.parseInt(val_s.substring(0, 2), 16));
        		memory.put(addr+1, Integer.parseInt(val_s.substring(2, 4), 16));
        		memory.put(addr+2, Integer.parseInt(val_s.substring(4, 6), 16));
        		memory.put(addr+3, Integer.parseInt(val_s.substring(6, 8), 16));
        	}
        	else {
	    		instr.put(addr, val_s);
	    		int val;
	    		int t = 0;
	    		int len = val_s.length();
	    		while (t < len/2) {
	    			val = Integer.parseInt(val_s.substring(2*t, 2*t+2), 16);
	    			memory.put(addr+t, val);
	    			t++;
	    		}
	    		if (h == -1) {
    				instructions_addr[ind] = "0x" +Integer.toHexString(addr);
    				instructions[ind] = line.substring(j+2);
    				ind++;
	    			if (l == -1)
	    				instr_s.put(addr, line.substring(j+1).trim());
	    			else 
	    				instr_s.put(addr, line.substring(l+1).trim());
	    		}
	    		else {
		    		instructions_addr[ind] = "0x" +Integer.toHexString(addr);
		    		instructions[ind] = line.substring(j+2, h);
		    		ind++;
		    		if (l == -1)
		    			instr_s.put(addr, line.substring(j+1, h).trim());
		    		else
		    			instr_s.put(addr, line.substring(l+1, h).trim());
	    		}
        	}
        	line = br.readLine();
        }
        load = true;
        fr.close();
        br.close();
	}
		
	static public void PCUpdate() {
        Write.PC = Memory.PC;
        if (Memory.st == 0) {
            Memory.PC = Execute.PC;
        }
        if (Execute.st == 0) {
            Execute.PC = Decode.PC;
        }
        else 
        	Execute.PC = -1;
        if (Decode.st == 0) {
            Decode.PC = Fetch.PC;
        }
        else if (Decode.st == 1) 
        	Decode.PC = -1;
        Fetch.PC = Fetch.f_pc();
	}
	
    static public void Clock() {
        if (!Fetch.stall()) {
            Fetch.predPC = Fetch.new_F_predPC();
            Fetch.st = 0;
        }
        else Fetch.st = 2;
        
        if (!Decode.bubble() && !Decode.stall()) {
            Decode.st = 0;
            Decode.stat = Fetch.f_stat;
            Decode.icode = Fetch.f_icode;
            Decode.ifun = Fetch.f_ifun;
            Decode.rA = Fetch.f_rA;
            Decode.rB = Fetch.f_rB;
            Decode.valC = Fetch.f_valC;
            Decode.valP = Fetch.f_valP;
        }
        else if (Decode.bubble() && !Decode.stall()) {
            Decode.st = 1;
            Decode.stat = "AOK";
            Decode.icode = 0;
            Decode.ifun = 0;
            Decode.rA = Register.non;
            Decode.rB = Register.non;
            Decode.valC = 0;
            Decode.valP = 0;
        }
        else Decode.st = 2;
        
        if (!Execute.bubble()) {
            Execute.st = 0;
            Execute.stat = Decode.d_stat;
            Execute.icode = Decode.d_icode;
            Execute.ifun = Decode.d_ifun;
            Execute.valC = Decode.d_valC;
            Execute.valA = Decode.d_valA;
            Execute.valB = Decode.d_valB;
            Execute.dstE = Decode.d_dstE;
            Execute.dstM = Decode.d_dstM;
            Execute.srcA = Decode.d_srcA;
            Execute.srcB = Decode.d_srcB;
        }
        else {
            Execute.st = 1;
            Execute.stat = "AOK";
            Execute.icode = 0;
            Execute.ifun = 0;
            Execute.valC = 0;
            Execute.valA = 0;
            Execute.valB = 0;
            Execute.dstE = Register.non;
            Execute.dstM = Register.non;
            Execute.srcA = Register.non;
            Execute.srcB = Register.non;
        }
        
        if (!Memory.bubble && !Memory.stall)
        {
            Memory.st = 0;
            Memory.stat = Execute.e_stat;
            Memory.icode = Execute.e_icode;
            Memory.Bch = Execute.e_Bch;
            Memory.valE = Execute.e_valE;
            Memory.valA = Execute.e_valA;
            Memory.dstE = Execute.e_dstE;
            Memory.dstM = Execute.e_dstM;
        }
        Write.stat = Memory.m_stat;
        Write.icode = Memory.m_icode;
        Write.valE = Memory.m_valE;
        Write.valM = Memory.m_valM;
        Write.dstE = Memory.m_dstE;
        Write.dstM = Memory.m_dstM;
    }

    static public String toHexString(int num) {
    	String str = Integer.toHexString(num);
    	for (; str.length() < 8; str = "0" +str);
    	return str;
    }
    static public String to2byteString(int num) {
    	String str = Integer.toHexString(num);
    	for (; str.length() < 2; str = "0" +str);
    	return str;
    }
    
    static public void saveCycle(int c) {
    	f_icode[c] = Fetch.f_icode;
    	f_ifun[c] = Fetch.f_ifun;
    	f_rA[c] = Fetch.f_rA;
    	f_rB[c] = Fetch.f_rB;
    	f_valC[c] = Fetch.f_valC;
    	f_valP[c] = Fetch.f_valP;
    	F_predPC[c] = Fetch.predPC;
    	f_predPC[c] = Fetch.f_predPC;
    	F_PC[c] = Fetch.PC;
    	F_st[c] = Fetch.st;
    	f_stat[c] = Fetch.f_stat;
    	
    	D_icode[c] = Decode.icode;
    	d_icode[c] = Decode.d_icode;
    	D_ifun[c] = Decode.ifun;
    	d_ifun[c] = Decode.d_ifun;
    	D_rA[c] = Decode.rA;
    	D_rB[c] = Decode.rB;
    	D_valC[c] = Decode.valC;
    	D_valP[c] = Decode.valP;
    	d_valA[c] = Decode.d_valA;
    	d_valB[c] = Decode.d_valB;
    	d_valC[c] = Decode.d_valC;
    	d_srcA[c] = Decode.d_srcA;
    	d_srcB[c] = Decode.d_srcB;
    	d_dstE[c] = Decode.d_dstE;
    	d_dstM[c] = Decode.d_dstM;
    	d_rvalA[c] = Decode.d_rvalA;
    	d_rvalB[c] = Decode.d_rvalB;
    	D_PC[c] = Decode.PC;
    	D_st[c] = Decode.st;
    	D_stat[c] = Decode.stat;
    	d_stat[c] = Decode.d_stat;
    	
    	E_icode[c] = Execute.icode;
    	e_icode[c] = Execute.e_icode;
    	E_ifun[c] = Execute.ifun;
    	e_ifun[c] = Execute.e_ifun;
     	e_aluifun[c] = Execute.e_aluifun;
    	E_valA[c] = Execute.valA;
    	E_valB[c] = Execute.valB;
    	E_valC[c] = Execute.valC;
		e_valA[c] = Execute.e_valA;
		e_valB[c] = Execute.e_valB;
		e_valC[c] = Execute.e_valC;
		e_valE[c] = Execute.e_valE;
		E_dstE[c] = Execute.dstE;
		E_dstM[c] = Execute.dstM;
		E_srcA[c] = Execute.srcA;
		E_srcB[c] = Execute.srcB;
		e_dstE[c] = Execute.e_dstE;
		e_dstM[c] = Execute.e_dstM;
		OF[c] = Execute.OF;
		ZF[c] = Execute.ZF;
		SF[c] = Execute.SF;
		E_PC[c] = Execute.PC;
		E_st[c] = Execute.st;
		E_stat[c] = Execute.stat;
		e_stat[c] = Execute.e_stat;
    	
    	M_icode[c] = Memory.icode;
    	m_icode[c] = Memory.m_icode;
    	M_valA[c] = Memory.valA;
    	M_valE[c] = Memory.valE;
    	m_valA[c] = Memory.m_valA;
    	m_valE[c] = Memory.m_valE;
    	m_valM[c] = Memory.m_valM;
 		M_dstE[c] = Memory.dstE;
 		M_dstM[c] = Memory.dstM;
 		m_dstE[c] = Memory.m_dstE;
 		m_dstM[c] = Memory.m_dstM;
 		M_Bch[c] = Memory.Bch;
 		M_PC[c] = Memory.PC;
 		M_st[c] = Memory.st;
 		M_stat[c] = Memory.stat;
 		m_stat[c] = Memory.m_stat;
    	
    	W_icode[c] = Write.icode;
    	W_valE[c] = Write.valE;
    	W_valM[c] = Write.valM;
    	W_dstE[c] = Write.dstE;
    	W_dstM[c] = Write.dstM;
    	W_PC[c] = Write.PC;
    	W_st[c] = Write.st;
    	W_stat[c] = Write.stat;
    	
    	for(Map.Entry<Integer, Integer> entry:mem.entrySet()){    
    		memsave[entry.getKey()][c] = entry.getValue();  
    	}
    	for(Map.Entry<Integer, Integer> entry:memory.entrySet()){    
    		memorysave[entry.getKey()][c] = entry.getValue();  
    	}
    	for (int i = 0; i < 9; i++)
    		registersave[i][c] = register[i];
    	
    	Iterator<Integer> iterator = stack.iterator();
    	int i = 0;
        while (iterator.hasNext()) {
        	int t = iterator.next();
            stacksave[i++][c] = Integer.toHexString(t);
        }
    }
    static public void setCycle(int c) {
    	cycle = c;
    	Fetch.f_icode = f_icode[c];
    	Fetch.f_ifun = f_ifun[c];
    	Fetch.f_rA = f_rA[c];
    	Fetch.f_rB = f_rB[c];
    	Fetch.f_valC = f_valC[c];
    	Fetch.f_valP = f_valP[c];
    	Fetch.predPC = F_predPC[c];
    	Fetch.f_predPC = f_predPC[c];
    	Fetch.PC = F_PC[c];
    	Fetch.st = F_st[c];
    	Fetch.f_stat = f_stat[c];
    	
    	Decode.icode = D_icode[c];
    	Decode.d_icode = d_icode[c];
    	Decode.ifun = D_ifun[c];
    	Decode.d_ifun = d_ifun[c];
    	Decode.rA = D_rA[c];
    	Decode.rB = D_rB[c];
    	Decode.valC = D_valC[c];
    	Decode.valP = D_valP[c];
    	Decode.d_valA = d_valA[c];
    	Decode.d_valB = d_valB[c];
    	Decode.d_valC = d_valC[c];
    	Decode.d_srcA = d_srcA[c];
    	Decode.d_srcB = d_srcB[c];
    	Decode.d_dstE = d_dstE[c];
    	Decode.d_dstM = d_dstM[c];
    	Decode.d_rvalA = d_rvalA[c];
    	Decode.d_rvalB = d_rvalB[c];
    	Decode.PC = D_PC[c];
    	Decode.st = D_st[c];
    	Decode.stat = D_stat[c];
    	Decode.d_stat = d_stat[c];
    	
    	Execute.icode = E_icode[c];
    	Execute.e_icode = e_icode[c];
    	Execute.ifun = E_ifun[c];
    	Execute.e_ifun = e_ifun[c];
    	Execute.e_aluifun = e_aluifun[c];
    	Execute.valA = E_valA[c];
    	Execute.valB = E_valB[c];
    	Execute.valC = E_valC[c];
    	Execute.e_valA = e_valA[c];
    	Execute.e_valB = e_valB[c];
    	Execute.e_valC = e_valC[c] ;
    	Execute.e_valE = e_valE[c];
    	Execute.dstE = E_dstE[c];
    	Execute.dstM = E_dstM[c];
    	Execute.srcA = E_srcA[c];
    	Execute.srcB = E_srcB[c];
    	Execute.e_dstE = e_dstE[c];
    	Execute.e_dstM = e_dstM[c];
    	Execute.OF = OF[c];
    	Execute.ZF = ZF[c];
    	Execute.SF = SF[c];
    	Execute.PC = E_PC[c];
    	Execute.st = E_st[c];
    	Execute.stat = E_stat[c];
    	Execute.e_stat = e_stat[c];
    	
    	Memory.icode = M_icode[c];
    	Memory.m_icode = m_icode[c];
    	Memory.valA = M_valA[c];
    	Memory.valE = M_valE[c];
    	Memory.m_valA = m_valA[c];
    	Memory.m_valE = m_valE[c];
    	Memory.m_valM = m_valM[c];
    	Memory.dstE = M_dstE[c];
    	Memory.dstM = M_dstM[c];
    	Memory.m_dstE = m_dstE[c];
    	Memory.m_dstM = m_dstM[c];
    	Memory.Bch = M_Bch[c];
    	Memory.PC = M_PC[c];
    	Memory.st = M_st[c];
    	Memory.stat = M_stat[c];
    	Memory.m_stat = m_stat[c];
    	
    	Write.icode = W_icode[c];
    	Write.valE = W_valE[c];
    	Write.valM = W_valM[c];
    	Write.dstE = W_dstE[c];
    	Write.dstM = W_dstM[c];
    	Write.PC = W_PC[c];
    	Write.st = W_st[c];
    	Write.stat = W_stat[c];
    	
    	for (int i = 0; i < maxMem; i++) {
    		if (mem.containsKey(i))
    			mem.put(i, memsave[i][c]);
    	}
    	for (int i = 0; i < maxMem; i++) {
    		if (memory.containsKey(i))
    			memory.put(i, memorysave[i][c]);
    	}
    	for (int i = 0; i < 9; i++)
    		register[i] = registersave[i][c];
    	stack.clear();
    	for (int i = 0; i < maxMem && stacksave[i][c] != null && stacksave[i][c].length() != 0; i++) 
    		stack.add(Integer.parseInt(stacksave[i][c], 16));
    }
    
    static public void runProgram() {
    	while (Write.icode != Icode.halt) {
    		saveCycle(cycle);
    		Write.write();
    		Memory.memory();
    		Execute.execute();
    		Decode.decode();
    		Fetch.fetch();
    		Clock();
    		PCUpdate();
    		totalcycle++;
    		cycle++;
    	}
    	saveCycle(cycle);
    	setCycle(0);
    }
    
    static public void writeFile(String savepath) throws IOException {
    	String path=savepath;
    	if (savepath.indexOf(".txt") == -1)
    		path += ".txt";
        File file=new File(path);
        if(!file.exists())
            file.createNewFile();
        FileWriter initwriter = new FileWriter(path);
        initwriter.write("");
        initwriter.close();
        
        String str = "";
        FileWriter writer; 
        
        for (int c = 0; c <= totalcycle; c++) {
            str = "";
            str += "Cycle_" +c+ "\n";
            str += "--------------------\n";
            str += "FETCH:\n";
            str += "	F_predPC	= 0x" +Pipeline.toHexString(F_predPC[c])+ "\n\n";
            str += "DECODE:\n";
            str += "	D_icode		= 0x" +Integer.toHexString(D_icode[c])+ "\n";
            str += "	D_ifun		= 0x" +Integer.toHexString(D_ifun[c])+ "\n";
            str += "	D_rA		= 0x" +Integer.toHexString(D_rA[c])+ "\n";
            str += "	D_rB		= 0x" +Integer.toHexString(D_rB[c])+ "\n";
            str += "	D_valC		= 0x" +Pipeline.toHexString(D_valC[c])+ "\n";
            str += "	D_valP		= 0x" +Pipeline.toHexString(D_valP[c])+ "\n\n";
            str += "EXECUTE:\n";
            str += "	E_icode		= 0x" +Integer.toHexString(E_icode[c])+ "\n";
            str += "	E_ifun		= 0x" +Integer.toHexString(E_ifun[c])+ "\n";
            str += "	E_valC		= 0x" +Pipeline.toHexString(E_valC[c])+ "\n";
            str += "	E_valA		= 0x" +Pipeline.toHexString(E_valA[c])+ "\n";
            str += "	E_valB		= 0x" +Pipeline.toHexString(E_valB[c])+ "\n";
            str += "	E_dstE		= 0x" +Integer.toHexString(E_dstE[c])+ "\n";
            str += "	E_dstM		= 0x" +Integer.toHexString(E_dstM[c])+ "\n";
            str += "	E_srcA		= 0x" +Integer.toHexString(E_srcA[c])+ "\n";
            str += "	E_srcB		= 0x" +Integer.toHexString(E_srcB[c])+ "\n\n";
            str += "MEMORY:\n";
            str += "	M_icode		= 0x" +Integer.toHexString(M_icode[c])+ "\n";
            str += "	M_Bch		= " +M_Bch[c]+ "\n";
            str += "	M_valE		= 0x" +Pipeline.toHexString(M_valE[c])+ "\n";
            str += "	M_valA		= 0x" +Pipeline.toHexString(M_valA[c])+ "\n";
            str += "	M_dstE		= 0x" +Integer.toHexString(M_dstE[c])+ "\n";
            str += "	M_dstM		= 0x" +Integer.toHexString(M_dstM[c])+ "\n\n";
            str += "WRITE BACK:\n";
            str += "	W_icode		= 0x" +Integer.toHexString(W_icode[c])+ "\n";
            str += "	W_valE		= 0x" +Pipeline.toHexString(W_valE[c])+ "\n";
            str += "	W_valM		= 0x" +Pipeline.toHexString(W_valM[c])+ "\n";
            str += "	W_dstE		= 0x" +Integer.toHexString(W_dstE[c])+ "\n";
            str += "	W_dstM		= 0x" +Integer.toHexString(W_dstM[c])+ "\n\n";
            writer = new FileWriter(path, true); 
            writer.write(str);
        	writer.close();
        }
        setCycle(0);
    }

    static public String getSt(int st) {
    	if (st == 1) return "[bubble]";
    	else if (st == 2) return "[stall]";
    	else return "";
    }

    static public String getInstr(int pc) {
    	if (instr_s.containsKey(pc))
    		return instr_s.get(pc);
    	else if (pc == -1) {
    			return "nop";
    	}
    	return "";
    }
}
