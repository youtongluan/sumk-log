/**
 * Copyright (C) 2016 - 2030 youtongluan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yx.log.impl;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.yx.common.ThreadContext;
import org.yx.conf.AppInfo;
import org.yx.log.LogKits;
import org.yx.log.LogLevel;
import org.yx.util.SumkDate;

public class LogObject {
	public static final char LN = '\n';
	public static final Charset CHARSET = StandardCharsets.UTF_8;

	public final SumkDate logDate;

	public final LogLevel methodLevel;

	public final String body;

	public final String sn;

	public final boolean test;

	public final String threadName;

	public final Throwable exception;

	public final SumkLoggerImpl logger;

	public final CodeLine codeLine;

	public LogObject(LogLevel methodLevel, String message, Throwable e, SumkLoggerImpl logger) {
		this.methodLevel = methodLevel;
		this.body = LogKits.clipIfNecessary(message);
		this.exception = e;
		this.logDate = SumkDate.now();
		this.logger = logger;
		sn = ThreadContext.get().userIdOrContextSN();
		test = ThreadContext.get().isTest();
		threadName = Thread.currentThread().getName();
		if (AppInfo.getBoolean("sumk.log.codeline", false)) {
			this.codeLine = LogObjectUtil.extractCodeLine("org.yx.log.");
		} else {
			this.codeLine = null;
		}
	}

}