superdirt-midiTest1 : UnitTest {
	test_check_classname {
		var result = superdirt-midi.new;
		this.assert(result.class == superdirt-midi);
	}
}


superdirt-midiTester {
	*new {
		^super.new.init();
	}

	init {
		superdirt-midiTest1.run;
	}
}
