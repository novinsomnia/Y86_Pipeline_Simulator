package y86;

import java.io.IOException;
import java.util.Iterator;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;


public class MainWindow{
	
	final Display display = Display.getDefault();
	final Shell shell=new Shell();
	final Label y86 = new Label(shell, SWT.NONE);
	final Label simu = new Label(shell, SWT.NONE);
	
	final Color bkColor = new Color(Display.getCurrent(),255,255,255);
	final Color blue = new Color(Display.getCurrent(), 65, 155, 249);
	final Color black = new Color(Display.getCurrent(), 0, 0, 0);
	final Font small = new Font(display, "Gulim", 12, SWT.NORMAL);
	final Font small13 = new Font(display, "Gulim", 13, SWT.NORMAL);
	
	final Group instr_group = new Group(shell, 1);
	final Table instr = new Table(instr_group, SWT.READ_ONLY | SWT.V_SCROLL | SWT.H_SCROLL);
	
	final Group pipeline = new Group(shell, SWT.NONE);
	final Label write = new Label(pipeline, SWT.NONE);
	final Label memory = new Label(pipeline, SWT.NONE);
	final Label execute = new Label(pipeline, SWT.NONE);
	final Label decode = new Label(pipeline, SWT.NONE);
	final Label fetch = new Label(pipeline, SWT.NONE);
	final Label writeinstr = new Label(pipeline, SWT.NONE);
	final Label memoryinstr = new Label(pipeline, SWT.NONE);
	final Label executeinstr = new Label(pipeline, SWT.NONE);
	final Label decodeinstr = new Label(pipeline, SWT.NONE);
	final Label fetchinstr = new Label(pipeline, SWT.NONE);
	
	final Label W_stat_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label W_icode_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label W_valE_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label W_valM_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label W_dstE_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label W_dstM_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	
	final Label W_stat = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label W_icode = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label W_valE = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label W_valM = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label W_dstE = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label W_dstM = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label w = new Label(pipeline, SWT.NONE);

	final Label M_stat_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label M_icode_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label M_Cnd_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label M_valE_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label M_valA_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label M_dstE_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label M_dstM_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	
	final Label M_stat = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label M_icode = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label M_Cnd = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label M_valE = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label M_valA = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label M_dstE = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label M_dstM = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label m = new Label(pipeline, SWT.NONE);

	final Label E_stat_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_icode_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_ifun_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_valC_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_valA_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_valB_l = new Label(pipeline, SWT.NONE | SWT.TOP);

	final Label E_stat = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_icode = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_ifun = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_valC = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_valA = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_valB = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label e1 = new Label(pipeline, SWT.NONE);

	final Label E_dstE_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_dstM_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_srcA_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_srcB_l = new Label(pipeline, SWT.NONE | SWT.TOP);

	final Label E_dstE = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_dstM = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_srcA = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label E_srcB = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label e2 = new Label(pipeline, SWT.NONE);	
	
	final Label D_stat_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label D_icode_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label D_ifun_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label D_rA_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label D_rB_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label D_valC_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label D_valP_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	
	final Label D_stat = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label D_icode = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label D_ifun = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label D_rA = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label D_rB = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label D_valC = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label D_valP = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label d = new Label(pipeline, SWT.NONE);
	
	final Label F_predPC_l = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label F_predPC = new Label(pipeline, SWT.NONE | SWT.TOP);
	final Label f = new Label(pipeline, SWT.NONE);
	
	Button load = new Button(shell, SWT.NONE);
	Button save = new Button(shell, SWT.NONE);
	
	final Group set = new Group(shell, 1); //frequency & cycle
	final Slider freqslider = new Slider(set, SWT.NONE);
	final Label frequency = new Label(set, SWT.NONE);
	final Label freql = new Label(set, SWT.NONE);
	final Label cycle = new Label(set, SWT.NONE);
	final Label c = new Label(set, SWT.NONE);
	final Button run = new Button(set, SWT.NONE);
	final Button stop = new Button(set, SWT.NONE);
	final Button next = new Button(set, SWT.NONE);
	final Button back = new Button(set, SWT.NONE);
	final Label gotocycle_l = new Label(set, SWT.NONE);
	final Text gotocycle = new Text(set, SWT.NONE);
	final Button setcycle = new Button(set, SWT.NONE);
	final Button reset = new Button(set, SWT.NONE);
	final ProgressBar progress = new ProgressBar(set, SWT.SMOOTH);
	
	final Group stacktable = new Group(shell, SWT.NONE);
	final Table stack = new Table(stacktable, SWT.READ_ONLY | SWT.V_SCROLL | SWT.H_SCROLL);
	final Label stack_l = new Label(stacktable, SWT.NONE | SWT.CENTER);
	
