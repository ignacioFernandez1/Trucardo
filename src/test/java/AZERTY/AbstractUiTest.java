package AZERTY;

//import com.sun.tools.javac.Main;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.edt.GuiTask;
import org.assertj.swing.testing.AssertJSwingTestCaseTemplate;
import org.assertj.swing.core.Settings;
import org.assertj.swing.core.AbstractComponentMatcher;
import org.assertj.swing.fixture.*;
import org.assertj.swing.fixture.AbstractButtonFixture;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.matcher.NamedComponentMatcherTemplate;
import org.assertj.swing.core.Settings;
import java.awt.GraphicsEnvironment;
import java.awt.Dimension;

import static org.assertj.swing.finder.WindowFinder.findFrame;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Assume;
import org.junit.Test;
/**
 * Base class for all my UI tests taking care of the basic setup.
 */
public class AbstractUiTest extends AssertJSwingTestCaseTemplate {

    /**
     * The main entry point for any tests: the wrapped MainWindow.
     */
    protected FrameFixture frame;

    /**
     * Installs a {@link FailOnThreadViolationRepaintManager} to catch violations of Swing threading rules.
     */
    @BeforeClass
    public static final void setUpOnce() {
        // avoid UI test execution in a headless environment (e.g. when building in CI environment like Jenkins or TravisCI)
        Assume.assumeFalse("Automated UI Test cannot be executed in headless environment", GraphicsEnvironment.isHeadless());
        FailOnThreadViolationRepaintManager.install();
    }

    /**
     * Sets up this test's fixture, starting from creation of a new <code>{@link Robot}</code>.
     *
     * @see #setUpRobot()
     * @see #onSetUp()
     */
    @Before
    public final void setUp() {
        // call provided AssertJSwingTestCaseTemplate.setUpRobot()
        this.setUpRobot();
        // initialize the graphical user interface
        MainMenu  mainMenu = GuiActionRunner.execute(new GuiQuery<MainMenu>() {

            @Override
            protected MainMenu executeInEDT() throws Exception {
                return App.showWindow();
            }
        });
        this.frame = new FrameFixture(this.robot(), mainMenu);
        this.frame.show();
        onSetUp();
    }

    /**
     * Subclasses that need to set up their own test fixtures in this method. Called as <strong>last action</strong> during {@link #setUp()}.
     */
    protected void onSetUp() {
        // default: everything is already set up
    }

    /*****************************************************************************************
     * Here you could insert further helper methods, e.g. frequently used component matchers *
     *****************************************************************************************/

    /**
     * Cleans up any resources used in this test. After calling <code>{@link #onTearDown()}</code>, this method cleans up resources used by this
     * test's <code>{@link Robot}</code>.
     *
     * @see #cleanUp()
     * @see #onTearDown()
     */
    @After
    public final void tearDown() {
        try {
            onTearDown();
            this.frame = null;
        } finally {
            cleanUp();
        }
    }

    /**
     * Subclasses that need to clean up resources can do so in this method. Called as <strong>first action</strong> during {@link #tearDown()}.
     */
    protected void onTearDown() {
        // default: nothing more to tear down
    }
}