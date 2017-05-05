package com.kun.randomusers.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Julio Kun
 * @version 0.1
 *
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
