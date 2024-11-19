package com.DsAlgo.utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
public class RetryListener implements IAnnotationTransformer {
@Override
  public void transform (final ITestAnnotation annotation, final Class testClass, final Constructor testConstructor,
  final Method testMethod) {
  annotation.setRetryAnalyzer (RetryAnalyzer.class);
  }
}
