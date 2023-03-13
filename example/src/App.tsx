import React from 'react';
import {SafeAreaView, StyleSheet, Text, View} from 'react-native';
import {
  RNDListView
} from './CustomNativeRecyclerView';

import JSScrollView from './JSScrollView';

const USE_JS_LIST = false;

const DATA = Array(1)
  .fill(null)
  .map((_, idx) => ({
    key: idx.toString(),
    text: `Card #${idx}`,
  }));

function List() {
  if (USE_JS_LIST) {
    return <JSScrollView />;
  }

  return (
    <RNDListView
      style={[styles.flex, styles.border, styles.list]}>
      {/* DATA.map((item) => (
        <View
          key={item.key}
          style={[styles.center, styles.card]}>
          <Text style={styles.text}>{item.text}</Text>
        </View>
      )) */}
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
    height: 100,
    backgroundColor: 'tomato'
  },
  center: {
    alignItems: 'center',
    justifyContent: 'center',
  },
  card: {
    height: 100,
    backgroundColor: 'tomato',
  },
  border: {
    borderColor: 'aqua',
    borderWidth: 4,
  },
  text: {
    color: 'black',
  },
});

export default App;