	final Group reg = new Group(shell, SWT.NULL);
	final Label eax_l = new Label(reg, SWT.NONE);
	final Label ecx_l = new Label(reg, SWT.NONE);
	final Label edx_l = new Label(reg, SWT.NONE);
	final Label ebx_l = new Label(reg, SWT.NONE);
	final Label esp_l = new Label(reg, SWT.NONE);
	final Label ebp_l = new Label(reg, SWT.NONE);
	final Label esi_l = new Label(reg, SWT.NONE);
	final Label edi_l = new Label(reg, SWT.NONE);
	final Text eax = new Text(reg, SWT.READ_ONLY | SWT.CENTER);
	final Text ecx = new Text(reg, SWT.READ_ONLY | SWT.CENTER);
	final Text edx = new Text(reg, SWT.READ_ONLY | SWT.CENTER);
	final Text ebx = new Text(reg, SWT.READ_ONLY | SWT.CENTER);
	final Text esp = new Text(reg, SWT.READ_ONLY | SWT.CENTER);
	final Text ebp = new Text(reg, SWT.READ_ONLY | SWT.CENTER);
	final Text esi = new Text(reg, SWT.READ_ONLY | SWT.CENTER);
	final Text edi = new Text(reg, SWT.READ_ONLY | SWT.CENTER);
	
	final Group CC = new Group(shell, 1);
	final Label cc = new Label(CC, SWT.NONE | SWT.CENTER);
	final Label of_l = new Label(CC, SWT.NONE);
	final Label sf_l = new Label(CC, SWT.NONE);
	final Label zf_l = new Label(CC, SWT.NONE);
	final Text of = new Text(CC, SWT.READ_ONLY | SWT.CENTER);
	final Text sf = new Text(CC, SWT.READ_ONLY | SWT.CENTER);
	final Text zf = new Text(CC, SWT.READ_ONLY | SWT.CENTER);
	
	final Group mem = new Group(shell, 1);
	final Label getmemory_l = new Label(mem, SWT.NONE | SWT.CENTER);
	final Button getmemory = new Button(mem, SWT.NONE);
	final Label mem_addr_l = new Label(mem, SWT.NONE);
	final Label mem_byte_l = new Label(mem, SWT.NONE);
	final Label mem_result_l = new Label(mem, SWT.NONE);
	final Text mem_addr = new Text(mem, SWT.NONE);
	final Text mem_byte = new Text(mem, SWT.NONE);
	final Text mem_result = new Text(mem, SWT.NONE | SWT.READ_ONLY);
		
	int freq; //ms
	int currentcycle = 0;
	boolean running = false;
	String path; //file path
	
	public void setTitle() {
		// TITLE: Y86 PIPELINE SIMULATOR
		y86.setText("Y86 PIPELINE");
		y86.setFont(new Font(display, "Gulim", 26, SWT.NORMAL));
		y86.setBounds(35, 30, 200, 60);
		
		simu.setText("SIMULATOR");
		simu.setFont(new Font(display, "Gulim", 29, SWT.BOLD));
		simu.setBounds(35, 60, 200, 60);
		//TITLE END
	}

	public void setInstructionTable() {
		// TABLE: SHOW INSTRUCTIONS
		instr_group.setBounds(715, 25, 250, 450);
		
		instr.setHeaderVisible(true);
		instr.setLinesVisible(true);
		instr.setBounds(3, 2, 240, 440);
		instr.setFont(small);
		
		TableColumn col1 = new TableColumn(instr, SWT.CENTER);
		col1.setText("ADDR");
		col1.setWidth(50);
		TableColumn col2 = new TableColumn(instr, SWT.NONE);
		col2.setText("INSTRUCTIONS");
		col2.setWidth(300);
		// TABLE INSTRUCTIONS END
	}

