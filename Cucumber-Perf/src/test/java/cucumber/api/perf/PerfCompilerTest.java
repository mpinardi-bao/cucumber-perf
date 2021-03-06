package cucumber.api.perf;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import cucumber.api.perf.FeatureBuilderTest.options1;
import cucumber.api.perf.salad.ast.Slice;
import cucumber.runtime.model.CucumberFeature;
import gherkin.ast.TableCell;
import gherkin.ast.TableRow;
import gherkin.events.PickleEvent;
import gherkin.pickles.Pickle;

public class PerfCompilerTest {

	@Test
	public void testCompileFeature() {
		PerfCompiler c = new PerfCompiler();
		List<TableCell> tcl = Arrays.asList(new TableCell[] {new TableCell(null, "value out")});
		List<TableCell> tcl2 = Arrays.asList(new TableCell[] {new TableCell(null, "test")});
		List<CucumberFeature> features = FeatureBuilder.getFeatures(options1.class);
		List<PickleEvent> p =c.compileFeature(features.get(0), new Slice(Arrays.asList(new TableRow[] {new TableRow(null, tcl),new TableRow(null, tcl2)})));
		assertEquals("System out \"test\"",p.get(0).pickle.getSteps().get(1).getText());
	}

	@Test
	public void testCompile() {
		PerfCompiler c = new PerfCompiler();
		List<TableCell> tcl = Arrays.asList(new TableCell[] {new TableCell(null, "value out")});
		List<TableCell> tcl2 = Arrays.asList(new TableCell[] {new TableCell(null, "test")});
		List<CucumberFeature> features = FeatureBuilder.getFeatures(options1.class);
		List<Pickle> p =c.compile(features.get(0).getGherkinFeature(), new Slice(Arrays.asList(new TableRow[] {new TableRow(null, tcl),new TableRow(null, tcl2)})));
		assertEquals("System out \"test\"",p.get(0).getSteps().get(1).getText());
	}

}
