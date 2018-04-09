package edu.cuny.brooklyn.io.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleHtmlEditor {
	private final static int BUFFER_SIZE = 1024;

    /*
     * "
     *    Programmers are not to be measured by their ingenuity and their logic but by 
     *    the completeness of their case analysis.
     *                    -- Alan Perlis
     * " 
     * 
     * The following is an example of an analysis about what happens in the application.
     * 
     * Problem: If content changed, then notify a user before the user
     *      - closes this file,
     *      - open a new file
     *      - exit the app
     *     Otherwise, the user loses the changes made to the file. This reasoning may be incomplete.
     * 
     * Solution: we can model the application logic with a state machine (to reason with discipline)
     * 
     * State:  (), (changed), (unchanged), (data lose), ((no data lose))
     *         state in (( )) is designated as desired final state.
     *         state "()" indicates a special state that the program has not stated yet.
     * Action: [app starts], 
     *         [create a new file], 
     *         [open a file], 
     *         [close the file], 
     *         [save the file], 
     *         [edit content], 
     *         [app exits from the menu],
     *         [app exits from "x" button],
     *         [app exits unexpectedly]  
     * Transition: expressed in arrow -->, indicates state change, triggered by an action. We
     *         use this format
     *            (state 1)--[action]-->(state 2)
     *            
     * When at a state, test whether an action can be applied. For instance when at the "()" state, only
     * the [app starts] action can be applied.
     * 
     *             (open a file)                   [app exits unexpectedly]
     *           (create a new file)              [app exits from "x" button]
     *                      +---+   +--------------[app exits from the menu]------------->((no data lose))
     *                      |   |  / .
     *                      V   | /.
     * ()--[app starts]-->(unchanged)--[edit content]-->(changed)--[app exits unexpectedly]-->(data lose)      
     *                      ^                             /|
     *                      |                            / | 
     *                      +----(save the file)--------+  |  
     *                      |                              |    
     *                      +-----------[close the file]---+    
     * 
     * This belongs to application logic. It should be implemented in the model. In this version,
     * unfortunately only part of the logic is in this model (editorContentChanged).
     */
	private boolean editorContentChanged;

	private Path theFilePath;

	public SimpleHtmlEditor() {
		editorContentChanged = false;
		theFilePath = null;
	}

	public Path getTheFile() {
		return theFilePath;
	}

	public void setTheFile(Path theFilePath) {
		this.theFilePath = theFilePath;
	}

	public String readFile() throws FileNotFoundException, IOException {
		// TODO 1: read the content of the HTML file to be edited to a String 
		//         and return the String
		//
		//         when designing the application logic, consider the following
		//         (1) what type of streams should you use, such as, character 
		//             or non-character, buffered or un-buffered? 
		//         (2) if you choose a character stream, what encoding should you
		//             use? 
		return null;
	}

	public void setEditorContentChanged(boolean changed) {
		editorContentChanged = changed;
	}

	public boolean isEditorContentChanged() {
		return editorContentChanged;
	}

	public void saveTheFile(String htmlText) throws FileNotFoundException, IOException {
		// TODO 2: write the content of the file being edited to an HTML file
		//         and return the String
		//
		//         when designing the application logic, consider the following
		//         (1) what type of streams should you use, such as, character 
		//             or non-character, buffered or un-buffered? 
		//         (2) if you choose a character stream, what encoding should you
		//             use? 
	}
}
