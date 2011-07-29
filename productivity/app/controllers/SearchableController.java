package controllers;

import controllers.deadbolt.Deadbolt;
import play.Logger;
import play.mvc.After;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Util;
import play.mvc.With;
import util.Config;

@With(Deadbolt.class)
public class SearchableController extends Controller {

    @Util
	public static int setRowCount(int rowCount) {
		if (rowCount == 0) {
			rowCount = Integer.parseInt(session.get("rowCount"));
			Logger.debug("SearchableController.setRowCount() got from session: "
					+ rowCount);
		} else {
			session.remove("rowCount");
			session.put("rowCount", new Integer(rowCount));
		}
		return rowCount;
	}

	@Before
	public static void setupRowCount() {
		Logger.debug("SearchableController.setupRowCount() session: " + session.all());
		if (!session.contains("rowCount")) {
			Logger.info("ControllerUtil.setupRowCount() no 'rowCount' in session. Setting default to "
							+ Config.ROW_COUNT);
			session.put("rowCount", new Integer(Config.ROW_COUNT));
		}

	}

	@After
	public static void cleanUp() {
		Logger.setUp("WARN");
	}

}
