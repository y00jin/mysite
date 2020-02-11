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
			case "view":
				return new ViewAction();
			case "list":
				return new ListAction();
			default:
				return new ListAction();
			}
		}
	}

}
