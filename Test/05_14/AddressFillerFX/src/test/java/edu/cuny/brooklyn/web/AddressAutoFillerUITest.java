package edu.cuny.brooklyn.web;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.TextInputControlMatchers;

import javafx.stage.Stage;

public class AddressAutoFillerUITest extends ApplicationTest {
	@Override
	public void start(Stage stage) throws Exception {
		new AddressAutoFillerFXApp().start(stage);
	}

	@Override
	public void stop() {
	}

	@Test
	public void should_address_fill_for_11210() {
		clickOn("#zipCodeField");
		write("11210");
		sleep(1, TimeUnit.SECONDS);
		FxAssert.verifyThat("#cityField", TextInputControlMatchers.hasText("BROOKLYN"));
		FxAssert.verifyThat("#stateField", TextInputControlMatchers.hasText("NY"));
		FxAssert.verifyThat("#countryField", TextInputControlMatchers.hasText("US"));
		sleep(5, TimeUnit.SECONDS);
	}

	@Test
	public void should_address_fill_for_20001() {
		clickOn("#zipCodeField");
		write("20001");
		sleep(1, TimeUnit.SECONDS);
		FxAssert.verifyThat("#cityField", TextInputControlMatchers.hasText("WASHINGTON"));
		FxAssert.verifyThat("#stateField", TextInputControlMatchers.hasText("DC"));
		FxAssert.verifyThat("#countryField", TextInputControlMatchers.hasText("US"));
		sleep(5, TimeUnit.SECONDS);
	}
}