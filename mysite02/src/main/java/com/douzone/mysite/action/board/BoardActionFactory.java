package com.douzone.mysite.action.board;

import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		if (actionName == null) {
			return new ListAction();
		} else {
			switch (actionName) {
			case "view": return new ViewAction();
			case "list": return new ListAction();
			case "writeform": return new WriteFormAction();
			case "write": return new WriteAction();
			case "update": return new UpdateAction();
			case "updateform": return new UpdateFormAction();
			case "delete": return new DeleteAction();
			case "replyform": return new ReplyFormAction();
			case "reply":	return new ReplyAction();
			default:
				return new ListAction();
			}
		}
	}

}
