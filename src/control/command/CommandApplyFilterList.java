package control.command;

import control.FilterSelectorHandler;
import control.command.exceptions.CommandConstructionException;
import control.command.exceptions.CommandExecutionException;


public class CommandApplyFilterList extends HandlerCommand {

    public void doExecute() throws CommandExecutionException {
    	if(!(this.getHandler() instanceof FilterSelectorHandler))
            throw new CommandExecutionException(this);
    	
    	FilterSelectorHandler handler = (FilterSelectorHandler)this.getHandler();
		handler.getDialog().setVisible(false);
		try {
			
			String[] filterNames = handler.getListaSeleccionados().getItems();
			FilterListCommand buildCommand = CommandFactory.buildCommand(filterNames, frame, handler.getChkDefaults().getState());
			buildCommand.execute();
			
		} catch (CommandConstructionException e) {
			handler.closeAll(e);
		} catch (CommandExecutionException e) {
			handler.closeAll(e);
		}
		
		handler.getDialog().dispose();
    }
}
