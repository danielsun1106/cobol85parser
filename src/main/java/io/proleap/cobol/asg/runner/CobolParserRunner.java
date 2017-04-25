/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.runner;

import java.io.File;
import java.io.IOException;

import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolDialect;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public interface CobolParserRunner {

	Program analyzeDirectory(File inputDirectory, CobolSourceFormatEnum format) throws IOException;

	Program analyzeDirectory(File inputDirectory, CobolSourceFormatEnum format, CobolDialect dialect) throws IOException;

	Program analyzeFile(File inputFile, CobolSourceFormatEnum format) throws IOException;

	Program analyzeFile(File inputFile, CobolSourceFormatEnum format, CobolDialect dialect) throws IOException;

}
