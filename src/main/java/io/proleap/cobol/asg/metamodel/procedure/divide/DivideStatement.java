/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.divide;

import io.proleap.cobol.Cobol85Parser.DivideByGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideIntoGivingStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideIntoStatementContext;
import io.proleap.cobol.Cobol85Parser.DivideRemainderContext;
import io.proleap.cobol.asg.metamodel.procedure.NotOnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.OnSizeErrorPhrase;
import io.proleap.cobol.asg.metamodel.procedure.Statement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

/**
 * Divides one number by another and store the result.
 */
public interface DivideStatement extends Statement {

	enum DivideType {
		BY_GIVING, INTO, INTO_GIVING
	}

	DivideByGivingStatement addDivideByGivingStatement(DivideByGivingStatementContext ctx);

	DivideIntoGivingStatement addDivideIntoGivingStatement(DivideIntoGivingStatementContext ctx);

	DivideIntoStatement addDivideIntoStatement(DivideIntoStatementContext ctx);

	Remainder addRemainder(DivideRemainderContext ctx);

	DivideByGivingStatement getDivideByGivingStatement();

	DivideIntoGivingStatement getDivideIntoGivingStatement();

	DivideIntoStatement getDivideIntoStatement();

	DivideType getDivideType();

	ValueStmt getDivisorValueStmt();

	NotOnSizeErrorPhrase getNotOnSizeErrorPhrase();

	OnSizeErrorPhrase getOnSizeErrorPhrase();

	Remainder getRemainder();

	void setDivideType(DivideType divideType);

	void setDivisorValueStmt(ValueStmt divisorValueStmt);

	void setNotOnSizeErrorPhrase(NotOnSizeErrorPhrase notOnSizeErrorPhrase);

	void setOnSizeErrorPhrase(OnSizeErrorPhrase onSizeErrorPhrase);

}
