package testerplugin.handlers;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleListener;
import org.eclipse.ui.console.IPatternMatchListener;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;
import org.eclipse.ui.handlers.HandlerUtil;

import driver.Application;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public SampleHandler() {
		// empty constructor for now
	}

	/**
	 * Output for the Eclipse Plugin Prompts the user first for a file path, Then
	 * prompts for a language type using radio buttons, Then prompt the user for the
	 * percent match for method duplication
	 * 
	 * Prints results from application.driver
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		int percent = 100;
		String language = null;

		// text window to accept a file path to be ran on the Brumby Code Checker
		InputDialog dlg = new InputDialog(HandlerUtil.getActiveShellChecked(event), "  Brumby Code Checker",
				"Enter in File Path to Check", "C:\\Users\\username\\workspace\\TestingDuplicate\\src", null);
		if (dlg.open() == InputDialog.OK) {
			// User clicked OK; save entered path
			String input = dlg.getValue();

			// radio button text window to accept a language type for Brumby Code Checker
			MessageDialog dialog = new MessageDialog(HandlerUtil.getActiveShellChecked(event), "  Brumby Code Checker",
					null, "Select the Lanuage of your code", MessageDialog.QUESTION,
					new String[] { "Java", "C++", "Ada" }, 0);
			// save numeric button position the user clicked on
			int result = dialog.open();

			// user selected Java radio button
			if (result == 0) {
				language = ".java";
			}
			// user selected C++ radio button
			if (result == 1) {
				language = ".cpp";
			}
			// user selected Ada radio button
			if (result == 2) {
				language = ".ada";
			}

			// text window to accept a maximum percent match for the Brumby Code Checker
			InputDialog dlg3 = new InputDialog(HandlerUtil.getActiveShellChecked(event), "  Brumby Code Checker",
					"Enter in Percent Match: ", "75", null);
			if (dlg3.open() == InputDialog.OK) {
				// User clicked OK; save entered path
				percent = Integer.parseInt(dlg3.getValue());
			}

			// call the driver from the Brumby Code Checker java project
			driver.Application brumby = new driver.Application();
			String output = brumby.plugin(input, language, percent) + "\nCompete File Extract Found At: c:/temp/BCC_Output.txt";
			
			// output message
			MessageDialog.openInformation(HandlerUtil.getActiveShellChecked(event), "  Brumby Code Checker Results: ", output);
		}
		return null;
	}

}
