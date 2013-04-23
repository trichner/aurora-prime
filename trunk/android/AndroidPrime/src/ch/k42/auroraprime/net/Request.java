package ch.k42.auroraprime.net;

import java.io.Serializable;

public class Request implements Serializable{
	public enum Command{
		SETQUORG,
		STARTQUORG,
		GETUPDATE
	}
	
	Object arg;
	Command cmd;
	boolean wasHandled;
	
	public Request(Command cmd,Object arg){
		this.arg = arg;
		this.cmd = cmd;
	}
	
	public boolean wasHandled(){
		return wasHandled;
	}

	public Object getArg() {
		return arg;
	}

	public void setArg(Object arg) {
		this.arg = arg;
	}

	public Command getCmd() {
		return cmd;
	}

	public void setCmd(Command cmd) {
		this.cmd = cmd;
	}
	
	
	
}