/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;

public interface BeforeAfterPhrase extends CobolDivisionElement {

	enum BeforeAfterType {
		AFTER, BEFORE
	}

	BeforeAfterType getBeforeAfterType();

	ValueStmt getDataItemValueStmt();

	void setBeforeAfterType(BeforeAfterType beforeAfterType);

	void setDataItemValueStmt(ValueStmt dataItemValueStmt);
}
