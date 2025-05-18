package org.toptal.test.copilot.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Keep annotation at runtime
@Target(ElementType.METHOD) // Apply to methods only
public @interface TrackExecutionTime {
}