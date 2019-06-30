# SuperDirtMIDI.sc

Adds more MIDI in capabilities to SuperDirt

## Features

* Easily map MIDI CC to Synthdef parameters
* Setup external controller for sending CC messages from Tidal (to do...)

## Usage

First make sure to have SuperDirt running.

```supercollider
// Setup midi first
MIDIIn.connectAll;

// Setup SuperDirtMIDI
~smidi = SuperDirtMIDI.new(~dirt);
~smidi.start;

// Map some CCs to some Synthdef parameter, and pass a function to transform
// the CC value. `v` is a value from 0-1 (scaled from 0-127)
~smidi.map(16, \modP, { |v| v * 10 });
~smidi.map(17, \carP, { |v| v * 10 });
~smidi.map(18, \detune, { |v| v * 20 });
~smidi.map(19, \index, { |v| v * 100 });

// You can map and scale values with .mapScaled
// e.g. this maps CC 16 to \modP and scales values from 3 to 30
~smidi.mapScaled(16, \modP, 3, 30);

// Trace parameter changes and print them on the Post console
~smidi.trace;
~smidi.trace(false); // stop trace

// Finally, free CC handlers when not using them anymore
// you will have to run .start again to remap them.
~smidi.free;
```

### Installation

Open up SuperCollider and evaluate the following line of code:
`Quarks.install("https://github.com/munshkr/SuperDirtMIDI.sc")`

When the Quark is installed, recompile your class library. Go to `Language` menu,
`Recompile class library`, or hit <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>L</kbd>.

## Contributing

Bug reports and pull requests are welcome on GitHub at
https://github.com/munshkr/SuperDirtMIDI.sc.  This project is intended to be a
safe, welcoming space for collaboration, and contributors are expected to
adhere to the [Contributor Covenant](http://contributor-covenant.org) code of
conduct.

## LICENSE

See [LICENSE](LICENSE.md)
