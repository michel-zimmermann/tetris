package tetris.main;

import tetris.params.Params;

public class SysoutLogger implements Logger {
	
	@Override
	public void log(String msg) {
		System.out.println(msg);
	}
	
	@Override
	public void debug(String msg) {
		if (Params.getBoolean(Params.PRINCIPAL_LOG)) {
			log(msg);
		}		
	}
}
