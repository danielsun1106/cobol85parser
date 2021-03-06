/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.use.impl;

import io.proleap.cobol.Cobol85Parser.UseDebugOnContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.use.DebugOn;

public class DebugOnImpl extends CobolDivisionElementImpl implements DebugOn {

	protected UseDebugOnContext ctx;

	protected DebugOnType debugOnType;

	protected Call onCall;

	public DebugOnImpl(final ProgramUnit programUnit, final UseDebugOnContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DebugOnType getDebugOnType() {
		return debugOnType;
	}

	@Override
	public Call getOnCall() {
		return onCall;
	}

	@Override
	public void setOnCall(final Call onCall) {
		this.onCall = onCall;
	}

	@Override
	public void setType(final DebugOnType debugOnType) {
		this.debugOnType = debugOnType;
	}

}
