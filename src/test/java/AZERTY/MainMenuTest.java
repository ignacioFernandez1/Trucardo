package AZERTY;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.edt.GuiTask;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.assertj.swing.core.Settings;
import org.assertj.swing.fixture.*;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.core.AbstractComponentMatcher;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.matcher.NamedComponentMatcherTemplate;

import org.junit.Test;

public class MainMenuTest extends AbstractUiTest {

    private JButtonFixture jugarButtonFixture;
    private JButtonFixture salirButtonFixture;

    @Override
    protected void onSetUp() {
        this.jugarButtonFixture = this.frame.button("jugar");
        this.salirButtonFixture = this.frame.button("salir");
    }

    @Test
    public void testWithDifferingComponentMatchers() {
        // use JTextComponentMatcher.any() as there is only one text input
        this.jugarButtonFixture.requireVisible().requireEnabled().click();
        this.salirButtonFixture.requireVisible().requireEnabled().click();
    }

    @Override
    protected void onTearDown() {
        this.jugarButtonFixture = null;
        this.salirButtonFixture = null;
    }
}