	public void setPipeline() {
		pipeline.setBounds(230, 25, 460, 450);

		write.setFont(small);
		write.setText("WRITE BACK:");
		write.setBounds(10, 15, 100, 20);		
		memory.setFont(small);
		memory.setText("MEMORY:");
		memory.setBounds(10, 90, 100, 20);	
		execute.setFont(small);
		execute.setText("EXECUTE:");
		execute.setBounds(10, 165, 100, 20);	
		decode.setFont(small);
		decode.setText("DECODE:");
		decode.setBounds(10, 290, 100, 20);
		fetch.setFont(small);
		fetch.setText("FETCH:");
		fetch.setBounds(10, 365, 100, 20);
		
		writeinstr.setFont(small);
		writeinstr.setBounds(120, 15, 200, 20);
		memoryinstr.setFont(small);
		memoryinstr.setBounds(120, 90, 200, 20);	
		executeinstr.setFont(small);
		executeinstr.setBounds(120, 165, 200, 20);	
		decodeinstr.setFont(small);
		decodeinstr.setBounds(120, 290, 200, 20);
		fetchinstr.setFont(small);
		fetchinstr.setBounds(120, 365, 200, 20);
		
	}
	public void setWrite() {
		W_stat_l.setText("stat");
		W_stat_l.setFont(small);
		W_stat_l.setBounds(30, 39, 50, 48);
		W_icode_l.setText("icode");
		W_icode_l.setFont(small);
		W_icode_l.setBounds(62, 39, 50, 48);
		W_valE_l.setText("valE");
		W_valE_l.setFont(small);
		W_valE_l.setBounds(150, 39, 50, 48);
		W_valM_l.setText("valM");
		W_valM_l.setFont(small);
		W_valM_l.setBounds(245, 39, 50, 48);
		W_dstE_l.setText("dstE");
		W_dstE_l.setFont(small);
		W_dstE_l.setBounds(329, 39, 50, 48);
		W_dstM_l.setText("dstM");
		W_dstM_l.setFont(small);
		W_dstM_l.setBounds(359, 39, 50, 48);
		
		W_stat.setText("AOK");
		W_stat.setFont(small);
		W_stat.setBounds(30, 60, 50, 48);
		W_icode.setText("0x0");
		W_icode.setFont(small);
		W_icode.setBounds(70, 60, 50, 48);
		W_valE.setText("0x00000000");
		W_valE.setFont(small);
		W_valE.setBounds(130, 60, 100, 48);
		W_valM.setText("0x00000000");
		W_valM.setFont(small);
		W_valM.setBounds(225, 60, 100, 48);
		W_dstE.setText("0x8");
		W_dstE.setFont(small);
		W_dstE.setBounds(333, 60, 50, 48);
		W_dstM.setText("0x8");
		W_dstM.setFont(small);
		W_dstM.setBounds(363, 60, 50, 48);
		
		w.setImage(new Image(display, Program.class.getResourceAsStream("image/write.png")));
		w.setBounds(10, 35, 420, 45);
	}
	public void setMemory() {
		M_stat_l.setText("stat");
		M_stat_l.setFont(small);
		M_stat_l.setBounds(30, 114, 50, 48);
		M_icode_l.setText("icode");
		M_icode_l.setFont(small);
		M_icode_l.setBounds(62, 114, 50, 48);
		M_Cnd_l.setText("Cnd");
		M_Cnd_l.setFont(small);
		M_Cnd_l.setBounds(106, 114, 50, 48);
		M_valE_l.setText("valE");
		M_valE_l.setFont(small);
		M_valE_l.setBounds(176, 114, 50, 48);
		M_valA_l.setText("valA");
		M_valA_l.setFont(small);
		M_valA_l.setBounds(276, 114, 50, 48);
		M_dstE_l.setText("dstE");
		M_dstE_l.setFont(small);
		M_dstE_l.setBounds(362, 114, 50, 48);
		M_dstM_l.setText("dstM");
		M_dstM_l.setFont(small);
		M_dstM_l.setBounds(392, 114, 50, 48);
		
		M_stat.setText("AOK");
		M_stat.setFont(small);
		M_stat.setBounds(30, 135, 50, 48);
		M_icode.setText("0x0");
		M_icode.setFont(small);
		M_icode.setBounds(69, 135, 50, 48);
		M_Cnd.setText("false");
		M_Cnd.setFont(small);
		M_Cnd.setBounds(106, 135, 100, 48);
		M_valE.setText("0x00000000");
		M_valE.setFont(small);
		M_valE.setBounds(156, 135, 100, 48);
		M_valA.setText("0x00000000");
		M_valA.setFont(small);
		M_valA.setBounds(256, 135, 100, 48);
		M_dstE.setText("0x8");
		M_dstE.setFont(small);
		M_dstE.setBounds(365, 135, 50, 48);
		M_dstM.setText("0x8");
		M_dstM.setFont(small);
		M_dstM.setBounds(397, 135, 50, 48);

		m.setImage(new Image(display, Program.class.getResourceAsStream("image/memory.png")));
		m.setBounds(10, 110, 420, 45);
	}
	public void setExecute() {
		E_stat_l.setText("stat");
		E_stat_l.setFont(small);
		E_stat_l.setBounds(30, 189, 50, 48);

		E_icode_l.setText("icode");
		E_icode_l.setFont(small);
		E_icode_l.setBounds(62, 189, 50, 48);
		E_ifun_l.setText("ifun");
		E_ifun_l.setFont(small);
		E_ifun_l.setBounds(103, 189, 50, 48);
		E_valC_l.setText("valC");
		E_valC_l.setFont(small);
		E_valC_l.setBounds(173, 189, 50, 48);
		E_valA_l.setText("valA");
		E_valA_l.setFont(small);
		E_valA_l.setBounds(268, 189, 50, 48);
		E_valB_l.setText("valB");
		E_valB_l.setFont(small);
		E_valB_l.setBounds(363, 189, 50, 48);
		
		E_stat.setText("AOK");
		E_stat.setFont(small);
		E_stat.setBounds(30, 210, 50, 48);
		E_icode.setText("0x0");
		E_icode.setFont(small);
		E_icode.setBounds(66, 210, 50, 48);
		E_ifun.setText("0x0");
		E_ifun.setFont(small);
		E_ifun.setBounds(103, 210, 50, 48);
		E_valC.setText("0x00000000");
		E_valC.setFont(small);
		E_valC.setBounds(153, 210, 100, 48);
		E_valA.setText("0x00000000");
		E_valA.setFont(small);
		E_valA.setBounds(248, 210, 100, 48);
		E_valB.setText("0x00000000");
		E_valB.setFont(small);
		E_valB.setBounds(343, 210, 100, 48);
		
		e1.setImage(new Image(display, Program.class.getResourceAsStream("image/execute1.png")));
		e1.setBounds(10, 185, 420, 45);
		
		E_dstE_l.setText("dstE");
		E_dstE_l.setFont(small);
		E_dstE_l.setBounds(30, 239, 50, 48);
		E_dstM_l.setText("dstM");
		E_dstM_l.setFont(small);
		E_dstM_l.setBounds(64, 239, 50, 48);
		E_srcA_l.setText("srcA");
		E_srcA_l.setFont(small);
		E_srcA_l.setBounds(100, 239, 50, 48);
		E_srcB_l.setText("srcB");
		E_srcB_l.setFont(small);
		E_srcB_l.setBounds(136, 239, 50, 48);
		
		E_dstE.setText("0x8");
		E_dstE.setFont(small);
		E_dstE.setBounds(31, 260, 50, 48);
		E_dstM.setText("0x8");
		E_dstM.setFont(small);
		E_dstM.setBounds(66, 260, 50, 48);
		E_srcA.setText("0x8");
		E_srcA.setFont(small);
		E_srcA.setBounds(103, 260, 50, 48);
		E_srcB.setText("0x8");
		E_srcB.setFont(small);
		E_srcB.setBounds(139, 260, 50, 48);
		
		e2.setImage(new Image(display, Program.class.getResourceAsStream("image/execute2.png")));
		e2.setBounds(10, 235, 420, 45);
	}
	public void setDecode() {
		D_stat_l.setText("stat");
		D_stat_l.setFont(small);
		D_stat_l.setBounds(30, 314, 50, 48);
		D_icode_l.setText("icode");
		D_icode_l.setFont(small);
		D_icode_l.setBounds(62, 314, 50, 48);
		D_ifun_l.setText("ifun");
		D_ifun_l.setFont(small);
		D_ifun_l.setBounds(103, 314, 50, 48);
		D_rA_l.setText("rA");
		D_rA_l.setFont(small);
		D_rA_l.setBounds(156, 314, 50, 48);
		D_rB_l.setText("rB");
		D_rB_l.setFont(small);
		D_rB_l.setBounds(188, 314, 50, 48);
		D_valC_l.setText("valC");
		D_valC_l.setFont(small);
		D_valC_l.setBounds(248, 314, 50, 48);
		D_valP_l.setText("valP");
		D_valP_l.setFont(small);
		D_valP_l.setBounds(338, 314, 50, 48);
		
		D_stat.setText("AOK");
		D_stat.setFont(small);
		D_stat.setBounds(30, 335, 50, 48);
		D_icode.setText("0x0");
		D_icode.setFont(small);
		D_icode.setBounds(66, 335, 50, 48);
		D_ifun.setText("0x0");
		D_ifun.setFont(small);
		D_ifun.setBounds(103, 335, 50, 48);
		D_rA.setText("0x8");
		D_rA.setFont(small);
		D_rA.setBounds(153, 335, 50, 48);
		D_rB.setText("0x8");
		D_rB.setFont(small);
		D_rB.setBounds(185, 335, 50, 48);
		D_valC.setText("0x00000000");
		D_valC.setFont(small);
		D_valC.setBounds(223, 335, 100, 48);
		D_valP.setText("0x00000000");
		D_valP.setFont(small);
		D_valP.setBounds(318, 335, 100, 48);
		
		d.setImage(new Image(display, Program.class.getResourceAsStream("image/decode.png")));
		d.setBounds(10, 310, 420, 45);
	}
	public void setFetch() {
		F_predPC_l.setText("predPC");
		F_predPC_l.setFont(small);
		F_predPC_l.setBounds(105, 389, 50, 48);
		
		F_predPC.setText("0x0");
		F_predPC.setFont(small);
		F_predPC.setBounds(115, 410, 50, 48);
		
		f.setImage(new Image(display, Program.class.getResourceAsStream("image/fetch.png")));
		f.setBounds(10, 385, 420, 45);
	}
	
	
	public void setValue() {
        if (Write.st != 0)
           	writeinstr.setForeground(blue);
        else 
        	writeinstr.setForeground(black);
		writeinstr.setText(Pipeline.getSt(Write.st) + " " + Pipeline.getInstr(Write.PC));
		 W_stat.setText(Write.stat);
         W_icode.setText("0x" +Integer.toHexString(Write.icode));
         W_valE.setText("0x" +Pipeline.toHexString(Write.valE));
         W_valM.setText("0x" +Pipeline.toHexString(Write.valM));
         W_dstE.setText("0x" +Integer.toHexString(Write.dstE));
         W_dstM.setText("0x" +Integer.toHexString(Write.dstM)); 
      
         if (Memory.st != 0)
           	memoryinstr.setForeground(blue);
         else 
        	memoryinstr.setForeground(black);
 		memoryinstr.setText(Pipeline.getSt(Memory.st) + " " + Pipeline.getInstr(Memory.PC));
         M_stat.setText(Memory.stat);
         M_icode.setText("0x" +Integer.toHexString(Memory.icode));
         M_Cnd.setText(Boolean.toString(Memory.Bch));
         M_valE.setText("0x" +Pipeline.toHexString(Memory.valE));
         M_valA.setText("0x" +Pipeline.toHexString(Memory.valA));
         M_dstE.setText("0x" +Integer.toHexString(Memory.dstE));
         M_dstM.setText("0x" +Integer.toHexString(Memory.dstM));
         
         if (Execute.st != 0)
          	executeinstr.setForeground(blue);
         else 
        	executeinstr.setForeground(black);
 		executeinstr.setText(Pipeline.getSt(Execute.st) + " " + Pipeline.getInstr(Execute.PC));
         E_stat.setText(Execute.stat);
         E_icode.setText("0x" +Integer.toHexString(Execute.icode));
         E_ifun.setText("0x" +Integer.toHexString(Execute.ifun));
         E_valC.setText("0x" +Pipeline.toHexString(Execute.valC));
         E_valA.setText("0x" +Pipeline.toHexString(Execute.valA));
         E_valB.setText("0x" +Pipeline.toHexString(Execute.valB));
         E_dstE.setText("0x" +Integer.toHexString(Execute.dstE));
         E_dstM.setText("0x" +Integer.toHexString(Execute.dstM));
         E_srcA.setText("0x" +Integer.toHexString(Execute.srcA));
         E_srcB.setText("0x" +Integer.toHexString(Execute.srcB));
         
         if (Decode.st != 0)
         	decodeinstr.setForeground(blue);
         else 
        	decodeinstr.setForeground(black);
 		decodeinstr.setText(Pipeline.getSt(Decode.st) + " " + Pipeline.getInstr(Decode.PC));
         D_stat.setText(Decode.stat);
         D_icode.setText("0x" +Integer.toHexString(Decode.icode));
         D_ifun.setText("0x" +Integer.toHexString(Decode.ifun));
         D_rA.setText("0x" +Integer.toHexString(Decode.rA));
         D_rB.setText("0x" +Integer.toHexString(Decode.rB));
         D_valC.setText("0x" +Pipeline.toHexString(Decode.valC));
         D_valP.setText("0x" +Pipeline.toHexString(Decode.valP));
         
        if (Fetch.st != 0)
        	fetchinstr.setForeground(blue);
        else 
        	fetchinstr.setForeground(black);
 		fetchinstr.setText(Pipeline.getSt(Fetch.st) + " " + Pipeline.getInstr(Fetch.PC));
         F_predPC.setText("0x" +Integer.toHexString(Fetch.predPC));
         
         c.setText(Integer.toString(currentcycle));
         progress.setSelection(currentcycle);
         
         eax.setText("0x" +Pipeline.toHexString(Pipeline.register[0]));
         ecx.setText("0x" +Pipeline.toHexString(Pipeline.register[1]));
         edx.setText("0x" +Pipeline.toHexString(Pipeline.register[2]));
         ebx.setText("0x" +Pipeline.toHexString(Pipeline.register[3]));
         esp.setText("0x" +Pipeline.toHexString(Pipeline.register[4]));
         ebp.setText("0x" +Pipeline.toHexString(Pipeline.register[5]));
         esi.setText("0x" +Pipeline.toHexString(Pipeline.register[6]));
         edi.setText("0x" +Pipeline.toHexString(Pipeline.register[7]));
         
         if (Execute.ZF == true)
        	 zf.setText("1");
         else
        	 zf.setText("0");
         if (Execute.SF == true)
        	 sf.setText("1");
         else
        	 sf.setText("0");
         if (Execute.OF == true)
        	 of.setText("1");
         else
        	 of.setText("0");
         
        stack.removeAll();
     	Iterator<Integer> iterator = Pipeline.stack.iterator();
        while (iterator.hasNext()) {
        	TableItem item = new TableItem(stack, SWT.NONE);
        	int addr = iterator.next();
        	if (addr == Pipeline.register[4])
            	item.setBackground(new Color(Display.getCurrent(), 220, 220, 220));
        	if (Pipeline.mem.containsKey(addr))
        		item.setText(new String[] {"0x"+ Integer.toHexString(addr), "0x" +Pipeline.toHexString(Pipeline.mem.get(addr))});
        	else
        		item.setText(new String[] {"0x"+ Integer.toHexString(addr), "0x00000000"});
         }
        
		instr.removeAll();
		for (int i = 0; Pipeline.instructions_addr[i] != null && Pipeline.instructions_addr[i].length() != 0; i++) {
			TableItem item = new TableItem(instr, SWT.NONE);
			int addr = Integer.parseInt(Pipeline.instructions_addr[i].substring(2), 16);
			if (addr == Fetch.PC || addr == Decode.PC || addr == Execute.PC || addr == Memory.PC || addr == Write.PC)
				item.setForeground(blue);
			item.setText(new String[] {Pipeline.instructions_addr[i], Pipeline.instructions[i]});
		}
         
	}

