package io.proleap.cobol.preprocessor.tandem;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import io.proleap.cobol.applicationcontext.CobolGrammarContext;
import io.proleap.cobol.applicationcontext.CobolGrammarContextFactory;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class IdentificationDivisionTest {

	@Test
	public void test() throws Exception {
		CobolGrammarContextFactory.configureDefaultApplicationContext();

		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/preprocessor/tandem/IdentificationDivision.cbl");
		final File libDirectory = inputFile.getParentFile();
		final String preProcessedInput = CobolGrammarContext.getInstance().getCobolPreprocessor().process(inputFile,
				libDirectory, CobolSourceFormatEnum.TANDEM);

		final File expectedFile = new File(
				"src/test/resources/io/proleap/cobol/preprocessor/tandem/IdentificationDivision.cbl.preprocessed");
		final String expected = FileUtils.readFileToString(expectedFile);
		assertEquals(expected, preProcessedInput);
	}
}