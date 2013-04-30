package ch.k42.auroraprime.net.DTO;

import java.io.Serializable;

public class Request implements Serializable {

    public enum Command{
		SETQUORG,
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

	public Request setArg(Object arg) {
		this.arg = arg;
        return this;
	}

	public Command getCmd() {
		return cmd;
	}

	public Request setCmd(Command cmd) {
		this.cmd = cmd;
        return this;
	}

    public Request setHandled(boolean wasHandled) {
        this.wasHandled = wasHandled;
        return this;
    }
}
