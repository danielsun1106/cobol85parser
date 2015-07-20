/*
Copyright (C) 2015 u.wol@wwu.de

This file is part of cobol85grammar.

cobol85grammar is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

cobol85grammar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with cobol85grammar. If not, see <http://www.gnu.org/licenses/>.
 */

package org.cobol85.runner.impl;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cobol85.Cobol85Lexer;
import org.cobol85.Cobol85Parser;
import org.cobol85.ThrowingErrorListener;
import org.cobol85.applicationcontext.Cobol85GrammarContext;
import org.cobol85.runner.Cobol85ParseTestRunner;

/**
 * VB6 parse runner for JUnit tests.
 */
public class Cobol85ParseTestRunnerImpl implements Cobol85ParseTestRunner {

	private final static Logger LOG = LogManager.getLogger(Cobol85ParseTestRunnerImpl.class);

	@Override
	public void parseDirectory(final File inputDirectory) throws IOException {
		if (inputDirectory.isDirectory() && !inputDirectory.isHidden()) {
			for (final File inputFile : inputDirectory.listFiles()) {
				if (inputFile.isFile() && !inputFile.isHidden()) {
					parseFile(inputFile);
				}
			}
		}
	}

	@Override
	public void parseFile(final File inputFile) throws IOException {
		final File libDirectory = inputFile.getParentFile();

		// preprocess input stream
		final String preProcessedInput = Cobol85GrammarContext.getInstance().getCobol85Preprocessor().process(inputFile,
				libDirectory);

		LOG.info("Parsing file {}.", inputFile.getName());

		// run the lexer
		final Cobol85Lexer lexer = new Cobol85Lexer(new ANTLRInputStream(preProcessedInput));

		lexer.removeErrorListeners();
		lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final Cobol85Parser parser = new Cobol85Parser(tokens);

		parser.removeErrorListeners();
		parser.addErrorListener(ThrowingErrorListener.INSTANCE);

		parser.startRule();
	}
}
