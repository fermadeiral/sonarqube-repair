package sorald.processor;

import org.junit.Test;
import org.sonar.java.checks.EqualsOnAtomicClassCheck;
import org.sonar.java.checks.verifier.JavaCheckVerifier;
import sorald.Constants;
import sorald.Main;
import sorald.TestHelper;

public class EqualsOnAtomicClassProcessorTest {

	@Test
	public void test() throws Exception {
		String fileName = "EqualsOnAtomicClass.java";
		String pathToBuggyFile = Constants.PATH_TO_RESOURCES_FOLDER + fileName;
		String pathToRepairedFile = Constants.SORALD_WORKSPACE + "/" + Constants.SPOONED +"/" + fileName;

		JavaCheckVerifier.verify(pathToBuggyFile, new EqualsOnAtomicClassCheck());
		Main.main(new String[]{
				Constants.ARG_SYMBOL + Constants.ARG_ORIGINAL_FILES_PATH, pathToBuggyFile,
				Constants.ARG_SYMBOL + Constants.ARG_RULE_KEYS, "2204",
				Constants.ARG_SYMBOL + Constants.ARG_WORKSPACE, Constants.SORALD_WORKSPACE});
		TestHelper.removeComplianceComments(pathToRepairedFile);
		JavaCheckVerifier.verifyNoIssue(pathToRepairedFile, new EqualsOnAtomicClassCheck());
	}

}
