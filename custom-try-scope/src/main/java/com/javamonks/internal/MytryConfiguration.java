package com.javamonks.internal;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Operations;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(MytryOperations.class)
@Extension(name= "MyTryScope")
public class MytryConfiguration {
}
