SuperDirtMIDI {
	var midiFuncCc;
	var <lastValues;
	var <ccMapping;
	var <traceEnabled;

	*new {
		^super.new.init;
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
				var p = map[\param];
				var v = map[\value].value(scaledVal);

				~dirt.orbits.do { |orbit|
					orbit.server.sendMsg("/n_set", orbit.group, p, v);
					orbit.defaultParentEvent[p] = v;
					lastValues[p] = v;
				};

				if (traceEnabled) { [p, lastValues[p]].postln };
			}
		})
	}

	map { |cc, param, value|
		ccMapping[cc] = (param: param, value: value)
	}

	mapScaled { |cc, param, fromValue, toValue|
		this.map(cc, param, { |v| (v * (toValue - fromValue)) + fromValue });
	}

	free {
		midiFuncCc.free
	}

	trace { |value=true|
		traceEnabled = value;
	}
}
