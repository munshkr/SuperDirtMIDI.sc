SuperDirtMIDITest : UnitTest {
	test_check_classname {
		var result = SuperDirtMIDI.new;
		this.assert(result.class == SuperDirtMIDI);
	}
}

SuperDirtMIDITester {
	*new {
		^super.new.init();
	}

	init {
		SuperDirtMIDITest.run;
	}
}
