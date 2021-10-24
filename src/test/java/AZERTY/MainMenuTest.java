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
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.timing.Pause;
import static org.assertj.swing.core.MouseButton.LEFT_BUTTON;

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
    public void testBotonJugarMainMenu() {
        this.jugarButtonFixture.requireVisible().requireEnabled().click(LEFT_BUTTON);
        System.out.println("boton jugar");
    }

    @Test
    public void testBotonSalirMainMenu() {
        this.salirButtonFixture.requireVisible().requireEnabled().click(LEFT_BUTTON);
        System.out.println("boton salir");
    }

    @Override
    protected void onTearDown() {
        this.jugarButtonFixture = null;
        this.salirButtonFixture = null;
    }
}