	public void setFreq() {
		frequency.setText("FREQUENCY: 	      hz");
		frequency.setBounds(8, 10, 160, 30);
		frequency.setFont(small13);
		
		freqslider.setMinimum(1);
		freqslider.setMaximum(60);
		freqslider.setSelection(5);
		freq = 1000/5;
		freql.setFont(small13);
		freql.setText("5");
		freqslider.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				freql.setText("" + freqslider.getSelection());
				freq = 1000 / freqslider.getSelection();
			}
		});
		freql.setBounds(110, 10, 60, 30);
		freqslider.setBounds(10, 26, 150, 20);
	}
	public void setCycle(){
		cycle.setText("CURRENT CYCLE:");
		cycle.setBounds(8, 90, 160, 30);
		cycle.setFont(small13);
		
		c.setText("0");
		c.setBounds(130, 90, 30, 30);
		c.setFont(small13);
		
		progress.setBounds(10, 103, 150, 20);
		progress.setMinimum(0);
	}
	
	public void setloadFile() {
		load.setText("LOAD");
		load.setBounds(30, 110, 90, 30);
		load.setFont(small);
		load.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				if (running == true)
					running = false;
                FileDialog dlg = new FileDialog(shell, SWT.OPEN);
                dlg.setFilterExtensions(new String[] {"*.yo"});
                try {
                	try {
                		path = dlg.open();
                	} catch(Exception e1) {
                		e1.printStackTrace();
                	}
                	if(path != null) {
                		Pipeline.init();
                		Pipeline.readFile(path);
						Pipeline.runProgram();
						progress.setMaximum(Pipeline.totalcycle);
						currentcycle = 0;
						setValue();
                	}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
		});
	}
	public void setSaveFile() {
		save.setText("SAVE");
		save.setBounds(120, 110, 90, 30);
		save.setFont(small);
		save.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				if (running == true)
					running = false;
                try {
                	if (Pipeline.load == false) {
                		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                		dialog.setText("ERROR");
                        dialog.setMessage("Please load a file first!");
                        dialog.open();
                	}
                	else {
                		FileDialog dialog = new FileDialog(shell,SWT.SAVE);
                		String fileName = dialog.open();
	                	if (fileName != null && fileName.length() != 0) {
	                		Pipeline.writeFile(fileName);
	                	}
                	}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
		});
	}
	
	public void setRun() {
		run.setText("RUN");
		run.setFont(small);
		run.setBounds(3, 55, 85, 20);
		run.setSelection(false);

		run.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				if (running == true)
					return;
				running = true;
				if (Pipeline.load == true) {
					Runnable time = new Runnable(){  
					    public void run(){
					    	if (running == true) {
						    	if (currentcycle <= Pipeline.totalcycle) {
							    	Pipeline.setCycle(currentcycle);
							    	setValue();
							    	currentcycle++;
							    	display.timerExec(freq, this);
							    }
								else {
			            			MessageBox dialog = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
				            		dialog.setText("INFORMATION");
				                    dialog.setMessage("End of the program.");
				                    dialog.open();
				                    running = false;
				                    currentcycle--;
				                    return;
								}
					    	}
					    }
					};
					if (currentcycle <= Pipeline.totalcycle && running) {
						display.timerExec(freq,time);
					}
            	}
            	else{
            		running = false;
            		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
            		dialog.setText("ERROR");
                    dialog.setMessage("Please load a file first!");
                    dialog.open();
            	}
			}
		});
	}
	public void setStop() {
		stop.setText("STOP");
		stop.setFont(small);
		stop.setBounds(82, 55, 85, 20);
		stop.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				running = false;
				currentcycle--;
			}
		});
	}
	public void setNext() {
		next.setText("NEXT");
		next.setFont(small);
		next.setBounds(82, 125, 85, 20);
		next.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				if (running == true)
					return;
				if (Pipeline.load == true) {
					if (currentcycle < Pipeline.totalcycle) {
						currentcycle++;
						Pipeline.setCycle(currentcycle);
						setValue();
					}
					else {
            			MessageBox dialog = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
	            		dialog.setText("INFORMATION");
	                    dialog.setMessage("End of the program.");
	                    dialog.open();
					}
            	}
            	else{
            		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
            		dialog.setText("ERROR");
                    dialog.setMessage("Please load a file first!");
                    dialog.open();
            	}
			}
		});
	}
	public void setBack() {
		back.setText("BACK");
		back.setFont(small);
		back.setBounds(3, 125, 85, 20);
		back.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				if (running == true)
					return;
				if (Pipeline.load == true) {
					if (currentcycle > 0) {
						currentcycle--;
						Pipeline.setCycle(currentcycle);
						setValue();
					}
					else {
            			MessageBox dialog = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
	            		dialog.setText("INFORMATION");
	                    dialog.setMessage("Start of the program.");
	                    dialog.open();
					}
            	}
            	else{
            		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
            		dialog.setText("ERROR");
                    dialog.setMessage("Please load a file first!");
                    dialog.open();
            	}
			}
		});
	}
	public void setGoToCycle() {
		gotocycle_l.setFont(small13);
		gotocycle_l.setText("JUMP TO CYCLE:");
		gotocycle_l.setBounds(8, 159, 160, 30);
		
		gotocycle.setFont(small);
		gotocycle.setBounds(130, 159, 25, 15);
		gotocycle.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				boolean b = ("0123456789".indexOf(e.text) >= 0);
				e.doit = b;
			}
		});
		
		setcycle.setFont(small);
		setcycle.setText("SET");
		setcycle.setBounds(3, 182, 85, 20);
		setcycle.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				if (running == true) {
					running = false;
				}
				if (Pipeline.load == true) {
					if (gotocycle.getText().length() != 0 && Integer.parseInt(gotocycle.getText()) >= 0 && Integer.parseInt(gotocycle.getText()) <= Pipeline.totalcycle) {
						currentcycle = Integer.parseInt(gotocycle.getText());
						Pipeline.setCycle(currentcycle);
						setValue();
					}
					else if (gotocycle.getText().length() == 0) {
            			MessageBox dialog = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
	            		dialog.setText("INFORMATION");
	                    dialog.setMessage("Please enter the cycle number.");
	                    dialog.open();
					}
					else {
            			MessageBox dialog = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);
	            		dialog.setText("INFORMATION");
	                    dialog.setMessage("Out of range.");
	                    dialog.open();
					}
            	}
            	else{
            		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
            		dialog.setText("ERROR");
                    dialog.setMessage("Please load a file first!");
                    dialog.open();
            	}
			}
		});
		
		reset.setFont(small);
		reset.setText("RESET");
		reset.setBounds(82, 182, 85, 20);
		reset.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				if (running == true)
					running = false;
				if (Pipeline.load == true) {
					gotocycle.setText("0");
					currentcycle = 0;
					Pipeline.setCycle(currentcycle);
					setValue();
				}    
            	else{
            		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
            		dialog.setText("ERROR");
                    dialog.setMessage("Please load a file first!");
                    dialog.open();
            	}
			}
		});
	}
	
	public void setStack() {
		stacktable.setBounds(32, 390, 175, 220);
		
		stack_l.setFont(small);
		stack_l.setText("[ STACK ]");
		stack_l.setBounds(8, 13, 155, 20);
		
		stack.setHeaderVisible(true);
		stack.setBounds(3, 35, 165, 175);
		stack.setFont(small);
		
		TableColumn col1 = new TableColumn(stack, SWT.CENTER);
		col1.setText("ADDR");
		col1.setWidth(50);
		TableColumn col2 = new TableColumn(stack, SWT.CENTER);
		col2.setText("CONTENT");
		col2.setWidth(115);	
		
	}
	
	public void setReg() {
		reg.setBounds(345, 490, 345, 120);
		
		eax_l.setFont(small);
		eax_l.setText("%eax:");
		eax_l.setBounds(10, 18, 50, 30);	
		ecx_l.setFont(small);
		ecx_l.setText("%ecx:");
		ecx_l.setBounds(10, 38, 50, 30);
		edx_l.setFont(small);
		edx_l.setText("%edx:");
		edx_l.setBounds(10, 58, 50, 30);
		ebx_l.setFont(small);
		ebx_l.setText("%ebx:");
		ebx_l.setBounds(10, 78, 50, 30);
		esp_l.setFont(small);
		esp_l.setText("%esp:");
		esp_l.setBounds(170, 18, 50, 30);
		ebp_l.setFont(small);
		ebp_l.setText("%ebp:");
		ebp_l.setBounds(170, 38, 50, 30);
		esi_l.setFont(small);
		esi_l.setText("%esi:");
		esi_l.setBounds(170, 58, 50, 30);	
		edi_l.setFont(small);
		edi_l.setText("%edi:");
		edi_l.setBounds(170, 78, 50, 30);
		
		eax.setBounds(60, 18, 100, 14);
		eax.setText("0x00000000");
		eax.setFont(small);
		ecx.setBounds(60, 38, 100, 14);
		ecx.setText("0x00000000");
		ecx.setFont(small);
		edx.setBounds(60, 58, 100, 14);
		edx.setText("0x00000000");
		edx.setFont(small);
		ebx.setBounds(60, 78, 100, 14);
		ebx.setText("0x00000000");
		ebx.setFont(small);
		esp.setBounds(220, 18, 100, 14);
		esp.setText("0x00000000");
		esp.setFont(small);
		ebp.setBounds(220, 38, 100, 14);
		ebp.setText("0x00000000");
		ebp.setFont(small);
		esi.setBounds(220, 58, 100, 14);
		esi.setText("0x00000000");
		esi.setFont(small);
		edi.setBounds(220, 78, 100, 14);
		edi.setText("0x00000000");
		edi.setFont(small);		
	}
	public void setCC() {
		CC.setBounds(230, 490, 105, 120);
		
		cc.setFont(small);
		cc.setText("[ CC ]");
		cc.setBounds(8, 18, 85, 16);
		
		zf_l.setFont(small);
		zf_l.setText("ZF:");
		zf_l.setBounds(10, 38, 50, 20);
		sf_l.setFont(small);
		sf_l.setText("SF:");
		sf_l.setBounds(10, 58, 50, 20);
		of_l.setFont(small);
		of_l.setText("OF:");
		of_l.setBounds(10, 78, 50, 20);
		
		zf.setFont(small);
		zf.setText("0");
		zf.setBounds(45, 38, 43, 14);
		sf.setFont(small);
		sf.setText("0");
		sf.setBounds(45, 58, 43, 14);
		of.setFont(small);
		of.setText("0");
		of.setBounds(45, 78, 43, 14);
		
	}
	public void setMem() {
		mem.setBounds(715, 490, 250, 120);
		
		getmemory_l.setFont(small);
		getmemory_l.setText("[ CHECK MEMORY ]");
		getmemory_l.setBounds(10, 15, 230, 16);
		
		mem_addr_l.setFont(small);
		mem_addr_l.setText("FROM ADDRESS: 0x");
		mem_addr_l.setBounds(10, 38, 150, 20);
		
		mem_byte_l.setFont(small);
		mem_byte_l.setText("LENGTH:        BYTES");
		mem_byte_l.setBounds(10, 60, 150, 20);
		
		mem_result_l.setFont(small);
		mem_result_l.setText("RESULT: 0x");
		mem_result_l.setBounds(10, 81, 150, 20);
		
		mem_addr.setFont(small);
		mem_addr.addVerifyListener(new VerifyListener() {
			public void verifyText(VerifyEvent e) {
				boolean b = ("0123456789abcdefABCDEF".indexOf(e.text) >= 0);
				e.doit = b;
			}
		});
		mem_addr.setBounds(135, 36, 93, 15);
		
		mem_byte.setFont(small);
		mem_byte.setText("1");
		mem_byte.setBounds(67, 59, 25, 15);
		
		mem_result.setFont(small);
		mem_result.setBounds(87, 80, 140, 15);
		
		getmemory.setFont(small);
		getmemory.setText("CHECK");
		getmemory.setBounds(150, 56, 85, 22);
		getmemory.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				if (running == true)
					running = false;
				if (Pipeline.load == true) {
					if (mem_addr.getText() == null || mem_addr.getText().length() == 0) {
	            		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
	            		dialog.setText("ERROR");
	                    dialog.setMessage("Please enter the initial address!");
	                    dialog.open();
	                    return;
					}
					if (mem_byte.getText() == null || mem_byte.getText().length() == 0) {
						mem_byte.setText("1");
					}
					int addr = Integer.parseInt(mem_addr.getText(), 16);
					int len = Integer.parseInt(mem_byte.getText());
					if (Pipeline.memory.get(addr) == null) {
	            		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
	            		dialog.setText("ERROR");
	                    dialog.setMessage("Out of memory!");
	                    dialog.open();
					}
					else {
						String rst = "";
						int i;
						for (i = 0, addr = addr + len - 1; i < len; i++) {
							if (Pipeline.memory.containsKey(addr) == true) {
								rst += Pipeline.to2byteString(Pipeline.memory.get(addr));
								addr--;
							}
							else {
			            		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
			            		dialog.setText("ERROR");
			                    dialog.setMessage("Out of memory!");
			                    dialog.open();
			                    break;
							}
						}
						mem_result.setText(rst);
					}
            	}
            	else{
            		MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
            		dialog.setText("ERROR");
                    dialog.setMessage("Please load a file first!");
                    dialog.open();
            	}
			}
		});
		
	}
	
	public MainWindow() throws IOException {
		// TODO Auto-generated method stub
		
		shell.setSize(1000, 700);
		shell.setBackground(bkColor);
		shell.setText("Y86 PIPELINE SIMULATOR ©YIJING XIA 2015");
		
		setTitle();
		setInstructionTable();
		
		setPipeline();
		setWrite();
		setMemory();
		setExecute();
		setDecode();	
		setFetch();	

		set.setBounds(32, 150, 175, 225);
		setFreq();
		setCycle();
		
		setloadFile();
		setSaveFile();
		
		setRun();
		setStop();
		setNext();
		setBack();
		setGoToCycle();
		
		setStack();
		
		setCC();
		setReg();
		setMem();
		
		
		shell.layout();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		
	}

}






