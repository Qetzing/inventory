package qetz.inventory;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import qetz.components.ClassScanFactory;
import qetz.components.ClassScanModule;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReactiveInventoryClassScanModule implements ClassScanModule {
  public static ReactiveInventoryClassScanModule create() {
    return new ReactiveInventoryClassScanModule();
  }

  @Override
  public void configure(ClassScanFactory classScanFactory) {
    classScanFactory.withRecursivePackageByClass(getClass());
  }
}