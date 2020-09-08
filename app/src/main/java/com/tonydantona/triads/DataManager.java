package com.tonydantona.triads;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private List<StringSet> mStringSets = new ArrayList<>();
    private List<Key> mKeys = new ArrayList<>();
    private List<Chord> mChords = new ArrayList<>();

    private static DataManager instance = null;

    public List<StringSet> getStringSets() {
        return mStringSets;
    }
    public List<Key> getKeys() {
        return mKeys;
    }

    public List<Chord> getChords() {
        return mChords;
    }

    public Key getKey(int r) {
            if(r <= mKeys.size()) {
                return mKeys.get(r);
            }

            return null;
    }

    public StringSet getStringSet(int r) {
        if( r <= mStringSets.size()) {
            return mStringSets.get(r);
        }

        return null;
    }

    public Chord getChord(int r) {
        if( r <= mChords.size()) {
            return mChords.get(r);
        }

        return null;
    }

    public static DataManager getInstance() {
        if(instance == null) {
            instance = new DataManager();
            instance.initializeStringSet();
            instance.initializeChords();
            instance.initializeKeys();
        }

        return instance;
    }

    private void initializeChords() {
        String[] chordNames = {"maj", "min", "aug", "dim", "6th", "9th"};

        for (String chordName : chordNames) {
            Chord chord = new Chord();
            chord.setName(chordName);
            mChords.add(chord);
        }
    }

    private void initializeStringSet() {
        String[] setNames = {"string set 1", "string set 2", "string set 3", "string set 4"};

        for (String setName : setNames) {
            StringSet ss = new StringSet();
            ss.setName(setName);
            mStringSets.add(ss);
        }
    }

    private void initializeKeys() {
        String[] keyNames = {"A", "Ab", "A#",
                "B", "Bb",
                "C", "Cb", "C#",
                "D", "Db", "D#",
                "E", "Eb",
                "F", "Fb", "F#",
                "G", "Gb", "G#"
        };

        for (String keyName : keyNames) {
            Key key = new Key();
            key.setName(keyName);
            mKeys.add(key);
        }
    }
}
