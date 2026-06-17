package balls;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Набір ШВИДКИХ тестів (фізика частинок)")
@SelectPackages("balls")
@IncludeTags("fast")
public class FastTestsSuite {
}