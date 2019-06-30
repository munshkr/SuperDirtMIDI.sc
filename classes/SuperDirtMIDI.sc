SuperDirtMIDI {
	var dirt;
	var midiFuncCc;
	var <lastValues;
	var <ccMapping;

	*new { |dirt|
		^super.newCopyArgs(dirt).init;
	}

	init {
		ccMapping = ();
		lastValues = ();
	}

	start {
		if (midiFuncCc.notNil) { midiFuncCc.free };

		midiFuncCc = MIDIFunc.cc({ |val, num, chan, src|
			var scaledVal, map;

			scaledVal = val / 127;
			map = ccMapping[num];
			if (map.notNil) {
				dirt.orbits.do { |orbit|
					var p = map[\param];
					var v = map[\value].value(scaledVal);
					orbit.server.sendMsg("/n_set", orbit.group, p, v);
					orbit.defaultParentEvent[p] = v;
					lastValues[p] = v;
				}
			}
		})
	}

	map { |cc, param, value|
		ccMapping[cc] = (param: param, value: value)
	}

	free {
		midiFuncCc.free
	}
}
