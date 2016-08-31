package org.pensions.util;

import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

public class RadioUtil {

	public static String getTextOf(Toggle toggle) {
		return ((RadioButton)toggle).getText();
	}
	
}
