package ch.k42.auroraprime.net.DTO;

import java.io.Serializable;
/**
 * ==== SETQUORG Sets a new Quorg on a given Matrix
 * parameter
 *   arg1 : Integer
 *    specifies the number of the Matrix to set
 *   arg2 : String
 *    delivers the classname of the String, as provided in the 'supported_quorgs' list of 'StaticQuorgList'
 *   arg3 : String[] settings for the quorg
 * returns:
 *   wasHandled : true if successful, else false
 *
 *
 * ==== GETUPDATE Returns all active Quorgs in a List
 * parameter:
 *
 * returns:
 *   arg1 : Map<Integer,Class> with all running Quorgs (subclass of Quorg.class)
 *   arg2 : List<Integer> with all available Matrices
 *   wasHandled : true if successful, else false
 *
 *
 *
 */
public class Request implements Serializable {

    public enum Command{
		SETQUORG,
		GETUPDATE
	}


	
	Serializable[] args;
	Command cmd;
	boolean wasHandled;
	
	public Request(Command cmd,Serializable[] args){
		this.args = args;
		this.cmd = cmd;
	}
	
	public boolean wasHandled(){
		return wasHandled;
	}

	public Serializable[] getArgs() {
		return args;
	}

	public Request setArgs(Serializable[] args) {
		this.args = args;
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
