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
	 * prompts for a language type Prints results form application.driver
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String type = null;
		// text window to accept a file path to be ran on the Brumby Code Checker
		InputDialog dlg = new InputDialog(HandlerUtil.getActiveShellChecked(event), "Enter Path Name",
				"Enter in File Path to Check", "C:\\Users\\username\\workspace\\TestingDuplicate\\src", null);
		if (dlg.open() == InputDialog.OK) {
			// User clicked OK; save entered path
			String input = dlg.getValue();

			// text window to accept a language type for Brumby Code Checker
			InputDialog dlg2 = new InputDialog(HandlerUtil.getActiveShellChecked(event), "Enter Language Type",
					"Enter in a File Language Type (.java .cpp)", ".java", null);
			
			if (dlg2.open() == InputDialog.OK) {
				// User clicked OK; save entered path
				type = dlg2.getValue();
			}

			driver.Application brumby = new driver.Application();
			String results = brumby.plugin(input, type);

			// output message
			MessageDialog.openInformation(HandlerUtil.getActiveShellChecked(event), "Code Duplicates within: " + input,
					results);

		}
		return null;
	}

}