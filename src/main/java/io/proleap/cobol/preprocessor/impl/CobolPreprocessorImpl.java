/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.proleap.cobol.preprocessor.CobolPreprocessor;
import io.proleap.cobol.preprocessor.sub.line.CobolCleanLinesSubPreprocessor;
import io.proleap.cobol.preprocessor.sub.line.CobolMarkCommentEntriesSubPreprocessor;
import io.proleap.cobol.preprocessor.sub.line.CobolNormalizeLinesSubPreprocessor;
import io.proleap.cobol.preprocessor.sub.line.impl.CobolCleanLinesSubPreprocessorImpl;
import io.proleap.cobol.preprocessor.sub.line.impl.CobolMarkCommentEntriesSubPreprocessorImpl;
import io.proleap.cobol.preprocessor.sub.line.impl.CobolNormalizeLinesSubPreprocessorImpl;
import io.proleap.cobol.preprocessor.sub.parser.CobolParserPreprocessor;
import io.proleap.cobol.preprocessor.sub.parser.impl.CobolParserPreprocessorImpl;

public class CobolPreprocessorImpl implements CobolPreprocessor {

	private final static Logger LOG = LogManager.getLogger(CobolPreprocessorImpl.class);

	@Override
	public String process(final File cobolFile, final File libDirectory, final CobolSourceFormatEnum format)
			throws IOException {
		return process(cobolFile, libDirectory, format, null);
	}

	@Override
	public String process(final File cobolFile, final File libDirectory, final CobolSourceFormatEnum format,
			final CobolDialect dialect) throws IOException {
		LOG.info("Preprocessing file {}.", cobolFile.getName());

		final InputStream inputStream = new FileInputStream(cobolFile);
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader bufferedInputStreamReader = new BufferedReader(inputStreamReader);
		final StringBuffer outputBuffer = new StringBuffer();

		String line = null;

		while ((line = bufferedInputStreamReader.readLine()) != null) {
			outputBuffer.append(line + NEWLINE);
		}

		bufferedInputStreamReader.close();

		final String result = process(outputBuffer.toString(), libDirectory, format, dialect);
		return result;
	}

	@Override
	public String process(final String cobolSourceCode, final File libDirectory, final CobolSourceFormatEnum format) {
		return process(cobolSourceCode, libDirectory, format, null);
	}

	@Override
	public String process(final String cobolSourceCode, final File libDirectory, final CobolSourceFormatEnum format,
			final CobolDialect dialect) {
		final CobolCleanLinesSubPreprocessor cleanLinesPreprocessor = new CobolCleanLinesSubPreprocessorImpl();
		final CobolMarkCommentEntriesSubPreprocessor markCommentEntriesPreprocessor = new CobolMarkCommentEntriesSubPreprocessorImpl();
		final CobolNormalizeLinesSubPreprocessor normalizeLinesPreprocessor = new CobolNormalizeLinesSubPreprocessorImpl();
		final CobolParserPreprocessor parserPreprocessor = new CobolParserPreprocessorImpl(libDirectory);

		final String cleanedCode = cleanLinesPreprocessor.processLines(cobolSourceCode, dialect, format);
		final String markedCode = markCommentEntriesPreprocessor.processLines(cleanedCode, dialect, format);
		final String normalizedCode = normalizeLinesPreprocessor.processLines(markedCode, dialect, format);
		final String result = parserPreprocessor.processLines(normalizedCode, dialect, format);

		LOG.debug("Processed input:\n\n{}\n\n", result);

		return result;
	}

}
