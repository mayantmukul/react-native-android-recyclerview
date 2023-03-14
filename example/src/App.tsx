import React, {useState} from 'react';
import {SafeAreaView, StyleSheet, Text, View} from 'react-native';
import {
  RNDListView
} from './CustomNativeRecyclerView';

import JSScrollView from './JSScrollView';

const USE_JS_LIST = false;

const DATA = Array(40)
  .fill(null)
  .map((_, idx) => ({
    key: idx.toString(),
    text: `Card #${idx}`,
  }));

function clamp(x: number, min: number, max: number) {
  return x > max ? max : x < min ? min : x;
}

const INITIAL_SLICE = 10
const PER_BATCH = 5

function List() {
  const [slice, setSlice] = useState(INITIAL_SLICE)

  if (USE_JS_LIST) {
    return <JSScrollView />;
  }

  return (
    <RNDListView
      size={DATA.length}
      style={[styles.flex, styles.list]}
      onNextBatch={() => {
        if (slice !== DATA.length) {
          setSlice((s) => clamp(s + PER_BATCH, 0, DATA.length))
        }
      }}>
      {DATA.slice(0, slice).map((item) => (
        <View
          key={item.key}
          style={[styles.center, styles.card, styles.border]}>
          <Text style={styles.text}>{item.text}</Text>
        </View>
      ))}
    </RNDListView>
  );
}

const App = () => {
  return (
    <SafeAreaView style={styles.flex}>
      <View style={[styles.flex, styles.center]}>
        <Text>Custom List!</Text>
        <List />
      </View>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  flex: {
    flex: 1,
  },
  list: {
    width: 400,
    // height: 1000,
    backgroundColor: 'tomato'
  },
  center: {
    alignItems: 'center',
    justifyContent: 'center',
  },
  card: {
    height: 100,
    backgroundColor: 'aqua',
  },
  border: {
    borderColor: 'red',
    borderWidth: 2,
  },
  text: {
    color: 'black',
  },
});

export default App;
