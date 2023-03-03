import React, {useRef, useState} from 'react';
import {SafeAreaView, StyleSheet, Text, View} from 'react-native';

import {RecyclerView} from './RecyclerViewList';
import DataSource from './DataSource';

const DATA = Array(6)
  .fill(null)
  .map((_, idx) => ({
    key: idx.toString(),
    text: `Card #${idx}`,
  }));

const App = () => {
  const [dataSource] = useState(new DataSource(DATA, x => x.key));
  const ref = useRef<RecyclerView | null>(null);

  return (
    <SafeAreaView style={styles.flex}>
      <View style={[styles.flex, styles.center, styles.border]}>
        <Text>Hello!</Text>
        <RecyclerView
          ref={ref}
          style={[styles.list, styles.border]}
          dataSource={dataSource}
          renderItem={({item}, index) => {
            return (
              <View style={[styles.border, styles.card, styles.center]}>
                <Text style={styles.text}>{item.text}</Text>
              </View>
            )
          }}
        />
      </View>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  flex: {
    flex: 1,
  },
  center: {
    alignItems: 'center',
    justifyContent: 'center',
  },
  card: {
    width: 300,
    height: 200,
    marginVertical: 8,
  },
  border: {
    borderColor: 'aqua',
    borderWidth: 4,
  },
  list: {
    flex: 1,
    width: 500,
    // alignItems: 'stretch',
    // justifyContent: 'flex-start',
  },
  text: {
    color: 'black'
  }
});

export default App;
