package balls;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Набір ПОВІЛЬНИХ тестів (симуляція)")
@SelectPackages("balls")
@IncludeTags("slow")
public class SlowTestsSuite {
}