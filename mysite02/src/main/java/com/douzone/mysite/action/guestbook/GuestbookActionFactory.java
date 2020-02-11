package com.douzone.mysite.action.guestbook;

import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch(actionName) {
		case "add": return new AddAction();
		case "deleteform": return new DeleteFormAction();
		case "delete": return new DeleteAction();
		case "list": return new ListAction();
			default: return new ListAction();
		}
	}

}
